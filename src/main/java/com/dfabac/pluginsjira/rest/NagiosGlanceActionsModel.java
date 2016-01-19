package com.dfabac.pluginsjira.rest;

import javax.xml.bind.annotation.*;
import java.util.List;


@XmlRootElement(name = "actions")
@XmlAccessorType(XmlAccessType.FIELD)
public class NagiosGlanceActionsModel {

	@XmlElement(name = "nagios_actions")
	private List<NagiosGlanceAction> na = null;

	@XmlElement(name = "jira_actions")
	private List<NagiosGlanceAction> ja = null;

	@XmlElement(name = "other_actions")
	private List<NagiosGlanceAction> oa = null;

	public NagiosGlanceActionsModel() {}

	public List<NagiosGlanceAction> getNagiosActions() {
		return this.na;
	}

	public void setNagiosActions(List<NagiosGlanceAction> actions) {
		this.na = actions;
	}

	public List<NagiosGlanceAction> getJiraActions() {
		return this.ja;
	}

	public void setJiraActions(List<NagiosGlanceAction> actions) {
		this.ja = actions;
	}

	public List<NagiosGlanceAction> getOtherActions() {
		return this.oa;
	}

	public void setOtherActions(List<NagiosGlanceAction> actions) {
		this.oa = actions;
	}
}