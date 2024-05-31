package com.example.teamcity.ui.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.example.teamcity.ui.Selectors;
import com.example.teamcity.ui.elements.ProjectElement;
import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.Selenide.elements;

public class ProjectsPage extends Page {

    private static final String FAVORITE_PROJECTS_URL = "/favorite/projects";

    private ElementsCollection subprojects = elements(Selectors.byClass("Subproject__container--Px"));

    public ProjectsPage open() {
        Selenide.open(FAVORITE_PROJECTS_URL);
        return this;
    }

    public List<ProjectElement> getSubprojects() {
        return generatePageElements(subprojects);
    }

    public List<ProjectElement> generatePageElements(ElementsCollection subprojects) {
        var elements = new ArrayList<ProjectElement>();

        subprojects.asDynamicIterable().forEach(webElement -> {
                    var pageElement = new ProjectElement(webElement);
                    elements.add(pageElement);
                }
        );
        return elements;
    }

}
