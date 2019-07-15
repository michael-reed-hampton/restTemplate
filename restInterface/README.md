# REST Interface
Defines the REST Interface, and implementation of the REST server.  This ties the other modules in this repository together.  It uses the business interface, with a configuration that defines what the implementation of the interface is.  It handles conversion of business data structures to API data structures.
## The REST Server
The REST server is implemented in `name.hampton.mike.Main`.  It is a trivial implementation, because the point of this example is to isolate the importance of the REST server itself from data structures, business interface and the business implementation.  The server created is a Glassfish Grizzly instance, with a base URI of [http://localhost:8080/myapp/](http://localhost:8080/myapp/).  It looks for REST resources in the package `"name.hampton.mike"`, and uses the resulting `org.glassfish.jersey.server.ResourceConfig` to configure ans start the Grizzly instance.
## The REST Resources
The endpoints for the API are defined using plain classes that are annotated with JAX-RS annotations.  They use the [`java.util.ServiceLoader`](https://docs.oracle.com/javase/7/docs/api/java/util/ServiceLoader.html) to load the business interface implementation for the `name.hampton.mike.IBusinessFunctionality` business interface.  The `ServiceLoader` is configured by placing a file into the `META-INF/services` directory with the fully qualified name of the interface (in this case `name.hampton.mike.IBusinessFunctionality`) that contains the fully qualified name of the class that implements the interface (in this case `name.hampton.mike.defaultImpl.BusinessFunctionality`).  The `ServiceLoader` allows us to do a simple form of dependency injection without additional frameworks. 
### MyResource
* **GET** Endpoint [http://localhost:8080/myapp/myresource](http://localhost:8080/myapp/myresource) - produces `text/plain`

This class defines the most basic of REST resources. It loads a `IBusinessFunctionality` instance and returns a string from this instance.
### MyResource2
* **GET** Endpoint [http://localhost:8080/myapp/myresource2/{resourceId}](http://localhost:8080/myapp/myresource2/{resourceId}) - depending on the HTTP `Accept` header value in the request, produces: 
  * `text/plain` - The returned string is the passed `resourceId` and the value of the fetched BusinessObject; `resourceId={resourceId}, item=simple`
  * `application/json` - the returned JSON is a serialized `V1BusinessObject`, which is the converted form of the internal `BusinessObject`;
  
        {
          "number": 0,
          "text": "{resourceId}"
        }
* **POST** Endpoint http://localhost:8080/myresource2/{resourceId}/transformStuff - produces `application/json`.  Takes in the body of a `V1BusinessObject` in `application/json` format and returns a transformed `V1BusinessObject`. 
### MyResource3
* **POST** Endpoint http://localhost:8080/myresource3/{resourceId}/duplicate/{count} - produces `application/json`.  Takes in the body of a `V1BusinessObject` in `application/json` format and returns a transformed collection of `V1BusinessObject`'s. 
* **GET** Endpoint http://localhost:8080/myresource3/{resourceId}/duplicate/{count} - produces `application/json`.  Returns a generated collection of `V1BusinessObject`'s. 