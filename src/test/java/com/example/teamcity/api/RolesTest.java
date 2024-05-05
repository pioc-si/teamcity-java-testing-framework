package com.example.teamcity.api;

import com.example.teamcity.api.requests.unchecked.UncheckedProject;
import com.example.teamcity.api.spec.Specifications;
import org.apache.http.HttpStatus;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;

public class RolesTest extends BaseApiTest{

    @Test
    public void unauthorizedUser() {
        new UncheckedProject(Specifications.getSpec().unauthSpec())
                .create(testData.getProject())
                .then().assertThat().statusCode(HttpStatus.SC_UNAUTHORIZED)
                .body(Matchers.equalTo("Authentication required\n" +
                        "To login manually go to \"/login.html\" page"));

        new UncheckedProject(Specifications.getSpec().authSpec(testData.getUser()))
                .get(testData.getProject().getId())
                .then().assertThat().statusCode(HttpStatus.SC_NOT_FOUND)
                .body(Matchers.containsString("No project found by locator 'count:1,id:" +
                        testData.getProject().getId() + "'"));



    }

}
