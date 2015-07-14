package com.liqiang.stock.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBException;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;

import com.liqiang.stock.domain.Stock;
import com.liqiang.stock.domain.WeiXinNewsMessage;
import com.liqiang.stock.domain.WeiXinReturnMessage;
import com.liqiang.stock.domain.WeiXinTextMessage;
import com.liqiang.stock.server.StockSubscribeServer;
import com.liqiang.stock.service.WeiXinStockService;
import com.liqiang.utils.Util;

public class StockServerServlet extends HttpServlet {

	
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private WeiXinStockService service;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		StockSubscribeServer s = new StockSubscribeServer();
		
		service = new WeiXinStockService();
		
		s.init();
		
		config.getServletContext().setAttribute("server", s);
		
		super.init(config);
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		

	String signature = req.getParameter("signature");
	
	String timestamp = req.getParameter("timestamp");
	
	String nonce = req.getParameter("nonce");
	
	String echostr = req.getParameter("echostr");
	
	if(service.check(signature, timestamp, nonce))
	{
		resp.getOutputStream().print(echostr);		
	}
	
	
	
	
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		//http://image.sinajs.cn/newchart/min/n/sh000001.gif
		//http://image.sinajs.cn/newchart/daily/n/sz002331.gif
		
	
		
		//System.out.println("begintime: "+new SimpleDateFormat("hh:ss").format(new Date()));
		
		PrintWriter out = resp.getWriter();
		
		String str = IOUtils.toString(req.getInputStream(),"UTF-8");
		//
		//System.out.println("from weixin message : "+str);
		
		
		WeiXinReturnMessage r = new WeiXinReturnMessage();
		
		try {
			
			r = (WeiXinReturnMessage) Util.xmlToObject(r.getClass(), str);
			
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Pattern p = Pattern.compile("\\W+");
		
		String content = r.getContent();
		
		if(StringUtils.isBlank(content))
		{
			return;
		}
		
		//如果是中文
		if(p.matcher(content).matches())
		{
			
			//content = Util.convertUnicode(content);
			
			content = URLEncoder.encode(content, "utf-8");
		}else{
			
			content = content.toLowerCase();
			
		}
		
		List<Stock> stocks = service.queryStock(content);
		
		WeiXinTextMessage textMessage = new WeiXinTextMessage();
		WeiXinNewsMessage newsMessage = new WeiXinNewsMessage();
		
		
		textMessage.setCreateTime(String.valueOf(System.currentTimeMillis()));
		textMessage.setFromUserName(r.getToUserName());
		textMessage.setToUserName(r.getFromUserName());
		
		newsMessage.setCreateTime(String.valueOf(System.currentTimeMillis()));
		newsMessage.setFromUserName(r.getToUserName());
		newsMessage.setToUserName(r.getFromUserName());
		
		
		
		String xml = "";
		if(stocks.size()>0)
		{
			if(stocks.size()>1)
			{
				
				StringBuffer buffer = new StringBuffer();
				buffer.append("您要查询的是哪只股票呢？\r\n");
				
				for(Stock stock:stocks)
				{
					buffer.append( convert(stock.getChineseName())+"("+stock.getPinYin()+")"+"\r\n");
				}
				
				textMessage.setContent(buffer.toString());
				
				
				try {	
					
					xml = Util.objectToXML(WeiXinTextMessage.class, textMessage);
				} catch (JAXBException e) {
					e.printStackTrace();
				}
				
			}
			
			else{
				
				Stock stock = stocks.get(0);
				stock.getPriceFormSina();
				newsMessage.addItem(convert(stock.getChineseName()), convert(stock.getChineseName()), "http://image.sinajs.cn/newchart/daily/n/"+stock.getCode()+".gif", "http://image.sinajs.cn/newchart/daily/n/"+stock.getCode()+".gif");
				//newsMessage.addItem(convert(stock.getChineseName()), convert(stock.getChineseName()), "http://image.sinajs.cn/newchart/daily/n/"+stock.getCode()+".gif", "http://liq.tunnel.mobi/myapp/stockKLineQuery.jsp?code="+stock.getCode());
				newsMessage.addItem("现价:"+stock.getXj()+" 涨幅:"+stock.getZf(), convert(stock.getChineseName()), "http://image.sinajs.cn/newchart/min/n/"+stock.getCode()+".gif", "http://stocks.sina.cn/sh/?code="+stock.getCode());
				//newsMessage.addItem("现价:"+stock.getXj()+" 涨幅:"+stock.getZf(), convert(stock.getChineseName()), "http://image.sinajs.cn/newchart/min/n/"+stock.getCode()+".gif", "http://liq.tunnel.mobi/myapp/stockQuery.jsp?code="+stock.getCode());
				
				
					try {	
					
					xml = Util.objectToXML(WeiXinNewsMessage.class, newsMessage);
					
					//System.out.println(xml);
				} catch (JAXBException e) {
					e.printStackTrace();
				}
				
				
			}
			
			
			
			
			
			
			
		}
		
		else{
			//System.out.println(stocks.size());
			textMessage.setContent("您的输入有误,目前只支持A股股票查询.");
		try {	
					
				xml = Util.objectToXML(WeiXinTextMessage.class, textMessage);
			} catch (JAXBException e) {
				e.printStackTrace();
			}
			 
			
		}
		
		//System.out.println("endTime: "+new SimpleDateFormat("MM:ss").format(new Date()));
		
		out.print(xml);
		
		
		
		//System.out.println(content);
		
		
		
	}

	
	public String convert(String utfString){  
	    StringBuilder sb = new StringBuilder();  
	    int i = -1;  
	    int pos = 0;  
	      
	    while((i=utfString.indexOf("\\u", pos)) != -1){  
	        sb.append(utfString.substring(pos, i));  
	        if(i+5 < utfString.length()){  
	            pos = i+6;  
	            sb.append((char)Integer.parseInt(utfString.substring(i+2, i+6), 16));  
	        }  
	    }  
	      
	    return sb.toString();  
	}
	 
	
}



  