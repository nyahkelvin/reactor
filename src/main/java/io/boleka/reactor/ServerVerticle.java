/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.boleka.reactor;

import io.boleka.reactor.domain.Bid;
import io.boleka.reactor.domain.Loan;
import io.boleka.reactor.domain.Person;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.http.HttpServer;
import io.vertx.core.json.Json;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.BodyHandler;
import io.vertx.ext.web.handler.CorsHandler;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author kelvinashu
 */
public class ServerVerticle extends AbstractVerticle {

    private static final Logger LOGGER = LoggerFactory.getLogger(ServerVerticle.class);

    private static final String JSON_CONTENT_TYPE = "application/json";
    private static final String CONTENT_TYPE_TEXT = "content-type";
    private static final String LOAN_CONTENT = "Labore eum dicta. "
            + "Illo aut voluptates veniam hic cumque dolorum doloremque quis. "
            + "Reiciendis ut aut facere nostrum voluptatem sed laborum est "
            + "architecto.";

    // Store our product
    private Map<Integer, Person> personMap = new LinkedHashMap<>();

    //Store our loans
    List<Loan> loans = new ArrayList<>();

    public static void main(String[] args) {
        Vertx vertx = Vertx.vertx();
        vertx.deployVerticle(new ServerVerticle());
    }

    @Override
    public void start(Future<Void> startFuture) throws Exception {
        initLoan();
        startHTTPServer();
    }

    private Future<Void> startHTTPServer() {
        Future<Void> future = Future.future();
        HttpServer server = vertx.createHttpServer();

        Router router = Router.router(vertx);

        router.route().handler(CorsHandler.create("*")
                .allowedMethod(HttpMethod.GET)
                .allowedMethod(HttpMethod.POST)
                .allowedMethod(HttpMethod.PUT)
                .allowedMethod(HttpMethod.DELETE)
                .allowedMethod(HttpMethod.OPTIONS)
                .allowedHeader("Content-Type"));
        router.route().handler(BodyHandler.create());

        router.get("/").handler(this::homeRoute);
        router.get("/api/people").handler(this::getAll);
        router.post("/api/person").handler(this::addPerson);
        router.post("/api/bid").handler(this::addBid);

        server.requestHandler(router::accept).listen(8080, ar -> {
            if (ar.succeeded()) {
                LOGGER.info("Server started");
                future.complete();
            } else {
                LOGGER.error("Server failed to start");
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

    private void initLoan() {
        for (int i = 0; i < 3; i++) {
            List<Bid> bids = new ArrayList<>();
            Loan loan = new Loan();
            loan.setId(i);
            loan.setBorrower_avatar("https://s3.amazonaws.com/uifaces/faces/twitter/waghner/128.jpg");
            loan.setLoan_amount(i + 6800.00);
            loan.setBalance(i + 90.3);
            loan.setTime_left(i + 160);
            loan.setLoan_type("Payday loan");
            loan.setDescription(LOAN_CONTENT);
            loan.setLoan_interest(12.5);
            loan.setRisk_level("text-success");
            loan.setRisk_lable("label-success");
            loan.setRisk_progress("progress-bar-success");
            loan.setRisk_alert("hgreen");
            loan.setPayment_period(i + 1);
            loan.setLoan_progress(i + 20);
            loan.setCreated_date(new Date());

            for (int k = 0; k < 3; k++) {
                Bid bid = new Bid();
                bid.setBidder("@Conrad Haag");
                bid.setBidder_avatar("https://s3.amazonaws.com/uifaces/faces/twitter/andrewofficer/128.jpg");
                bid.setBid_date(new Date());
                bid.setBid_amount(9000.8);
                bid.setBid_interest(9.5);
                bid.setBid_rating("fa fa-star-half-o");
                bids.add(bid);
            }
            loan.setBids(bids);
            loans.add(loan);
        }

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

    private void addBid(RoutingContext context) {
        System.out.println("bid as string " + context.getBodyAsString());
        final Bid bid = Json.decodeValue(context.getBodyAsString(), Bid.class);
        LOGGER.info("Bid from service: " + bid);
        //personMap.put(person.getId(), person);
        context.response()
                .setStatusCode(201)
                .putHeader(CONTENT_TYPE_TEXT, JSON_CONTENT_TYPE)
                .end(Json.encodePrettily(bid));
    }

    private void homeRoute(RoutingContext context) {
        context.response().putHeader(CONTENT_TYPE_TEXT, JSON_CONTENT_TYPE).end(Json.encodePrettily(loans));
    }

}
