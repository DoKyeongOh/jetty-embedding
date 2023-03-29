package org.example;

import org.eclipse.jetty.io.ssl.SslClientConnectionFactory;
import org.eclipse.jetty.server.*;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.util.ssl.SslContextFactory;

import java.security.KeyStore;

public class MyServer {

    Server server;

    public static void main(String[] args) throws Exception {
        System.out.println("hello world!");
        MyServer myServer = new MyServer();
        myServer.start();
    }

    public void start() throws Exception {
        ServletContextHandler context = new ServletContextHandler();
        context.setContextPath("/");
        context.addServlet(new ServletHolder(HelloServlet.class), "/hello");

        SslContextFactory factory = new SslContextFactory();
        factory.setKeyStorePath("/Users/dokyeongoh/IdeaProjects/Inspien/https-server-demo/dk-store");
        factory.setKeyStorePassword("123123");
        factory.setKeyManagerPassword("123123");
        factory.setTrustStorePassword("123123");

        server = new Server();

        ServerConnector httpConnector = new ServerConnector(server);
        httpConnector.setPort(8080);
        server.addConnector(httpConnector);

        ServerConnector httpsConnector = new ServerConnector(server, factory);
        httpsConnector.setPort(8081);
        server.addConnector(httpsConnector);

        server.setHandler(context);
        server.start();

        System.out.println(server.getState());
    }


}
