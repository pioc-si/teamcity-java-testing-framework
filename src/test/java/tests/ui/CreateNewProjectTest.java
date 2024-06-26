package tests.ui;

import tests.api.generators.RandomData;
import tests.ui.pages.favorites.ProjectsPage;
import tests.ui.pages.admin.CreateNewProject;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Condition.text;

public class CreateNewProjectTest extends BaseUiTest {

    @Test
    public void authorizedUserShouldBeAbleCreateNewProject() {
        var testData = testDataStorage.addTestData();
        var url = "https://github.com/pioc-si/teamcity-java-testing-framework.git";

        loginAsUser(testData.getUser());

        new CreateNewProject()
                .open(testData.getProject().getParentProject().getLocator())
                .createProjectByUrl(url)
                .setupProject(testData.getProject().getName(), testData.getBuildType().getName());


        new ProjectsPage().open()
                .getSubprojects()
                .stream().reduce((first, second) -> second).get()
                .getHeader().shouldHave(text(testData.getProject().getName()));


    }

    @Test
    public void authorizedUserShouldNotBeAbleCreateNewProjectWith256SymbolsName() {
        var testData = testDataStorage.addTestData();
        var url = "https://github.com/pioc-si/teamcity-java-testing-framework.git";

        var longProjectName = testData.getProject();
        longProjectName.setName(RandomData.get256String());

        loginAsUser(testData.getUser());

        new CreateNewProject()
                .open(testData.getProject().getParentProject().getLocator())
                .createProjectByUrl(url)
                .setupProject(longProjectName.getName(), testData.getBuildType().getName());

        new ProjectsPage().open()
                .getSubprojects()
                .stream().reduce((first, second) -> second).get()
                .getHeader().shouldNotHave(text(testData.getProject().getName()));
        //should be error
    }

}
