package com.example.teamcity.api.requests.checked;


import com.example.teamcity.api.models.Project;
import com.example.teamcity.api.models.User;
import com.example.teamcity.api.requests.CrudInterface;
import com.example.teamcity.api.requests.unchecked.UncheckedProject;
import org.apache.http.HttpStatus;

public class CheckedProject implements CrudInterface {

    private final User user;

    public CheckedProject(User user) {
        this.user = user;
    }

    @Override
    public Project create(Object obj) {
        return new UncheckedProject(user).create(obj)
                .then().assertThat().statusCode(HttpStatus.SC_OK)
                .extract().as(Project.class);

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
    public String delete(String id) {
        return new UncheckedProject(user)
                .delete(id)
                .then().assertThat().statusCode(HttpStatus.SC_OK)
                .extract().asString();
    }
}
