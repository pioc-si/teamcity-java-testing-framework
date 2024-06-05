package tests.ui.elements;

import com.codeborne.selenide.SelenideElement;
import tests.ui.Selectors;
import lombok.Getter;

@Getter
public class BuildElement extends PageElement {

    private final SelenideElement linkToBuildConf;

    public BuildElement(SelenideElement element) {
        super(element);

        this.linkToBuildConf = findElement(Selectors.byId("homeLink"));
    }

}
