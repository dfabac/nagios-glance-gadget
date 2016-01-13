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
		actions.add(this.makeActionAckSrv(host, svc));
		actions.add(this.makeActionPingHost(host));
		actions.add(this.makeActionCopyHost(host));
		actions.add(this.makeActionFindRelated(host));
		actions.add(this.makeActionCreate(host, svc));

		NagiosGlanceActionsModel ng = new NagiosGlanceActionsModel();
		ng.setActions(actions);

		return Response.ok(ng).build();
	}

	// TODO
	private NagiosAction makeActionOpenHost(String host)
	{
		String path = "TO_BE_DETERMINED_?host=" + host;
		return new NagiosAction(i18n.getText("nagios-gadget.rest-res.actions.viewhost"), path);
	}

	private NagiosAction makeActionOpenSvc(String host, String svc)
	{
		String path = "/cgi-bin/nagios3/extinfo.cgi?type=2&host=" + host + "&service=" + svc;
		return new NagiosAction(i18n.getText("nagios-glance.rest-res.actions.viewserv"), path);
	}

	// TODO
	private NagiosAction makeActionAckSrv(String host, String svc)
	{
		String path = "TO_BE_DETERMINED_?host=" + host + "&service=" + svc;
		return new NagiosAction(i18n.getText("nagios-gadget.rest-res.actions.acknowledge"), path);
	}

	// TODO
	private NagiosAction makeActionPingHost(String host)
	{
		String path = "TO_BE_DETERMINED_?host=" + host;
		return new NagiosAction(i18n.getText("nagios-gadget.rest-res.actions.pinghost"), path);
	}

	// TODO
	private NagiosAction makeActionCopyHost(String host)
	{
		String path = "TO_BE_DETERMINED_?host=" + host;
		return new NagiosAction(i18n.getText("nagios-gadget.rest-res.actions.copyhost"), path);
	}

	// TODO
	private NagiosAction makeActionFindRelated(String host)
	{
		String path = "TO_BE_DETERMINED_?host=" + host;
		return new NagiosAction(i18n.getText("nagios-gadget.rest-res.actions.related"), path);
	}

	// TODO
	private NagiosAction makeActionCreate(String host, String svc)
	{
		String path = "TO_BE_DETERMINED_?host=" + host;
		return new NagiosAction(i18n.getText("nagios-gadget.rest-res.actions.create"), path);
	}
}