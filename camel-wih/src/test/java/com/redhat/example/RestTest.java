package com.redhat.example;

import java.util.concurrent.TimeUnit;

import org.apache.camel.Exchange;
import org.apache.camel.ProducerTemplate;

import com.redhat.demo.Order;
import com.redhat.demo.OrderResponse;

public class RestTest {

    public static void main(String[] args) {

        try {
            CamelInit camelInit = new CamelInit();
            camelInit.init();
            camelInit.addRoutes(new OrderRouteBuilder());
            ProducerTemplate template = camelInit.createProducerTemplate();

            Order order = new Order();
            order.setItem("phone");
            order.setPrice(120.0);
            order.setQuantity(4L);
            
            Exchange exchange = camelInit.getExchange()
                    .withBody(order)
                    .build();
            template.send("direct:start", exchange);
            System.out.println(exchange.getIn().getBody());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
