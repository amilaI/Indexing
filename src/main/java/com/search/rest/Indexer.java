package com.search.rest;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.Term;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
import org.apache.pdfbox.cos.COSDocument;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.util.PDFTextStripper;
import org.apache.poi.hslf.extractor.PowerPointExtractor;
import org.apache.poi.hssf.extractor.ExcelExtractor;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.extractor.WordExtractor;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

import com.search.content.domain.IndexItem;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Indexer {
	private IndexWriter writer;

	public Indexer(String indexDir) throws IOException {
		// create the index
		if (writer == null) {
			writer = new IndexWriter(FSDirectory.open(new File(indexDir)),
					new IndexWriterConfig(Version.LUCENE_36,
							new StandardAnalyzer(Version.LUCENE_36)));
		}
	}

	/**
	 * Parsing pdf content
	 */
	public String PdfFileParser(String pdffilePath)
			throws FileNotFoundException, IOException {
		String content;
		FileInputStream fi = new FileInputStream(new File(pdffilePath));
		PDFParser parser = new PDFParser(fi);
		parser.parse();
		COSDocument cd = parser.getDocument();
		PDFTextStripper stripper = new PDFTextStripper();
		content = stripper.getText(new PDDocument(cd));
		cd.close();
		return content;
	}

	/**
	 * Parsing word Documents
	 */
	public String ContentParser(IndexItem file) {
		POIFSFileSystem fs = null;
		String filePath = file.getPath();
		try {
			if (filePath != null) {

				if (filePath.endsWith(".xls")) { // if the file is excel file
					
					ExcelExtractor ex = new ExcelExtractor(fs);
					return ex.getText(); // returns text of the excel file

				} else if (filePath.endsWith(".ppt")) { // if the file is power
														// point file

					PowerPointExtractor extractor = new PowerPointExtractor(fs);
					return extractor.getText(); // returns text of the power
												// point file

				} else if (filePath.endsWith(".docx")
						|| filePath.endsWith(".doc")) {
					// else for .doc file
					
					fs = new POIFSFileSystem(new FileInputStream(filePath));
					HWPFDocument doc = new HWPFDocument(fs);
					WordExtractor we = new WordExtractor(doc);
					return we.getText();// if the extension is .doc

				} else if (filePath.endsWith(".pdf")) {
					return PdfFileParser(filePath);

				} else {
					System.out
							.println("Document  "
									+ file.getTitle()
									+ "  file type did not match the system  file types");

				}
			} else {
				System.out.println("Document  " + file.getTitle()
						+ "  content path is null");
			}
		} catch (Exception e) {
			System.out.println("Document  " + file.getTitle()
					+ " can't be indexed. Cause :-" + e);
		}
		return "";
	}

	/**
	 * This method will add the items into index
	 */
	public void index(IndexItem indexItem) throws IOException {

		// deleting the item, if already exists
		writer.deleteDocuments(new Term(IndexItem.ID, indexItem.getId()
				.toString()));
		String fileContent = ContentParser(indexItem) + "Amila";
		System.out.println("file content -- " + fileContent);

		Document doc = new Document();

		doc.add(new Field(IndexItem.ID, indexItem.getId().toString(),
				Field.Store.YES, Field.Index.NOT_ANALYZED));
		doc.add(new Field(IndexItem.TITLE, indexItem.getTitle(),
				Field.Store.YES, Field.Index.ANALYZED));
		doc.add(new Field(IndexItem.CONTENT, fileContent, Field.Store.YES,
				Field.Index.ANALYZED));
		doc.add(new Field(IndexItem.DESCRIPTION, indexItem.getDescription(), Field.Store.YES,
				Field.Index.ANALYZED));

		// add the document to the index
		writer.addDocument(doc);
	}

	/**
	 * Closing the index
	 */
	public void close() throws IOException {
		writer.close();
	}
}
