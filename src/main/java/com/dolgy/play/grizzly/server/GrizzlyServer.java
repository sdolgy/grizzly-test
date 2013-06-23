package com.dolgy.play.grizzly.server;

import java.io.IOException;
import java.net.URI;

import javax.ws.rs.core.UriBuilder;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

import com.dolgy.play.grizzly.server.resources.UserResource;

public class GrizzlyServer {

    private static HttpServer webServer;

    public static final URI BASE_URI = getBaseURI();
    public static final String CONTENT = "JERSEY HTTPS EXAMPLE\n";

    private static URI getBaseURI() {
        return UriBuilder.fromUri("https://localhost/").port(getPort(4463)).build();
    }

    private static int getPort(int defaultPort) {
        final String port = System.getProperty("jersey.config.test.container.port");
        if (null != port) {
            try {
                return Integer.parseInt(port);
            } catch (NumberFormatException e) {
                System.out.println("Value of jersey.config.test.container.port property" +
                        " is not a valid positive integer [" + port + "]." +
                        " Reverting to default [" + defaultPort + "].");
            }
        }
        return defaultPort;
    }

    protected static void startServer() {

    	/*
        // Grizzly ssl configuration
        SSLContextConfigurator sslContext = new SSLContextConfigurator();

        // set up security context
        sslContext.setKeyStoreFile("./keystore_server"); // contains server keypair
        sslContext.setKeyStorePass("asdfgh");
        sslContext.setTrustStoreFile("./truststore_server"); // contains client certificate
        sslContext.setTrustStorePass("asdfgh");

		*/
    	
        ResourceConfig rc = new ResourceConfig();
        rc.registerClasses(UserResource.class);

        try {
        	webServer = GrizzlyHttpServerFactory.createHttpServer(getBaseURI(),rc,true);
//            webServer = GrizzlyHttpServerFactory.createHttpServer(
//                    getBaseURI(),
//                    rc,
//                    true,
//                    new SSLEngineConfigurator(sslContext).setClientMode(false).setNeedClientAuth(true)
//            );

            // start Grizzly embedded server //
            System.out.println("Jersey app started. Try out " + BASE_URI + "\nHit CTRL + C to stop it...");
            webServer.start();

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    protected static void stopServer() {
        webServer.stop();
    }

    @SuppressWarnings("ResultOfMethodCallIgnored")
    public static void main(String[] args) throws InterruptedException, IOException {
        startServer();

        System.in.read();
    }
}
