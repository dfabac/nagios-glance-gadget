package com.dfabac.pluginsjira.rest;

import javax.xml.bind.annotation.*;

@XmlRootElement(name = "action")
@XmlAccessorType (XmlAccessType.FIELD)
public class NagiosAction
{
	private String title;
	private String path;

	public NagiosAction() {}

	public NagiosAction(String title, String path) {
		this.title = title;
		this.path = path;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;	
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;	
	}
}