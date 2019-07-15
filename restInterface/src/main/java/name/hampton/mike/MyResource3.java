package name.hampton.mike;

import name.hampton.mike.internal.BusinessObject;
import name.hampton.mike.sdk.V1BusinessObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
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
@Path("myresource3/{resourceId}")
public class MyResource3 {
	
	private Logger logger = Logger.getLogger(this.getClass().getName());

  /**
   * Load the first implementation found.
   *
   * @return an IBusinessFunctionality instance
   * @throws ImplementationNotFoundException - if no IBusinessFunctionality is configured.
   * @see ServiceLoader#load(java.lang.Class)
   */
  private IBusinessFunctionality getIBusinessFunctionality() throws ImplementationNotFoundException {
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

  @POST
  @Path("/duplicate/{count}")
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(MediaType.APPLICATION_JSON)
  public List<V1BusinessObject> duplicate(@PathParam("resourceId") String resourceId, @PathParam("count") int count, V1BusinessObject apiObject) {
    List<V1BusinessObject> items = null;
    if (count > 0) {
      items = duplicateItem(resourceId, count, BusinessObjectToSdkObject.convert(apiObject));
    }
    return items;
  }

  @GET
  @Path("/duplicate/{count}")
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(MediaType.APPLICATION_JSON)
  public List<V1BusinessObject> duplicate(@PathParam("resourceId") String resourceId, @PathParam("count") int count) throws ImplementationNotFoundException {
    List<V1BusinessObject> items = null;
    if (count > 0) {
      IBusinessFunctionality businessFunctionality = getIBusinessFunctionality();
      items = duplicateItem(resourceId, count, businessFunctionality.getComplexItem());
    }
    return items;
  }

  private List<V1BusinessObject> duplicateItem(String resourceId, int count, BusinessObject internalDO) {
    List<V1BusinessObject> items = null;
    if (count > 0) {
      String origText = internalDO.getText();
      items = new ArrayList<>(count);
      for (int cnt = 0; cnt < count; cnt++) {
        /*
        Note that depending on the implementation, the internalDO may be the same object.
        The following works *in spite of that*, because of the call to convert the business item to
        an item that is intended for consumption.  The only call that really has any effect is the
        call to 'setNumber' which changes the number based on the current value.
         */
        internalDO.setNumber(internalDO.getNumber() + 1);
        internalDO.setText(String.format("resourceId is %s, text is %s, myNumber is %d", resourceId, origText, internalDO.getNumber()));
        items.add(BusinessObjectToSdkObject.convert(internalDO));
      }
    }
    return items;
  }
}
