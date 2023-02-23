package com.inetum.ejemplos.almacen.eventos;

import org.springframework.stereotype.Component;

import com.inetum.ejemplos.almacen.entidades.Producto;

import lombok.extern.java.Log;

@Log
@Component
public class LoguearListener implements DomainEventListener {

    @Override
    public void notify(DomainEvent de) {
        if(de instanceof ElementoSeleccionadoEvent ese) {
            log.info("LOGUEANDO PRODUCTO: " + ese.getProducto());
        }
    }
    
}
