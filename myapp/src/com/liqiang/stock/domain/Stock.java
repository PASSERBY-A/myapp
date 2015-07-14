package com.liqiang.stock.domain;

import java.math.BigDecimal;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.client.fluent.Content;
import org.apache.http.client.fluent.Request;

import com.liqiang.stock.Constant;

public class Stock {
	
	private static Log log = LogFactory.getLog(Stock.class);
	
	
	
	private String name;
	
	//sz sh
	private String type;
	
	//中文名称
	private String chineseName;
	
	//拼音
	private String pinYin;
	
	private String code;
	
	//现在价格
	private double xj;
	//昨收
	private double zs;
	
	//今开
	private double jk;
	
	//最高
	private double zg;

	//最低
	private double zd;
	
	//涨停
	private double zt;
	
	//跌停
	private double dt;
	
	//涨幅 
	private String zf;
	
	
	public Stock(){}
	public Stock(String code, Set<User> subscriber) {
		super();
		this.code = code;
		this.subscriber = subscriber;
	}

	private Set<User>  subscriber;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

  
	public Set<User> getSubscriber() {
		return subscriber;
	}

	public void setSubscriber(Set<User> subscriber) {
		this.subscriber = subscriber;
	}

	public double getZs() {
		return zs;
	}

	public void setZs(double zs) {
		this.zs = zs;
	}

	public double getJk() {
		return jk;
	}

	public void setJk(double jk) {
		this.jk = jk;
	}

	public double getZg() {
		return zg;
	}

	public void setZg(double zg) {
		this.zg = zg;
	}

	public double getZd() {
		return zd;
	}

	public void setZd(double zd) {
		this.zd = zd;
	}

	public double getZt() {
		return zt;
	}

	public void setZt(double zt) {
		this.zt = zt;
	}

	public double getDt() {
		return dt;
	}

	public void setDt(double dt) {
		this.dt = dt;
	}

	public double getXj() {
		return xj;
	}

	public void setXj(double xj) {
		this.xj = xj;
	}


	public String getZf() {
		return zf;
	}
	public void setZf(String zf) {
		this.zf = zf;
	}
	
	
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	
	
	private synchronized boolean toStockCode()
	{
		/*String[] strs = null;
		String url = String.format(Constant.GET_STOCK_CODE_URL, this.code1);
		
		String resultStr=null;
		try {
			Content con =  Request.Get(url) .execute().returnContent();
			
			 resultStr = con.asString();
			
			resultStr = resultStr.substring(resultStr.indexOf("\"")+1, resultStr.lastIndexOf("\""));

			  strs= resultStr.split("~");
			 this.setName(strs[2]);
			 
			 this.setCode2(strs[0]+strs[1]);
		
		} 
		catch (Exception e) {
			if(e instanceof java.lang.ArrayIndexOutOfBoundsException)
			{
				
				
				System.out.println(url+"....."+resultStr);
				return false;
			}
			log.error("code:"+this.code1+"get code exception:： "+e.getMessage());
			
			log.info(strs);
			e.printStackTrace();
		}*/
		return true;  
		
	}
	
	
	public boolean getPrice()
	{
		
		
		
		if(this.code==null)
		{
			return false;
			
			//toStockCode();
		}
				String url = String.format(Constant.GET_PRICE_URL, this.code);
				String resultStr=null;
				try {
					Content con =  Request.Get(url) .execute().returnContent();
					
					 resultStr = con.asString();
					
					resultStr = resultStr.substring(resultStr.indexOf("\"")+1, resultStr.lastIndexOf("\""));

					String[] prices= resultStr.split("~");
					 
					this.xj = Double.valueOf(prices[3]);
					this.zs = Double.valueOf(prices[4]);
					this.jk = Double.valueOf(prices[5]);
					this.zg = Double.valueOf(prices[33]);
					this.zd = Double.valueOf(prices[34]);
					this.zt = this.zs*1.10;
					BigDecimal   z   =   new   BigDecimal(zt);
					this.zt =z.setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
					this.dt = this.zs*0.90;
					BigDecimal   d   =   new   BigDecimal(dt);
					this.dt =d.setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
					
					BigDecimal   zhangfu   =   new   BigDecimal(((this.xj-this.zs)/this.zs*100));
					
					this.zf = String.valueOf(zhangfu.setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue()+"%");
					
					return true;
				
				 
			} catch (Exception e) {
				
				System.out.println(url+"....."+resultStr);
				
				log.error(name +"  "+"code:"+this.code+"get price exception: "+e.getMessage());
			
				return false;
			}  
	}
	
	
	
	public boolean getPriceFormSina()
	{
		
		
		
		if(this.code==null)
		{
			return false;
			
			//toStockCode();
		}
		
		
				String url = String.format("http://hq.sinajs.cn/list=%s", this.code);
				String resultStr=null;
				try {
					Content con =  Request.Get(url) .execute().returnContent();
					
					 resultStr = con.asString();
					
					resultStr = resultStr.substring(resultStr.indexOf("\"")+1, resultStr.lastIndexOf("\""));

					String[] prices= resultStr.split(",");
					this.xj = Double.valueOf(prices[3]);
					this.zs = Double.valueOf(prices[2]);
					this.jk = Double.valueOf(prices[1]);
					this.zg = Double.valueOf(prices[4]);
					this.zd = Double.valueOf(prices[5]);
					this.zt = this.zs*1.10;
					BigDecimal   z   =   new   BigDecimal(zt);
					this.zt =z.setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
					this.dt = this.zs*0.90;
					BigDecimal   d   =   new   BigDecimal(dt);
					this.dt =d.setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
					
					BigDecimal   zhangfu   =   new   BigDecimal(((this.xj-this.zs)/this.zs*100));
					
					this.zf = String.valueOf(zhangfu.setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue()+"%");/**/
					
					return true;
				
				 
			} catch (Exception e) {
				
				System.out.println(url+"....."+resultStr);
				
				log.error(name +"  "+"code:"+this.code+"get price exception: "+e.getMessage());
			
				return false;
			}  
	}
	
	
	
	
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getChineseName() {
		return chineseName;
	}
	public void setChineseName(String chineseName) {
		this.chineseName = chineseName;
	}
	public String getPinYin() {
		return pinYin;
	}
	public void setPinYin(String pinYin) {
		this.pinYin = pinYin;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((code == null) ? 0 : code.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Stock other = (Stock) obj;
		if (code == null) {
			if (other.code != null)
				return false;
		} else if (!code.equals(other.code))
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Stock [name=" + name + ", type=" + type + ", chineseName="
				+ chineseName + ", pinYin=" + pinYin + ", code=" + code
				+ ", xj=" + xj + ", zs=" + zs + ", jk=" + jk + ", zg=" + zg
				+ ", zd=" + zd + ", zt=" + zt + ", dt=" + dt + ", zf=" + zf
				+ ", subscriber=" + subscriber + "]";
	}
	public static void main(String[] args) {
		
		Stock s = new Stock();
		s.setCode("sh600487");
		//s.setCode1("000938");
		//s.setCode2("sz000938");
		s.getPriceFormSina();
		
		System.out.println(s.getXj());
		System.out.println(s.getZt());
	}

	
	
}
