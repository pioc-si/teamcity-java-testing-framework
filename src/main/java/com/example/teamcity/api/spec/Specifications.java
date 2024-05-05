package com.example.teamcity.api.spec;

import com.example.teamcity.api.config.Config;
import com.example.teamcity.api.models.User;


import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.http.ContentType;

public class Specifications {
    private static Specifications spec;

    private Specifications() {}

    public static Specifications getSpec() {
        if (spec == null) {
            spec = new Specifications();
        }
        return spec;
    }

    private RequestSpecBuilder reqBuilder() {
        var requestBuilder = new RequestSpecBuilder();
        requestBuilder.setBaseUri("http://" + Config.getProperty("host"));
        requestBuilder.addFilter(new RequestLoggingFilter());
        requestBuilder.addFilter(new ResponseLoggingFilter());
        requestBuilder.setContentType(ContentType.JSON);
        requestBuilder.setAccept(ContentType.JSON);
        return requestBuilder;
    }

    public io.restassured.specification.RequestSpecification unauthSpec() {
        var requestBuilder = reqBuilder();
        return requestBuilder.build();
    }

    public io.restassured.specification.RequestSpecification authSpec(User user) {
        var requestBuilder = reqBuilder();
        requestBuilder.setBaseUri("http://" + user.getUsername() + ":" + user.getPassword() + "@" + Config.getProperty("host"));
        return requestBuilder.build();
    }



}