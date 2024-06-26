package tests.ui.pages.favorites;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import tests.ui.Selectors;
import tests.ui.elements.BuildElement;
import tests.ui.elements.ProjectElement;

import java.util.List;

import static com.codeborne.selenide.Selenide.elements;

public class ProjectsPage extends FavoritesPage {

    private static final String FAVORITE_PROJECTS_URL = "/favorite/projects";

    private ElementsCollection subprojects = elements(Selectors.byClass("Subproject__container--Px"));
    private ElementsCollection projectQuickButtons = elements(Selectors.byClass("quickLinks"));

    public ProjectsPage open() {
        Selenide.open(FAVORITE_PROJECTS_URL);
        waitUntilFavoritePageIsLoaded();
        return this;
    }

    public ProjectsPage() {
        waitUntilPageIsLoaded();
    }

    public List<ProjectElement> getSubprojects() {
        return generatePageElements(subprojects, ProjectElement::new);
    }

    public List<BuildElement> getProjectQuickButtons() {
        return generatePageElements(projectQuickButtons, BuildElement::new);
    }





}
