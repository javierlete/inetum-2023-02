package com.inetum.ejemplos.almacen.eventos;

import com.inetum.ejemplos.almacen.entidades.Producto;

public class ElementoSeleccionadoEvent implements DomainEvent {
    private Producto producto;

    public ElementoSeleccionadoEvent(Producto producto) {
        this.producto = producto;
    }
    
    public Producto getProducto() {
        return producto;
    }
    
}
