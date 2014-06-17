package com.search.rest;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;

import com.search.content.domain.IndexItem;
/**
 * Engineering Dashboard (EDB) - Always Learning Module
 * Content Search Engine 
 * 
 * This class gets incoming request query and search index DIR and returns a result set
 *  
 * @author Amila Iddamalgoda - amila.iddamalgoda@pearson.com
 * @version 1.0
 * @since 10/05/2014
 */

public class Searcher {

    private IndexSearcher searcher;
    private QueryParser titleQueryParser;
    private QueryParser contentQueryParser;

    public Searcher(String indexDir) throws IOException {
        // open the index directory to search
        searcher = new IndexSearcher(IndexReader.open(FSDirectory.open(new File(indexDir))));
        StandardAnalyzer analyzer = new StandardAnalyzer(Version.LUCENE_36);

        // defining the query parser to search items by title field.
        titleQueryParser = new QueryParser(Version.LUCENE_36, IndexItem.TITLE, analyzer);

        // defining the query parser to search items by content field.
        contentQueryParser = new QueryParser(Version.LUCENE_36, IndexItem.CONTENT, analyzer);
    }

    /**
      * This method is used to find the indexed items by the title.
      * @param queryString - the query string to search for
      */
    public List<IndexItem> findByTitle(String queryString, int numOfResults) throws ParseException, IOException {
        // create query from the incoming query string.
        Query query = titleQueryParser.parse(queryString);
        // execute the query and get the results
        ScoreDoc[] queryResults = searcher.search(query, numOfResults).scoreDocs;

        List<IndexItem> results = new ArrayList<IndexItem>();
        // process the results
        for (ScoreDoc scoreDoc : queryResults) {
            Document doc = searcher.doc(scoreDoc.doc);
            results.add(new IndexItem(doc.get(IndexItem.ID), doc.get(IndexItem.TITLE), doc.get(IndexItem
                    .CONTENT)));
        }

         return results;
    }

    /**
      * This method is used to find the indexed items by the content.
      * @param queryString - the query string to search for
      */
    public List<IndexItem> findByContent(String queryString, int numOfResults) throws ParseException, IOException {
        // create query from the incoming query string.
        Query query = contentQueryParser.parse(queryString);
         // execute the query and get the results
        ScoreDoc[] queryResults = searcher.search(query, numOfResults).scoreDocs;

        /*// create query from the incoming query string.
        Query queryTitle = titleQueryParser.parse(queryString);
        // execute the query and get the results
        ScoreDoc[] queryResultsTitles = searcher.search(queryTitle, numOfResults).scoreDocs;
        
        ScoreDoc[] concatenatedDocs = ArrayUtils.addAll(queryResults, queryResultsTitles);
        */
        
        List<IndexItem> results = new ArrayList<IndexItem>();
        // process the results
        for (ScoreDoc scoreDoc : queryResults) {
            Document doc = searcher.doc(scoreDoc.doc);
            results.add(new IndexItem(doc.get(IndexItem.ID), doc.get(IndexItem.TITLE), doc.get(IndexItem
                    .CONTENT)));
        }

         return results;
    }

    public void close() throws IOException {
        searcher.close();
    }
}
