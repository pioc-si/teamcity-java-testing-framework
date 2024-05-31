package com.example.teamcity.ui.elements;

import com.codeborne.selenide.SelenideElement;
import com.example.teamcity.ui.Selectors;
import lombok.Getter;

@Getter
public class ProjectElement {

    private SelenideElement icon;
    private SelenideElement element;
    private SelenideElement header;

    public ProjectElement(SelenideElement element) {
        this.element = element;
        this.header = element.find(Selectors.byDataTest("subproject"));
        this.icon = element.find("svg");
    }


}