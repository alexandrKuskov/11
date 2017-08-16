package eu.datatiler.pages.main;

import com.qatestlab.base.BasePage;
import com.qatestlab.base.Locator;
import com.qatestlab.base.LocatorTypes;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Keyboard;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.List;

public class ContentRegion extends BasePage {

    private Locator region = new Locator(LocatorTypes.CSS,
            ".uat_folder_content");

  //  private Locator contextRegion = new Locator(LocatorTypes.XPATH,
  //          "//div[@ng-show='dviContextMode.visible']//button[text()='%s']");

    private Locator contextRegion = new Locator(LocatorTypes.XPATH,
                      "//div[@role='group']//button[@ng-click='dviContextMode.toggle(mode.id)' and text()='%s']");

    private Locator contextItemStatus = new Locator(LocatorTypes.XPATH,
            "//div[@ng-show='dviContextMode.visible']//button[text()='%s' and contains(@class,'active')]");

    private Locator item = region.concat(new Locator(LocatorTypes.XPATH, "/li"));

    private Locator itemByName = new Locator(LocatorTypes.XPATH,
            "//div[@class='name-container']//*[text()='%s']");

    private Locator itemByNamePattern = new Locator(LocatorTypes.XPATH,
            "//div[@class='name-container']//*[contains(text(), '%s')]");

    private Locator backToRoot = new Locator(LocatorTypes.XPATH,
            "//a[@href='/root']");

    private Locator checkBox = new Locator(LocatorTypes.XPATH,
            "//span[contains(@class,'uat_entity_link') and text()='%s']" +
                    "/preceding-sibling::*[@class='item-content-clickable-area']");

    private Locator multipleItemsCheckBox = new Locator(LocatorTypes.XPATH,
            "//div[@class='name-container']//strong[text()='%s']");

    private Locator projectByNamePattern = new Locator(LocatorTypes.XPATH,
            "//span[contains(@class,'uat_entity_link') and text()='%s']" +
                    "/preceding-sibling::*[contains(@class,'uat_entity_box')]");

    private Locator editName = new Locator(LocatorTypes.XPATH,
            "//span[@ng-if='selectedEntitiesPermissions.rename']");


    protected ContentRegion() {
    }


public void clickContextButton(String buttonName){
        waitForVisibility("wait for 'context' button '" + buttonName + "' be visiable", contextRegion, buttonName);
        click("click 'context' button '" + buttonName + "'", contextRegion, buttonName);
}


    public boolean checkForButtonActivity(String buttonName){
    return isPresent("check for '" + buttonName + "' activity",contextItemStatus, buttonName);
    }

    public int getItemsAmount() {
        return getCount("Get items amount", item);
    }

    public int getIdenticalItemsCount(String folderName) {
        return getCount("Getting items count with name " + folderName, itemByName, folderName);
    }

    public void waitForItem(String itemName) {
        waitForPresence(
                "Wait for item '" + itemName + "' presence",
                itemByName, itemName);
    }

    public boolean isItemPresent(String itemName) {
        return isPresent(10, "Checking if item present " + itemName, itemByName, itemName);
    }

    public boolean isItemPresentByNamePattern(String namePattern) {
        return isPresent(10, "Checking if item present " + namePattern, itemByNamePattern, namePattern);
    }

    public void clickOnItemByNamePattern(String namePattern){
        click("Click on item which name consist" + namePattern, itemByNamePattern, namePattern);
    }

    public void waitItemToBeClickable(String itemName) {
        waitToBeClickable("Waiting for item to be clickable: " + itemName, itemByName, itemName);
    }

    public void clickOnItemBy(String itemName) {
        click("Clicking on item " + itemName, itemByName, itemName);
    }

    public void scrollToPageFooter(){
        scrollDown("scroll down");
    }

    public void setCheckBox(String itemName) {
        executeJS("Checking item checkbox: " + itemName, "arguments[0].click()", itemByName, itemName);
    }

    public void waitBackToRootVisibility() {
        waitForVisibility("Waiting for 'Back to root' link visibility", backToRoot);
    }

    public void clickBackToRoot() {
        click("Clicking 'Back to root' link", backToRoot);
    }

    public void waitForItemInvisibility(String folderName) {
        waitForInvisibility("Waiting for item invisibility: " + folderName, itemByName, folderName);
    }

    public void waitForItemVisibility(String folderName){
        waitForInvisibility("Waiting for item visibility: " + folderName, itemByName, folderName);
    }

    public void setCheckBoxForAll(String itemName) {
        setCheckboxStateForAll("Setting checked for items: " + itemName, true, multipleItemsCheckBox, itemName);
    }

    public void selectAllDuplicatedItemsBy(String itemName) throws AWTException {

        Robot robot = new Robot();
        List<WebElement> elements = driver().findElements(multipleItemsCheckBox.getLocator(itemName));
        robot.keyPress(KeyEvent.VK_SHIFT);
        for (WebElement element : elements) {
            element.click();
        }
        robot.keyRelease(KeyEvent.VK_SHIFT);

    }

    public void waitEditButtonToBeClickable(String itemName) {
        waitToBeClickable("Waiting for 'edit name' button to be clickable", editName, itemName);
    }

    public void clickEditName(String itemName) {
        executeJS("Click 'edit name' button on item: " + itemName, "arguments[0].click()", editName, itemName);
    }

    public void clickOnItemByName(String itemName) {
        click("Clicking on item " + itemName, itemByName, itemName);
    }

}
