package com.liqiang.stock.server;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.liqiang.stock.domain.Stock;
import com.liqiang.stock.domain.User;
import com.liqiang.utils.MailUtil;


public class StockSubscribeServer {
	
	static boolean started=true;
	
	
	
	private List<Stock> stocks;
	
	public StockSubscribeServer()
	{
		
		init();
		
	}
	
	public void init()
	{
		Set<User> sets = new HashSet<User>();
		
		User user = new User();
		
		user.setEmail("liq4i@icloud.com");
		
		sets.add(user);
		
		Stock s = new Stock("603598",sets);
		
		//s.setCode1("603598");
		
		stocks = new ArrayList<Stock>();
		
		stocks.add(new Stock("603885",sets));
		stocks.add(new Stock("603598",sets));
		stocks.add(new Stock("002753",sets));
		
		Stock s1 =  new Stock("",sets);
		//s1.setCode2("sz000938");
		stocks.add(s1);
		
	}
	
	private static Log log = LogFactory.getLog(StockSubscribeServer.class);
	
	


	public void start()
	{
		
		new MailUtil().send("liq4i@icloud.com","info ","Application is started!");
		
		StockSubscribeServer.started = true;
		
		for(final Stock s:stocks )
		{
			new Thread(new StocksAction(s)).start();
		}
		
	}
	
	public void stop()
	{
		new MailUtil().send("liq4i@icloud.com","info ","Application is stop!");
		StockSubscribeServer.started = false;
	}
	
	
	
	public static void main(String[] args) {
		
		System.setProperty("java.net.preferIPv4Stack", "true");
		
		StockSubscribeServer s = new StockSubscribeServer();
		
		s.start();
		
		
		
		
		
	}

	 
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}


class StocksAction implements Runnable  {
	
	private static Log log = LogFactory.getLog(StockSubscribeServer.class);
	
	public StocksAction(Stock s){
		
		this.s = s;
	
	}
	
	private Stock s;
	
	public void run() {
		
		
		
		while(StockSubscribeServer.started)
		{
			if(s.getPrice())
			{
				
				log.info("That's work "+s.getCode()+"------zf:："+s.getZf());
				
				if(s.getZt()!=s.getXj())
				{
					
					new MailUtil().send("liq4i@icloud.com", "", s.getCode()+" zhangtingban kai le .......");
					
					try {
						Thread.sleep(3000);
					} catch (InterruptedException e) {
					}
					new MailUtil().send("liq4i@icloud.com", "", s.getCode()+" zhangtingban  kai le ");
					try {
						Thread.sleep(3000);
					} catch (InterruptedException e) {
					}
					
					new MailUtil().send("liq4i@icloud.com", "", s.getCode()+" zhangtingban  kai le");
					
					break;
				}
				else{
					continue;
				}
			}
			
	}






	}
	

}
 











