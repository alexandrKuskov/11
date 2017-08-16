package eu.datatiler.pages.project;

import com.qatestlab.base.BaseActions;
import com.qatestlab.base.BasePage;
import com.qatestlab.base.Locator;
import com.qatestlab.base.LocatorTypes;

/**
 * Created by Petro on 24.03.2016.
 */
public class ChartRegion extends BasePage {

    private Locator chartContainer = new Locator(LocatorTypes.XPATH,
            "//*[local-name() = 'svg']//*[name()='g' and @class='highcharts-series-group']");

    private Locator chartToolBar = new Locator(LocatorTypes.XPATH,
            "//div[@id='panel']");

    private Locator chartToolBarOptionRegion = new Locator(LocatorTypes.XPATH,
            "//span[div[div[div[text()='%s']]]]");

    private Locator chartToolBarOptionItem = new Locator(LocatorTypes.XPATH,
            "//div[div[label[text()='%s']]]");

    private Locator optionRegion = new Locator(LocatorTypes.XPATH,
            "//div[span[div[div[div[text()='%s']]]]]");

    private Locator colorCheckbox = optionRegion.concat( new Locator(LocatorTypes.XPATH,
            "//td[text()='Text color']/preceding-sibling::td//div/button"));

    private Locator colorPopUpWindow = new Locator(LocatorTypes.XPATH,
            "//script[@src='/dvi/index.63a7da1c.js']/following-sibling::style//following-sibling::div/div");

    private Locator greenColorCheckbox = colorPopUpWindow.concat( new Locator(LocatorTypes.XPATH,
            "//div[@class='flexbox-fix']/following-sibling::div/following-sibling::div//div[div[contains(@style,'(0, 163, 0)')]]"));

    private Locator greenDataLabel = new Locator(LocatorTypes.XPATH,
           // "//*[local-name() = 'svg']//*[name()='g' and contains (@class,'highcharts-data-labels highcharts-series-1')]/*[name()='g' and @transform='translate(28,5)']/*[name()='text' and contains(@style,'0,163,0')]");
            "//*[name()='g' and contains (@class,'highcharts-data-labels highcharts-series-1')]//*[name()='text' and contains(@style,'0,163,0')]");



    protected ChartRegion() {
    }

    public void waitToLoad() {
        waitForVisibility("Wait for chart page to be visible", chartContainer);
    }

    public boolean isChartContainerPresent() {
        return isPresent(5, "Chart container is present", chartContainer);
    }

    public boolean isOptionRegionPresent(String optionName) {
        return isPresent(5, "option region with name '" +optionName+ "' is present", optionRegion, optionName);
    }

    public void clickOnOptionColorCheckbox(String optionName) {
        waitToBeClickable("wait for color checkbox be clickable", colorCheckbox, optionName);
        click("click on color checkbox ", colorCheckbox, optionName);
    }

    public boolean isChartToolBarPresent() {
        return isPresent(5, "Chart toolbar is present", chartToolBar);
    }

    public boolean isColorPopUpWindowVisible() {
        return isVisible(5, "Color pop-up window is visible", colorPopUpWindow);
    }

    public boolean isGreenDataLabelPresent() {
        BaseActions.wait(3);
        return isVisible("green data label is present", greenDataLabel);
    }

    public void clickOnChartToolBarOption(String optionName) {
        waitToBeClickable("wait for chart option '" + optionName + "' be clickable", chartToolBarOptionRegion, optionName);
        click("click on chart option '" + optionName + "' ", chartToolBarOptionRegion, optionName);
    }

    public void clickOnChartToolBarOptionItem(String itemName) {
            waitForVisibility("wait for chart option item '" + itemName + "' be visible", chartToolBarOptionItem, itemName);
            click("click on chart option item '" + itemName + "' ", chartToolBarOptionItem, itemName);
    }

    public void clickOnGreenColor() {
        BaseActions.wait(3);
        waitToBeClickable("wait for green color option be clickable ", greenColorCheckbox);
        click("click on green color option", greenColorCheckbox);
    }
}

