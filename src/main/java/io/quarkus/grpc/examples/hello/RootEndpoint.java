package io.quarkus.grpc.examples.hello;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Tags;
import javax.inject.Inject;

@Path("/")
public class RootEndpoint {

    @Inject
    MeterRegistry registry;

    @GET
    public String sayHello() {
        registry.counter("greeting_counter", Tags.of("root", "root")).increment();
        return "hello";
    }
}
