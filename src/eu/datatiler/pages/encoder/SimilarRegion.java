package eu.datatiler.pages.encoder;

import com.qatestlab.base.BasePage;
import com.qatestlab.base.Locator;
import com.qatestlab.base.LocatorTypes;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Petro on 07.03.2016.
 */
public class SimilarRegion extends BasePage {

    private Locator similarShowBlock = new Locator(LocatorTypes.XPATH,
            "//div[div[text()='%s']  and ./md-content/ul[contains(@class, 'entries')]]");

    private Locator entrySimilarShowBlock = new Locator(LocatorTypes.XPATH,
            "//ul[contains(@class, 'entries')]/li[%s]/div[contains(@class, 'name')]");

    private Locator disabledEntriesSimilarShowBlock = new Locator(LocatorTypes.XPATH,
            "//ul[contains(@class, 'entries')]/li[contains(@class, 'disabled')]/div[contains(@class, 'name')]");

    private Locator listEntriesSimilarShowBlock = new Locator(LocatorTypes.XPATH,
            "//ul[contains(@class, 'entries')]/li/div[contains(@class, 'name')]");

    private Locator confirmRemovalButton = new Locator(LocatorTypes.XPATH,
            "//div[contains(@class, 'deleteBtn')]/button");

    public void waitForLoad(String nameMergedGroup) {
        waitForVisibility("Wait for 'Similar Show' blocj to be visible", similarShowBlock, nameMergedGroup);
    }

    public boolean isSimilarShowBlockVisible(String nameMergedGroup) {
        return isPresent("Similar block is present", similarShowBlock, nameMergedGroup);
    }

    public void selectEntrySimilarShowBlock(int idEntry) {
        waitToBeClickable("Wait for entry from similar show block to be clickable", entrySimilarShowBlock, idEntry);
        click("Click on entry from similar show block", entrySimilarShowBlock, idEntry);
    }

    public List<String> getDisabledEntriesSimilarShowBlock() {
        List<String> nameEntries = new ArrayList<>();

        for (WebElement webElement : getElements(
                "Get disabled entries similar show block", disabledEntriesSimilarShowBlock)) {
            nameEntries.add(webElement.getText());
        }

        return nameEntries;
    }

    public List<String> getEntriesSimilarShowBlock() {
        List<String> nameEntries = new ArrayList<>();

        for (WebElement webElement : getElements("Get entries similar show block", listEntriesSimilarShowBlock)) {
            nameEntries.add(webElement.getText());
        }

        return nameEntries;
    }

    public boolean isConfirmButtonVisible() {
        return isVisible("'Confirm removal' button is present", confirmRemovalButton);
    }

    public void clickConfirmRemovalButton() {
        waitToBeClickable("Wait for 'Confirm removal' button to be clickable", confirmRemovalButton);
        click("Click 'Confirm removal' button", confirmRemovalButton);
    }

}
