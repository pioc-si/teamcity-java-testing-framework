package com.example.teamcity.ui.elements;

import com.codeborne.selenide.SelenideElement;
import com.example.teamcity.ui.Selectors;
import lombok.Getter;

@Getter
public class BuildConfElement extends PageElement {

    private final SelenideElement buildRunButton;

    public BuildConfElement(SelenideElement element) {
        super(element);

        this.buildRunButton = findElement(Selectors.byDataTest("run-build"));
    }

}
