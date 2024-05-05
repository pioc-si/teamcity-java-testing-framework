package com.example.teamcity.api.requests.unchecked;

import com.example.teamcity.api.models.User;
import com.example.teamcity.api.requests.CrudInterface;
import com.example.teamcity.api.spec.Specifications;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class UncheckedProject implements CrudInterface {

    private static final String PROJECT_ENDPOINT = "/app/rest/projects";

    private User user;

    public UncheckedProject(User user) {
        this.user = user;
    }

    @Override
    public Response create(Object obj) {
        return given()
                .spec(Specifications.getSpec().authSpec(user))
                .body(obj)
                .post(PROJECT_ENDPOINT);
    }

    @Override
    public Object get(String id) {
        return null;
    }

    @Override
    public Object update(Object obj) {
        return null;
    }

    @Override
    public Response delete(String id) {

        return given()
                .spec(Specifications.getSpec().authSpec(user))
                .delete(PROJECT_ENDPOINT + "/id:" + id);
    }
}
