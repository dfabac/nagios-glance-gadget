package com.dfabac.pluginsjira.rest;

import javax.xml.bind.annotation.*;
import java.util.List;


@XmlRootElement(name = "nagiosactions")
@XmlAccessorType(XmlAccessType.FIELD)
public class NagiosGlanceActionsModel {

	@XmlElement(name = "actions")
	private List<NagiosAction> actions = null;

	public NagiosGlanceActionsModel() {}

	public List<NagiosAction> getActions() {
		return actions;
	}

	public void setActions(List<NagiosAction> actions) {
		this.actions = actions;
	}
}