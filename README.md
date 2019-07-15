# restTemplate
This is a full rest server example.

## Set up and Run
1. Clone the repo
2. `cd restTemplate/restInterface` 
3. `mvn exec:java -Dexec.mainClass="name.hampton.mike.Main"`
    1.  You can pass the host IP if needed.
`mvn exec:java -Dexec.mainClass="name.hampton.mike.Main" -Dexec.args="192.168.0.51"`
4. use a rest client to test "http://localhost:8080/myapp/myresource" (or whatever the IP is)
5. To see the definition of services, look at "http://localhost:8080/myapp/application.wadl" with a web browser

You can use the following to test the example in Postman

[![Run in Postman](https://run.pstmn.io/button.svg)](https://app.getpostman.com/run-collection/5551a4f74acd13f5c662#?env%5BRest%20Template%20Env1%5D=W3sia2V5IjoicmVzb3VyY2VJZCIsInZhbHVlIjoiMTIzNCIsImVuYWJsZWQiOnRydWV9LHsia2V5IjoiY291bnQiLCJ2YWx1ZSI6IjMiLCJlbmFibGVkIjp0cnVlfV0=)

## Frameworks in use
* Jersey for a RESTful framework
* Grizzly as a HTTP framework
* Maven as a build framework 

## The modules in the repository

* [businessDataStructureBase](./businessDataStructureBase/README.md) : This module defines data structures used by internal services and business functionality.  These data structures can be exposed to consumers, but if they are there is an implicit tie between internal data structures and the REST API.  Changes made to the REST API will affect the internal system, and changes to the internal system will affect the external REST API.  There is a trade-off to doing this.  The functionality is more consistent, but deployment and backwards compatibility becomes more difficult. The `apiDataStructures` module will be used to create a set of objects that will be exposed to the consumer via REST.
* [businessInterfaceBase](./businessInterfaceBase/README.md) : This module defines the business interface.  This is not REST specific.  It is the interface that is used to interact with the _business_.  The interface defined in this is not exposed to consumers, but is used to access the business data.
* [businessImplementationBase](./businessImplementationBase/README.md) : This module defines the implementation of the business interface.  This is not REST specific.  It is a implementation of the interface only.  This is not exposed to consumers, it is used by the REST interface.
* [apiDataStructures](./apiDataStructures/README.md) : This module defines data structures that are exposed to outside consumers.  As noted above, it is possible to expose the underlying data structures directly, but this leads to difficulties with forwards and backwards compatibility.  By having a separate set of data objects for consumption outside the business, we are able to better support API changes.
* [restInterface](./restInterface/README.md) : This module holds the REST implementation to access the business logic.  It defines the REST interface, configures what implementation of the businessInterface is used, and handles converting the business data structures into API data structures.
