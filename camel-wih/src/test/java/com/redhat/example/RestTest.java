package com.redhat.example;

import org.apache.camel.Exchange;
import org.apache.camel.ProducerTemplate;

import com.redhat.demo.Order;

public class RestTest {

    public static void main(String[] args) {

        try {
            CamelInit camelInit = new CamelInit();
            camelInit.init();
            OrderRouteBuilder routeBuilder = new OrderRouteBuilder();
            routeBuilder.setServiceOrderUrl("localhost:4010");
            camelInit.addRoutes(routeBuilder);
            ProducerTemplate template = camelInit.createProducerTemplate();

            Order order = new Order();
            order.setItem("phone");
            order.setPrice(120.0);
            order.setQuantity(4L);
            
            Exchange exchange = camelInit.getExchange()
                    .withBody(order)
                    .build();
            template.send("direct:start", exchange);
            System.out.println(exchange.getOut().getBody());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
