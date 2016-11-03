package com.mycompany;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

public class PayloadProcessor implements Processor {

	@Override
	public void process(Exchange arg0) throws Exception {
		String javaHome = System.getenv("HOSTNAME");
		String body = "<html><body>Route running on "+javaHome+" </body></html>";
		arg0.getOut().setBody(body);
	}
}
