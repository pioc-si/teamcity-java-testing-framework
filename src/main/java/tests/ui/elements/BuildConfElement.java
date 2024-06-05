package tests.ui.elements;

import com.codeborne.selenide.SelenideElement;
import tests.ui.Selectors;
import lombok.Getter;

@Getter
public class BuildConfElement extends PageElement {

    private final SelenideElement buildRunButton;

    public BuildConfElement(SelenideElement element) {
        super(element);

        this.buildRunButton = findElement(Selectors.byDataTest("run-build"));
    }

}
