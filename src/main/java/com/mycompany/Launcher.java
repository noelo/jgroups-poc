package com.mycompany;

import org.apache.camel.CamelContext;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.main.Main;
import org.apache.camel.component.jgroups.JGroupsComponent;
import org.jgroups.Channel;
import org.jgroups.JChannel;

public class Launcher {
    /**
     * A main() so we can easily run these routing rules in our IDE
     */
    public static void main(String... args) throws Exception {
        Main main = new Main();
        CamelContext context = new DefaultCamelContext();
        JGroupsComponent myJgroups = new JGroupsComponent();
        Channel myChannel = new JChannel("kube-tcp-test.xml");
        myJgroups.setChannel(myChannel);
        
        context.addComponent("pocjgroups", myJgroups);
//        context.addRoutes(new CamelRouteConsumer());
//        context.addRoutes(new CamelRouteEmitter());
        context.addRoutes(new CamelHASingletonRoute());
        context.addRoutes(new JGroupsMasterRoute());
        context.start();	
        main.run(args);
    }
}
