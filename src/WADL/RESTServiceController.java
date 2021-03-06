package WADL;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;

//[ONLY IF RESOURCE IS RELATED OF ANOTHER] --> added only if the resource is a related resource of another resource


@Path("/account/{accountId}/RESTService")
public class RESTServiceController
{
	private RESTServiceModel oRESTService; //resourceModel object
	private POSTRESTServiceHandler oPOSTRESTServiceHandler;// add all the activity handlers this controller is associated with in the PSM
	private GETRESTServiceHandler oGETRESTServiceHandler;
	private PUTRESTServiceHandler oPUTRESTServiceHandler;
	private DELETERESTServiceHandler oDELETERESTServiceHandler;
	private GETRESTServiceListHandler oGETRESTServiceListHandler; // only if the resource is of aggregate type
	
	@Context
	private UriInfo oApplicationUri;
	
	public RESTServiceController() {}
	
	//GET for individual resource
	@Path("/{RESTServiceId}")
	@GET
	@Produces("application/JSON")

	
	//TODO CHANGE RESTServiceModel to RESTServiceRepresentation
	public RESTServiceModel getRESTService(@PathParam("RESTServiceId") Integer RESTServiceId)
	{
		//create a new get<resourceName>Handler
		oGETRESTServiceHandler = new GETRESTServiceHandler(RESTServiceId,oApplicationUri);
		return oGETRESTServiceHandler.getRESTService();
	}
	 
	 //POST

	 @Path("/")
	 @POST
	 @Consumes("application/JSON")
	 @Produces("application/JSON")
	 
	 public RESTServiceModel postRESTService (@PathParam("accountId") Integer accountId, RESTServiceModel oRESTService )
	 {
		//create a new post<resourceName>Handler
		oPOSTRESTServiceHandler = new POSTRESTServiceHandler(accountId, oRESTService,oApplicationUri);
		return oPOSTRESTServiceHandler.postRESTService();
	 }
	 
	//PUT

	 @Path("/{RESTServiceId}")
	 @PUT
	 @Consumes("application/JSON")
	 @Produces("application/JSON")
	 
	 public RESTServiceModel putRESTService (@PathParam("accountId") Integer accountId, @PathParam("RESTServiceId") Integer RESTServiceId, RESTServiceModel oRESTService)
	 {
		//create a new put<resourceName>Handler
		oPUTRESTServiceHandler = new PUTRESTServiceHandler(accountId, RESTServiceId, oRESTService,oApplicationUri);
		return oPUTRESTServiceHandler.putRESTService();
	 }
	 
	 //DELETE

	@Path("/{RESTServiceId}")
	@Produces("application/json")
	@DELETE

	public RESTServiceModel deleteRESTService (@PathParam("RESTServiceId") Integer RESTServiceId)
	{
		//create a new delete<resourceName>Handler
		oDELETERESTServiceHandler = new DELETERESTServiceHandler(RESTServiceId,oApplicationUri);
		return oDELETERESTServiceHandler.deleteRESTService();
	}

	//GET (aggregate resource)
	@Path("/")
	@GET
	@Produces("application/json")

	public RESTServiceModel getRESTServiceList(@PathParam("accountId") Integer accountId)
	{
		//create a new get<resourceName>ListHandler
		oGETRESTServiceListHandler = new GETRESTServiceListHandler(accountId, oApplicationUri);
		return oGETRESTServiceListHandler.getRESTServiceList();
	}
}