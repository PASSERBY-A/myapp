package com.liqiang.stock.domain;

import javax.xml.bind.JAXBException;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.liqiang.utils.Util;
import com.sun.xml.internal.txw2.annotation.XmlCDATA;

@XmlRootElement(name = "xml")
@XmlType(propOrder = { "toUserName", "fromUserName","createTime", "msgType","content" })
public class WeiXinTextMessage  {

	
	private String toUserName;
	
	private String fromUserName;
	
	private String createTime;
	
	private String msgType;
	 
	private String content;

	public WeiXinTextMessage()
	{
		
		msgType ="text";
	}
	
	
	@XmlElement(name = "ToUserName")
	public String getToUserName() {
		return toUserName;
	}
	
	@XmlElement(name = "FromUserName")
	public String getFromUserName() {
		return fromUserName;
	}

	@XmlElement(name = "CreateTime")
	public String getCreateTime() {
		return createTime;
	}

	@XmlElement(name = "MsgType")
	public String getMsgType() {
		return msgType;
	}
	

	@XmlCDATA
	@XmlElement(name = "Content")
	public String getContent() {
		return content;
	}

	public void setToUserName(String toUserName) {
		this.toUserName = toUserName;
	}

	public void setFromUserName(String fromUserName) {
		this.fromUserName = fromUserName;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public void setContent(String content) {
		this.content = content;
	}

	 

	 public static void main(String[] args) throws JAXBException {
		
	   
		WeiXinTextMessage r = new WeiXinTextMessage();
		
		r.setToUserName("username");
		
		r.setContent("content");

		r.setFromUserName("fromUserName");
		
		r.setCreateTime(String.valueOf(System.currentTimeMillis()));
		
		System.out.println(Util.objectToXML(WeiXinTextMessage.class, r) );
		
		
		
	}


	@Override
	public String toString() {
		return "WeiXinTextMessage [toUserName=" + toUserName
				+ ", fromUserName=" + fromUserName + ", createTime="
				+ createTime + ", msgType=" + msgType + ", content=" + content
				+ "]";
	}
 
	 
	 
	 
	 
	 
}
