package eu.datatiler.pages;

import com.qatestlab.base.BasePage;
import com.qatestlab.base.Locator;
import com.qatestlab.base.LocatorTypes;

public class ProfilePage extends BasePage {

    private Locator profileHeader = new Locator(LocatorTypes.XPATH,
            "//div[text()='Profile']");

    private Locator firstNameInput = new Locator(LocatorTypes.ID,
            "firstName");

    private Locator saveProfileButton = new Locator(LocatorTypes.XPATH,
            "//button[@ng-click='updateProfile()']");

    protected ProfilePage() {
    }

    public void waitFirstNameVisibility() {
        waitForVisibility("Waiting for 'first name' field visibility", firstNameInput);
    }

    public void typeFirstName(String name) {
        click("Clicking on firstName field", firstNameInput);
        type("Typing first name", name, firstNameInput);
    }

    public void clickSaveButton() {
        click("Clicking 'save profile' button", saveProfileButton);
    }

    public boolean isProfileHeaderVisible() {
        return isVisible("Check is profile header visible", profileHeader);
    }

    public String getEnteredNewFirstName() {
        return getText("Get entered new first name", firstNameInput);
    }
}
