package com.liqiang.stock.domain;

import javax.xml.bind.JAXBException;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.liqiang.utils.Util;

@XmlRootElement(name = "xml")
public class WeiXinReturnMessage  {

	
	private String toUserName;
	
	private String fromUserName;
	
	private String createTime;
	
	private String msgType;
	
	private String content;

	public WeiXinReturnMessage()
	{
		
		
	}
	
	
	private String msgId;

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
	

	@XmlElement(name = "Content")
	public String getContent() {
		return content;
	}

	@XmlElement(name = "MsgId")
	public String getMsgId() {
		return msgId;
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

	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public void setMsgId(String msgId) {
		this.msgId = msgId;
	}

	 

	 public static void main(String[] args) throws JAXBException {
		
		String xml ="<xml><ToUserName><![CDATA[gh_47c39a29865c]]></ToUserName><FromUserName><![CDATA[ou7EguFSm_EMMltTR0QnBDwlhnWM]]></FromUserName><CreateTime>1434206780</CreateTime><MsgType><![CDATA[text]]></MsgType><Content><![CDATA[000938]]></Content><MsgId>6159871216007943840</MsgId></xml>"; 
	   
		WeiXinReturnMessage r = new WeiXinReturnMessage();
		
		  r= Util.xmlToObject(WeiXinReturnMessage.class, xml);
		
		//r = r.xmlToObject(xml);
		
		System.out.println(r);
		
		
		
	}

	@Override
	public String toString() {
		return "WeiXinReturnMessage [toUserName=" + toUserName
				+ ", fromUserName=" + fromUserName + ", createTime="
				+ createTime + ", msgType=" + msgType + ", content=" + content
				+ ", msgId=" + msgId + "]";
	}
	 
	 
}
