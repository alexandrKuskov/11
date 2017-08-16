package eu.datatiler.pages;

import com.qatestlab.base.BaseActions;
import com.qatestlab.base.BasePage;
import com.qatestlab.base.Locator;
import com.qatestlab.base.LocatorTypes;

public class NavigationBar extends BasePage {
    private Locator loader = new Locator(LocatorTypes.ID,
            "loading-bar");

    private Locator loaderSpinner = new Locator(LocatorTypes.ID,
            "loading-bar-spinner");

    private Locator bar = new Locator(LocatorTypes.XPATH,
            "//ul[contains(@class, 'navbar-nav')]");

    private Locator toggleBox = bar.concat(new Locator(LocatorTypes.XPATH, "//li[@class='accountBox']"));

    private Locator logoutButton = new Locator(LocatorTypes.XPATH,
            "//li[@ng-click='logout()']");

    private Locator logoutFromClickboard = new Locator(LocatorTypes.XPATH,"//span[text()='Log out']");

    private Locator profileButton = bar.concat(new Locator(LocatorTypes.XPATH,
            "//div[contains(@click-to-href,'/profile')]"));

    private Locator mainPageLink = new Locator(LocatorTypes.XPATH,
            "//a[contains(@class, 'uat_main')]");

    private Locator adminButton = bar.concat(new Locator(LocatorTypes.XPATH,
            "//a[contains(@ng-bind, 'Administration')]"));

    private Locator successAlert = new Locator(LocatorTypes.XPATH,
            "//div[contains(@class, 'alert-success')]");

    private Locator settingButton = new Locator(LocatorTypes.XPATH,
            "//div[@class='dvi-panel-right']//div[@ng-if='dviSettings.canShow']/a");

    private Locator progressBar = new Locator(LocatorTypes.XPATH,
            "//div[@class='progress-bar']");

    private Locator lastFolder = new Locator(LocatorTypes.XPATH,
            "//ol[contains(@class, 'breadcrumb')]/li[last()]/span");

    private Locator breadCrumbs = new Locator(LocatorTypes.XPATH,
            "//ol[contains(@class, 'breadcrumb')]/li/*");

    private Locator breadCrumb = new Locator(LocatorTypes.XPATH,
            "//ol[contains(@class, 'breadcrumb')]/li[%s]/*");

    private Locator itemSearchField = new Locator(LocatorTypes.XPATH,
            "//div[text()='Search variables']/following-sibling::input");

    private Locator nameHeader = new Locator(LocatorTypes.XPATH,
            "//div[text()='Name']");


    protected NavigationBar() {
    }

    public void waitNavigationBar() {
        waitForVisibility("Wait 'Navigation' bar", bar);
    }

    public void waitLoader() {
        waitForInvisibility(5, 90, "Wait until page content loaded", loader);
    }

    public void waitForSearchField(){
        waitForVisibility("wait for search field visibility", itemSearchField);
    }

    public void waitProgressBar() {
        waitForInvisibility(1, 90, "Wait until page content loaded", progressBar);
    }

    public void clickToggleButton() {
        click("Click toggle box navigation bar", toggleBox);
    }

    public void clickLogoutButton() {
        click("Click 'Log out' button", logoutButton);
    }

    public void clickLogoutButtonFromClickboard() {
        click("Click 'Log out' button", logoutFromClickboard);
    }

    public void waitLogoutButton() {
        waitForVisibility("Wait for 'Log out' visibility", logoutButton);
    }

    public void waitLogoutButtonFromClickboard() {
        waitForVisibility("Wait for 'Log out' visibility", logoutFromClickboard);
    }

    public boolean isLogoutButtonPresent() {
        return isPresent(30, "Checking if 'logout' button present", logoutButton);
    }

    public void waitProfileButton() {
        waitToBeClickable("Wait for 'Profile' to be clickable", profileButton);
    }

    public void clickProfileButton() {
        clickWithJS("Clicking 'Profile' button", profileButton);
    }

    public void waitMainPageLink() {
        waitToBeClickable("Wait for 'Main page' link to be clickable", mainPageLink);
    }

    public void clickMainPageLink() {
        executeJS("Clicking 'Main page' link", "arguments[0].click()", mainPageLink);
    }

    public String getUserName() {
        return getText("Getting Username from profile button", profileButton);
    }

    public boolean isAdminButtonPresent() {
        return isPresent("Checking if 'Administration' button present", adminButton);
    }

    public void waitSuccessAlertVisibility() {
        waitForVisibility("Waiting for 'Success' alert visibility", successAlert);
    }

    public boolean isSuccessAlertVisible() {
        return isVisible("Check is 'success' alert visible", successAlert);
    }

    public void waitSuccessAlertInvisibility() {
        waitForInvisibility("Waiting for 'Success' alert invisibility", successAlert);
    }

    public void waitProjectSettingsButton() {
        waitForVisibility("Waiting for 'Settings' button visibility", settingButton);
    }

    public void clickProjectSettings() {
        click("Click'Settings' button", settingButton);
        BaseActions.wait(5);
    }

    public String getLastFolderName() {
        waitForVisibility("Wait for last bread crumb visibility", lastFolder);
        return getText("Get 'last' folder name", lastFolder);
    }

    public String getBreadCrumbsHierarchy() {

        String breadCrumbsHierarchy = "";

        for (int i = 1; i <= getElements("", breadCrumbs).size(); i++)
            breadCrumbsHierarchy += "/" + getText("", breadCrumb, i);

        return breadCrumbsHierarchy;
    }


}
