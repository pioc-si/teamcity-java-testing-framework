package tests.ui.pages;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import tests.ui.Selectors;
import lombok.Getter;

import java.time.Duration;
import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Selenide.element;

@Getter
public class StartUpPage extends Page {

    private SelenideElement acceptLicense = Selenide.element(Selectors.byId("accept"));
    private SelenideElement submitButton = element(Selectors.byType("submit"));
    private SelenideElement proceedButton = element(Selectors.byId("proceedButton"));
    private SelenideElement header = element(Selectors.byId("header"));


    public StartUpPage open() {
        Selenide.open("/");
        return this;
    }

    public StartUpPage setupTeamcityServer() {
        waitUntilPageIsLoaded();
        proceedButton.click();
        waitUntilPageIsLoaded();
        proceedButton.click();
        waitUntilPageIsLoaded();
        acceptLicense.shouldBe(enabled, Duration.ofMinutes(5));
        acceptLicense.scrollTo();
        acceptLicense.click();
        submitButton.click();

        return this;
    }

}
