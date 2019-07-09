package name.hampton.mike;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

import java.io.IOException;
import java.net.URI;
import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Main class.
 *
 */
public class Main {
    // Base URI the Grizzly HTTP server will listen on
    static final String BASE_URI = "http://localhost:8080/myapp/";

    /**
     * Starts Grizzly HTTP server exposing JAX-RS resources defined in this application.
     * @return Grizzly HTTP server.
     */
    static HttpServer startServer() {
        // create a resource config that scans for JAX-RS resources and providers
        // in name.hampton.mike package
        final ResourceConfig rc = new ResourceConfig().packages("name.hampton.mike");

        // create and start a new instance of grizzly http server
        // exposing the Jersey application at BASE_URI
        return GrizzlyHttpServerFactory.createHttpServer(URI.create(BASE_URI), rc);
    }

    /**
     * Main method.
     * @param args - no arguments
     * @throws IOException - if there is a problem starting the server
     */
    public static void main(String[] args) throws IOException {
      Logger l = Logger.getLogger("org.glassfish.grizzly.http.server.HttpHandler");
      l.setLevel(Level.FINE);
      l.setUseParentHandlers(false);
      ConsoleHandler ch = new ConsoleHandler();
      ch.setLevel(Level.ALL);
      l.addHandler(ch);

      final HttpServer server = startServer();
        System.out.println(String.format("Jersey app started with WADL available at "
                + "%sapplication.wadl\nHit enter to stop it...", BASE_URI));
      int ignored = System.in.read();
        server.shutdownNow();
      System.out.format("Shut down on keystroke = %d", ignored);
    }
}

