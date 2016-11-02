package com.mycompany;

import org.apache.camel.builder.RouteBuilder;

public class CamelHASingletonRoute extends RouteBuilder {

	@Override
	public void configure() throws Exception {
		from("timer://foo?fixedRate=true&period=1000").routeId("HASinTimer").autoStartup(false).setBody().simple("Master Timer").log("Sending message ${body}");
	}
}