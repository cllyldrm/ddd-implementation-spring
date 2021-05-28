package com.domaindrivendesignapi.domain.aggregate.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public class DomainEventPublisher {
    @Autowired
    ApplicationEventPublisher applicationEventPublisher;

    public void publish(AggregateRoot domainObject){

        Collection<Object> events = domainObject.getDomainEvents();

        for (Object e : events) {

            applicationEventPublisher.publishEvent(e);
        }
    }
}
