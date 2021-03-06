package WADL;

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

@Path("/account/{accountId}/RESTService/{RESTServiceId}/resource/{resourceId}/RESTMethod")
public class RESTMethodController
{
	private RESTMethodModel oRESTMethod; //resourceModel object
	private POSTRESTMethodHandler oPOSTRESTMethodHandler;// add all the activity handlers this controller is associated with in the PSM
	private GETRESTMethodHandler oGETRESTMethodHandler;
	private PUTRESTMethodHandler oPUTRESTMethodHandler;
	private DELETERESTMethodHandler oDELETERESTMethodHandler;
	private GETRESTMethodListHandler oGETRESTMethodListHandler; // only if the resource is of aggregate type
	
	@Context
	private UriInfo oApplicationUri;
	
	public RESTMethodController() {}
	
	//placeholder to add any HTTPActivity() template operation

	@Path("/{RESTMethodId}")
	@GET
	@Produces("application/json")

	public RESTMethodModel getRESTMethod(@PathParam("RESTMethodId") int RESTMethodId)
	{
		//create a new get<resourceName>Handler
		oGETRESTMethodHandler = new GETRESTMethodHandler(RESTMethodId,oApplicationUri);
		return oGETRESTMethodHandler.getRESTMethod();
	}
	 
	 @Path("/")
	 @POST
	 @Consumes("application/json")
	 @Produces("application/json")
	 
	 public RESTMethodModel postRESTMethod (@PathParam("resourceId") int resourceId, RESTMethodModel oRESTMethod )
	 {
		//create a new post<resourceName>Handler
		oPOSTRESTMethodHandler = new POSTRESTMethodHandler(resourceId, oRESTMethod,oApplicationUri);
		return oPOSTRESTMethodHandler.postRESTMethod();
	 }
	 
	 @Path("/{RESTMethodId}")
	 @PUT
	 @Consumes("application/json")
	 @Produces("application/json")
	 
	 public RESTMethodModel putRESTMethod (@PathParam("resourceId") int resourceId, @PathParam("RESTMethodId") int RESTMethodId, RESTMethodModel oRESTMethod)
	 {
		//create a new put<resourceName>Handler
		oPUTRESTMethodHandler = new PUTRESTMethodHandler(resourceId, RESTMethodId, oRESTMethod,oApplicationUri);
		return oPUTRESTMethodHandler.putRESTMethod();
	 }
	 
	@Path("/{RESTMethodId}")
	@DELETE
	@Produces("application/json")

	public RESTMethodModel deleteRESTMethod (@PathParam("RESTMethodId") int RESTMethodId)
	{
		//create a new delete<resourceName>Handler
		oDELETERESTMethodHandler = new DELETERESTMethodHandler(RESTMethodId,oApplicationUri);
		return oDELETERESTMethodHandler.deleteRESTMethod();
	}


	@Path("/")
	@GET
	@Produces("application/json")

	public RESTMethodModel getRESTMethodList(@PathParam("resourceId") int resourceId)
	{
		//create a new get<resourceName>ListHandler
		oGETRESTMethodListHandler = new GETRESTMethodListHandler(resourceId,oApplicationUri);
		return oGETRESTMethodListHandler.getRESTMethodList();
	}
 
}