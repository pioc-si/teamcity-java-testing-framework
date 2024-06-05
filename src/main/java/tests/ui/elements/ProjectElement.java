package tests.ui.elements;

import com.codeborne.selenide.SelenideElement;
import tests.ui.Selectors;
import lombok.Getter;

@Getter
public class ProjectElement extends PageElement {

    private final SelenideElement icon;
    private final SelenideElement header;

    public ProjectElement(SelenideElement element) {
        super(element);

        this.header = findElement(Selectors.byDataTest("subproject"));
        this.icon = findElement("svg");
    }


}