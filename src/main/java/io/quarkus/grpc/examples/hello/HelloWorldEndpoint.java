package io.quarkus.grpc.examples.hello;

import examples.Greeter;
import examples.GreeterGrpc;
import examples.HelloReply;
import examples.HelloRequest;
import io.quarkus.grpc.GrpcClient;
import io.smallrye.mutiny.Uni;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("/hello")
public class HelloWorldEndpoint {

    @GrpcClient("hello")
    GreeterGrpc.GreeterBlockingStub blockingHelloService;

    @GrpcClient("hello")
    Greeter helloService;

    @GET
    @Path("/testme")
    public String sayHello() {
        return "hello";
    }

    @GET
    @Path("/blocking/{name}")
    public String helloBlocking(String name) {
        HelloReply reply = blockingHelloService.sayHello(HelloRequest.newBuilder().setName(name).build());
        return generateResponse(reply);

    }

    @GET
    @Path("/mutiny/{name}")
    public Uni<String> helloMutiny(String name) {
        return helloService.sayHello(HelloRequest.newBuilder().setName(name).build())
                .onItem().transform(this::generateResponse);
    }

    public String generateResponse(HelloReply reply) {
        return String.format("%s! HelloWorldService has been called %d number of times.", reply.getMessage(), reply.getCount());
    }
}
