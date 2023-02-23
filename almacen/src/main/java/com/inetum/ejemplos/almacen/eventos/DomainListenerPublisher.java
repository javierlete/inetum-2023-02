package com.inetum.ejemplos.almacen.eventos;

public interface DomainListenerPublisher {
    public void add(DomainEventListener del);
    public void publish(DomainEvent de);
}
