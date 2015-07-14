package com.liqiang.utils;

import java.util.UUID;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.client.fluent.Content;
import org.apache.http.client.fluent.Request;

public class HttpClient {

	private static Log log = LogFactory.getLog(HttpClient.class);
	
	public static void main(String[] args) throws Exception {
		System.out.println(UUID.randomUUID().toString());
		
		while(true)
		{
			Thread.sleep(1000);
			
			 Content con =  Request.Get("http://qt.gtimg.cn/4441_11&q=sz002331") .execute().returnContent();
			 
			 String connstr = con.asString();
			 
			 connstr = connstr.substring(connstr.indexOf("\"")+1, connstr.lastIndexOf("\""));
			 
			 log.info(connstr);
			
		}
		
		 
	}
	
	
}
