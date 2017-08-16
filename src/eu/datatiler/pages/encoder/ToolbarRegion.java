package eu.datatiler.pages.encoder;

import com.qatestlab.base.BasePage;
import com.qatestlab.base.Locator;
import com.qatestlab.base.LocatorTypes;

/**
 * Created by Petro on 07.03.2016.
 */
public class ToolbarRegion extends BasePage {

    private Locator toolbarBlock = new Locator(LocatorTypes.XPATH,
            "//div[contains(@class, 'encoderPage ')]/div[contains(@class, 'mainContent')]//div[@layout='row']");

    private Locator toolbarTab = new Locator(LocatorTypes.XPATH,
            "//md-tab-item/span[text()='%s']");

    private Locator switchSimilarShow = new Locator(LocatorTypes.XPATH,
            "//md-switch//div[@class='md-container']");

    private Locator dictionaryExportConfirmationBar = new Locator(LocatorTypes.XPATH,
            "//div[contains(@class, 'alert')]//div[text()='OK']");

    private Locator encoderDictionaryButton = toolbarBlock.concat(new Locator(LocatorTypes.XPATH,
            "//button[@id='encoderDictionaryBtn']"));

    public void waitToLoad() {
        waitForVisibility("Wait for tool bar to be loaded", toolbarBlock);
    }

    public void clickSwitchSimilarShow() {
        waitToBeClickable("Wait for similar show switch to be clickable", switchSimilarShow);
        click("Click on  similar show switch", switchSimilarShow);
    }

    public void clickEncoderDictionaryButton() {
        waitToBeClickable("Wait for encoder dictionary button to be clickable", encoderDictionaryButton);
        click("Click on encoder dictionary button", encoderDictionaryButton);
    }

    public void clickToolbarTab(String tabName) {
        waitToBeClickable("Wait for tab '" + tabName + "' to be clickable", toolbarTab, tabName);
        click("Click on tab '" + tabName + "'", toolbarTab, tabName);
    }

    public void waitExportDictionaryConfirmationBar() {
        waitForVisibility("Wait for confirmation exporting dictionary to be visible ", dictionaryExportConfirmationBar);
    }
}