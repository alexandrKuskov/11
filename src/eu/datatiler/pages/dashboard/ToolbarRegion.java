package eu.datatiler.pages.dashboard;

import com.qatestlab.base.BasePage;
import com.qatestlab.base.Locator;
import com.qatestlab.base.LocatorTypes;
import org.testng.Reporter;

public class ToolbarRegion extends BasePage {

    private Locator editDashboardButton = new Locator(LocatorTypes.XPATH,
            "//button//span[contains(text(), 'Edit')]");

    private Locator addMenu = new Locator(LocatorTypes.XPATH,
            "//ul[@class='dropdown-menu']");

    private Locator addButton = new Locator(LocatorTypes.XPATH,
            "//button[@id='single-button']");

    private Locator addMenuItemByNamePattern = new Locator(LocatorTypes.XPATH,
            "//*[text()='%s']");

    private Locator viewButton = new Locator(LocatorTypes.XPATH,
            "//button//span[contains(text(), 'View')]");

    private Locator editWidgetButton = new Locator(LocatorTypes.XPATH,
            "//button[contains(@ng-click, 'editWidget')]");

    private Locator removeButton = new Locator(LocatorTypes.XPATH,
            "//button[contains(@ng-click, 'removeWidget')]");

    private Locator acceptRemoveWidgetButton = new Locator(LocatorTypes.XPATH,
            "//div[h3[text()='Are you sure to delete this widget?']]//span[text()='Yes']");

    private Locator statesButton = new Locator(LocatorTypes.XPATH,
            "//div[text()='States']");

    private Locator addStateLink = new Locator(LocatorTypes.XPATH,
            "//a[@class='t_addState' and text()='Add a state']");

    private Locator defaultLink = new Locator(LocatorTypes.XPATH,
            "//div[@style]/div[text()='Default']");

    private Locator stateLink = new Locator(LocatorTypes.XPATH,
            "//span[@class='t_stateName' and text()='%s']");

    private Locator stateMoreLink = new Locator(LocatorTypes.XPATH,
            "//div[span[@class='t_stateName' and text()='%s']]/div/button[@class='t_openMenu']");

    private Locator conditionMoreLink = new Locator(LocatorTypes.XPATH,
            "//div[span[div[div[span[@class='t_stateName' and text()='%s']]]]]//div[text()='Condition 1']/..//button");

    private Locator stateMorePopUpWindow = new Locator(LocatorTypes.XPATH,
            "//div[div[span[@class='t_conditionCreate']]]");

    private Locator conditionMorePopUpWindow = new Locator(LocatorTypes.XPATH,
            "//div[div[span[@class='t_stateConditionDelete']]]");

    private Locator addConditionLink = new Locator(LocatorTypes.XPATH,
            "//div[contains(text(),'%s')]");

    private Locator conditionLink = new Locator(LocatorTypes.XPATH,

            "//span[text()='%s']/../../../div/../..//div[text()='Condition 1']");

    private Locator itemControl = new Locator(LocatorTypes.XPATH,
            "//item[div[div[div[@class='top-name horizontal clearfix']]]]");

    private Locator existedState = new Locator(LocatorTypes.XPATH,
            "//div[@style]//span[@class='t_stateName' and text()='%s']");

    private Locator dashboardSettingsLabel = new Locator(LocatorTypes.XPATH,
            "//div[text()='Dashboard settings']");


    protected ToolbarRegion() {
    }

    public void clickConditionMoreLink(String stateName){
        waitToBeClickable("wait while condition 'more' link be clickable", conditionMoreLink,stateName);
        click("click 'more' link under " + stateName + " link", conditionMoreLink,stateName);

    }

    public boolean isDashboardSettingsLabelPresent(){
        return isPresent("check for 'Dashboard settings' label availability", dashboardSettingsLabel);
    }

    public boolean isStatePresent(String stateName){
       return isPresent("check for state with name " + stateName + " is present",existedState, stateName);
    }

    public void clickOnCondition(String name){
        waitToBeClickable("wait while 'condition' be clickable", conditionLink, name);
        click("click on condition "+ name, conditionLink, name);
    }

