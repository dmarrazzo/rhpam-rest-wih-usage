package com.redhat.example;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.apache.camel.model.rest.RestBindingMode;

import com.redhat.demo.OrderResponse;

public class OrderRouteBuilder extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        restConfiguration()
            .host("localhost")
            .port("4010")
            .bindingMode(RestBindingMode.json);

        from("direct:start").id("java-rest")
            .setHeader("Accept").constant("application/json")
            .to("rest:post:order")
            .unmarshal().json(JsonLibrary.Jackson, OrderResponse.class)
            .to("log:DEBUG?showBody=true&showHeaders=true");
    }

}