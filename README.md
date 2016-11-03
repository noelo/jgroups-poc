## Simple POC to get JGroups running in Camel on Openshift.

Kube_ping is used to discover other pods in the namepspace. HA singleton route activation is done only on the master node.

S2I builds and base runtime is provided by fabric8 java s2i images https://github.com/fabric8io-images/s2i/tree/master/java

To deploy on openshift

* Build the app
```
oc new-app fabric8/s2i-java https://github.com/noelo/jgroups-poc.git
```

* Add the port for kube_ping to connect to by default this is 8888
```
    spec:
      containers:
        ports:
        - containerPort: 8778
          protocol: TCP
        - containerPort: 8888
          protocol: TCP
        - containerPort: 8080
          protocol: TCP
```

* Expose the http route
```
oc expose dc jgpoc --port=8080
```

* Add the view permissions to the default SA
```
oc policy add-role-to-user view system:serviceaccount:$(oc project -q):default -n $(oc project -q)
```

* Add the IPV4 stack for JGroups
```
oc env dc/jgpoc JAVA_OPTIONS=-Djava.net.preferIPv4Stack=true
```

* Add the OPENSHIFT_KUBE_PING_NAMESPACE environment variable via the downward API
```
oc edit dc jgpoc

    spec:
      containers:
      - env:
        - name: OPENSHIFT_KUBE_PING_NAMESPACE
          valueFrom:
            fieldRef:
              apiVersion: v1
              fieldPath: metadata.namespace
```

* Point a browser to the route 
```
http://route.namespace.osehost.com/whodat
```
