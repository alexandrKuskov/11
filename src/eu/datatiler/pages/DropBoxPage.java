package eu.datatiler.pages;

import com.qatestlab.base.BasePage;
import com.qatestlab.base.Locator;
import com.qatestlab.base.LocatorTypes;

/**
 * Created by user on 24.07.2017.
 */
public class DropBoxPage extends BasePage {

    private Locator popUpRegistrationWindow = new Locator(LocatorTypes.XPATH,
            "//div[@id='shared-link-immediate-signup-modal']/div[@class='db-modal']/div[@class='db-modal-box']");

    private Locator popUpCloseButton =popUpRegistrationWindow.concat(new Locator(LocatorTypes.XPATH,
            "/a[@aria-label]"));

    private Locator fileNamePattern = new Locator(LocatorTypes.XPATH,
            "//div[@class='react-title-bar']//h1[@class='filename' and contains(text(), '%s')]");


    private Locator downloadFileButton = new Locator(LocatorTypes.XPATH,
            "//div[contains(@class,'u-mar-right-xs')]//button[text()]");

    private Locator downloadDropdown = new Locator(LocatorTypes.XPATH,
            "//ul[@role='menu']");

    private Locator downloadDropdownOption = downloadDropdown.concat(new Locator(LocatorTypes.XPATH,
            "/li[1]/a"));

    private Locator waitForDropBoxStartPage = new Locator(LocatorTypes.XPATH,
            "//div[@class='preview-box']");

    private Locator fileItemPattern = new Locator(LocatorTypes.XPATH,
            "//ol[@class='sl-grid-body clearfix']//li[a[div[contains(text(),'%s')]]]");

    private Locator backToItemsList = new Locator(LocatorTypes.XPATH,
            "//div[@class='react-title-bubble__container']/button[@class='react-title-bar__close']");



    public void clickBackLink() {
        waitForVisibility("wait for 'back' link be visible",backToItemsList);
        click("click 'back' link", backToItemsList);
    }

    public void selectFile(String fileName) {
        waitForVisibility("wait until file '"+fileName+"' be visible",fileItemPattern, fileName);
        click("click '"+fileName+"' file", fileItemPattern, fileName);
    }

    public void waitForDropBoxStartPage() {
    waitForVisibility("Wait until dropbox start page is loaded", waitForDropBoxStartPage);
    }

    public void waitForRegistrationPopUp() {
        waitForVisibility("Wait until registration pop-up is loaded", popUpRegistrationWindow);
    }

    public void waitForRegistrationPopUpInvisibility() {
        waitForInvisibility("Wait until registration pop-up be invisible", popUpRegistrationWindow);
    }

    public void closeRegistrationPopUp() {
        waitToBeClickable("wait until 'close' button on pop up window be clickable",popUpCloseButton);
        click("click 'close' pop up", popUpCloseButton);
    }

    public void waitForFileNameVisibility(String name) {
        waitForVisibility("Wait until file with name " +name+ " be visible", fileNamePattern, name);
    }

    public void clickDownloadButton() {
        waitToBeClickable("wait until 'download' button be clickable",downloadFileButton);
        click("click 'download' button", downloadFileButton);
    }

    public void selectDownloadOption() {
        waitForVisibility("Wait until download dropdown pop up be visible",downloadDropdown);
        click("click 'direct download' download option", downloadDropdownOption);
    }

    public void waitDownloadDropdownInvisibility() {
        waitForInvisibility("Wait until download dropdown pop  be invisible", downloadDropdown);
    }

}
