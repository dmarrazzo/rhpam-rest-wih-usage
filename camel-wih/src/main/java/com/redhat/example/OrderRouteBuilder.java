package com.redhat.example;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.beans.factory.annotation.Value;

import com.redhat.demo.OrderResponse;

public class OrderRouteBuilder extends RouteBuilder {

    @Value("${service.order.url}")
    private String serviceOrderUrl;

    public void setServiceOrderUrl(String serviceOrderUrl) {
        this.serviceOrderUrl = serviceOrderUrl;
    }

    @Override
    public void configure() throws Exception {
        restConfiguration()
            .host(serviceOrderUrl)
            .bindingMode(RestBindingMode.json);

        from("direct:start").id("java-rest")
            .setHeader("Accept").constant("application/json")
            .to("rest:post:order")
            .unmarshal().json(JsonLibrary.Jackson, OrderResponse.class);
    }

}