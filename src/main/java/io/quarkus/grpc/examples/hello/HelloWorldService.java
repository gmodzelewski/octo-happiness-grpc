package io.quarkus.grpc.examples.hello;

import java.util.concurrent.atomic.AtomicInteger;
import javax.inject.Inject;

import examples.Greeter;
import examples.HelloReply;
import examples.HelloRequest;
import io.quarkus.grpc.GrpcService;
import io.smallrye.mutiny.Uni;

import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Tags;

@GrpcService
public class HelloWorldService implements Greeter {

    @Inject
    MeterRegistry registry;

    AtomicInteger counter = new AtomicInteger();

    @Override
    public Uni<HelloReply> sayHello(HelloRequest request) {
        int count = counter.incrementAndGet();
        String name = request.getName();
        
        registry.counter("greeting_counter", Tags.of("name", name)).increment();
        
        return Uni.createFrom().item("Hello " + name)
                .map(res -> HelloReply.newBuilder().setMessage(res).setCount(count).build());
    }
}
