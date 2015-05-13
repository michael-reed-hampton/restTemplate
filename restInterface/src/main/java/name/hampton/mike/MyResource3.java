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
@Path("myresource3/{resourceId}")
public class MyResource3 {
	
	private Logger logger = Logger.getLogger(this.getClass().getName());

	@POST
	@Path("/transformStuff")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public BusinessObject transformStuff(@PathParam("resourceId") String resourceId, BusinessObject actual) {

		actual.setText(String.format("transformed! resourceId is %s, origtext is %s", resourceId, actual.getText()));
		actual.setNumber(actual.getNumber() + 1);

		return actual;
	}
}
