package com.inetum.ejemplos.almacen;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.inetum.ejemplos.almacen.eventos.ConsolaListener;
import com.inetum.ejemplos.almacen.eventos.DomainListenerPublisher;
import com.inetum.ejemplos.almacen.eventos.LoguearListener;

@SpringBootApplication
public class AlmacenApplication implements CommandLineRunner {
	@Autowired
	private DomainListenerPublisher dlp;

	@Autowired
	private ConsolaListener cl;
	
	@Autowired
	private LoguearListener ll;

	public static void main(String[] args) {
		SpringApplication.run(AlmacenApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		dlp.add(cl);
		dlp.add(ll);
	}

}
