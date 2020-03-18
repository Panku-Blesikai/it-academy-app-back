package it.academy.app;

import org.springframework.data.annotation.Id;

public class Test {

    @Id
    private String id;

    private String name;

    public Test() {
    }

    public Test(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}