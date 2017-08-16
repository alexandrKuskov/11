package eu.datatiler.pages.main;

import com.qatestlab.base.BasePage;
import com.qatestlab.base.Locator;
import com.qatestlab.base.LocatorTypes;

public class SidebarRegion extends BasePage {

    private Locator region = new Locator(LocatorTypes.XPATH,
            "//div[contains(@class, 'sidebar')]");

    private Locator mySpaceFolder = region.concat(new Locator(LocatorTypes.XPATH,
            "//span[contains(@class,'uat_name') and text()=' My space']"));

    private Locator sharedWithMeFolder = region.concat(new Locator(LocatorTypes.XPATH,
            "//span[contains(@class,'uat_name') and text()=' Shared With Me']"));

    private Locator sectionByNamePattern = new Locator(LocatorTypes.XPATH,
            "//ul[@class='folders']/li//span[text()='%s']");

    protected SidebarRegion(){}

    public void waitForSectionVisibility(String sectionName){
        waitForVisibility("Waiting for item visibility: " + sectionName, sectionByNamePattern, sectionName);
    }

    public void clickSection(String sectionName){
        click("Clicking on item: " + sectionName, sectionByNamePattern, sectionName);
    }
}
