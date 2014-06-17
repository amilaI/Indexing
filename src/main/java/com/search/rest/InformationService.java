package com.search.rest;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.search.domain.EmployeeList;
import com.search.domain.InfomationList;

public class InformationService {

	public static void main(String[] args) {
		RestTemplate restTemplate = new RestTemplate();
		String jsonResult = null;
		
		try {
			URI url = new URI("http://localhost:8081/samples/readData");
			jsonResult  =  "{\"compemployes\":[{\"id\":1001,\"name\":\"jhon\"}, {\"id\":1002,\"name\":\"jhon\"}]}";
			//jsonResult = restTemplate.getForObject(url, String.class);
			System.out.println(jsonResult);			
		} catch (RestClientException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		
		//String jsonString = jsonResult.replace("{\"_embedded\":", "");
		//String mapperString = jsonString.replace("}]}}", "}]}");
		
		//System.out.println(mapperString);
		
		ObjectMapper mapper = new ObjectMapper();
		EmployeeList list = null;
		try {
			list = mapper.readValue(jsonResult, EmployeeList.class);
			System.out.println(list.getCompemployes().size());
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
	}
}
