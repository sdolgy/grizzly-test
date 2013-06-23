package com.dolgy.play.grizzly.server.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;

import org.glassfish.jersey.internal.util.Base64;

import com.dolgy.play.grizzly.server.GrizzlyServer;


@Path("/user")
public class UserResource {

    @GET
    public String get1(@Context HttpHeaders headers) {
        // you can get username form HttpHeaders
        System.out.println("Service: GET / User: " + getUser(headers));

        return GrizzlyServer.CONTENT;
    }

    private String getUser(HttpHeaders headers) {

        // this is a very minimalistic and "naive" code; if you plan to use it
        // add necessary checks (see org.glassfish.jersey.examples.httpsclientservergrizzly.authservergrizzly.SecurityFilter)

        String auth = headers.getRequestHeader("authorization").get(0);

        auth = auth.substring("Basic ".length());
        String[] values = new String(Base64.decodeAsString(auth)).split(":");

        // String username = values[0];
        // String password = values[1];

        return values[0];
    }
}