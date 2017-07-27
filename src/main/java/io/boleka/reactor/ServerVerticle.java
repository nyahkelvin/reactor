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
import java.util.LinkedHashMap;
import java.util.Map;

/**
 *
 * @author kelvinashu
 */
public class ServerVerticle extends AbstractVerticle {

    private static final String JSON_CONTENT_TYPE = "application/json";
    private static final String CONTENT_TYPE_TEXT = "content-type";

    // Store our product
    private Map<Integer, Person> personMap = new LinkedHashMap<>();

    public static void main(String[] args) {
        Vertx vertx = Vertx.vertx();
        vertx.deployVerticle(new ServerVerticle());
    }

    @Override
    public void start(Future<Void> startFuture) throws Exception {
        initDB();
        startHTTPServer();
    }

    private Future<Void> startHTTPServer() {
        Future<Void> future = Future.future();
        HttpServer server = vertx.createHttpServer();

        Router router = Router.router(vertx);

        router.route().handler(BodyHandler.create());
        router.get("/").handler(this::homeRoute);
        router.get("/api/people").handler(this::getAll);
        router.post("/api/person").handler(this::addPerson);

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

    private void initDB() {
        Person p1 = new Person(20, "John Doe");
        Person p2 = new Person(23, "Marry Doe");
        personMap.put(p1.getId(), p1);
        personMap.put(p2.getId(), p2);
    }

    private void getAll(RoutingContext context) {
        context.response()
                .putHeader(CONTENT_TYPE_TEXT, JSON_CONTENT_TYPE)
                .end(Json.encodePrettily(personMap));
    }

    private void addPerson(RoutingContext context) {
        System.out.println("as string " + context.getBodyAsString());
        final Person person = Json.decodeValue(context.getBodyAsString(), Person.class);
        System.out.println("Person: " + person);
        personMap.put(person.getId(), person);
        context.response()
                .setStatusCode(201)
                .putHeader(CONTENT_TYPE_TEXT, JSON_CONTENT_TYPE).end(Json.encodePrettily(person));
    }

    private void homeRoute(RoutingContext context) {
        context.response().putHeader(CONTENT_TYPE_TEXT, JSON_CONTENT_TYPE).end(Json.encodePrettily(new Person(90, "Kelvin Ashu")));
    }

}
