Some CXF questions
==================

Build and execute
-----------------

This project is a java main class `Main.java` that can execute action that a simple static methods.

To build:

    $ mvn clean install

To execute:

    $ java -cp target/cxfQuestions-1.0-SNAPSHOT.jar Main <action>

Proxy SOCKS5 authentication
---------------------------

- Action: `proxySocksWithAuthent` : https://github.com/ypiel-talend/cxfQuestions/blob/9454bed34bb3136a0f28a9afaf535c95b9167b51/src/main/java/Main.java#L38

This cxf client can't authenticate to SOCKS5 proxy server. It works well
when authentication is not required.

To deploy a local SOCKS5 Dante server please follow this:
https://github.com/ypiel-talend/docker-dante

        $ java -cp target/cxfQuestions-1.0-SNAPSHOT.jar Main  proxySocksWithAuthent                                                             
        Sep 01, 2022 4:44:26 PM org.apache.cxf.phase.PhaseInterceptorChain doDefaultLogging                                                                                                                        
        WARNING: Interceptor for {https://gist.githubusercontent.com/ypiel-talend/c3277c902b92a8e266d1eb3c0b0e576a/raw/956d70aa753af6cfa2a3a710a9150deff70b47f6/geologists_ok.json}WebClient has thrown exception, 
        unwinding now                                                                                                                                                                                              
        org.apache.cxf.interceptor.Fault: Could not send Message.                                                                                                                                                  
                at org.apache.cxf.interceptor.MessageSenderInterceptor$MessageSenderEndingInterceptor.handleMessage(MessageSenderInterceptor.java:67)                                                              
                at org.apache.cxf.phase.PhaseInterceptorChain.doIntercept(PhaseInterceptorChain.java:307)
                at org.apache.cxf.jaxrs.client.AbstractClient.doRunInterceptorChain(AbstractClient.java:710)
                at org.apache.cxf.jaxrs.client.WebClient.doChainedInvocation(WebClient.java:1086)
                at org.apache.cxf.jaxrs.client.WebClient.doInvoke(WebClient.java:932)
                at org.apache.cxf.jaxrs.client.WebClient.doInvoke(WebClient.java:901)
                at org.apache.cxf.jaxrs.client.WebClient.invoke(WebClient.java:364)
                at Main.proxySocksWithAuthent(Main.java:54)
                at Main.main(Main.java:15)
        Caused by: java.net.SocketException: SocketException invoking https://gist.githubusercontent.com/ypiel-talend/c3277c902b92a8e266d1eb3c0b0e576a/raw/956d70aa753af6cfa2a3a710a9150deff70b47f6/geologists_ok.j
        son: SOCKS : authentication failed
