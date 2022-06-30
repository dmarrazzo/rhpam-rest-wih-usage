package com.redhat.example;

import java.util.logging.Logger;

import org.apache.camel.Exchange;
import org.apache.camel.ProducerTemplate;
import org.jbpm.process.workitem.core.AbstractLogOrThrowWorkItemHandler;
import org.kie.api.runtime.process.WorkItem;
import org.kie.api.runtime.process.WorkItemManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("CamelWIH")
public class CamelWIH extends AbstractLogOrThrowWorkItemHandler {

    Logger logger = Logger.getLogger(CamelWIH.class.getName());

    private CamelInit camelInit;

    @Autowired
    private void init(CamelInit init) throws Exception {
        camelInit = init;

        camelInit.addRoutes(new OrderRouteBuilder());
    }

    @Override
    public void executeWorkItem(WorkItem workItem, WorkItemManager manager) {
        logger.info("executeWorkItem: " + workItem.getName());

        try {
            ProducerTemplate template = camelInit.createProducerTemplate();

            Exchange exchange = camelInit.getExchange()
                    .withBody(workItem.getParameter("Input"))
                    .build();
            exchange = template.send("direct:start", exchange);

            workItem.getResults().put("Output", exchange.getIn().getBody());

            manager.completeWorkItem(workItem.getId(), workItem.getResults());

        } catch (Exception e) {
            e.printStackTrace();
            manager.abortWorkItem(workItem.getId());
        }

    }

    @Override
    public void abortWorkItem(WorkItem workItem, WorkItemManager manager) {
        logger.info("abortWorkItem: " + workItem.getName());
        manager.abortWorkItem(workItem.getId());
    }

}
