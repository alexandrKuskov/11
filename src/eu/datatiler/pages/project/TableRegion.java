package eu.datatiler.pages.project;

import com.qatestlab.base.*;
import com.qatestlab.properties.BrowserTypes;
import com.qatestlab.properties.Properties;
import com.qatestlab.reporting.Reporter;
import org.openqa.selenium.*;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.interactions.Actions;

import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

/**
 * Created by Petro on 24.03.2016.
 */
public class TableRegion extends BasePage {
    private Locator calculateButton = new Locator(LocatorTypes.XPATH,
            "//div[contains(@class, 'dvi-toolbar')]/*[contains(@class, 'dvi-calculate')]");

    private Locator filterBodyField = new Locator(LocatorTypes.XPATH,
            "//div[@class='dvi-filter-body']");

    private Locator expressionBuilderFirstField = new Locator(LocatorTypes.XPATH,
            "//any//li[2]//div/div/span[2]");

    private Locator filterValue = new Locator(LocatorTypes.XPATH,
            "//div[@class='dvi-filter-body']//span");

    private Locator region = new Locator(LocatorTypes.XPATH,
            "//div[contains(@class, 'variables-tree-container')]");

    private Locator categoryByNamePattern = region.concat(new Locator(LocatorTypes.XPATH,
            "//span[text()='%s']"));

    private Locator categoryByNamePatternTest = new Locator(LocatorTypes.XPATH,
            "//span[text()='%s']");

    private Locator resetFilterButton = new Locator(LocatorTypes.XPATH,
            "//div[@class='dvi-filter-reset']");

    private Locator crossTabRows = new Locator(LocatorTypes.XPATH,
            "//div[@class='rows-view']//canvas[contains(@class, 'upper-canvas')]");

    private Locator crossTabColumns = new Locator(LocatorTypes.XPATH,
            "//div[@class='columns-view']//canvas[contains(@class, 'upper-canvas')]");

    private Locator expressionBuilderRegion = new Locator(LocatorTypes.XPATH,
            "//div[@class='dropRootZone']");

    private Locator logicalExpressionButtons = new Locator(LocatorTypes.XPATH,
             "//button/div/span[text()='%s']");

    private Locator expressionType =  new Locator(LocatorTypes.XPATH,
            "//span[text()='%s']");

    private Locator buttomExpressionType = new Locator(LocatorTypes.XPATH, "//button[@tabindex='0']/div/span[text()='%s']");

    private Locator itemExpression = new Locator(LocatorTypes.XPATH,
            "//div[@class='droppable-filters']//ul//ul/li[%s]/div/div/span[@class='fa fa-check item-modifier']");

    private Locator itemExpressionText = new Locator(LocatorTypes.XPATH,
            "//div[@class='droppable-filters']//ul//ul/li[%s]/div/div/div/any[2]/span[@class='modifier']");

    private Locator currentLogicalType = new Locator(LocatorTypes.XPATH,
            "//div[@class='droppable-filters']//span[@class='modifier']");

    private Locator footerButtonExprBuilder = new Locator(LocatorTypes.XPATH,
            "//button[div[div[span[text()='%s']]]]");

    private Locator canvasValueNameField = new Locator(LocatorTypes.XPATH,
            "//input[@class='name-editor']");

    private Locator elementsLogicalExpression = new Locator(LocatorTypes.XPATH,
            "//div[@class='droppable-filters']/div/ul/li/div/div/div/any[2]/span");

    private Locator dropableItem = new Locator(LocatorTypes.XPATH,
            "//div[contains(@class,'node-container')]/div/span[@class='collapse-btn']");

    private Locator dviRegion = new Locator(LocatorTypes.XPATH,
            "//div[@class='dvi-grid-component']");

    private Locator disabledChart = new Locator(LocatorTypes.XPATH,
            "//div[@class='dvi-panel-right']//button[contains(text(), 'Chart') and @disabled='disabled']");

    private Locator activeChart = new Locator(LocatorTypes.XPATH,
            "//div[@class='dvi-panel-right']//button[contains(text(), 'Chart')]");


    private Locator defaultChose = new Locator(LocatorTypes.XPATH,
            "//div[@value='0'][div[button[@class='ChartConfigTab']]]");

    private Locator gradSchoolItemChart = new Locator(LocatorTypes.XPATH,
            "//*[name()='svg']//*[name()='rect' and @height='187']");

