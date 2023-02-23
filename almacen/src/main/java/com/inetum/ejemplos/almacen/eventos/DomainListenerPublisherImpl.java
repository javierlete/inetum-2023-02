package com.inetum.ejemplos.almacen.eventos;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class DomainListenerPublisherImpl implements DomainListenerPublisher {

    private List<DomainEventListener> listeners = new ArrayList<>();
    @Override
    public void add(DomainEventListener del) {
        listeners.add(del);
    }

    @Override
    public void publish(DomainEvent de) {
        for(DomainEventListener del: listeners) {
            del.notify(de);
        }
    }
    
}
