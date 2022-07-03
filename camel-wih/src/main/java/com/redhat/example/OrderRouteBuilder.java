package com.redhat.example;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import com.redhat.demo.OrderResponse;

@Component
public class OrderRouteBuilder extends RouteBuilder {

	@Autowired
	Environment environment;

    @Override
    public void configure() throws Exception {
        restConfiguration()
            .host(environment.getProperty("route.order.orderHost", "localhost:4010"))
            .bindingMode(RestBindingMode.json);

        from("direct:orderService").id("java-rest")
            .setHeader("Accept").constant("application/json")
            .to("rest:post:order")
            .unmarshal().json(JsonLibrary.Jackson, OrderResponse.class);
    }

}