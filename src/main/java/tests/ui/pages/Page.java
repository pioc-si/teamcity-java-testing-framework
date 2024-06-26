package tests.ui.pages;


import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import tests.ui.Selectors;
import tests.ui.elements.PageElement;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.element;


public class Page {

    protected SelenideElement submitButton = element(Selectors.byType("submit"));
    private SelenideElement savingWaitingMarker = element(Selectors.byId("saving"));
    private SelenideElement pageWaitingMarker = element(Selectors.byDataTest("ring-loader"));

    public void submit() {
        submitButton.click();
        waitUntilDataIsSaved();
    }

    public void waitUntilPageIsLoaded() {
        pageWaitingMarker.shouldNotBe(visible, Duration.ofMinutes(1));
    }

    public void waitUntilDataIsSaved() {
        savingWaitingMarker.shouldNotBe(visible, Duration.ofMinutes(1));
    }



    public  <T extends PageElement> List<T> generatePageElements(
            ElementsCollection collection,
            Function<SelenideElement, T> creator) {
        var elements = new ArrayList<T>();
        collection.asDynamicIterable().forEach(webElement ->
                elements.add(creator.apply(webElement)));
        return elements;
    }


}
