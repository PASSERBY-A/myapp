package com.liqiang.stock.domain;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBException;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.liqiang.utils.Util;

@XmlRootElement(name = "xml")
@XmlType(propOrder = { "toUserName", "fromUserName", "createTime", "msgType",
		"articleCount", "articles" })
public class WeiXinNewsMessage {

	private List<Item> items = new ArrayList<Item>();

	public WeiXinNewsMessage() {

		this.msgType = "news";

		this.articles = new Articles();

		articles.setItems(items);

	}

	private String toUserName;

	private String fromUserName;

	private String createTime;

	private String msgType;

	private String articleCount;

	private Articles articles;

	@XmlElement(name = "ArticleCount")
	public String getArticleCount() {
		return String.valueOf(items.size());
	}

	@XmlElement(name = "Articles")
	public Articles getArticles() {
		return articles;
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

	public void setArticleCount(String articleCount) {
		this.articleCount = articleCount;
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

	public void setArticles(Articles articles) {
		this.articles = articles;
	}

	public void addItem(String title, String description, String picUrl,
			String url) {
		Item item = new Item();

		item.setTitle(title);

		item.setDescription(description);

		item.setPicUrl(picUrl);

		item.setUrl(url);

		items.add(item);

	}

	public static void main(String[] args) {

		WeiXinNewsMessage m = new WeiXinNewsMessage();

		m.setArticleCount("1");
		m.setCreateTime("11111111");
		m.setFromUserName("setFromUserName");
		m.setMsgType("news");
		m.setToUserName("liqiang");

		m.addItem("title", "description", "picUrl", "url");

		m.addItem("title1", "description1", "picUrl1", "url1");

		m.addItem("title2", "description2", "picUrl2", "url2");

		try {
			String s = Util.objectToXML(WeiXinNewsMessage.class, m);
			System.out.println(s);

		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}

class Articles {

	private List<Item> items;

	@XmlElement(name = "item")
	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}

}

class Item {

	private String title;

	private String description;

	private String picUrl;

	private String url;

	@XmlElement(name = "Title")
	public String getTitle() {
		return title;
	}

	@XmlElement(name = "Description")
	public String getDescription() {
		return description;
	}

	@XmlElement(name = "PicUrl")
	public String getPicUrl() {
		return picUrl;
	}

	@XmlElement(name = "Url")
	public String getUrl() {
		return url;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}
