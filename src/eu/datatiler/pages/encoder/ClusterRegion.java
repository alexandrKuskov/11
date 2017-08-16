package eu.datatiler.pages.encoder;

import com.qatestlab.base.BasePage;
import com.qatestlab.base.Locator;
import com.qatestlab.base.LocatorTypes;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;

/**
 * Created by Petro on 07.03.2016.
 */
public class ClusterRegion extends BasePage {

    private Locator clusterBlock = new Locator(LocatorTypes.XPATH,
            "//div[contains(@ng-show, 'clusters') and contains(@class, 'mainContent')]");

    private Locator clusterItem = new Locator(LocatorTypes.XPATH,
            "//div[contains(@ng-show, 'clusters') and contains(@class, 'mainContent')]/md-content/div[%s]");

    private Locator clusterItemValue = clusterItem.concat(new Locator(LocatorTypes.XPATH,
            "//ul/li/span"));

    private Locator mergeClusterButton = clusterItem.concat(new Locator(LocatorTypes.XPATH,
            "//button/span[text()='Merge']"));

    private Locator clusterNameField = clusterItem.concat(new Locator(LocatorTypes.XPATH,
            "//input"));

    public void waitForLoad() {
        waitForVisibility("Wait for cluster block to be loaded", clusterBlock);
    }

    public void setClusterItemName(int idItem, String clusterItemName) {
        waitForVisibility("Wait for input field clusters name to be visible", clusterNameField, idItem);
        type("Set name '" + clusterItemName + "' for cluster item", clusterItemName, clusterNameField, idItem);
    }

    public void clickMergeClusterItem(int idItem) {
        waitToBeClickable("Wait for 'Merge' button to be clickable", mergeClusterButton, idItem);
        click("Click on 'Merge' button", mergeClusterButton, idItem);
    }

    public ArrayList<String> getClusterItemValues(int idItem) {
        waitForLoad();

        ArrayList<String> itemValues = new ArrayList<>();

        for (WebElement webElement : getElements("Get cluster item values", clusterItemValue, idItem)) {
            itemValues.add(webElement.getText());
        }

        return itemValues;
    }
}