    public boolean isConditionStatePresent(String conditionState){
        return isPresent("Check for "+ conditionState +" is present", conditionLink, conditionState);
    }

    public void clickConditionOption(String conditionName){
        waitToBeClickable("wait while '" + conditionName + "' button be clickable", addConditionLink,conditionName);
        click("click '" + conditionName + "' link", addConditionLink,conditionName);
    }

    public boolean isStateMorePopUpWindowPresent(){
        return isPresent("Check for more pop-up window is present", stateMorePopUpWindow);
    }

    public boolean isConditionMorePopUpWindowPresent(){
        return isPresent("Check for more pop-up window is present", conditionMorePopUpWindow);
    }

    public void clickStateMoreLink(String stateName){
        waitToBeClickable("wait while state 'more' link be clickable", stateMoreLink,stateName);
        click("click 'more' link under " + stateName + " link", stateMoreLink,stateName);
    }

    public boolean isStateLinkPresent(String stateName){
        return isPresent("Check for 'add state' link is present", stateLink, stateName);
    }

    public void clickAddState(){
        waitToBeClickable("wait while 'add state' button be clickable", addStateLink);
        click("click 'add state' button", addStateLink);
    }

    public void clickStates(){
        waitToBeClickable("wait while 'states' button be clickable", statesButton);
        click("click 'states' button", statesButton);
    }

    public boolean isAddStateLinkPresent(){
        return isPresent("Check for 'add state' link is present", defaultLink);
    }

    public void clickDefaultLink(){
        waitToBeClickable("wait while 'default' button be clickable", defaultLink);
        click("click 'default' button", defaultLink);
    }


    public boolean isDefaultLinkLinkPresent(){
        return isPresent("Check for 'default' link is present", addStateLink);
    }

    public boolean isStatesButtonPresent(){
        return isPresent("Check for 'states' button is present", statesButton);
    }



    public void waitEditDashboardButton() {
        waitToBeClickable("Waiting for 'Edit dashboard' button to be clickable", editDashboardButton);
    }

    public boolean isEditDashboardButtonVisibility() {
        return isVisible(ELEMENT_EXTRASMALL_TIMEOUT_SECONDS, "Checking if 'Edit' button present", editDashboardButton);
    }

    public boolean isViewButtonPresent() {
        return isPresent(ELEMENT_EXTRASMALL_TIMEOUT_SECONDS, "Checking if 'View' button present", viewButton);
    }

    public void waitEditDashboardButton(int timeout) {
        waitForVisibility(timeout, "Waiting for 'Edit dashboard' button visibility", editDashboardButton);
    }

    public void clickEditDashboard() {
        click("Clicking 'Edit dashboard' button", editDashboardButton);
    }

    public void waitAddButtonToBeClickable() {
        waitToBeClickable("Waiting for 'Add' button to be clickable", addButton);
    }

    public void clickAddButton() {
        click("Clicking 'Add' button", addButton);
    }

    public void waitForAddMenuVisibility() {
        waitForVisibility("Waiting for 'Add menu' visibility", addMenu);
    }

    public void clickOnItem(String itemName) {
        click("Clicking on item: " + itemName, addMenuItemByNamePattern, itemName);
    }

    public void waitEditWidgetButtonToBeClickable() {
        waitToBeClickable("Waiting for 'Edit Widget' button to be clickable", editWidgetButton);
    }

    public void clickEditWidgetButton() {
        click("Clicking 'Edit widget' button", editWidgetButton);
    }

    public void waitViewButtonToBeClickable() {
        waitToBeClickable("Waiting for 'View' button to be clickable", viewButton);
    }

    public void clickViewButton() {
        waitForVisibility("Wait for 'View' button visibility", viewButton);
        click("Clicking 'View' button", viewButton);

    }

    public void waitRemoveButtonToBeClickable() {
        waitToBeClickable("Waiting for 'Remove' button to be clickable", removeButton);
    }

    public void clickRemoveButton() {
        click("Clicking 'Remove' button", removeButton);
    }

    public void acceptAlert() {
        click("Clicking 'Ok' button on remove alert", acceptRemoveWidgetButton);
    }
}
