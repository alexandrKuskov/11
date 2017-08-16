package eu.datatiler.pages.encoder;

import com.qatestlab.base.BasePage;
import com.qatestlab.base.Locator;
import com.qatestlab.base.LocatorTypes;
import com.qatestlab.reporting.Reporter;

/**
 * Created by Petro on 29.02.2016.
 */
public class CategoryRegion extends BasePage {

    private Locator listInterviewItems = new Locator(LocatorTypes.XPATH,
            "//div[contains(@class, 'sideColumn')]//ul[@class='items']");

    private Locator interviewItem = new Locator(LocatorTypes.XPATH,
            "//ul[@class='items']/li[%s]");

    private Locator interviewItemLabel = interviewItem.concat(new Locator(LocatorTypes.XPATH,
            "//md-select-value//div"));

    private Locator itemOption = new Locator(LocatorTypes.XPATH,
            "//div[contains(@class, 'md-active')]//*[text()='%s']");

    protected CategoryRegion() {
    }

    public void waitForLoad() {
        waitForVisibility("Waiting for 'Category' region visibility", listInterviewItems);
        Reporter.log("Encoder option is loaded");
    }

    public boolean isListInterviewItemVisible() {
        return isPresent("List interview item is visible", listInterviewItems);
    }

    public void waitInterviewItem(int idItem) {
        waitForVisibility("Wait car model item to be visible", interviewItem, idItem);
    }

    public void clickInterviewItem(int idItem) {
        waitToBeClickable("Wait for item to be clickable", interviewItem, idItem);
        click("Click on interview item", interviewItem, idItem);
    }

    public void clickInterviewItemLabel(int idItem) {
        click("Click on '" + idItem + "' item label", interviewItemLabel, idItem);
    }

    public void clickItemOption(String optionName) {
        Reporter.log("Click on '" + optionName + "' option");
        waitToBeClickable("Waiting for '" + optionName + "' option to be clickable", itemOption, optionName);
        click("Click on '" + optionName +"' option", itemOption, optionName);
    }
}
