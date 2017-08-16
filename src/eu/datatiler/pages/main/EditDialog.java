package eu.datatiler.pages.main;

import com.qatestlab.base.BasePage;
import com.qatestlab.base.Locator;
import com.qatestlab.base.LocatorTypes;

public class EditDialog extends BasePage {

    private Locator dialog = new Locator(LocatorTypes.XPATH,
            "//div[@class='modal-dialog']");

    private Locator nameField = new Locator(LocatorTypes.XPATH,
            "//input[@id='rename-input']");

    private Locator saveButton = new Locator(LocatorTypes.XPATH,
            "//button[contains(@class, 'uat_rename_rename')]");

    private Locator closeButton = new Locator(LocatorTypes.XPATH,
            "//button[contains(@class, 'uat_rename_close')]");

    private Locator mDialogHeader = new Locator(LocatorTypes.XPATH,
            "//div[@class='modal-header']/h3");

    protected EditDialog() {
    }

    public void waitForVisibility() {
        waitForVisibility("Waiting for 'Edit' dialog visibility", dialog);
    }

    public boolean isModalDialogCancelButtonVisible() {
        return isVisible("Check is modal dialog visible", dialog);
    }

    public void typeName(String name) {
        type("Typing new name: " + name, name, nameField);
    }

    public void clickSaveButton() {
        click("Clicking 'Save' button", saveButton);
    }

    public void waitForInvisibility() {
        waitForInvisibility("Waiting for 'Edit' dialog invisibility", dialog);
    }

    public String getModalDialogHeader() {
        return getText("Get modal dialog header", mDialogHeader);
    }
}
