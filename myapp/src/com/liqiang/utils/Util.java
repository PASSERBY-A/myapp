package com.liqiang.utils;

import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.apache.commons.lang3.StringUtils;

import com.liqiang.stock.domain.WeiXinReturnMessage;


public class Util {

	
    public static  String reconvertChinese(String str){  
        char[]c=str.toCharArray();  
        String resultStr= "";  
        for(int i=0;i<c.length;i++)  
          resultStr += String.valueOf(c[i]);  
        return resultStr;  
   }  
    
   public static String convertUnicode(String str) {  
       String result = "";  
       for(int i = 0; i < str.length(); i++) {  
           String temp = "";  
           int strInt = str.charAt(i);  
           if(strInt > 127) {  
               temp += "\\u" + Integer.toHexString(strInt);  
           } else {  
               temp = String.valueOf(str.charAt(i));  
           }  
             
           result += temp;  
       }  
       return result;  
   } 
   
   
   public static  <T> T xmlToObject(Class<T> clazz,String xml)  throws JAXBException
	 {
		 JAXBContext context = JAXBContext.newInstance(clazz);
		 Unmarshaller um = context.createUnmarshaller();
		 return  (T) um.unmarshal(new StringReader(xml));
	 }
	
	 
	  public  static String objectToXML(Class clazz, Object object) throws JAXBException
	  {
		        String xml = null;
		        JAXBContext context = JAXBContext.newInstance(clazz);
		        Marshaller m = context.createMarshaller();
		        m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.FALSE);
		        m.setProperty(Marshaller.JAXB_ENCODING, "utf-8");
		        Writer w = new StringWriter();
		        m.marshal(object, w);
		        xml = w.toString();
		        return xml;
	  }
   
   
    
   public static void main(String args[]){  
       
	   
	 System.out.println(reconvertChinese("\u4e2d\u8bc1\u6c11\u4f01"));
       
   }  
    	
    	
    	
}
