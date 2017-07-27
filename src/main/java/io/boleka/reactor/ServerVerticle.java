/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.boleka.reactor;

import io.boleka.reactor.domain.Person;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServer;
import io.vertx.core.json.Json;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.BodyHandler;

/**
 *
 * @author kelvinashu
 */
public class ServerVerticle extends AbstractVerticle {

    public static void main(String[] args) {
        Vertx vertx = Vertx.vertx();
        vertx.deployVerticle(new ServerVerticle());
    }

    @Override
    public void start(Future<Void> startFuture) throws Exception {
        startHTTPServer();
    }

    private Future<Void> startHTTPServer() {
        Future<Void> future = Future.future();
        HttpServer server = vertx.createHttpServer();

        Router router = Router.router(vertx);
        
        router.get("/").handler(this::homeRoute);
        router.post().handler(BodyHandler.create());

        server.requestHandler(router::accept).listen(8080, ar -> {
            if (ar.succeeded()) {
                System.out.println("Server started");
                future.complete();
            } else {
                System.out.println("Server failed to start");
                future.fail(ar.cause());
            }
        });

        return future;
    }

    private Future<Void> prepareDatabase() {
        Future<Void> future = Future.future();

        return future;
    }

    private void homeRoute(RoutingContext context) {
        context.response().putHeader("content-type", "application/json").end(Json.encodePrettily(new Person(23,"Kelvin Ashu")));
    }

}
