package name.hampton.mike;

import name.hampton.mike.internal.BusinessObject;
import name.hampton.mike.sdk.V1BusinessObject;

import java.util.Iterator;
import java.util.ServiceLoader;
import java.util.logging.Logger;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Root resource (exposed at "myresource" path)
 */
@Path("myresource2/{resourceId}")
public class MyResource2 {

  private Logger logger = Logger.getLogger(this.getClass().getName());

  /**
   * Load the first implementation found.
   *
   * @return an IBusinessFunctionality instance
   * @throws ImplementationNotFoundException - if no IBusinessFunctionality is configured.
   * @see ServiceLoader#load(java.lang.Class)
   */
  private IBusinessFunctionality getiBusinessFunctionality() throws ImplementationNotFoundException {
    final ServiceLoader<IBusinessFunctionality> serviceLoader = ServiceLoader.load(IBusinessFunctionality.class);

    Iterator<IBusinessFunctionality> impls = serviceLoader.iterator();

    if (!impls.hasNext()) {
      logger.severe("No name.hampton.mike.IBusinessFunctionality found");
      throw new ImplementationNotFoundException("No name.hampton.mike.IBusinessFunctionality found");
    }

    IBusinessFunctionality instance;
    instance = impls.next();
    return instance;
  }

  /**
   * Method handling HTTP GET requests. The returned object will be sent
   * to the client as "text/plain" media type.
   *
   * @return String that will be returned as a text/plain response.
   * @throws ImplementationNotFoundException - if the ServiceLoader cannot find the implementation.
   */
  @GET
  @Produces(MediaType.TEXT_PLAIN)
  public String getIt(@PathParam("resourceId") String resourceId) throws ImplementationNotFoundException {
    IBusinessFunctionality instance = getiBusinessFunctionality();
    String actual = instance.getSimpleItem();

    return String.format("resourceId=%s, item=%s", resourceId, actual);
  }

  /**
   * Method handling HTTP GET requests. The returned object will be sent
   * to the client as "text/plain" media type.
   *
   * @return String that will be returned as a text/plain response.
   * @throws ImplementationNotFoundException - if the ServiceLoader cannot find the implementation.
   */
  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public V1BusinessObject getItAsJson(@PathParam("resourceId") String resourceId) throws ImplementationNotFoundException {
    IBusinessFunctionality instance = getiBusinessFunctionality();
    BusinessObject actual = instance.getComplexItem();
    actual.setText(resourceId);

    return BusinessObjectToSdkObject.convert(actual);
  }

  @POST
  @Path("/transformStuff")
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(MediaType.APPLICATION_JSON)
  public V1BusinessObject transformStuff(@PathParam("resourceId") String resourceId, V1BusinessObject actualV1) {
    if (actualV1!=null) {
      BusinessObject actual = BusinessObjectToSdkObject.convert(actualV1);

      actual.setText(String.format("transformed! resourceId is %s, origtext is %s", resourceId, actual.getText()));
      actual.setNumber(actual.getNumber() + 1);

      return BusinessObjectToSdkObject.convert(actual);
    }
    return null;
  }
}