    private Locator gradSchoolItem = new Locator(LocatorTypes.XPATH,
            "//*[./*[contains(text(),'Grad School')]]");

    private Locator spednemEducationItemChart = new Locator(LocatorTypes.XPATH,
            "//*[name()='svg']//*[name()='rect' and @height='60']");

    private Locator spednemEducationItem = new Locator(LocatorTypes.XPATH,
            "//*[./*[contains(text(),'Spednem Education')]]");

    private Locator highEducationItemChart = new Locator(LocatorTypes.XPATH,
            "//*[name()='svg']//*[name()='rect' and @height='199']");

    private Locator highEducationItem = new Locator(LocatorTypes.XPATH,
            "//*[./*[contains(text(),'High Education')]]");

    private Locator difficultToAnswerItemChart = new Locator(LocatorTypes.XPATH,
            "//*[name()='svg']//*[name()='rect' and @height='18']");

    private Locator difficultToAnswerItem = new Locator(LocatorTypes.XPATH,
            "//*[./*[contains(text(),'Difficult to answer')]]");

    private Locator chartSchemaPresent = new Locator(LocatorTypes.XPATH,
            "//dvi[@id='chart']");

    private Locator seriesButton = new Locator(LocatorTypes.XPATH,
            "//button[@class='ChartConfigTab']/div/div[text()='Series']");

    private Locator seriesGroupName = new Locator(LocatorTypes.XPATH,
            "//input[@type='text' and @value='%s']");

    private Locator seriesGroupItem = new Locator(LocatorTypes.XPATH,
            "//*[@value='%s']");

    private Locator underButton = new Locator(LocatorTypes.XPATH,
            "//button[@tabindex='0']/div/span[text()='%s']");


    protected TableRegion() {
    }

    public void clickOnSomePlace() {
        click("click on some place on thge page", gradSchoolItem);
    }


    public void editSeriesName(String oldName, String newName) throws AWTException {
        waitForVisibility("wait for '" + oldName + "' series name visibility", seriesGroupItem, oldName);
        click("click on " + oldName + " button", seriesGroupItem, oldName);
        type("typing '" + newName + "' into '" + oldName + "' serial  name", newName, seriesGroupItem, oldName);
    }

    public boolean isButtonPresent(String buttonName) {
        waitForVisibility("wait for button '" + buttonName + "' is present", underButton, buttonName);
        return isPresent("check for button '" + buttonName + "' is present", underButton, buttonName);

    }

    public boolean isSeriesGroupItemPresent(String itemName) {
        return isPresent("check for serious group with '" + itemName + "' name is present", seriesGroupItem, itemName);
    }

    public boolean isSeriesGroupNamePresent(String name) {
        waitForVisibility("wait for serious group with '" + name + "' name visibility", seriesGroupName, name);
        return isPresent("check for serious group with '" + name + "' name is present", seriesGroupName, name);
    }

    public boolean isDesignChartPageOpen() {
        waitForVisibility("wait for design chart page visibility", chartSchemaPresent);
        return isPresent("check for design page", chartSchemaPresent);
    }

    public boolean isGradSchoolChartAndItemPresent() {
        return isPresent("check for 'grad school' item", gradSchoolItem);
    }

    public boolean isSpednemEducationChartAndItemPresent() {
        return isPresent("check for 'spednem education' item", spednemEducationItem);
    }

    public boolean isHighEducationChartAndItemPresent() {
        return isPresent("check for 'high education' item", highEducationItem);
    }

    public boolean isDifficultToAnswerChartAndItemPresent() {
        return isPresent("check for 'difficult to answer' item", difficultToAnswerItem);
    }


    public boolean isChartDefaultChose() {
        waitForVisibility("wait for  chosen 'Chart'  ", defaultChose);
        return isPresent("check chosen 'Chart' button", defaultChose);
    }

    public boolean isChartDisabled() {
        waitForVisibility("", disabledChart);
        return isPresent("check disable chart button", disabledChart);
    }

    public boolean isChartActive() {
        waitForVisibility("", activeChart);
        return isPresent(" check active chart button", activeChart);
    }

    public void clickChartButton() {
        click("Click 'chart' button", activeChart);
    }

    public void clickSeriesButton() {
        click("Click 'series' button", seriesButton);
    }

    public void waitForLoad() {
        waitForVisibility("Waiting for DVI page to load", calculateButton);
    }

