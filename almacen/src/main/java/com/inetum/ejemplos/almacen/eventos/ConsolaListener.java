package com.inetum.ejemplos.almacen.eventos;

import org.springframework.stereotype.Component;

import com.inetum.ejemplos.almacen.entidades.Producto;

@Component
public class ConsolaListener implements DomainEventListener {

    @Override
    public void notify(DomainEvent de) {
        if(de instanceof ElementoSeleccionadoEvent ese) {
            System.out.println("MOSTRANDO PRODUCTO: " + ese.getProducto());
        }
    }
    
}
