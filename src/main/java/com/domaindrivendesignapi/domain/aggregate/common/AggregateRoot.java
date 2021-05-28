package com.domaindrivendesignapi.domain.aggregate.common;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.domain.AbstractAggregateRoot;

import java.util.Collection;

public class AggregateRoot extends AbstractAggregateRoot {

    @JsonIgnore
    public Collection<Object> getDomainEvents(){

        return domainEvents();
    }
}