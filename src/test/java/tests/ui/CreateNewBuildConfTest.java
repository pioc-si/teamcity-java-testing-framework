package tests.ui;

import tests.ui.pages.BuildConfPage;
import tests.ui.pages.admin.CreateNewProject;
import tests.ui.pages.favorites.ProjectsPage;
import org.testng.annotations.Test;


public class CreateNewBuildConfTest extends BaseUiTest{

    @Test
    public void authorizedUserShouldBeAbleCreateNewBuild() {
        var testData = testDataStorage.addTestData();
        var url = "https://github.com/pioc-si/teamcity-java-testing-framework.git";

        loginAsUser(testData.getUser());

        new CreateNewProject()
                .open(testData.getProject().getParentProject().getLocator())
                .createProjectByUrl(url)
                .setupProject(testData.getProject().getName(), testData.getBuildType().getName());

        new ProjectsPage()
                .getProjectQuickButtons()
                .stream().reduce((first, second) -> second).get()
                .getLinkToBuildConf().click();

        new BuildConfPage()
                .getBuildConfButtons()
                .stream().reduce((first, second) -> second).get()
                .getBuildRunButton().click();


        new BuildConfPage().waitUntilBuildIsSuccess();

    }




}
