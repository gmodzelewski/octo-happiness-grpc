package io.quarkus.grpc.examples.hello;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("/")
public class RootEndpoint {

    @GET
    public String sayHello() {
        return "hello";
    }
}
