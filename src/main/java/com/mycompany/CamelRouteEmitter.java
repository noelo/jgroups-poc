package com.mycompany;

import org.apache.camel.builder.RouteBuilder;

public class CamelRouteEmitter extends RouteBuilder {

	@Override
	public void configure() throws Exception {
		from("timer://foo?fixedRate=true&period=1000").setBody().simple("THIS IS A TEST").log("Sending message ${body}").to("pocjgroups:noctest");
		
	}
}
