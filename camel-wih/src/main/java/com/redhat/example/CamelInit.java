package com.redhat.example;

import java.util.logging.Logger;

import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.ExchangeBuilder;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.http4.HttpComponent;
import org.apache.camel.component.log.LogComponent;
import org.apache.camel.component.rest.RestComponent;
import org.apache.camel.impl.DefaultCamelContext;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CamelInit implements InitializingBean {

    private static Logger logger = Logger.getLogger(CamelInit.class.getName());

    private CamelContext context = new DefaultCamelContext();
    @Autowired
    OrderRouteBuilder orderRouteBuilder;

    public void init() {
        logger.info("Camel init");
        try {
            context.addComponent("camel-log", new LogComponent());
            context.addComponent("camel-http", new HttpComponent());
            context.addComponent("camel-rest", new RestComponent());

            context.addRoutes(orderRouteBuilder);

            context.start();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ProducerTemplate createProducerTemplate() {
        return context.createProducerTemplate();
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        init();
    }

    public ExchangeBuilder getExchange() {
        return new ExchangeBuilder(context);
    }

    public void addRoutes(RouteBuilder routeBuilder) throws Exception {
        context.addRoutes(routeBuilder);
    }
}
