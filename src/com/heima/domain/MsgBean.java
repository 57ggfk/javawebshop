package com.heima.domain;

public class MsgBean {
	
	private String title;			//标题
	private String content;			//信息内容
	private String goButton;		//主按钮名称
	private String goUrl;			//主按钮跳转地址
	private String backButton;		//返回按钮名
	private String backUrl;			//返回跳转路径
	private boolean autoForward;	//是否自动跳转到主按钮指定地址
	
	public MsgBean(String title, String goButton, String goUrl) {
		super();
		this.title = title;
		this.goButton = goButton;
		this.goUrl = goUrl;
	}
	
	public MsgBean() {
		super();
	}

	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public String getContent() {
		return content;
	}


	public void setContent(String content) {
		this.content = content;
	}


	public String getGoButton() {
		return goButton;
	}


	public void setGoButton(String goButton) {
		this.goButton = goButton;
	}


	public String getGoUrl() {
		return goUrl;
	}


	public void setGoUrl(String goUrl) {
		this.goUrl = goUrl;
	}


	public String getBackButton() {
		return backButton;
	}


	public void setBackButton(String backButton) {
		this.backButton = backButton;
	}


	public String getBackUrl() {
		return backUrl;
	}


	public void setBackUrl(String backUrl) {
		this.backUrl = backUrl;
	}

	public boolean isAutoForward() {
		return autoForward;
	}

	public void setAutoForward(boolean autoForward) {
		this.autoForward = autoForward;
	}

}
