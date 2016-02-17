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
	public Response getActions(@QueryParam("host") String host, @QueryParam("service") String svc, 
							@QueryParam("project") String projectId, @QueryParam("issuetype") String issueTypeId)
	{
		ArrayList<NagiosGlanceAction> na = new ArrayList<NagiosGlanceAction>();
		na.add(this.makeActionOpenSvc(host, svc));
		na.add(this.makeActionOpenHost(host));
		na.add(this.makeActionAckSrv(host, svc));

		ArrayList<NagiosGlanceAction> oa = new ArrayList<NagiosGlanceAction>();
		oa.add(this.makeActionPingHost(host));
		oa.add(this.makeActionCopyHost(host));
		
		ArrayList<NagiosGlanceAction> ja = new ArrayList<NagiosGlanceAction>();
		ja.add(this.makeActionFindRelated(host, projectId));
		ja.add(this.makeActionCreate(host, svc, projectId, issueTypeId));

		NagiosGlanceActionsModel ng = new NagiosGlanceActionsModel();
		ng.setNagiosActions(na);
		ng.setJiraActions(ja);
		ng.setOtherActions(oa);

		return Response.ok(ng).build();
	}

	private NagiosGlanceAction makeActionOpenHost(String host)
	{
		String path = "/extinfo.cgi?type=1&host=" + host;
		return new NagiosGlanceAction(i18n.getText("nagios-gadget.rest-res.actions.viewhost"), path);
	}

	private NagiosGlanceAction makeActionOpenSvc(String host, String svc)
	{
		String path = "/extinfo.cgi?type=2&host=" + host + "&service=" + svc;
		return new NagiosGlanceAction(i18n.getText("nagios-glance.rest-res.actions.viewserv"), path);
	}

	private NagiosGlanceAction makeActionAckSrv(String host, String svc)
	{
		String path = "/cmd.cgi?cmd_typ=34&host=" + host + "&service=" + svc;
		return new NagiosGlanceAction(i18n.getText("nagios-gadget.rest-res.actions.acknowledge"), path);
	}

	// TODO
	private NagiosGlanceAction makeActionPingHost(String host)
	{
		String path = "TO_BE_DETERMINED_?host=" + host;
		return new NagiosGlanceAction(i18n.getText("nagios-gadget.rest-res.actions.pinghost"), path);
	}

	// TODO
	private NagiosGlanceAction makeActionCopyHost(String host)
	{
		String path = "TO_BE_DETERMINED_?host=" + host;
		return new NagiosGlanceAction(i18n.getText("nagios-gadget.rest-res.actions.copyhost"), path);
	}

	private NagiosGlanceAction makeActionFindRelated(String host, String projectId)
	{
		String jql  = "project %3D " + NagiosGlance.shortenID(projectId) + " AND created >%3D -60m AND text ~ " + host;
		String path = "/issues/?jql=" + jql;
		return new NagiosGlanceAction(i18n.getText("nagios-gadget.rest-res.actions.related"), path);
	}

	private NagiosGlanceAction makeActionCreate(String host, String svc, String projectId, String issueTypeId)
	{
		String summary = "[NAGIOS] " + host + " - " + svc;
		String path = "/secure/CreateIssueDetails!init.jspa?pid=" + NagiosGlance.shortenID(projectId) 
		+ "&issuetype=" + issueTypeId + "&summary=" + summary + "&priority=3";
		return new NagiosGlanceAction(i18n.getText("nagios-gadget.rest-res.actions.create"), path);
	}

	private static String shortenID(String longID)
	{
		return longID.substring(longID.lastIndexOf("-") + 1);
	}
}
