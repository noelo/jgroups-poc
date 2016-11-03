package com.mycompany;

import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;

public class CamelHASingletonRoute extends RouteBuilder {

	@Override
	public void configure() throws Exception {
		Processor myProcessor = new PayloadProcessor();
		from("jetty:http://0.0.0.0:8080/whodat").process(myProcessor).log("Sending message ${body}");
	}
}