package eu.datatiler.pages.dashboard;

import com.qatestlab.base.BasePage;
import com.qatestlab.base.Locator;
import com.qatestlab.base.LocatorTypes;
import com.qatestlab.reporting.Reporter;
import org.openqa.selenium.WebElement;


import java.util.*;

public class TextWidgetDialog extends BasePage {

    private Locator dialog = new Locator(LocatorTypes.XPATH,
            "//div[contains(@class, 'mce-edit-focus')]");

    private Locator textArea = new Locator(LocatorTypes.XPATH,
            "//div[contains(@class, 'mce-edit-focus')]");

    private Locator textWidgetArea = new Locator(LocatorTypes.XPATH,
            "//item[contains(@class, 'widget')]");

    private Locator dashboardArea = new Locator(LocatorTypes.XPATH,
            "//items[@class='md-shadow-bottom-z-1']");

    private Locator formattingButtonsByName = new Locator(LocatorTypes.XPATH,
            "//div[span[text()='Heading %s']]");

    private Locator dashboardElement = new Locator(LocatorTypes.XPATH,
            "//items[contains(@class,'md-shadow')]/item[@class=' image']");

    private Locator editButton = new Locator(LocatorTypes.XPATH,
            "//span[contains(text(),'Edit')]");

    private Locator saveWidgetButton = new Locator(LocatorTypes.XPATH,
            "//button[div//*[contains(@d, 'M17') or contains(@d, 'M 17')]]");

    private Locator formatButton = new Locator(LocatorTypes.XPATH,
            "//button[span[text()='Format']]");

    private Locator formatsButton = new Locator(LocatorTypes.XPATH,
            "//button[span[text()='Formats']]");

    private Locator headingsButton = new Locator(LocatorTypes.XPATH,
            "//div[span[text()='Headings']]");

    private Locator dashboardLeftActions = new Locator(LocatorTypes.XPATH,
            "//div[@class='pull-left actions']");

    private Locator modalWindow = new Locator(LocatorTypes.XPATH,
            "//div[@class='modal-content']/div/h3[text()='Create image widget']");

    private Locator imageArea = new Locator(LocatorTypes.XPATH,
            "//div[@class='decorator layout-fill-height ui-draggable-handle']");

    private Locator dashboardLeftActionsItem = dashboardLeftActions.concat(new Locator(LocatorTypes.XPATH,
            "//span[%s]/span/button/i"));

    private Locator toolbar = new Locator(LocatorTypes.XPATH,
            "//div[@class='toolbar']");

    private Locator settingWidgetToolbar = toolbar.concat(new Locator(LocatorTypes.XPATH,
            "//div[text()='Widget settings']"));

    private Locator toolbarIcons = toolbar.concat(new Locator(LocatorTypes.XPATH,
            "//div[@style='padding: 16px;']/button[%s]"));

    private Locator nativeImage = new Locator(LocatorTypes.XPATH,
            "//item[contains(@class,'image ng-touched ui-resizable')]");

    private Locator cloneImage = new Locator(LocatorTypes.XPATH,
            "//div[@class='widgets']/item[2]");

    private Locator images =new Locator(LocatorTypes.XPATH,
            "//div[@class='items-container']//item[@id]");

    private Locator image = new Locator(LocatorTypes.XPATH,
            "//div[@class='items-container']//item[@id='%s']");

    protected TextWidgetDialog() {
    }

    public boolean IsBothImagesPresent() {
        boolean status = true;
        List<String> attributeValues = new ArrayList<>();
        List<WebElement> elements = getElements("get images ID's", images);
        for (WebElement element : elements) {
            attributeValues.add(element.getAttribute("id"));
        }
        for (String id : attributeValues) {
            status = isPresent("check for image present", image, id);
            if (!status) {
                status = false;
                break;
            }
        }
        return status;
    }

    public boolean isCloningImagePresent(){
        return isPresent("check for cloning image", cloneImage);
    }

    public boolean isNativeImagePresent(){
        return isPresent("check for cloning image", nativeImage);
    }

    public void clickCloneIcon(){
        click("click 'clone' icon", toolbarIcons,"3");
    }


    public boolean isToolbarPresent(){
        return isPresent("check for left toolbar", toolbar);
    }

