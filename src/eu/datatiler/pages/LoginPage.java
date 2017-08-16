package eu.datatiler.pages;

import com.qatestlab.base.BasePage;
import com.qatestlab.base.Locator;
import com.qatestlab.base.LocatorTypes;


public class LoginPage extends BasePage {
    private Locator loginForm = new Locator(LocatorTypes.XPATH,
            "//form[@class='t_loginForm']");

    private Locator loginEmailInput = new Locator(LocatorTypes.XPATH,
            "//input[@name='username']");

    private Locator loginPassInput = new Locator(LocatorTypes.XPATH,
            "//input[@name='password']");

    private Locator loginButton = loginForm.concat(new Locator(LocatorTypes.XPATH,
            "//button[@type='submit']"));

    private Locator errorMessage = new Locator(LocatorTypes.XPATH, "//div[@id='growlContainer']/div/div[text()]");

    private Locator footer = new Locator(LocatorTypes.XPATH,
            "//p[@class='text-muted']");


    protected LoginPage() {
    }

    public void waitForLoad() {
        waitForVisibility("Wait until login page is loaded", loginForm);
    }

    public boolean isLoginFormVisible() {
        return isVisible("Check login form is visible.", loginForm);
    }

    public void typeLogin(String value) {
        type("Type login: " + value, value, loginEmailInput);
    }

    public String getLoginText() {
        return getText("Get login text", loginEmailInput);
    }

    public void typePassword(String value) {
        type("Type password: " + value, value, loginPassInput);
    }

    public String getPasswordText() {
        return getText("Get login text", loginPassInput);
    }

    public boolean isPasswordEntered() {
        return !getText("Get password", loginPassInput).isEmpty();
    }

    public void clickLoginButton() {
        click("Click 'Log in' button", loginButton);
    }

    public void waitForLoginInvisibility() {
        waitForInvisibility("Waiting for 'Login' button invisibility", loginButton);
    }

    public void waitErrorMessage() {
        waitForVisibility("Wait for login error message", errorMessage);
    }

    public String getErrorMessageText() {
        return getText("Get login error message text",errorMessage).trim();
    }

    public void waitFooterTextVisibility() {
        waitForVisibility("Wait for footer load", footer);
    }

    public String getFooterText() {
        return getText("Get footer text", footer);
    }

    public void typeLineBreakLogin() {
        typeNewline("Typing newline to email field", loginEmailInput);
    }

    public void typeLineBreakPass() {
        typeNewline("Typing newline to password field", loginPassInput);
    }
}
