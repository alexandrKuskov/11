package eu.datatiler.pages.project;

import com.qatestlab.base.BasePage;
import com.qatestlab.base.Locator;
import com.qatestlab.base.LocatorTypes;
import com.qatestlab.base.WebElementExtender;
import com.qatestlab.properties.PropertiesNames;
import org.apache.commons.io.FileUtils;

import java.io.File;

public class CategoryRegion extends BasePage {

    private Locator region = new Locator(LocatorTypes.XPATH,
            "//div[contains(@class, 'dt-grid-container')]");

    private Locator categoryByNamePattern = new Locator(LocatorTypes.XPATH,
            "//span[text()='%s']");

    private Locator itemsRegion = new Locator(LocatorTypes.XPATH,
            "//div[@class='dt-grid-container']");

    private Locator itemByNamePattern = itemsRegion.concat(new Locator(LocatorTypes.XPATH,
            "//div[text()='%s']"));

    private Locator rowsCell = new Locator(LocatorTypes.XPATH,
            "//div[@class='rows-view']//canvas[contains(@class, 'upper-canvas')]");

    private Locator columnsCell = new Locator(LocatorTypes.XPATH,
            "//div[@class='columns-view']//canvas[contains(@class, 'upper-canvas')]");

    private Locator mySpaceLink = new Locator(LocatorTypes.XPATH,
            "//a[text()='My space']");

    private Locator listValuesItemCategory = new Locator(LocatorTypes.XPATH,
            "//div[contains(@class, 'noheader')]//div[@class='dt-grid-container']//table//tr");

    private Locator dviRegion = new Locator(LocatorTypes.XPATH,
            "//div[@class='dvi-grid-component']");

    private Locator contextByName = new Locator(LocatorTypes.XPATH,
            "//div[contains(@class,'dvi-context-mode ')]/div[@class='btn-group']//button[text()='%s']");

    private Locator resetState = new Locator(LocatorTypes.XPATH,
            "//button[@title='Reset state']");



    protected CategoryRegion() {
    }

    public boolean isResetStateButtonPresent(){
        return isPresent("check for 'reset state button' visibility",resetState);
    }

    public void clickResetStateButton() {
            waitToBeClickable("Waiting for 'reset state' link to be clickable", resetState);
            click("click on 'reset state' link", resetState);
    }

    public void waitForLoad() {
        waitForVisibility("Waiting for 'Category' region visibility", region);
    }

    public void waitForMySpaceLinkToBeClickable() {
        waitToBeClickable("Waiting for 'My space' link to be clickable", mySpaceLink);
    }

    public void waitForMySpaceLinkToBeVisible() {
        waitForVisibility("Waiting for 'My space' link to be visible", mySpaceLink);
    }

    public boolean isDVIRegionVisible() {
        return isVisible("Check 'DVI' region is visible", dviRegion);
    }

    public void clickMySpaceLink() {
        clickJS("Click 'My space' link", mySpaceLink);
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

    public void fillRows(String itemName) {
        dragAndDrop("Drag and drop item to row: " + itemName, categoryByNamePattern, rowsCell, itemName);
    }

    public void getScreenshotDViRow(String screenshotName) throws Exception {

        waitForVisibility("Wait for DVi rows", rowsCell);

        FileUtils.copyFile(WebElementExtender.captureElementBitmap(driver().findElement(rowsCell.getLocator())),
                new File(
                        System.getProperty(PropertiesNames.SCREENSHOT_DIR.toString()) +
                                File.separator + screenshotName + ".png"));
    }

    public void fillColumns(String itemName) {
        dragAndDrop("Drag and drop item to column: " + itemName, categoryByNamePattern, columnsCell, itemName);
    }

    public void getScreenshotDViColumns(String screenshotName) throws Exception {

        waitForVisibility("Wait for DVi column", columnsCell);

        FileUtils.copyFile(WebElementExtender.captureElementBitmap(driver().findElement(columnsCell.getLocator())),
                new File(
                        System.getProperty(PropertiesNames.SCREENSHOT_DIR.toString()) +
                                File.separator + screenshotName + ".png"));
    }

    public int getCountValuesCategoryItem(String categoryName) {
        click("Clicking on category: " + categoryName, categoryByNamePattern, categoryName);
        return getCount("Get values " + categoryName + " item", listValuesItemCategory);
    }

    public void setContextByName(String name){
        click("Set '" + name + "' context", contextByName, name);
    }



}

