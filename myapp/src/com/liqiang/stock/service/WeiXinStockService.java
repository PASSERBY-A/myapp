package com.liqiang.stock.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.client.fluent.Content;
import org.apache.http.client.fluent.Request;

import com.liqiang.stock.Constant;
import com.liqiang.stock.domain.Stock;

public class WeiXinStockService {

	public boolean check(String signature,String timestamp,String nonce)
	{
		StringBuffer sign = new StringBuffer();
		
		String[] arrays = new String[]{"liqiang",timestamp,nonce};
		
		Arrays.sort(arrays);
		
		for(String s:arrays)
		{
			sign.append(s);
		}
		
		
		return DigestUtils.sha1Hex(sign.toString()).equals(signature);
	}
	
	
	
	
	public synchronized List<Stock> queryStock(String inputString)
	{
		 List<Stock> result = new ArrayList<Stock>();
		
		
		String url = String.format(Constant.GET_STOCK_CODE_URL, inputString);
		
		String resultStr=null;
		try {
			Content con =  Request.Get(url) .execute().returnContent();
			
			 resultStr = con.asString();
			
			resultStr = resultStr.substring(resultStr.indexOf("\"")+1, resultStr.lastIndexOf("\""));
			
			if(!resultStr.equals("N"))
			{
				String[] stocks = resultStr.split("\\^");
				
			    for(String s:stocks)
			    {
			    	
			    	
			    	 	if(!StringUtils.equalsIgnoreCase("sz", s.split("~")[0]) && !StringUtils.equalsIgnoreCase("sh", s.split("~")[0]))
			    	 	{
			    	 		
			    	 		System.out.println("continue--------------");
			    	 		continue;
			    	 		
			    	 	}
			    	 	
			    		Stock stock = new Stock();
			    		
			    		stock.setCode(s.split("~")[0]+s.split("~")[1]);
			    		
			    		stock.setType(s.split("~")[0]);
			    		
			    		stock.setChineseName(s.split("~")[2]);
			    		
			    		stock.setPinYin(s.split("~")[3]);
			    		
			    		result.add(stock);
			    }
			    
			    
			}
			else{
				return result;
			}
			 
			// this.setName(strs[2]);
			 
			// this.setCode2(strs[0]+strs[1]);
		
			
			
			
			
		} 
		catch (Exception e) {
			 
			e.printStackTrace();
		}
		return result;
		
	}
	
	
	public static void main(String[] args) {
		
		System.out.println(new WeiXinStockService().queryStock("000938"));
		
		
		
		
		
	}
	
	
	
	
	
}
