package com.dfabac.pluginsjira.rest;

import com.atlassian.plugins.rest.common.security.AnonymousAllowed;
import com.atlassian.sal.api.message.I18nResolver;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;


@Path("/actions")
public class NagiosGlance {

	private I18nResolver i18n;

	public void setI18nResolver(I18nResolver i18n) {
		this.i18n = i18n;
	}

	@GET
	@AnonymousAllowed
	@Produces({MediaType.APPLICATION_JSON})
	public Response getActions(@QueryParam("host") String host, @QueryParam("service") String svc)
	{
		ArrayList<NagiosAction> actions = new ArrayList<NagiosAction>();
		actions.add(this.makeActionOpenSvc(host, svc));
		actions.add(this.makeActionOpenHost(host));

		NagiosGlanceActionsModel ng = new NagiosGlanceActionsModel();
		ng.setActions(actions);

		return Response.ok(ng).build();
	}

	private NagiosAction makeActionOpenHost(String host)
	{
		return new NagiosAction("example", "example");
	}

	private NagiosAction makeActionOpenSvc(String host, String svc)
	{
		String path = "/cgi-bin/nagios3/extinfo.cgi?type=2&host=" + host + "&service=" + svc;
		return new NagiosAction(i18n.getText("com.dfabac.pluginsjira.nagios-glance-gadget.actions.viewserv"), path);
	}
}