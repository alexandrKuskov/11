package eu.datatiler.pages.dashboard;

import com.qatestlab.base.BaseActions;
import com.qatestlab.base.BasePage;
import com.qatestlab.base.Locator;
import com.qatestlab.base.LocatorTypes;
import com.qatestlab.reporting.Reporter;
import org.openqa.selenium.JavascriptExecutor;

public class ControlPage extends BasePage {

    private Locator controlRegion = new Locator(LocatorTypes.XPATH,
            "//div[contains(@class, 'control-waves')]");

    private Locator clearCategoriesButton = new Locator(LocatorTypes.XPATH,
            "//div[2]//div/div[2]//div[contains(@class, 'clearfix ui-sortable')]");

    private Locator controlEditorRegion = new Locator(LocatorTypes.XPATH,
            "//div[contains(@class, 'control-editor')]");

    private Locator styleByValuePattern = new Locator(LocatorTypes.XPATH,
            "//div[input[@value='%s']]");

    private Locator styleByValuePattern1 = new Locator(LocatorTypes.XPATH,
            "//div[label[text()='Vertical']]/div/div[3]");

    private Locator availableSetNameCheckBox = new Locator(LocatorTypes.XPATH,
            "//input[@type='checkbox' and @name='visibleName']");

    private Locator controlStateButtonPattern = new Locator(LocatorTypes.XPATH,
            "//button[text()='%s']");

    private Locator addControlButton = new Locator(LocatorTypes.XPATH,
            "//button[@ng-click='controlEditor.save()']");

    private Locator removeCategoryByName = new Locator(LocatorTypes.XPATH,
            "//span[text()='%s']/following-sibling::span[contains(@class, 'glyphicon-remove')]");

    private Locator controlStateDropdownPattern = new Locator(LocatorTypes.XPATH,
            "//div[contains(@class, 'control-controls')]/div");

    private Locator controlStateValue = new Locator(LocatorTypes.XPATH,
            "//div[contains(@class, 'ui-select-choices-row')]//*[text()='%s']");

    private Locator inputFieldControlName = new Locator(LocatorTypes.XPATH,
            "//input[@name='name']");

    private Locator controlCategories = new Locator(LocatorTypes.XPATH,
            "//div[contains(@class, 'dashboard-control')]/div");

    protected ControlPage(){}

    public void waitForControlRegionLoad(){
        waitForPresence("Waiting for 'Clear selected categories' button visibility", clearCategoriesButton);
    }

    public void waitForControlEditorLoad(){
        waitForVisibility("Waiting for 'Edit' region visibility", controlEditorRegion);
    }

    public void selectStyle(String styleName){
        scrollToElement("scroll to '"+styleName+"' style mode", styleByValuePattern,styleName);
        BaseActions.wait(3);
        setCheckboxState("Selecting style: " + styleName, true, styleByValuePattern, styleName);
    }

    public void clickOnStyle(String styleName){
        scrollToElement("scroll to '"+styleName+"' style mode", styleByValuePattern,styleName);
        waitToBeClickable("Wait for style visibility: " + styleName, styleByValuePattern, styleName);
        click("click style: " + styleName, styleByValuePattern, styleName);
        BaseActions.wait(5);
        click("click style: " + styleName, styleByValuePattern, styleName);

    }

    public void selectButtonState(String stateName){
        click("Clicking state button: " + stateName, controlStateButtonPattern, stateName);
    }

    public void openStateDropdown(){
        click("Opening control state dropdown list ", controlStateDropdownPattern);
    }

    public void selectDropdownState(String stateName){
        click("Clicking control state value: " + stateName, controlStateValue, stateName);
    }

    public void waitAddButtonToBeClickable(){
        waitToBeClickable("Waiting for 'add' button to be clickable", addControlButton);
    }

    public void clickAddControl(){
        executeJS("Clicking 'Add control' button", "arguments[0].click()", addControlButton);
    }

    public void removeCategory(String name){
        clickJS("Clicking 'Remove category' button", removeCategoryByName, name);
    }

    public void setAvailableSetNameCheckBox(){
        setCheckboxState("Selecting checkbox for set name control: ", true, availableSetNameCheckBox);
    }

    public void setControlName(String name){
        type("Set name control", name, inputFieldControlName);
    }

    public boolean isVerticalOrientation(){
        return getAttributeValue("Get control category attribute", "class", controlCategories).contains("vertical");
    }


}