    public void waitFilterBody() {
        waitForVisibility("Waiting for 'Filer' body", calculateButton);
    }

    public void clickCalculateButton() {
        waitForVisibility("Waiting for 'Calculate' button visibility", calculateButton);
        click("click 'Calculate' button", calculateButton);
    }

    public void setFilter(String itemName) {
        dragAndDrop("Drag and drop item to row: " + itemName, categoryByNamePatternTest, filterBodyField, itemName);
    }

    public String getFilterValue() {
        return getText("Get filter value", filterValue);
    }

    public void clickResetFilterButton() {
        waitToBeClickable("Wait 'Reset filter' button to be clickable", resetFilterButton);
        click("Click 'Reset filter' button", resetFilterButton);
        BaseActions.wait(3);

    }

    public void clickOnExpressionBuilderIcon() {
        switch (Properties.getBrowser()) {

            case CHROME:

                int height = Integer.parseInt(getAttributeValue("Get height canvas rows", "height", crossTabRows).trim());

                int weight = Integer.parseInt(getAttributeValue("Get width canvas rows", "width", crossTabRows).trim());

                moveToElement("Click on expression builder icon", crossTabRows,
                        weight - ((weight * 10) / 90), height / 2 + (height / 2) / 2).click()
                        .perform();
                break;

            default:
                Robot robot = null;
                try {
                    robot = new Robot();
                } catch (AWTException e) {
                    e.printStackTrace();
                }

                WebElement fromElement = driver().findElement(crossTabRows.getLocator());
                Point p = fromElement.getLocation();
                int x = p.getX();
                int y = p.getY();
                Dimension d = fromElement.getSize();
                int h = d.getHeight();
                int w = d.getWidth();
                Reporter.log("  " + w);

                robot.mouseMove(x + (w - 12), y + (h / 3) + 80);
                robot.mousePress(InputEvent.BUTTON1_MASK);
                robot.mouseRelease(InputEvent.BUTTON1_MASK);
                break;
        }
    }

    public void clickOnExpressionBuilderIconTest() {

        int y = Integer.parseInt(getAttributeValue("Get height canvas rows", "height", crossTabRows).trim());

        int x = Integer.parseInt(getAttributeValue("Get width canvas rows", "width", crossTabRows).trim());

        //TODO
        moveToElement("Click on expression builder icon", crossTabRows,
                x - ((x * 10) / 100), y / 2 + (y / 2) / 2)
                .perform();
    }


    public void clickOnVariableName() {
        waitForVisibility("", crossTabRows);
        waitToBeClickable("", crossTabRows);

        switch (Properties.getBrowser()) {
            case FIREFOX:
                WebElement canvasElement = driver().findElement(crossTabRows.getLocator());
                Point p = canvasElement.getLocation();
                int x = p.getX();
                int y = p.getY();
                Dimension d = canvasElement.getSize();
                int h = d.getHeight();
                int w = d.getWidth();

                Robot r = null;
                try {
                    r = new Robot();
                } catch (AWTException e) {
                    e.printStackTrace();
                }
                r.mouseMove(x + (w / 5), y + (h / 2) + 80);
                r.mousePress(InputEvent.BUTTON1_MASK);
                r.mouseRelease( InputEvent.BUTTON1_MASK );
                r.mousePress(InputEvent.BUTTON1_MASK);
                r.mouseRelease( InputEvent.BUTTON1_MASK );
                BaseActions.wait(5);

                break;

            default:
                int height = Integer.parseInt(getAttributeValue("Get height canvas rows", "height", crossTabRows).trim());

                int width = Integer.parseInt(getAttributeValue("Get width canvas rows", "width", crossTabRows).trim());

                //TODO
                moveToElement("Click on cross tab variable name", crossTabRows, width - ((width * 90) / 100), height / 2 + (height / 2) / 2)
                        .doubleClick().perform();
                BaseActions.wait(5);
                break;

        }


    }

    public void setCrossTabVariableName(String varName) {
        type("Set variable name '" + varName + "' to cross tab", varName, canvasValueNameField);
    }

    public String getVariableName() {
        return getText("Get cross tab variable name", canvasValueNameField);
    }

    public boolean isLogicalExpressionButtonsPresent(String andButton, String orButton) {
        return isPresent("Expression buttons is present in expression builder", logicalExpressionButtons, andButton) &
                isPresent("Expression buttons is present in expression builder", logicalExpressionButtons, orButton);
    }

