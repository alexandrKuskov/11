package eu.datatiler.pages.main;

import com.qatestlab.base.*;
import org.openqa.selenium.Alert;

public class ShareDialog extends BasePage {

    private Locator dialog = new Locator(LocatorTypes.CSS,
            ".uat_share_window");

    private Locator copyToClipboardButton = new Locator(LocatorTypes.XPATH,
            "//div[@class='share-public-form']//button");

    private Locator publicCheckbox = new Locator(LocatorTypes.XPATH,
            "//input[@ng-model='sharedEntity.public']");

    private Locator referenceInput = new Locator(LocatorTypes.ID,
            "exampleInputAmount");

    private Locator emailInput = new Locator(LocatorTypes.CSS,
            ".uat_share_address");

    private Locator closeButton = new Locator(LocatorTypes.XPATH,
            "//button[@ng-click='cancel()']");

    private Locator shareURLField = new Locator(LocatorTypes.CSS,
            ".input-group");

    protected ShareDialog() {
    }

    public void waitForLoad() {
        waitForVisibility("Waiting for 'Share' dialog visibility", dialog);
    }

    public boolean isShareWindowVisible(){
        return isVisible("Check share window is visible", dialog);
    }

    public void checkPublicCheckbox() {
        waitToBeClickable("", publicCheckbox);
        setCheckboxState("Checking 'Public' checkbox", true, publicCheckbox);
    }

    public void waitCopyButtonClickable() {
        waitToBeClickable("Waiting for 'Copy to clipboard' button to be clickable", copyToClipboardButton);
    }

    public void clickCopyToClipboard() {
        click("Clicking 'Copy to clipboard' button", copyToClipboardButton);
        if (isDriverIE()){
            acceptAlertWindow();
        }
    }
    public void clickCloseButton() {
        click("Clicking 'Close' button", closeButton);
    }

    public String getPublicReference() {
        return getText("Getting public reference", referenceInput);
    }

    public void pasteToEmailInput() {
        pasteText("Pasting copied reference to 'email' input", emailInput);
    }

    public String getEmailInputText() {
        return getText("Getting test from 'Email' input", emailInput);
    }

    public boolean isShareURLFieldVisible(){
        return isVisible("", shareURLField);
    }
}
