package name.hampton.mike;

import java.util.Iterator;
import java.util.ServiceLoader;
import java.util.logging.Logger;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Root resource (exposed at "myresource" path)
 */
@Path("myresource")
public class MyResource {
	
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
    public String getIt() throws ImplementationNotFoundException {
		final ServiceLoader<IBusinessFunctionality> serviceLoader = ServiceLoader.load(IBusinessFunctionality.class);
		
		Iterator<IBusinessFunctionality> impls = serviceLoader.iterator();
		
		if (!impls.hasNext()) {
			logger.severe("No name.hampton.mike.IBusinessFunctionality found");
			throw new ImplementationNotFoundException("No name.hampton.mike.IBusinessFunctionality found");
		}

		IBusinessFunctionality instance = null;
		instance = impls.next();
		String actual = instance.getSimpleItem();
			
		return actual;
    }
}
