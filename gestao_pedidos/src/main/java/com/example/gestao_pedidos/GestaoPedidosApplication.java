package com.example.gestao_pedidos;

import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.availability.AvailabilityChangeEvent;
import org.springframework.boot.availability.ReadinessState;
import org.springframework.context.event.EventListener;
import org.springframework.core.env.Environment;

@SpringBootApplication
@EnableAutoConfiguration
@Slf4j
public class GestaoPedidosApplication {

	@Autowired
	private Environment env;
	public static void main(String[] args) {
		SpringApplication.run(GestaoPedidosApplication.class, args);
	}

	@EventListener
	public void onEvent(AvailabilityChangeEvent<ReadinessState> event) {
		if (ReadinessState.ACCEPTING_TRAFFIC == event.getState()) {
			log.info("🚀 Server ready at http://localhost:{}/api", env.getProperty("server.port"));
		}
	}
}
