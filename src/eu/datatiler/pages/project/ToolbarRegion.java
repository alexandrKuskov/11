package eu.datatiler.pages.project;

import com.qatestlab.base.BasePage;
import com.qatestlab.base.Locator;
import com.qatestlab.base.LocatorTypes;

public class ToolbarRegion extends BasePage {
    private Locator calculateButton = new Locator(LocatorTypes.XPATH,
            "//div[contains(@ng-show, 'table')]//*[contains(@class, 'dvi-calculate')]");

    private Locator tableButton = new Locator(LocatorTypes.XPATH,
            "//div[contains(@ng-show, 'chart')]//button[contains(text(), 'Table')]");

    private Locator chartButton = new Locator(LocatorTypes.XPATH,
            "//div[contains(@ng-show, 'table')]//button[contains(text(), 'Chart')]");

    private Locator settingsButton = new Locator(LocatorTypes.XPATH,
            "//div[contains(@ng-show, 'table')]//a[contains(text(),'  ')]");

    private Locator exportToDashboardDropdown = new Locator(LocatorTypes.XPATH,
            "//button[@title='Export to dashboard']");

    private Locator exportToDashboardDropdownFromChart = new Locator(LocatorTypes.XPATH,
            "//div[@class='btn-toolbar dvi-toolbar']//button[@title='Export to dashboard']");

    private Locator bookMarkerRegion = new Locator(LocatorTypes.XPATH,
            "//div[contains(@class, 'dvi-bookmarker')]");

    private Locator saveBookMarkerButton = bookMarkerRegion.concat(new Locator(LocatorTypes.XPATH,
            "//button[contains(@ng-click, 'saveBookmark')]"));

    private Locator dropDownBookMarkerButton = new Locator(LocatorTypes.XPATH,
            "//div[@class='btn-toolbar dvi-toolbar main-top-toolbar']//div[contains(@class, 'dvi-bookmarker')]//button[contains(@class, 'dropdown-toggle')]");

    private Locator listBookMarkers = new Locator(LocatorTypes.XPATH,
            "//div[@class='btn-group dvi-bookmarker open']//ul[@class='dropdown-menu ng-scope']");

    private Locator listItemBookMarker = listBookMarkers.concat(new Locator(LocatorTypes.XPATH,
            "//li//div[contains(@class,'name') and text()='%s']"));

    private Locator removeItemBookMarker = listBookMarkers.concat(new Locator(LocatorTypes.XPATH,
            "//li[.//div[text()='%s']]//div[@title='Remove']"));

    private Locator saveAsItemBookMarker = listBookMarkers.concat(new Locator(LocatorTypes.XPATH,
            "//li[.//div[text()='%s']]//div[contains(@title, 'Save as')]"));

    private Locator bookMarkerNameField = new Locator(LocatorTypes.XPATH,
            "//*[@id='bookmarkerFormInput']");

    private Locator bookMarkerModalWindow = new Locator(LocatorTypes.XPATH,
            "//div[@class='modal-content' and //*[contains(@*, 'bookmark')]]");

    private Locator bookMarkerModalWindowButton = bookMarkerModalWindow.concat(new Locator(LocatorTypes.XPATH,
            "//div[contains(@class, 'modal-footer')]//button[text()='%s']"));

    protected ToolbarRegion(){}

    public void waitForLoad(){
        waitForVisibility("Waiting for DVI page to load", calculateButton);
    }

    public void clickCalculateButton(){
        waitToBeClickable("Waiting for 'Calculate' button to be clickable", calculateButton);
        click("Click 'Calculate' button", calculateButton);
    }

    public void clickEnter(){
        clickEnterMain();
    }

    public void clickChartButton(){
        waitToBeClickable("wait while 'chart' button be clickable", chartButton);
        click("Click 'Chart' button", chartButton);
    }

    public void clickTableButton(){
        click("Click 'Table' button", tableButton);
    }

    public void waitExportBubbtonToBeClickable(){
        waitToBeClickable("Waiting for 'Export to dashboard' button to be clickable", exportToDashboardDropdown);
    }

    public void clickExportToDashboard(){
        click("Clicking 'Export to dashboard'", exportToDashboardDropdown);
    }
    public void clickExportToDashboardFromChart(){
        click("Clicking 'Export to dashboard'", exportToDashboardDropdownFromChart);
    }

    public void waitBookMarkerModalWindow(){
        waitForVisibility("Wait to bookmarker modal window to be visible", bookMarkerModalWindow);
    }

    public boolean isBookMarkerModalWindow(){
        return isVisible("Check is book marker modal window visible", bookMarkerModalWindow);
    }

    public void waitBookMarkerButton(){
        waitToBeClickable("Wait 'Book marker' button to be clickable", saveBookMarkerButton);
    }

    public void clickBookMarkerButton(){
        click("Click 'Book marker' button", saveBookMarkerButton);
    }

    public void waitBookMarkerNameField(){
        waitForVisibility("Wait 'Book marker' name field to be visible", bookMarkerNameField);
    }

    public void setBookMarkerName(String bookMarkerName){
        type("Set '"+ bookMarkerName + "'  book marker name", bookMarkerName, bookMarkerNameField);
    }

    public void clickBookMarkerModWindowButton(String buttonName){
        click("Click '" + buttonName + "'", bookMarkerModalWindowButton, buttonName);
    }

    public boolean isBookMarkerModWindowButtonVisible(String buttonName){
        return isVisible("Check is '" + buttonName + "' button book's marker modal window visible",
                bookMarkerModalWindowButton, buttonName);
    }

    public void clickBookMarkerDropDownButton(){
        waitToBeClickable("Wait drop down book market to be clickable", dropDownBookMarkerButton);
        click("Click drop down book market button", dropDownBookMarkerButton);
    }

    public boolean isBookMarkerDropDownButtonPresent(){
        return isPresent("Check is book marker drop down button present", dropDownBookMarkerButton);
    }

    public void waitBookMarkerList(){
        waitForVisibility("Wait list book markers", listBookMarkers);
    }
    public void waitBookMarkerListInvisibility(){
        waitForInvisibility("Wait book markers list invisibility", listBookMarkers);
    }

    public boolean isBookMarkerItemPresent(String itemName){
        return isPresent("Book mark '" + itemName +"' is present", listItemBookMarker, itemName);
    }

    public void clickBookMarkerItemByName(String itemName){
        click("Click '" + itemName + "' bookmark", listItemBookMarker, itemName);
    }

    public void clickRemoveBookMark(String bookMarkName){
        waitToBeClickable("Wait for 'Remove' bookmark button to be clickable", removeItemBookMarker, bookMarkName);
        click("Remove '" + bookMarkName + "' bookmark", removeItemBookMarker, bookMarkName);
    }

    public void clickSaveAsBookMark(String bookMarkName){
        waitToBeClickable("Wait for 'Save as' bookmark button to be clickable", saveAsItemBookMarker, bookMarkName);
        click("Click 'Save as' bookmark", saveAsItemBookMarker, bookMarkName);
    }

    public void clickSettingsButton(){
        waitToBeClickable("Wait for 'Settings' button be clickable", settingsButton);
        click("Click 'Settings' button", settingsButton);
    }
}