    public boolean isSettingsWidgetHeaderPresent(){
        return isPresent("check for 'settings widget' header", settingWidgetToolbar);
    }

    public boolean isPositionalIconsPresent(){
        boolean upPositionalIcon;
        boolean downPositionalIcon;
        waitForVisibility("wait for 'Positioning up' icon visibility", toolbarIcons,"1");
        upPositionalIcon = isPresent("check for 'Positioning up' icon", toolbarIcons, "1");
        waitForVisibility("wait for 'Positioning down' icon visibility", toolbarIcons,"2");
        downPositionalIcon = isPresent("check for 'Positioning down' icon", toolbarIcons, "2");
        return upPositionalIcon & downPositionalIcon;
    }

    public boolean isCloneIconPresent(){
        return isPresent("check for 'Clone' icon", toolbarIcons,"3");
    }

    public boolean isDeleteIconPresent(){
        return isPresent("check for 'Delete' icon", toolbarIcons,"4");
    }

    public void clickOnImage(){
        waitForVisibility("wait for image area be visible", imageArea);
        click("click on image area", imageArea);
    }

    public boolean isModalWindowPresent(){
      return isPresent("wait for 'modal window' visibility", modalWindow);
    }

    public void clickLeftToolsIcon(String itemName, List<String> allItemsName){
        if(allItemsName.contains(itemName)){
            clickItem(String.valueOf(allItemsName.indexOf(itemName)+1), itemName);
        }
        else {
            Reporter.log("There is no any item with the name " + itemName + " on the page");
            throw new NoSuchElementException();
        }
    }

    public void clickItem(String number, String itemName){
        waitToBeClickable("wait for "+ itemName +"  icon to bo clickable", dashboardLeftActionsItem, number);
        click("click "+ itemName +"  icon", dashboardLeftActionsItem, number);

    }

    public boolean isDashboardLeftActionsPresent() {
        return isPresent("check for dashboard left actions", dashboardLeftActions);
    }

    public boolean isLeftActionsItemPresent(List<String>itemsName) {
        List<Boolean> itemsStatus = new ArrayList<>();
        for (int i = 0; i <= itemsName.size() - 1; i++) {
                waitForVisibility("wait for '" +itemsName.get(i) + "' item visibility", dashboardLeftActionsItem, String.valueOf(i+1));
                itemsStatus.add(isPresent("check for '" +itemsName.get(i) + "' item", dashboardLeftActionsItem, String.valueOf(i+1)));
            }
        return itemsStatus.get(0) & itemsStatus.get(1) & itemsStatus.get(2);
    }


    public void clickEditButton() {
        waitForVisibility("wait for 'Edit' button visibility", editButton);
        click("click 'Edit' button", editButton);
    }

    public boolean isDashBoardAreaPresent() {
        waitForVisibility("wait for dashboard area visibility", dashboardArea);
        return isPresent("check for dashboard area", dashboardArea);
    }

    public boolean isDashBoardAreaEmpty() {
        return isPresent("checking for dashboard fulfilling", dashboardElement);
    }

    public void waitForDialogLoad() {
        waitForVisibility("Waiting for 'Create text widget' dialog visibility", dialog);
    }

    public boolean isDialogAreaPresent() {
        return isPresent("Check is text wiidget diaolog present", dialog);
    }

    public void typeWidgetText(String text) {
        type("Typing text: " + text, text, textArea);
    }


    public void waitFormattingToBeClickable(String name) {
        waitToBeClickable(String.format("waiting for '%s' button to be clickable", name), formattingButtonsByName, name);
    }

    public void selectFormatting(String name) {
        clickWithJS("Selecting text heading: " + name, formattingButtonsByName, name);
    }

    public void waitAddButtonToBeClickable() {
        waitToBeClickable("Waiting for 'Add' button to be clickable", saveWidgetButton);
    }

    public void clickSaveWidget() {
        click("Clicking 'Add' button", saveWidgetButton);
    }

    public void clickFormatsButton() {
        waitToBeClickable("Wait 'Format' button", formatsButton);
        click("Click 'Format' button", formatsButton);
    }

    public void moveToHeadingsButton() {
        moveToElement("Move to 'Headings' button", headingsButton, 0, 0).perform();
    }

    public void clickTextWidgetArea() {
        click("Click text widget area", textWidgetArea);
    }
}
