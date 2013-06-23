package com.dolgy.play.grizzly.server;

import java.io.IOException;
import java.net.URI;

import javax.ws.rs.core.UriBuilder;

import org.glassfish.grizzly.http.server.HttpServer;

import com.dolgy.play.grizzly.server.config.RestApplication;
import com.sun.jersey.api.container.grizzly2.GrizzlyServerFactory;

public class GrizzlyServer {
    
    private static URI getBaseURI() {
        return UriBuilder.fromUri("http://localhost/").port(18888).build();
    }
    
    protected static HttpServer startServer() throws IOException {
        System.out.println("Starting grizzly...");
        RestApplication ra = new RestApplication();
        return GrizzlyServerFactory.createHttpServer(getBaseURI(),ra);
    }    
  
    public static void main(String[] args) throws InterruptedException, IOException {
    	HttpServer httpServer = startServer();
    	
    	
        System.in.read();
        
        httpServer.stop();
    }
}
