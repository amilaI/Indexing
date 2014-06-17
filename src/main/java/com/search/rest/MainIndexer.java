package com.search.rest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.search.content.domain.IndexItem;

public class MainIndexer {
	 private static final String INDEX_DIR = "/home/amila/Lucene/REST/indexing";
	public static void main(String[] args) {
		
		Date today = new Date();
		System.out.println(today);
		
		//Call db get data which created on today
		
		//List<IndexItem> indexItemList = new ArrayList<IndexItem>();
		
		IndexItem indexItem = new IndexItem();
		
		indexItem.setId("6d8e36b0-d530-11e3-9c1a-0800200ac9a80");
		indexItem.setTitle("Java in Action");
		indexItem.setPath("/home/amila/Lucene/REST/content/Apache cook book.pdf");
		indexItem.setDescription("This is Java in Action Book .Designed to be a student's first exposure to object-oriented programming, it is a fun and easy way to begin learning the Java programming language.This is a sample description content which goes in the search");
		
/*		IndexItem indexItem = new IndexItem();
		
		indexItem.setId("asd36b0-d530-11e3-9c1a-q");
		indexItem.setTitle("Apache POI");
		indexItem.setPath("/home/amila/Lucene/REST/content/Apache Lucene.docx");*/
		
		//indexItemList.add(indexItem);
		 try {
			 Indexer indexer = new Indexer(INDEX_DIR);
			 indexer.index(indexItem);
			 indexer.close();
			 System.out.println("Indexing completed");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}

//the items to be indexed
/* IndexItem[] indexItems = {
         new IndexItem("6d8e36b0-d530-11e3-9c1a-0800200ac9a80", "Java in Action", "This is Java in Action Book .Designed to be a student's first exposure to object-oriented programming, it is a fun and easy way to begin learning the Java programming language."),
         new IndexItem("asd36b0-d530-11e3-9c1a-q", "Spring in Action", "Spring helps development teams everywhere build simple, portable,. fast and Keep it portable.The Spring Framework provides a comprehensive programming and configuration model for modern Java-based enterprise applications"),
         new IndexItem("adsadse36b0-d530-11e3-9c1a-080w0200c555", "Hibernate in Action", "This is Hibernate in Action Book . Hibernate an open source Java persistence framework project. Perform powerful object relational mapping and query databases using HQL and SQL"),
         new IndexItem("hgf36b0-d530-11e3-9c1a-0800200c123", "SOA in Action", "This is Java SOA in Action Book"),
         new IndexItem("ghj36b0-d530-11e3-9c1a-0800203e421311", "Apache Axis2 in Action", "This is Apache Axis2 in Action Book"),
         new IndexItem("hgf6b0-d530-11e3-9c1a-0800200c9a81", "Apache CXF in Action", "This is CXF in Action Book . Apache CXF is an open source services framework"),
         new IndexItem("ss36b0-d530-11e3-9c1a-0800200c42jk332", "jQuery in Action", "This is jQuery in Action Book . jQuery is a cross-platform JavaScript library designed to simplify the client-side scripting of HTML."),
         new IndexItem("6d8e36b0-d530-11e3-9c1a-sdasadds", "Oracle Technology Network for Java Developers", "Oracle Technology Network is the ultimate, complete, and authoritative source of technical information and learning about Java."),
         new IndexItem("ss36b0-d530-11e3-9c1a-0800200cddfds4332", "Pearson - Always Learning", "Pearson PLC is a British multinational publishing and education company headquartered in London. It is the largest education company and the largest book publisher in the world"),
         new IndexItem("ss36b0-d530-11e3-9c1a-0800200cdsd2", "MyLab & Mastering Pearson", "Break through to improving results with Pearson's MyLab & Mastering. We're working with educators and institutions to improve results for students everywhere."),
         new IndexItem("ss36b0-d530-11e3-9c1a-0800200c4234332", "Pearson LearningStudio - Pearson Learning Solutions", "Scalable infrastructure, Powerful analytics, Engaging interactions, Reliable service and support. Expand what's possible with Pearson LearningStudio."),
         new IndexItem("ss36b0-d530-11e3-9c1a-0800200rbbx32", "Heroku | Cloud Application Platform", "Heroku is a cloud platform as a service (PaaS) supporting several programming languages. Heroku was acquired by Salesforce.com in 2010.Heroku, one of the first cloud platforms, has been in development since June 2007, when it supported only the Ruby programming language, but has since added support for Java, Node.js, Scala, Clojure and Python and (undocumented) PHP and Perl."),
         new IndexItem("ss36b0-d530-11e3-9c1a-08002006yhfr4332", "Open Class", "Open Class helps educators bring social learning to their students. Explore ... Compare. See how OpenClass matches up against other learning platforms.OpenClass Learn more about OpenClass. Login. Sign In. Email Address"),
         new IndexItem("ss36b0-d530-11e3-9c1a-080020svgfgds34332", "Pearson VUE", "Pearson VUE provides a full suite of services from test development to data management, and delivers exams through the world’s most comprehensive and secure network of test centers in 175 countries. Pearson VUE is a business of Pearson (NYSE: PSO; LSE: PSON), the world's leading learning company.")
                       
  };*/

 // creating the indexer and indexing the items
/*      try {
		Indexer indexer = new Indexer(INDEX_DIR);
		for (IndexItem indexItem : indexItems) {
		    indexer.index(indexItem);
		}
		// close the index to enable them index
		indexer.close();
		System.out.println("Indexing completed");
		
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}*/