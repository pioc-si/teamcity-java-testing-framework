package tests.ui;

import com.codeborne.selenide.Configuration;
import tests.api.BaseTest;
import tests.api.config.Config;
import tests.api.models.User;
import tests.api.requests.checked.CheckedUser;
import tests.api.spec.Specifications;
import tests.ui.pages.LoginPage;
import org.testng.annotations.BeforeSuite;

public class BaseUiTest extends BaseTest {


    @BeforeSuite
    public void setUpUiTests() {
        Configuration.baseUrl = "http://" + Config.getProperty("host");
        Configuration.remote = Config.getProperty("remote");
        Configuration.reportsFolder = "target/surefire-reports";
        Configuration.downloadsFolder = "target/downloads";
        
        BrowserSettings.setup(Config.getProperty("browser"));
    }

    public void loginAsUser(User user) {
        new CheckedUser(Specifications.getSpec().superUserSpec())
                .create(user);
        new LoginPage().open().login(user);
    }

}
