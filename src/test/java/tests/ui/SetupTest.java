package tests.ui;

import tests.ui.pages.StartUpPage;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Condition.text;

public class SetupTest extends BaseUiTest{

    @Test
    public void startUpTest() {
        new StartUpPage()
                .open()
                .setupTeamcityServer()
                .getHeader().shouldHave(text("Create Administrator Account"));
    }


}
