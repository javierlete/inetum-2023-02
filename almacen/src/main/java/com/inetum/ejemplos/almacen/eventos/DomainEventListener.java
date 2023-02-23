package com.inetum.ejemplos.almacen.eventos;

public interface DomainEventListener {
    void notify(DomainEvent de);
}
