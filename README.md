# restTemplate
A full rest example.

To run the rest server, go to 'restInterface' and run the main class.  You can pass the host IP if needed.
mvn exec:java -Dexec.mainClass="name.hampton.mike.Main" -Dexec.args="192.168.0.51"

To test the interface, use a rest client to test "http://localhost:8080/myapp/myresource" (or whatever the IP is)

To see the definition of services, look at "http://localhost:8080/myapp/application.wadl"

[![Run in Postman](https://run.pstmn.io/button.svg)](https://app.getpostman.com/run-collection/8d902c1e93b1212746be#?env%5BRest%20Template%20Env1%5D=W3sia2V5IjoicmVzb3VyY2VJZCIsInZhbHVlIjoiMTIzNCIsImVuYWJsZWQiOnRydWV9XQ==)
