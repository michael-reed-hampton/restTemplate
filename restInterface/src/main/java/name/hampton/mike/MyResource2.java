package name.hampton.mike;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.Iterator;
import java.util.ServiceLoader;
import java.util.logging.Logger;

/**
 * Root resource (exposed at "myresource" path)
 */
@Path("myresource2/{resourceId}")
public class MyResource2 {
	
	private Logger logger = Logger.getLogger(this.getClass().getName());

    /**
     * Method handling HTTP GET requests. The returned object will be sent
     * to the client as "text/plain" media type.
     *
     * @return String that will be returned as a text/plain response.
     * @throws ImplementationNotFoundException 
     */
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getIt(@PathParam("resourceId") String resourceId) throws ImplementationNotFoundException {
		final ServiceLoader<IBusinessFunctionality> serviceLoader = ServiceLoader.load(IBusinessFunctionality.class);
		
		Iterator<IBusinessFunctionality> impls = serviceLoader.iterator();
		
		if (!impls.hasNext()) {
			logger.severe("No name.hampton.mike.IBusinessFunctionality found");
			throw new ImplementationNotFoundException("No name.hampton.mike.IBusinessFunctionality found");
		}

		IBusinessFunctionality instance = null;
		instance = impls.next();
		String actual = instance.getSimpleItem();
			
		return String.format("resourceId=%s, item=%s", resourceId, actual);
    }


	/**
	 * Method handling HTTP GET requests. The returned object will be sent
	 * to the client as "text/plain" media type.
	 *
	 * @return String that will be returned as a text/plain response.
	 * @throws ImplementationNotFoundException
	 */
	@GET
	@Produces("application/json")
	public BusinessObject getItAsJson(@PathParam("resourceId") String resourceId) throws ImplementationNotFoundException {
		final ServiceLoader<IBusinessFunctionality> serviceLoader = ServiceLoader.load(IBusinessFunctionality.class);

		Iterator<IBusinessFunctionality> impls = serviceLoader.iterator();

		if (!impls.hasNext()) {
			logger.severe("No name.hampton.mike.IBusinessFunctionality found");
			throw new ImplementationNotFoundException("No name.hampton.mike.IBusinessFunctionality found");
		}

		IBusinessFunctionality instance = null;
		instance = impls.next();
		BusinessObject actual = instance.getComplexItem();
		actual.setText(resourceId);

		return actual;
	}

	@POST
	@Path("/transformStuff")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public BusinessObject transformStuff(@PathParam("resourceId") String resourceId, BusinessObject actual) {

		actual.setText("transformed! " + actual.getText());
		actual.setNumber(actual.getNumber() + 1);

		return actual;
	}
}
