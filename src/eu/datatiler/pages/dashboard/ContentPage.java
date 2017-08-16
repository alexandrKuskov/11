package eu.datatiler.pages.dashboard;

import com.qatestlab.base.*;
import com.qatestlab.properties.PropertiesNames;
import com.qatestlab.reporting.Reporter;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.internal.BaseAction;

import java.awt.*;
import java.awt.event.InputEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ContentPage extends BasePage {

    private Locator dashboard = new Locator(LocatorTypes.XPATH,
            "//dashboard//items");

    private Locator table = new Locator(LocatorTypes.XPATH,
            "//div[contains(@class, 'dvi-grid-cmp')]");

    private Locator mySpaceLink = new Locator(LocatorTypes.LINK_TEXT,
            "My space");

    private Locator growlBar = new Locator(LocatorTypes.XPATH,
            "//div[@class='growl']");

    private Locator closeButtonGrowlBar = new Locator(LocatorTypes.XPATH
            ,
            "//button[@class='close']");
    private Locator buttonControlPattern = new Locator(LocatorTypes.XPATH,
            "//div[contains(@class, 'control-controls')]/div/button[text()='%s']");

    private Locator singleDropdownControlPattern = new Locator(LocatorTypes.XPATH,
            "//span[@class='select2-chosen' and text()='%s']");

    private Locator multiDropdownControlPattern = new Locator(LocatorTypes.XPATH,
            "//li[@class='select2-search-choice']/div[text()='%s']");

    private Locator dropdownChooseControlPattern = new Locator(LocatorTypes.XPATH,
            "//li[contains(@class,'select2-results__option') and text()='%s']");

    private Locator controlByStatePattern = new Locator(LocatorTypes.XPATH,
            "//div[contains(@class, 'dashboard-control') and //button[text()='%s']]");

    private Locator viewButton = new Locator(LocatorTypes.XPATH,
            "//button//span[contains(text(), 'View')]");


    private Locator dropdownControlList = new Locator(LocatorTypes.XPATH,
            "//li[@class='select2-search-field']");

    private Locator dropdownControlField = new Locator(LocatorTypes.XPATH,
            "//div[@class='control-controls DropDown']");


    private Locator controlsInEditMode = new Locator(LocatorTypes.XPATH,
            "//div[contains(@id, 'widget') and .//div[text()='%s']]");

    private Locator controlByNamePattern = new Locator(LocatorTypes.XPATH,
            "//div[text()='%s']/following-sibling::div/button[text()='%s']");

    private Locator controlRegionByNamePattern = new Locator(LocatorTypes.XPATH,
            "//div[contains(@class, 'widgets')]/item[2]");

    private Locator widgetsArea = new Locator(LocatorTypes.XPATH,
            "//div[@class='widgets']");

    private Locator controlStatesByName = new Locator(LocatorTypes.XPATH,
            "//div[text()='%s']/following-sibling::div/button");

    private Locator widgetHighChartHeightByName = new Locator(LocatorTypes.XPATH,
            "//div[contains(@class, 'widget-el-container') and .//pre[text()='%s']]" +
                    "//*[@data-assignment='rows']//div[contains(@class, 'uat_col_name')]");

    private Locator tableWidgetRows = new Locator(LocatorTypes.XPATH,
            "//div[@class='rows-view']//*[@class='canvas-container']");

    private Locator textWidgetContent = new Locator(LocatorTypes.XPATH,
            "//div[contains(@class, 'text-widget') and contains(@class, 'content')]/p[1]");

    private Locator textWidgetRegionPattern = new Locator(LocatorTypes.XPATH,
            "//item[contains(@class, 'text') and contains(@class, 'widget')]");

    private Locator textWidgetStatusAttribute = new Locator(LocatorTypes.XPATH,
            "//item[contains(@class, 'text') and contains(@class, 'widget')]/div/following-sibling::div");

    private Locator textWidgetPattern = new Locator(LocatorTypes.XPATH,
            "//div[contains(@class, 'text-widget') and //h%s[contains(text(), '%s')]]");

    private Locator textWidgetLabel = new Locator(LocatorTypes.XPATH,
            "//item[div[div[p[text()='%s']]]]");

    private Locator imageWidget = new Locator(LocatorTypes.XPATH,
            "//div[contains(@class, 'image-widget')]//img");

    private Locator dashboardName = new Locator(LocatorTypes.XPATH,
            "//div[@class='title']/h2[text()='%s']");

    private Locator widgetTitle = new Locator(LocatorTypes.XPATH,
            "//div[contains(@class, 'title')]//pre[text()='%s']");

    private Locator widgetMainContent = new Locator(LocatorTypes.XPATH,
            "//div[@class='dvi-grid-component']");

    private Locator controlRoleRegion = new Locator(LocatorTypes.XPATH,
            "//div[contains(@class, 'control-roles')]");

    private Locator tableWidgetColumns = new Locator(LocatorTypes.XPATH,
            "//div[@class='columns-view']//*[@class='canvas-container']/canvas[2]");

    private Locator chartWidget = new Locator(LocatorTypes.XPATH,
            "//*[@class='highcharts-grid' and *]");

    private Locator tableRegionViewMode = new Locator(LocatorTypes.XPATH,
            "//div[@class='overlay-container']/div");

    private Locator tableRegionEditMode = new Locator(LocatorTypes.XPATH,
            "//item[contains(@class, 'table') and @data-widget='item']");

    private Locator controlRoleButton = controlRoleRegion.concat(new Locator(LocatorTypes.XPATH,
            "//a[text()='%s']"));

    private Locator controlButtons = new Locator(LocatorTypes.XPATH,
            "//div[contains(@class, 'control-controls ')]//button");

    private Locator controlRolesRegion = new Locator(LocatorTypes.XPATH,
            "//div[@class='control-roles']");

    private Locator fulFillTextWidget = new Locator(LocatorTypes.XPATH,
            "//div[@class='text-widget content']/p[text()='%s']");

    private Locator confirmationPopUpWindow = new Locator(LocatorTypes.XPATH,
            "//div[h3[text()='Remove an assignment?']]");

    private Locator blueWidgetField = new Locator(LocatorTypes.XPATH,
            "//div[@class='widgets']/item[3]");

    private Locator activeTextWidget = new Locator(LocatorTypes.XPATH,
            "//item[contains(@class,'text ui-resizable ng-touched')]");

    private Locator inactiveWidgetField = new Locator(LocatorTypes.XPATH,
            "//item[contains(@class,'ng-untouched ng-valid text')]");

    private Locator inactiveTable = new Locator(LocatorTypes.XPATH,
            "//item[contains(@class,'ng-untouched ng-valid table')]");

    private Locator textWidgetField = new Locator(LocatorTypes.XPATH,
            "//item[contains(@class,'ng-valid text ui-resizable')]");

    private Locator assignAsStatesControllerHeader  = inactiveWidgetField.concat(new Locator(LocatorTypes.XPATH,
            "//h2[text()='Assign as states controller']"));

    private Locator assignAsStatesControllerButton  = inactiveWidgetField.concat(new Locator(LocatorTypes.XPATH,
            "//a[text()='Assign']"));

    private Locator controlState = new Locator(LocatorTypes.XPATH,
            "//div[@class='control-controls Button']//button[text()='%s']");

    private Locator dropdownExpandedArea = new Locator(LocatorTypes.XPATH,
            "//span[@class='selection']/span[@aria-expanded='true']");

    private Locator controlItem = new Locator(LocatorTypes.XPATH,
            "//item[contains(@class,'ng-valid control')]");

    private Locator addControlButton = new Locator(LocatorTypes.XPATH,
            "//span[contains(@ng-if, 'AddControl')]/button");

    protected ContentPage() {
    }




    public void clickControlDropdownField(){
        waitForVisibility("wait while dropdown field be visible",dropdownControlField);
        click("click dropdown field",dropdownControlField);
    }

    public boolean isControlDropdownFieldPresent(){
       return isPresent("check for dropdown field",dropdownControlField);
    }

    public boolean isExpandedAreaPresent(){
      return  isPresent("check for dropdown expanded area", dropdownExpandedArea);
    }

    public void clickDropdownOption(String option){
        waitForVisibility("wait while dropdown '" + option + "' option be visible",dropdownChooseControlPattern, option);
        BaseActions.wait(5);
        click("click dropdown '" + option + "' option",dropdownChooseControlPattern, option);
    }

    public boolean isTextWidgetTouched(){
        return isPresent("check for touched text widget", activeTextWidget);
    }

    public boolean isTextWidgetUntouched(){
        return isPresent("check for untouched text widget", inactiveWidgetField);
    }


    public void clickOnInactiveTextWidget(){
        waitForVisibility("wait while text widget be visible",inactiveWidgetField);
        click("click on text widget",inactiveWidgetField);

    }
    public void clickOnBlueTextWidget(){
        waitForVisibility("wait while text widget be visible",blueWidgetField);
        click("click on text widget",blueWidgetField);
    }

    public void clickOnDefaultTouchedTextWidget(){
        /*
        waitForVisibility("wait while text widget be visible",inactiveWidgetField);
        /*
        WebElement element = driver().findElement(inactiveWidgetField.getLocator());
        Point p =  element.getLocation();
        int x = p.getX();
        int y = p.getY();
        Dimension d =  element.getSize();
        int h = d.getHeight();
        int w = d.getWidth();

        Robot robot = null;
        try {
            robot = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }
        robot.mouseMove(x + (w/2 + 400), y + (h/2) + 80);
        robot.mousePress(InputEvent.BUTTON1_MASK);
        robot.mouseRelease(InputEvent.BUTTON1_MASK);
        */
        clickOnInactiveTextWidget();

    }

    public void doubleClickOnTextWidgetWithAction(){
        doubleClick("double click on text widget",textWidgetField);
    }

    public void doubleClickOnTextWidget(){
        /*
        WebElement element = driver().findElement(textWidgetField.getLocator());
        Point p =  element.getLocation();
        int x = p.getX();
        int y = p.getY();
        Dimension d =  element.getSize();
        int h = d.getHeight();
        int w = d.getWidth();

        Robot r = null;
        try {
            r = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }
        r.mouseMove(x + (w / 2), y + (h / 2) + 80);
        r.mousePress(InputEvent.BUTTON1_MASK);
        r.mouseRelease( InputEvent.BUTTON1_MASK );
        r.mousePress(InputEvent.BUTTON1_MASK);
        r.mouseRelease( InputEvent.BUTTON1_MASK );
        BaseActions.wait(2);
        */
        waitForVisibility("wait for text widget be visible",textWidgetField);
        WebElement secondElement = driver().findElement(textWidgetField.getLocator());
        Actions builder = new Actions(driver());
        builder.doubleClick(secondElement).build().perform();
    }

    public boolean isConfirmationWindowPresent(){
        return isPresent("Check for confirmation pop-up window", confirmationPopUpWindow);
    }

    public void clickAssignButton(){
        waitToBeClickable("wait while 'Assign' button be clickable",assignAsStatesControllerButton);
        click("click 'Assign' button",assignAsStatesControllerButton);

    }

    public boolean isAssignHeaderPresent(){
        return isPresent("Check for 'Assign as states controller' header", assignAsStatesControllerHeader);
    }

    public boolean isAssignButtonOnTextWidgetPresent(){
        return isPresent("Check for 'Assign' button", assignAsStatesControllerButton);
    }



    public boolean isFulFilledTextWidgetPresent(String text){
        return isPresent("Check for fulfill text widget", fulFillTextWidget, text);
    }


    public void waitForDashboardVisibility() {
        waitForVisibility("Waiting for 'Dashboard' visibility", dashboard);
    }

    public boolean isDashBoardOpened() {
        return isVisible("Check is dashboard opened", dashboard);
    }

    public boolean isTablePresent() {
        return isPresent(10, "Checking table presence", table);
    }

    public void waitMySpaceLinkVisibility() {
        waitForVisibility(10, "Waiting for 'My space' link visibility", mySpaceLink);
    }

    public void waitGrowlBarVisibility() {
        waitForVisibility("Waiting for 'Growl' bar visibility", growlBar);
    }

    public void clickCloseButtonGrowlBar() {
        click("Click 'Close' button growl bar ", closeButtonGrowlBar);

    }

    public void waitMySpaceLinkClickable() {
        waitToBeClickable("Waiting for 'My space' link clickable", mySpaceLink);
    }

    public void clickMySpaceLink() {
        click("Click 'My space' link", mySpaceLink);
    }


    public boolean isButtonControlStateActive(String stateName) {
        String classAttribute = getAttributeValue("Getting 'class' attribute from control", "class",
                buttonControlPattern, stateName);

        return classAttribute.contains("active");
    }

    public void clickControlByName(String controlName) {
        BaseActions.wait(2);
        waitToBeClickable("wait while control with name '"+controlName+"' be clickable", buttonControlPattern, controlName);
        click("Click '" + controlName + "' control", buttonControlPattern, controlName);
    }

    public boolean isButtonControlStateActive(WebElement element) {
        String classAttribute = getAttributeValue("Getting 'class' attribute from control", "class", element);
        return classAttribute.contains("active");
    }

    public boolean isMultiDropdownControlStateActive(String stateName) {
        boolean present = isPresent("Checking if dropdown control with multi select has active state: " + stateName,
                multiDropdownControlPattern, stateName);

        return present;
    }

    public boolean isSingleDropdownControlStateActive(String stateName) {
        boolean present = isPresent("Checking if dropdown control with single select has active state: " + stateName,
                singleDropdownControlPattern, stateName);

        return present;
    }

    public void selectButtonControl(String state) {
        click("Selecting control with button: " + state, controlByStatePattern, state);
    }

    public void selectDropdownControl(String state) {
        waitToBeClickable("Wait", controlsInEditMode, state);
        click("Selecting dropdown control", controlsInEditMode, state);
    }

    public boolean isControlStatePresent(String name) {
        return isPresent("Checking if button control present", controlByStatePattern, name, name, name);
    }

    public boolean isSingleDropdownControlPresent(String name) {
        return isPresent("Checking if dropdown control with present", singleDropdownControlPattern, name);
    }

    public void openDropdownControlList() {
        click("Opening 'Dropdown control' list", dropdownControlList);
    }


    public void selectControlState(String controlName, String stateName) {
        click(String.format("Selecting '%s' state in '%s'", stateName, controlName), controlByNamePattern,
                controlName, stateName);
    }

    public List<WebElement> getButtonControlStates(String controlName) {
        return getElements("Getting all buttons from control: " + controlName, controlStatesByName, controlName);
    }

    public String getWidgetHeightAttribute(String widgetTitle) {
        String height;
        try {
            height = getAttributeValue("Getting highchart's 'height' attribute value from widget with id: " +
                    widgetTitle, "height", widgetHighChartHeightByName, widgetTitle);

        } catch (StaleElementReferenceException e) {
            height = getAttributeValue("Getting highchart's 'height' attribute value from widget with id: " +
                    widgetTitle, "height", widgetHighChartHeightByName, widgetTitle);
        }
        Reporter.log("'height' value = " + height);
        return height;
    }

    public void getScreenshotCrosstabRow(String screenshotName) throws Exception {

        waitForVisibility("Wait for cross tab rows", tableWidgetRows);

        FileUtils.copyFile(WebElementExtender.captureElementBitmap(driver().findElement(tableWidgetRows.getLocator())),
                new File(System.getProperty(PropertiesNames.SCREENSHOT_DIR.toString()) + File.separator + screenshotName + ".png"));
    }

    public boolean isTextPresent(String text, String headingNum) {
        boolean present = isPresent("Checking if text widget present", textWidgetPattern, headingNum, text);
        return present;
    }

    public boolean isTextLabelPresent(String textLabel){
        return isPresent("Checking if text widget present", textWidgetLabel, textLabel);
    }



    public void selectTextWidget(String text) {
        click("Click on text widget", textWidgetRegionPattern, text);
    }

    public void selectCloneImage() {
        click("Click on clone image", controlRegionByNamePattern);
    }

    public void selectTableWidget() {
        click("Click on table widget", table);
    }

    public boolean isImageWidgetPresent() {
        boolean present = isPresent("Checking if image widget present", imageWidget);
        return present;
    }

    public Dimension getImageDimension() {
        Dimension dimension = getDimension("Getting image size", imageWidget);
        return dimension;
    }

    public boolean checkDashboardName(String exceptDashboardName) {
        Reporter.log(getText("", dashboardName, exceptDashboardName));
        return isPresent("Check dashboard " + exceptDashboardName + " visibility", dashboardName, exceptDashboardName);
    }

    public boolean isWidgetPresentWithTitle(String title) {
        return isPresent("Dashboard with title: " + title + "' is present", widgetTitle, title);
    }

    public boolean isWidgetPresent() {
        return isPresent("Widget main content is present", widgetMainContent);
    }

    public void waitControlRoleRegion() {
        waitForVisibility("Wait control role region", controlRoleRegion);
    }

    public void clickControlRoleButtonByName(String roleName) {
        waitToBeClickable("Wait for '" + roleName + "' button to be clickable", controlRoleButton, roleName);
        clickJS("Click '" + roleName + "' role", controlRoleButton, roleName);
    }

    public void selectControlByName() {
        waitToBeClickable("wait for 'Control' be clickable", controlRegionByNamePattern);
        scrollToElement("scroll to 'Control'", controlRegionByNamePattern);
        BaseActions.wait(3);
        click("Click on control 'Control'", controlRegionByNamePattern);
    }



    public void clickOnWidgetArea() {
        click("Click on widget area", widgetsArea);
    }

    public void clickOnEmptySpace(){
        Reporter.log("click om empty space");
        Robot robot = null;
        try {
            robot = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }

        WebElement fromElement = driver().findElement( textWidgetField.getLocator());
        Point p = fromElement.getLocation();
        int x = p.getX();
        int y = p.getY();
        Dimension d = fromElement.getSize();
        int h = d.getHeight();
        int w = d.getWidth();

        robot.mouseMove(x + (w/2 + 400), y + (h/2) + 80);
        robot.mousePress(InputEvent.BUTTON1_MASK);
        robot.mouseRelease(InputEvent.BUTTON1_MASK);

    }

    public boolean isTextWidgetSelected(){
        return getAttributeValue("Check is text widget selected", "class", textWidgetStatusAttribute).contains("cover active");
    }

    public String getTextWidgetContent(){
        return getText("Get text widget content", textWidgetContent);
    }

    public void getScreenshotCrossTab(String screenshotName) throws Exception {

        waitForPresence("Wait for cross tab rows", tableRegionViewMode);

        FileUtils.copyFile(WebElementExtender.captureElementBitmap(driver().findElement(tableRegionViewMode.getLocator())),
                new File(System.getProperty(PropertiesNames.SCREENSHOT_DIR.toString()) + File.separator + screenshotName + ".png"));
    }

    public void getScreenshotChartRegion(String screenshotName) throws Exception {

        waitForVisibility("Wait for cross tab rows", chartWidget);

        FileUtils.copyFile(WebElementExtender.captureElementBitmap(driver().findElement(chartWidget.getLocator())),
                new File(System.getProperty(PropertiesNames.SCREENSHOT_DIR.toString()) + File.separator + screenshotName + ".png"));
    }

    public void selectTable() {
        click("Click on table", tableRegionEditMode);
    }

    public void sortCrossTab(){

        if(BaseTest.isDriverFIREFOX){
            Robot robot = null;
            try {
                robot = new Robot();
            } catch (AWTException e) {
                e.printStackTrace();
            }

            WebElement fromElement = driver().findElement(tableWidgetColumns.getLocator());
            Point p = fromElement.getLocation();
            int x = p.getX();
            int y = p.getY();
            Dimension d = fromElement.getSize();
            int h = d.getHeight();
            int w = d.getWidth();
            Reporter.log("  " + w);

            robot.mouseMove(x + (w/2 -20), y + (h/2 +20) + 80);
            robot.mousePress(InputEvent.BUTTON1_MASK);
            robot.mouseRelease(InputEvent.BUTTON1_MASK);
        }

        int y = Integer.parseInt(getAttributeValue("Get height canvas rows", "height", tableWidgetColumns).trim());
        int x = Integer.parseInt(getAttributeValue("Get width canvas rows", "width", tableWidgetColumns).trim());

        moveToElement("Click on sort icon", tableWidgetColumns,
                x / 2 - (x / 2) / 2, y - ((y * 10) / 100)).click().perform();
    }

    public void hideControlRolesRegion() {
        ((JavascriptExecutor) driver()).executeScript(("arguments[0].setAttribute('style', 'display: none;')"),
                driver().findElement(controlRolesRegion.getLocator()));
    }

    public void displayControlRolesRegion() {
        ((JavascriptExecutor) driver()).executeScript(("arguments[0].setAttribute('style', 'display: block;')"),
                driver().findElement(controlRolesRegion.getLocator()));
    }

    public List<String> getControlNames() {
        List<String> listNames = new ArrayList<>();
        for (WebElement webElement : getElements("Get control names", controlButtons)) {
            listNames.add(webElement.getText());
        }
        return listNames;
    }

    public boolean checkControlAvailability(String state){
      return  isElementEnabledAndDisplayed("check for 'Control' availability", controlState, state);
    }


    public void moveTextWidget(){
        /*
        Robot robot = null;
        try {
            robot = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }

        WebElement fromElement = driver().findElement( inactiveWidgetField.getLocator());
        Point p = fromElement.getLocation();
        int x = p.getX();
        int y = p.getY();
        Dimension d = fromElement.getSize();
        int h = d.getHeight();
        int w = d.getWidth();


        robot.mouseMove(x + (w/2), y + (h/2) + 80);
        robot.delay(2000);
        robot.mousePress(InputEvent.BUTTON1_MASK);
        robot.delay(2000);
        robot.mouseMove(x + (w/2 + 160), y + (h/2) + 80);
        robot.delay(2000);
        robot.mouseRelease(InputEvent.BUTTON1_MASK);
        robot.delay(2000);
        */
        dragAndDrop("Drag and drop text widget to empty space", inactiveWidgetField, viewButton);
    }
    public void moveControl(){
        dragAndDrop("Drag and drop text widget to empty space", controlItem, addControlButton);
    }


}
