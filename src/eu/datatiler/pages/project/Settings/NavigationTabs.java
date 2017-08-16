package eu.datatiler.pages.project.Settings;

import com.qatestlab.base.BasePage;
import com.qatestlab.base.Locator;
import com.qatestlab.base.LocatorTypes;

/**
 * Created by Petro on 15.02.2016.
 */
public class NavigationTabs extends BasePage {

    private Locator tabGroup = new Locator(LocatorTypes.XPATH,
            "//div[contains(@class,'_navigationContainer')]");

    private Locator metaEditorTab = tabGroup.concat(new Locator(LocatorTypes.XPATH,
            "//a[text()='Meta-editor']"));

    private Locator dataTab = tabGroup.concat(new Locator(LocatorTypes.XPATH,
            "//a[text()='Data']"));

    private Locator filterTab = tabGroup.concat(new Locator(LocatorTypes.XPATH,
            "//a[text()='Filter']"));

    private Locator settingTab = tabGroup.concat(new Locator(LocatorTypes.XPATH,
            "//button/div/div[text()='Settings']"));

    private Locator activityTab = tabGroup.concat(new Locator(LocatorTypes.XPATH,
            "//a[text()='Activity']"));

    protected NavigationTabs() {
    }

    public void clickOnMetaEditorTab() {
        click("Click on 'Meta-editor' tab", metaEditorTab);
    }

    public void waitTabGroupToBeVisible() {
        waitForVisibility("Waiting group of tab to be visible", tabGroup);
    }

    public void clickOnDataTab() {
        click("Click on 'Data' tab", dataTab);
    }

    public void clickOnFilterTab() {
        click("Click on 'Filter' tab", filterTab);
    }

    public void clickOnSettingsTab() {
        waitToBeClickable("Wait settings tab to be clickable", settingTab);
        click("Click on 'Settings' tab", settingTab);
    }

    public void clickOnActivityTab() {
        click("Click on 'Activity' tab", activityTab);
    }

    public boolean isSettingsPageVisible(){
        return isVisible("Check is 'Settings' page opened", tabGroup);
    }
}
