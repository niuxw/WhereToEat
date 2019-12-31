package com.example.accessingdatamysql.WebSocket;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;


/**
 * The Class WebSocketConfig.
 *
 * @author Vamsi Krishna Calpakkam
 */
@Configuration
public class WebSocketConfig {
	
	/**
	 * Server endpoint exporter.
	 *
	 * @return the server endpoint exporter
	 */
	@Bean
	public ServerEndpointExporter serverEndpointExporter(){
		return new ServerEndpointExporter();
	}
	
	/**
	 * Custom configurator.
	 *
	 * @return the custom configurator
	 */
	@Bean
	public CustomConfigurator customConfigurator() {
		return new CustomConfigurator();
	}
}
