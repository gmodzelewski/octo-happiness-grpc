package io.quarkus.grpc.examples.hello;

import static io.restassured.RestAssured.get;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
class HelloWorldEndpointTest {

    @Test
    void testHelloWorldServiceUsingBlockingStub() {
        String response = get("/hello/blocking/neo").asString();
        assertThat(response).startsWith("Hello neo");
    }

    @Test
    void testHelloWorldServiceUsingMutinyStub() {
        String response = get("/hello/mutiny/neo-mutiny").asString();
        assertThat(response).startsWith("Hello neo-mutiny");
    }

    @Test
    void testSayHello() {
        String response = get("/hello/testme").asString();
        assertThat(response).isEqualTo("hello");
    }

}
