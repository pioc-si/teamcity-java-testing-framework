package com.example.teamcity.api;

import com.example.teamcity.api.enums.Role;
import com.example.teamcity.api.generators.RandomData;
import com.example.teamcity.api.generators.TestDataGenerator;
import com.example.teamcity.api.requests.checked.CheckedProject;
import com.example.teamcity.api.requests.unchecked.UncheckedProject;
import com.example.teamcity.api.spec.Specifications;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;

public class CreateProjectTest extends BaseApiTest{

    @Test
    public void systemAdminCreateProject() {
        var testData = testDataStorage.addTestData();

        testData.getUser().setRoles(TestDataGenerator.generateRoles(Role.SYSTEM_ADMIN, "g"));

        checkedWithSuperUser.getUserRequest().create(testData.getUser());

        var project = new CheckedProject(Specifications.getSpec()
                .authSpec(testData.getUser()))
                .create(testData.getProject());

        softy.assertThat(project.getId()).isEqualTo(testData.getProject().getId());
    }

    @Test
    public void projectNameWith255symbols() {
        var testData = testDataStorage.addTestData();
        checkedWithSuperUser.getUserRequest().create(testData.getUser());

        var longProjectName = testData.getProject();
        longProjectName.setName(RandomData.get255String());


        var project = new CheckedProject(Specifications.getSpec()
                .authSpec(testData.getUser()))
                .create(longProjectName);

        softy.assertThat(project.getId()).isEqualTo(longProjectName.getId());

    }

    @Test
    public void projectNameWith256symbols() {
        var testData = testDataStorage.addTestData();
        checkedWithSuperUser.getUserRequest().create(testData.getUser());

        var longProjectName = testData.getProject();
        longProjectName.setName(RandomData.get256String());


        new UncheckedProject(Specifications.getSpec()
                .authSpec(testData.getUser()))
                .create(longProjectName)
                .then().assertThat().statusCode(HttpStatus.SC_BAD_REQUEST);

    }

    @Test
    public void projectNameWithoutsymbols() {
        var testData = testDataStorage.addTestData();
        checkedWithSuperUser.getUserRequest().create(testData.getUser());

        var longProjectName = testData.getProject();
        longProjectName.setName("");

        new UncheckedProject(Specifications.getSpec()
                .authSpec(testData.getUser()))
                .create(longProjectName)
                .then().assertThat().statusCode(HttpStatus.SC_BAD_REQUEST);

    }

    @Test
    public void projectNameIsAlreadyCreated() {
        var testData = testDataStorage.addTestData();
        checkedWithSuperUser.getUserRequest().create(testData.getUser());

        new CheckedProject(Specifications.getSpec().superUserSpec())
                .create(testData.getProject());

        new UncheckedProject(Specifications.getSpec().superUserSpec())
                .create(testData.getProject())
                .then().assertThat().statusCode(HttpStatus.SC_BAD_REQUEST);


    }

    @Test
    public void projectWithoutId() {
        var testData = testDataStorage.addTestData();
        checkedWithSuperUser.getUserRequest().create(testData.getUser());

        var longProjectName = testData.getProject();
        longProjectName.setId("");

        new UncheckedProject(Specifications.getSpec()
                .authSpec(testData.getUser()))
                .create(longProjectName)
                .then().assertThat().statusCode(HttpStatus.SC_INTERNAL_SERVER_ERROR);

    }

}
