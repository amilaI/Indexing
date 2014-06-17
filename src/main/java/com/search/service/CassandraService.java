package com.search.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;

public class CassandraService {

	public static void main(String[] args) {
		
		String serverIP = "127.0.0.1";
		String keyspace = "scaffold_cassandra";

		Cluster cluster = Cluster.builder()
		  .addContactPoints(serverIP)
		  .build();
		
		SimpleDateFormat sdformat = new SimpleDateFormat("yyyy-MM-dd"); 
		Date today = null;
		try {
			today = sdformat.parse(sdformat.format(new Date()));
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}		

		Session session = cluster.connect(keyspace);
		String cqlStatement = "SELECT * FROM informations";
		for (Row row : session.execute(cqlStatement)) {
		  //System.out.println(row.getDate("createdate"));
		  Date  informationCreateDate  =row.getDate("createdate");
		  
		  try {
			  SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");      
			  Date dateWithoutTime = sdf.parse(sdf.format(informationCreateDate));
			 // System.out.println(dateWithoutTime);
			  
			  if (dateWithoutTime.equals("2013-")){
				  System.out.println("Working");
			  }
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		  
		}
	}
}