    public void clickOnExpressionType(String exprType, int... args) {
        Reporter.log("Click on '" + exprType + "' type expression");

        if (args.length > 0) {
            if (args[0] == 2) {
                waitToBeClickable("Wait expression buttons", buttomExpressionType, exprType);
                click("Click on '" + exprType + "' expression type", buttomExpressionType, exprType);
            } else {
                waitToBeClickable("Wait expression buttons", expressionType, exprType);
                click("Click on '" + exprType + "' expression type", expressionType, exprType);
            }
        } else {
            waitToBeClickable("Wait expression buttons", expressionType, exprType);
            click("Click on '" + exprType + "' expression type", expressionType, exprType);
        }
    }


    public String getCurrentLogicalType() {
        return getText("Get current  logical type", currentLogicalType);
    }

    public void setNoExprTypeForItemByIndexInExpressionBuilder(int itemIndex) {
        click("Click ", itemExpression, itemIndex);
    }

    public String getExprTypeItemByIndexInExpressionBuilder(int itemIndex) {
        return getText("Get expression type item", itemExpressionText, itemIndex);
    }

    public void waitExpressionRegion() {
        waitForVisibility("Wait to expression builder region to be visible", expressionBuilderRegion);
    }

    public boolean isExpressionRegionVisible() {
        return isPresent("Expression builder region is present", expressionBuilderRegion);
    }

    public void waitForExpressionBuilderInvisible() {
        waitForInvisibility("Wait for 'Expression builder' invisible", expressionBuilderRegion);
    }

    public void fillExpressionBuilderRegion(String itemName, int... args) {
        if (args.length > 0) {
            if (args[0] == 2) {
                dragAndDrop("Set expression in expression builder", categoryByNamePatternTest, expressionBuilderFirstField, itemName);
            } else {
                dragAndDrop("Set expression in expression builder", categoryByNamePatternTest, expressionBuilderRegion, itemName);
            }
        } else {
            dragAndDrop("Set expression in expression builder", categoryByNamePatternTest, expressionBuilderRegion, itemName);
        }
    }

    public void clickOnExpressionBuilderButton(String buttonText) {
        waitToBeClickable("Wait '" + buttonText + "' to be clickable", footerButtonExprBuilder, buttonText);
        BaseActions.wait(3);
        clickJS("Click on '" + buttonText + "' button expression builder window", footerButtonExprBuilder, buttonText);
    }

    public boolean isCanvasEditFieldPresent() {
        return isPresent("Canvas value field is visible", canvasValueNameField);
    }

    public void clickDropableItem() {
        waitToBeClickable("Wait to drop icon", dropableItem);
        click("Click on drop icon", dropableItem);
    }

    public String getLogicalExpressionFromExpressionBuilder() {
        StringBuilder logExpression = new StringBuilder("");

        for (WebElement webElem : getElements("Elements logical expression", elementsLogicalExpression)) {
            logExpression.append(webElem.getText());
        }

        return logExpression.toString();
    }

    public boolean isDVIRegionVisible() {
        BaseActions.wait(3);
        return isVisible("Check is dvi region visible", dviRegion);
    }

    public boolean isFilterBodyFieldPresent() {
        return isPresent("filter field body is empty", filterValue);
    }

    public void escape() throws AWTException {
        Reporter.log("click 'escape' button");
        Robot robot = new Robot();
        robot.keyPress(KeyEvent.VK_ESCAPE);
        robot.keyRelease(KeyEvent.VK_ESCAPE);
    }

    public void pressEscapeOnVariableName(){
        Reporter.log("click 'escape' on the canvas variable");
        WebElement element = driver().findElement(canvasValueNameField.getLocator());
        element.sendKeys(Keys.ESCAPE);
    }

    public void enter() throws AWTException {
        Reporter.log("click 'enter' button");
        Robot robot = new Robot();
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
    }

    public void pressEnterOnCanvasField(){
        Reporter.log("click 'enter' on the canvas field");
        WebElement element = driver().findElement(canvasValueNameField.getLocator());
        element.sendKeys(Keys.ENTER);
    }

    public void pressEnterOnChartSerialName(String newName){
        Reporter.log("click 'enter' on the serial chart name");
        WebElement element = driver().findElement(seriesGroupItem.getLocator(newName));
        element.sendKeys(Keys.ENTER);
    }
}
