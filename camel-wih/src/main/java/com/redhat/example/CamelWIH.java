package com.redhat.example;

import java.util.logging.Logger;

import org.jbpm.process.workitem.core.AbstractLogOrThrowWorkItemHandler;
import org.kie.api.runtime.process.WorkItem;
import org.kie.api.runtime.process.WorkItemManager;
import org.springframework.stereotype.Component;

@Component("CamelWIH")
public class CamelWIH extends AbstractLogOrThrowWorkItemHandler {

    Logger logger = Logger.getLogger(CamelWIH.class.getName());

    @Override
    public void executeWorkItem(WorkItem workItem, WorkItemManager manager) {
        logger.info("executeWorkItem: " + workItem.getName());
        manager.completeWorkItem(workItem.getId(), workItem.getResults());
    }

    @Override
    public void abortWorkItem(WorkItem workItem, WorkItemManager manager) {
        logger.info("abortWorkItem: " + workItem.getName());
        manager.abortWorkItem(workItem.getId());
    }

}
