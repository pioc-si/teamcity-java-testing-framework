package com.example.teamcity.api;

import com.example.teamcity.api.enums.Role;
import com.example.teamcity.api.generators.RandomData;
import com.example.teamcity.api.generators.TestDataGenerator;
import com.example.teamcity.api.requests.checked.CheckedBuildConfig;
import com.example.teamcity.api.requests.checked.CheckedUser;
import com.example.teamcity.api.requests.checked.CheckedProject;
import com.example.teamcity.api.requests.unchecked.UncheckedBuildConfig;
import com.example.teamcity.api.spec.Specifications;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;

public class BuildConfigurationTest extends BaseApiTest{

    @Test
    public void buildConfigurationTest(){

        var testData = testDataStorage.addTestData();

        new CheckedUser(Specifications.getSpec().superUserSpec())
                .create(testData.getUser());

        var project = new CheckedProject(Specifications.getSpec()
                .authSpec(testData.getUser()))
                .create(testData.getProject());

        softy.assertThat(project.getId()).isEqualTo(testData.getProject().getId());
    }

    @Test
    public void createBuildConfigurationTest(){
        var testData = testDataStorage.addTestData();
        testData.getUser().setRoles(TestDataGenerator.generateRoles(Role.SYSTEM_ADMIN, "g"));
        checkedWithSuperUser.getUserRequest().create(testData.getUser());

        var project = new CheckedProject(Specifications.getSpec()
                .authSpec(testData.getUser()))
                .create(testData.getProject());

        softy.assertThat(project.getId()).isEqualTo(testData.getProject().getId());

        var buildConfig = new CheckedBuildConfig(Specifications.getSpec()
                .authSpec(testData.getUser()))
                .create(testData.getBuildType());

        softy.assertThat(buildConfig.getId()).isEqualTo(testData.getBuildType().getId());
    }

    @Test
    public void createBuildConfiguration255SymbolsNameTest(){
        var testData = testDataStorage.addTestData();
        testData.getUser().setRoles(TestDataGenerator.generateRoles(Role.SYSTEM_ADMIN, "g"));
        checkedWithSuperUser.getUserRequest().create(testData.getUser());

        var project = new CheckedProject(Specifications.getSpec()
                .authSpec(testData.getUser()))
                .create(testData.getProject());

        var longBuildName = testData.getBuildType();
        longBuildName.setName(RandomData.get255String());

        var buildConfig = new CheckedBuildConfig(Specifications.getSpec()
                .authSpec(testData.getUser()))
                .create(longBuildName);

        softy.assertThat(buildConfig.getId()).isEqualTo(testData.getBuildType().getId());
    }

    @Test
    public void createBuildConfiguration256SymbolsNameTest(){
        var testData = testDataStorage.addTestData();
        testData.getUser().setRoles(TestDataGenerator.generateRoles(Role.SYSTEM_ADMIN, "g"));
        checkedWithSuperUser.getUserRequest().create(testData.getUser());

        var project = new CheckedProject(Specifications.getSpec()
                .authSpec(testData.getUser()))
                .create(testData.getProject());

        softy.assertThat(project.getId()).isEqualTo(testData.getProject().getId());

        var longBuildName = testData.getBuildType();
        longBuildName.setName(RandomData.get256String());

        new UncheckedBuildConfig(Specifications.getSpec()
                .authSpec(testData.getUser()))
                .create(longBuildName)
                .then().assertThat().statusCode(HttpStatus.SC_BAD_REQUEST);
        //build creates with 256 symbols name - need to ask, bc the answer:
        // Expected status code <400> but was <200>.
    }

    @Test
    public void createBuildConfigurationWithoutNameTest(){
        var testData = testDataStorage.addTestData();
        testData.getUser().setRoles(TestDataGenerator.generateRoles(Role.SYSTEM_ADMIN, "g"));
        checkedWithSuperUser.getUserRequest().create(testData.getUser());

        var project = new CheckedProject(Specifications.getSpec()
                .authSpec(testData.getUser()))
                .create(testData.getProject());

        softy.assertThat(project.getId()).isEqualTo(testData.getProject().getId());

        var noBuildName = testData.getBuildType();
        noBuildName.setName(null);

        new UncheckedBuildConfig(Specifications.getSpec()
                .authSpec(testData.getUser()))
                .create(noBuildName)
                .then().assertThat().statusCode(HttpStatus.SC_BAD_REQUEST);

    }

    @Test
    public void createBuildConfigurationWithoutIdTest(){
        var testData = testDataStorage.addTestData();
        testData.getUser().setRoles(TestDataGenerator.generateRoles(Role.SYSTEM_ADMIN, "g"));
        checkedWithSuperUser.getUserRequest().create(testData.getUser());

        var project = new CheckedProject(Specifications.getSpec()
                .authSpec(testData.getUser()))
                .create(testData.getProject());

        softy.assertThat(project.getId()).isEqualTo(testData.getProject().getId());

        var noBuildId = testData.getBuildType();
        noBuildId.setId("");

        new UncheckedBuildConfig(Specifications.getSpec()
                .authSpec(testData.getUser()))
                .create(noBuildId)
                .then().assertThat().statusCode(HttpStatus.SC_BAD_REQUEST);
        //Expected status code <400> but was <500>.

    }


    @Test
    public void createBuildConfigurationWithoutProjectTest(){
        var testData = testDataStorage.addTestData();
        testData.getUser().setRoles(TestDataGenerator.generateRoles(Role.SYSTEM_ADMIN, "g"));
        checkedWithSuperUser.getUserRequest().create(testData.getUser());

        var longBuildName = testData.getBuildType();
        longBuildName.setProject(null);

        new UncheckedBuildConfig(Specifications.getSpec()
                .authSpec(testData.getUser()))
                .create(longBuildName)
                .then().assertThat().statusCode(HttpStatus.SC_BAD_REQUEST);

    }

    @Test
    public void createBuildConfigurationAlreadyCreatedTest(){
        var testData = testDataStorage.addTestData();
        testData.getUser().setRoles(TestDataGenerator.generateRoles(Role.SYSTEM_ADMIN, "g"));
        checkedWithSuperUser.getUserRequest().create(testData.getUser());

        var project = new CheckedProject(Specifications.getSpec()
                .authSpec(testData.getUser()))
                .create(testData.getProject());

        softy.assertThat(project.getId()).isEqualTo(testData.getProject().getId());

        var buildConfig = new CheckedBuildConfig(Specifications.getSpec()
                .authSpec(testData.getUser()))
                .create(testData.getBuildType());

        softy.assertThat(buildConfig.getId()).isEqualTo(testData.getBuildType().getId());

        new UncheckedBuildConfig(Specifications.getSpec()
                .authSpec(testData.getUser()))
                .create(testData.getBuildType())
                .then().assertThat().statusCode(HttpStatus.SC_BAD_REQUEST);
    }


}