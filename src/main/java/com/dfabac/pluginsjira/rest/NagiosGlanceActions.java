package com.dfabac.pluginsjira.rest;

import com.atlassian.plugins.rest.common.security.AnonymousAllowed;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;


@Path("/actions")
public class NagiosGlanceActions {

    @GET
    @AnonymousAllowed
    @Produces({MediaType.APPLICATION_JSON})
    public Response getActions(@QueryParam("host") String host, @QueryParam("service") String svc)
	{
		ArrayList<NagiosAction> actions = new ArrayList<NagiosAction>();
		actions.add(this.makeActionOpenHost(host));
		actions.add(this.makeActionOpenSvc(host, svc));

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
		return new NagiosAction("example1"+host, "example1"+svc);
	}
}