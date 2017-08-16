package eu.datatiler.pages.main;

import com.qatestlab.base.BasePage;
import com.qatestlab.base.Locator;
import com.qatestlab.base.LocatorTypes;

public class ToolbarRegion extends BasePage {

    private Locator createButton = new Locator(LocatorTypes.XPATH,
            "//button[contains(@class, 'uat_creator_btn')]");

    private Locator createMenu = new Locator(LocatorTypes.XPATH,
            "//md-menu-content[@class='uat_creator_list entity-create__menu-content']");

    private Locator createMenuItemByNamePattern = new Locator(LocatorTypes.XPATH,
            "//span[text()='%s']");

    private Locator refreshButton = new Locator(LocatorTypes.XPATH,
            "//button[@ng-click='refresh()']");

    private Locator shareButton = new Locator(LocatorTypes.XPATH,
            "//span[@ng-if='selectedEntitiesPermissions.share']");

    private Locator renameButton = new Locator(LocatorTypes.XPATH,
            "//span[@ng-if='selectedEntitiesPermissions.rename']");

    private Locator removeButton = new Locator(LocatorTypes.XPATH,
            "//button[@ng-if='selectedEntitiesPermissions.remove']");

    private Locator settingsButton = new Locator(LocatorTypes.XPATH,
            "//a[contains(@class, 'uat_entity_settings')]");

    private Locator starButton = new Locator(LocatorTypes.XPATH,
            "//button[contains(@class, 'uat_entity_star')]");

    private Locator mDialogHeader = new Locator(LocatorTypes.XPATH,
            "//md-dialog-content/h2");

    private Locator mDialogCancelButton = new Locator(LocatorTypes.XPATH,
            "//button//span[text()='Cancel']");

    private Locator mDialogDeleteButton = new Locator(LocatorTypes.XPATH,
            "//button//span[text()='Delete']");


    protected ToolbarRegion() {
    }

    public void waitCreateButtonToBeClickable() {
        waitToBeClickable("Waiting for 'create' button to be clickable", createButton);
        waitForVisibility("Waiting for 'create' button to be clickable", createButton);
    }

    public void clickCreateButton() {
        click("Click 'Create' toolbar button", createButton);
    }

    public void waitCreateMenuVisibility() {
        waitForVisibility("Wait for 'Create' options menu visibility", createMenu);
    }

    public void waitCreateMenuToBeClickable() {
        waitToBeClickable("Wait for 'Create' options menu to be clickable", createMenu);
    }

    public void waitCreateMenuInvisibility() {
        waitForInvisibility("Wait for 'Create' options menu invisibility", createMenu);
    }

    public void clickCreateFolderMenuItem() {
        click("Click 'Folder' menu item", createMenuItemByNamePattern, "Folder");
    }

    public void clickCreateItem(String itemName) {
       waitForVisibility("Wait creation menu item : " + itemName, createMenuItemByNamePattern, itemName);
        click("Click creation menu item : " + itemName, createMenuItemByNamePattern, itemName);
    }

    public boolean isCreateItemPresent(String itemName) {
        return isVisible("Check menu item is present: " + itemName, createMenuItemByNamePattern, itemName);
    }

    public void waitRemoveButtonToBeClickable() {
        waitToBeClickable("Waiting for 'remove' button to be clickable", removeButton);
    }

    public void clickRemoveButton() {
        click("Clicking 'remove' button", removeButton);
    }

    public void checkAlert() {
        checkAlert("Clicking 'ok'");
    }


    public String getModalDialogHeader(){
        return getText("Get modal dialog header", mDialogHeader);
    }

    public boolean isModalDialogCancelButtonVisible() {
        return isVisible("Check is 'Cancel' modal dialog button visible", mDialogCancelButton);
    }

    public boolean isModalDialogDeleteButtonVisible(){
        return isVisible("Check is 'Delete' modal dialog button visible", mDialogDeleteButton);
    }

    public void clickDeleteButtonInDeleteModDialog() {
        click("Clicking 'Delete' button in delete modal dialog", mDialogDeleteButton);
    }

    public void waitShareButtonToBeClickable() {
        waitToBeClickable("Waiting for 'Share' button to be clickable", shareButton);
    }

    public void clickShareButton() {
        click("Clicking 'Share' button", shareButton);
    }

    public boolean isRefreshButtonVisible() {
        return isVisible("Check is 'Refresh' button visible", refreshButton);
    }

    public boolean isShareButtonVisible() {
        return isVisible("Check is 'Share' button visible", shareButton);
    }

    public boolean isRenameButtonVisible() {
        return isVisible("Check is 'Rename' button visible", renameButton);
    }

    public boolean isDeleteButtonVisible() {
        return isVisible("Check is 'Remove' button visible", removeButton);
    }

    public boolean isSettingsButtonVisible() {
        return isVisible("Check is 'Settings' button visible", settingsButton);
    }

    public void waitSettingsButtonToBeVisible(){
        waitForVisibility("Wait for 'Settings' button", settingsButton);
    }

    public void clickSettingsButton(){
        click("Click 'Settings' button", settingsButton);
    }

    public boolean isStarButtonVisible() {
        return isVisible("Check is 'Star' button visible", starButton);
    }


}
