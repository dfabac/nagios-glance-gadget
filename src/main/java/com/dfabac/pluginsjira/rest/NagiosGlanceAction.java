package com.dfabac.pluginsjira.rest;

import javax.xml.bind.annotation.*;

@XmlRootElement(name = "action")
@XmlAccessorType (XmlAccessType.FIELD)
public class NagiosGlanceAction
{
	private String title = null;
	private String path = null;
	private String cssClass = null;

	public NagiosGlanceAction() {}

	public NagiosGlanceAction(String title, String path, String cssClass) {
		this.title = title;
		this.path = path;
		this.cssClass = cssClass;
	}

	public NagiosGlanceAction(String title, String path) {
		this(title, path, new String());
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

	public String getCssCalss() {
		return this.cssClass;
	}

	public void setCssClass(String cssClass) {
		this.cssClass = cssClass;	
	}
}