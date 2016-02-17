package com.dfabac.pluginsjira.rest;

import javax.xml.bind.annotation.*;

@XmlRootElement(name = "action")
@XmlAccessorType (XmlAccessType.FIELD)
public class NagiosGlanceAction
{
	private String title = null;
	private String path = null;

	public NagiosGlanceAction() {}

	public NagiosGlanceAction(String title, String path) {
		this.title = title;
		this.path = path;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;	
	}

	public String getPath() {
		return this.path;
	}

	public void setPath(String path) {
		this.path = path;	
	}
}