package tests.ui.pages.favorites;

import com.codeborne.selenide.SelenideElement;
import tests.ui.Selectors;
import tests.ui.pages.Page;
import java.time.Duration;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.element;

public class FavoritesPage extends Page {
    private SelenideElement header = element(Selectors.byClass("ProjectPageHeader__title--ih"));

    public void waitUntilFavoritePageIsLoaded() {
        waitUntilPageIsLoaded();
        header.shouldBe(visible, Duration.ofMinutes(1));
    }
}