package com.mycompany;

import org.apache.camel.builder.RouteBuilder;

public class CamelRouteConsumer extends RouteBuilder {

	@Override
	public void configure() throws Exception {
		from("pocjgroups:noctest?enableViewMessages=true").log("Handling message ${headers.JGROUPS_CHANNEL_ADDRESS}");	
	}
}
