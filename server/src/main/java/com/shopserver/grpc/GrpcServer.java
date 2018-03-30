package com.shopserver.grpc;


import io.grpc.Server;
import io.grpc.ServerBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class GrpcServer {
    private int port = 42425;
    private Server server;


    @Autowired
    private ProductImpl product;

    @PostConstruct
    public void init() throws Exception {
        start();
        blockUntilShutdown();
        stop();
    }

    private void start() throws Exception {
        server = ServerBuilder.forPort(port)
                .addService(product)
                .build()
                .start();
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.err.println("*** JVM is shutting down. Turning off grpc server as well ***");
            GrpcServer.this.stop();
            System.err.println("*** shutdown complete ***");
        }));
    }

    private void stop() {
        if (server != null) {
            server.shutdown();
        }
    }


    private void blockUntilShutdown() throws InterruptedException {
        if (server != null) {
            server.awaitTermination();
        }
    }


}
