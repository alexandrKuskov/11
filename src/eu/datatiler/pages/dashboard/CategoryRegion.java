package eu.datatiler.pages.dashboard;

import com.qatestlab.base.BasePage;
import com.qatestlab.base.Locator;
import com.qatestlab.base.LocatorTypes;

public class CategoryRegion extends BasePage {

    private Locator region = new Locator(LocatorTypes.XPATH,
            "//div[contains(@class, 'dt-grid-container')]");

   private Locator categoryByNamePattern = region.concat(new Locator(LocatorTypes.XPATH,
           "//div[contains(@class,'name dragHandler')]/span[text()='%s']"));

    private Locator itemsRegion = new Locator(LocatorTypes.XPATH,
            "//div[contains(@class, 'ui-grid-viewport')]");

    private Locator itemByNamePattern = itemsRegion.concat(new Locator(LocatorTypes.XPATH,
            "//div[contains(@class, 'uat_name') and text()=' %s']"));

    private Locator groupArea = new Locator(LocatorTypes.XPATH,
            "//div[text()='Drop here']");

    private Locator mySpaceLink = new Locator(LocatorTypes.LINK_TEXT,
            "My space");

    protected CategoryRegion() {
    }

    public void waitForLoad() {
        waitForVisibility("Waiting for 'Category' region visibility", region);
    }

    public void waitMySpaceLinkToBeClickable() {
        waitToBeClickable("Waiting for 'My space' link to be clickable", mySpaceLink);
    }

    public void clickMySpaceLink() {
        click("Click 'My space' link", mySpaceLink);
    }

    public void clickCategory(String categoryName) {
        click("Clicking on category: " + categoryName, categoryByNamePattern, categoryName);
    }

    public void waitForItemsRegionVisibility() {
        waitForVisibility("Waiting for 'items' region visibility", itemsRegion);
    }

    public void selectItem(String itemName) {
        click("Clicking on item: " + itemName, itemByNamePattern, itemName);
    }

    public boolean isItemPresent(String itemName) {
        return isPresent("Checking if item present: " + itemName, itemByNamePattern, itemName);
    }

    public void fillGroup(String itemName) {
        waitForVisibility("Wait group area.", groupArea);
        waitToBeClickable("Wait to category name be clickable.", categoryByNamePattern, itemName);

        dragAndDrop("Drag and drop item to group: " + itemName, categoryByNamePattern, groupArea, itemName);
    }
}

