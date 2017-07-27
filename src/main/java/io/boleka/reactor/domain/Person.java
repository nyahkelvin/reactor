/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.boleka.reactor.domain;

import java.util.concurrent.atomic.AtomicInteger;

/**
 *
 * @author kelvinashu
 */
public class Person {

    private static final AtomicInteger COUNTER = new AtomicInteger();

    private final int id;
    private int age;
    private String name;

    public Person(int age, String name) {
        this.id = COUNTER.getAndIncrement();
        this.age = age;
        this.name = name;
    }

    public Person() {
        this.id = COUNTER.getAndIncrement();
    }

    public int getId() {
        return id;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
