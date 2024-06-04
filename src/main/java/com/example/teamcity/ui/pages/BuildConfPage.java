package com.example.teamcity.ui.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.example.teamcity.ui.Selectors;
import com.example.teamcity.ui.elements.BuildConfElement;
import com.example.teamcity.ui.elements.BuildElement;
import com.example.teamcity.ui.pages.favorites.ProjectsPage;

import java.time.Duration;
import java.util.List;

import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.element;
import static com.codeborne.selenide.Selenide.elements;

public class BuildConfPage extends Page {

    private SelenideElement pageSuccessMarker = element(Selectors.byTitle("Success"));
    private ElementsCollection buildConfButtons = elements(Selectors.byDataTest("overview-header"));
    private SelenideElement runButton = element(Selectors.byDataTest("run-build"));

    public BuildConfPage () {
        runButton.shouldBe(visible, Duration.ofMinutes(1));
    }

    public List<BuildConfElement> getBuildConfButtons() {
        return generatePageElements(buildConfButtons, BuildConfElement::new);
    }

    public void waitUntilBuildIsSuccess() {
        pageSuccessMarker.shouldBe(visible, Duration.ofMinutes(1));
    }


}
