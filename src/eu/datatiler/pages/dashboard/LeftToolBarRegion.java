package eu.datatiler.pages.dashboard;

import com.qatestlab.base.BasePage;
import com.qatestlab.base.Locator;
import com.qatestlab.base.LocatorTypes;

public class LeftToolBarRegion extends BasePage {

    private Locator region = new Locator(LocatorTypes.XPATH,
            "//div[@class='toolbar']");

    private Locator addControlButton = new Locator(LocatorTypes.XPATH,
            "//span[contains(@ng-if, 'AddControl')]/button");

    private Locator addTextButton = new Locator(LocatorTypes.XPATH,
            "//span[contains(@ng-click, 'createTextWidget')]//button");

    private Locator addImageButton = new Locator(LocatorTypes.XPATH,
            "//span[contains(@ng-click, 'createImage')]//button");

    private Locator editWidgetButton = new Locator(LocatorTypes.XPATH,
            "//div[text()='Widget settings']/../../following-sibling::div//button/*/*/*[contains(@d, 'M3 17') or contains(@d, 'M 3 17')]");

    private Locator deleteWidgetButton = new Locator(LocatorTypes.XPATH,
            "//div[text()='Widget settings']/../../following-sibling::div//button/*/*/*[contains(@d, 'M6') or contains(@d, 'M 6')]");

    private Locator tableSortingSwitcher = new Locator(LocatorTypes.XPATH,
            "//label[text()='Use table sorting']/following-sibling::div/../../input");

    private Locator tableSortingToggle = new Locator(LocatorTypes.XPATH,
            "//label[text()='Use table sorting']/following-sibling::div/div[2]");



    protected LeftToolBarRegion() {
    }


    public void waitForLoad() {
        waitForVisibility("Waiting for 'Left tool bar' region visibility", region);
    }

    public void clickAddControlButton(){
        click("Click 'Add control' button", addControlButton);
    }

    public void clickAddTextButton(){
        waitToBeClickable("Wait while 'Text' button be clickable", addTextButton);
        click("Click 'Text' button", addTextButton);
    }

    public void clickAddImageButton(){
        click("Click 'Image' button", addImageButton);
    }

    public void clickEditWidgetButton(){
        click("Click 'Edit' button", editWidgetButton);
    }

    public boolean isEditWidgetButtonPresent(){
        return isPresent("Check is 'Edit' button present", editWidgetButton);
    }

    public void clickDeleteWidgetButton(){
        click("Click 'Delete' button", deleteWidgetButton);
    }

    public boolean isDeleteWidgetButtonPresent(){
        return isPresent("Check is 'Edit' button present", deleteWidgetButton);
    }

    public boolean isAddControlButtonVisible(){
        return isVisible("Check is 'Add control' button visible", addControlButton);
    }

    public boolean isAddTextButtonVisible(){
        return isVisible("Check is 'Add text' button visible", addTextButton);
    }

    public boolean isAddImageButtonVisible(){
        return isVisible("Check is 'Add image' button visible", addImageButton);
    }

    public void clickTableSortingSwitcher(){
        click("Click 'Use table sorting' switcher", tableSortingSwitcher);
    }

    public String getToggleBackground() {
        return driver().findElement(tableSortingToggle.getLocator()).getCssValue("background-color").toString();
    }

}

