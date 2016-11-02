package com.mycompany;

import org.apache.camel.builder.RouteBuilder;
import static java.util.concurrent.TimeUnit.SECONDS;
import static org.apache.camel.component.jgroups.JGroupsExpressions.delayIfContextNotStarted;
import static org.apache.camel.component.jgroups.JGroupsFilters.dropNonCoordinatorViews;

public class JGroupsMasterRoute extends RouteBuilder {

	@Override
	public void configure() throws Exception {
		from("pocjgroups:noctest?enableViewMessages=true").filter(dropNonCoordinatorViews()).threads()
				.delay(delayIfContextNotStarted(SECONDS.toMillis(5)))
				.to("controlbus:route?routeId=HASinTimer&action=start&async=true");
	}
}