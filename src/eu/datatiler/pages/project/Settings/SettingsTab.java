package eu.datatiler.pages.project.Settings;

import com.qatestlab.base.*;
import com.qatestlab.properties.Properties;
import org.openqa.selenium.*;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.interactions.Actions;

import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

/**
 * Created by Petro on 15.02.2016.
 */
public class SettingsTab extends BasePage {

    private Locator settingTab = new Locator(LocatorTypes.XPATH,
            "//a[text()='Settings']");

    private Locator listSettings = new Locator(LocatorTypes.XPATH,
            "//div[@class='settingsSideMenu']");

    private Locator settingsItem = listSettings.concat(new Locator(LocatorTypes.XPATH,
            "//li/a[text()='%s']"));

    private Locator hideOnDVI = new Locator(LocatorTypes.XPATH,
            "//input[@type='checkbox']");

    private Locator defaultWeight = new Locator(LocatorTypes.XPATH,
            "//div[@class='form-group' and @aria-hidden='false']");

    private Locator listWeight = new Locator(LocatorTypes.XPATH,
            "//div[text()='W_Days']");

    private Locator rotatedItem = new Locator(LocatorTypes.XPATH,
            "//div[@class='MetricsSettingPage']/div[@style]/input[@type='checkbox']");

    private Locator checkedRotatedItem = new Locator(LocatorTypes.XPATH,
            "//div[@class='MetricsSettingPage']/div[@style]/input[@type='checkbox' and @checked]");

    private Locator rotatedVariableField = new Locator(LocatorTypes.XPATH,
            "//div[@class='MetricsSettingPage']/div[@class='rotatedBySelector']/div[@id]");

    private Locator rotatedVariables = new Locator(LocatorTypes.XPATH,
            "//md-virtual-repeat-container//li//span");

    private Locator rotatedVariable = new Locator(LocatorTypes.XPATH,
            "//md-virtual-repeat-container//li//span[text()='%s']");

    private Locator rotatedVariableInField = new Locator(LocatorTypes.XPATH,
            "//div/div[text()='%s']");

    private Locator rotatedPopUpWindow = new Locator(LocatorTypes.XPATH,
            "//div[contains(@style,'transform-origin: left top')]");

    private Locator rotatedDropDownItem = rotatedPopUpWindow.concat(new Locator(LocatorTypes.XPATH,
            "//div[text()='%s']"));

    private Locator itemsList = new Locator(LocatorTypes.XPATH,
            "//div[@class='dt-grid']//div[@class='dt-grid-container']");

    private Locator item = new Locator(LocatorTypes.XPATH,
            "//div[@class='item']//div[text()='%s']");

    private Locator itemWDays = new Locator(LocatorTypes.XPATH,
            "//div[@class='item']//div[@class='text' and text()='W_Days']");

    private Locator settingsToolIcon = new Locator(LocatorTypes.XPATH,
            "//div[label[text()='%s']]");

    private Locator popUpWindowForMRGroup = new Locator(LocatorTypes.XPATH,
            "//div[h3[text()='Create multi response group']]");

    private Locator popUpInputField = new Locator(LocatorTypes.XPATH,
            "//div[label[text()='%s']]//input[@type='text']");

    private Locator controlType = new Locator(LocatorTypes.XPATH,
            "//div[div[label[text()='%s']]]");

    private Locator optionButton = new Locator(LocatorTypes.XPATH,
            "//span[text()='%s']");

    private Locator itemVisibilityOff = new Locator(LocatorTypes.XPATH,
            "//div[div[div[div[text()='%s']]] and @class='item disabled']//div[contains(@class,'visibility-off')]");

    private Locator itemText = new Locator(LocatorTypes.XPATH,
            "//div[text()='%s']");

    private Locator errorLabel = new Locator(LocatorTypes.XPATH,
            "//div[@id='growlContainer']/div[text()]");

    private Locator itemSearch = new Locator(LocatorTypes.XPATH,
            "//div[@class='item search']");

    private Locator searchField = new Locator(LocatorTypes.XPATH,
            "//label[text()='Search variables']/following-sibling::input");

    private Locator dichotomyItem = popUpWindowForMRGroup.concat(new Locator(LocatorTypes.XPATH,
            "//div[@class='dt-grid-table']//div[text()='%s']"));

    private Locator secondItemList = new Locator(LocatorTypes.XPATH,
            "//div[@class='metaEditor__content']/div[2]");

    private Locator secondListItem = secondItemList.concat(new Locator(LocatorTypes.XPATH,
            "//div[text()='%s']"));

    private Locator kindOfGroup = new Locator(LocatorTypes.XPATH,
            "//div[@class='item']//div[contains(text(),'%s')]");

    private Locator deleteItem = new Locator(LocatorTypes.XPATH,
            "//div[@class='delete']");

    private Locator deleteConfirmationPopUp = new Locator(LocatorTypes.XPATH,
            "//div/h3[text()='Delete MR-group confirmation']");

    private Locator confirmButton = new Locator(LocatorTypes.XPATH,
            "//button[div[div[span[text()='Confirm']]]]");

    private Locator invisibleItemPattern = new Locator(LocatorTypes.XPATH,
            "//div[div[@class='name']//div[text()='%s']]/div[contains(@class,'visibility-off')]");

    private Locator projectFolderRegion = new Locator(LocatorTypes.XPATH,
            "//div[@class='subcontainer']//span[@title='%s']");

    private Locator createSubfolder = new Locator(LocatorTypes.XPATH,
            "//div[@class='subcontainer']//div[@class='itemControls']/*[name()='svg']");

    private Locator createFolderModalWindow = new Locator(LocatorTypes.XPATH,
            "//div[h3[text()='Create subfolder']]");

    private Locator createFolderModalWindowInputField = createFolderModalWindow.concat(new Locator(LocatorTypes.XPATH,
            "//input[@name='renameFolderInput']"));

    private Locator modalWindowOptions = createFolderModalWindow.concat(new Locator(LocatorTypes.XPATH,
            "//span[text()='%s']"));

    private Locator currentlySelectedItemDraggBox = new Locator(LocatorTypes.XPATH,
            "//div[@class='item current selected']//div[@class='ui-draggable-handle']");

    private Locator currentlySelectedItemDropDown = new Locator(LocatorTypes.XPATH,
            "//div[@class='item current selected']//div[@class='controls']//div[@class='actual']//*[name()='svg']");

    private Locator currentlySelectedItemWeightOption = new Locator(LocatorTypes.XPATH,
            "//div[@class='item current selected']//div[@class='controls']//div[@class='flagSelector active']//*[name()='svg'][2]");

    private Locator DVICheckbox = new Locator(LocatorTypes.XPATH,
            "//div[input[@type='checkbox']]");

    private Locator DVISelectingField = new Locator(LocatorTypes.XPATH,
            "//div[text()='Default weight is ']/div[@style]");

    private Locator DVIWeightPopUp = new Locator(LocatorTypes.XPATH,
            "//div[contains(@style,'z-index: 1000')]/div[@style]");

    private Locator DVIWeightPopUpItemPattern = DVIWeightPopUp.concat(new Locator(LocatorTypes.XPATH,
            "//div[text()='%s']"));

    private Locator selectedDVIItemPattern = DVISelectingField.concat(new Locator(LocatorTypes.XPATH,
            "//div[text()='%s']"));



    protected SettingsTab() {
    }

    public void waitForSelectedDVIWeightItem(String itemName){
        waitForVisibility("wait for DVI selected weight item '"+itemName+"' visibility" , selectedDVIItemPattern, itemName);

    }

    public void selectDVIWeightItem(String itemName){
        waitForVisibility("wait for DVI weight item '"+itemName+"' visibility" , DVIWeightPopUpItemPattern, itemName);
        click("select DVI weight item '"+itemName+"'", DVIWeightPopUpItemPattern, itemName);
    }

    public void waitForDVIWeightPopUp(){
        waitForVisibility("wait for DVI weight pop-up", DVIWeightPopUp);
    }

    public void clickDVIWeightPopUp(){
        waitForVisibility("wait for DVI selecting field", DVISelectingField);
        click("click DVI selecting field", DVISelectingField);
    }

    public void clickDVICheckbox(){
        waitForVisibility("wait for DVI checkbox", DVICheckbox);
        click("click DVI checkbox", DVICheckbox);
    }

    public void clickCurrentlySelectedItemOption(){
        waitForVisibility("wait for currently selected option", currentlySelectedItemWeightOption);
        click("click currently selected option", currentlySelectedItemWeightOption);
    }

    public void clickCurrentlySelectedItemDropDown(){
        waitForVisibility("wait for currently selected item dropdown visibility", currentlySelectedItemDropDown);
        click("click currently selected item dropdown", currentlySelectedItemDropDown);
    }

    public void addItemIntoFolder(String folderName){
        dragAndDropReverse("add currently selected item into '"+folderName+"' folder",currentlySelectedItemDraggBox, projectFolderRegion, folderName);
        BaseActions.wait(3);
    }

    public void selectItem(String itemName){
        waitForVisibility("wait for '"+itemName+"' item be visible", item, itemName);
        click("click on '"+itemName+"' item", item, itemName);
    }

    public void selectModalWindowOption(String optionName){
        waitToBeClickable("wait for '"+optionName+"' option be clickable", modalWindowOptions, optionName);
        click("click on '"+optionName +"' option", modalWindowOptions, optionName);
    }

    public void typeTextIntoInputField(String folderName){
        waitForVisibility("wait for input field be visible", createFolderModalWindowInputField);
        type("type '" + folderName + "' folder name into input field",folderName,createFolderModalWindowInputField);
    }

    public void waitForCreateFolderModalWindowBeVisible() {
        waitForVisibility("wait for create folder modal window be visible", createFolderModalWindow);
    }

    public void waitForCreateFolderModalWindowBeInvisible() {
        waitForInvisibility("wait for create folder modal window be invisible", createFolderModalWindow);
    }
    public void createSubfolder(){
        waitToBeClickable("wait for 'create' subfolder option be clickable", createSubfolder);
        click("click on 'create' subfolder option", createSubfolder);
    }

    public void waitForProjectFolder(String folderName){
        waitForVisibility("wait for '"+folderName+"' project folder be visible", projectFolderRegion, folderName);
        click("click on the 'root' folder", projectFolderRegion, folderName);
    }

    public void selectFolder(String folderName){
        click("click on the 'root' folder", projectFolderRegion, folderName);
    }

    public boolean isItemInvisibleOnThePage(String itemName){
        return isPresent("check for item '" + itemName + "' is invisible ", invisibleItemPattern, itemName);
    }

    public void makeItemVisible(String itemName){
        waitToBeClickable("wait while 'make element visible' option be clickable ", invisibleItemPattern, itemName);
        click("click 'confirm' item ", invisibleItemPattern, itemName);
    }

    public void clickDeleteConfirmation(){
        waitToBeClickable("wait while 'confirm' button be clickable ",confirmButton);
        click("click 'confirm' item ", confirmButton);
    }

    public boolean isDeleteConfirmationPopUpPresent(){
        return isPresent("check for delete confirmation popup window", deleteConfirmationPopUp);
    }

    public void clickDeleteItem(){
        waitToBeClickable("wait while 'delete' option be clickable ",deleteItem);
        click("click 'delete' item ", deleteItem);
    }

    public boolean isItemElementPresent(String itemName){
        return isPresent("check for item with name"+ itemName +" is present in the items list", item, itemName);
    }

    public boolean isKindOfGroupPresent(String groupKind) {
        return isPresent("check for '"+ groupKind + "' group item", kindOfGroup, groupKind);
    }

    public boolean isSecondListItemPresent(String itemName) {
        return isPresent("check for '"+ itemName + "' group item", secondListItem, itemName);
    }

    public void clickGroup(String groupName){
        click("click '" + groupName + "' group", item, groupName);
        BaseActions.wait(3);
    }

    public boolean isGroupItemListPresent() {
        return isPresent("check for group items list group item", secondItemList);
    }

    public void selectDichotomyTypeItems(String items[]){
        for (int i = 0; i < items.length; i++) {
            waitForVisibility("wait for '"+ items[i] + "' item text visibility", dichotomyItem,items[i]);
            click("click '"+items[i]+"'",dichotomyItem,items[i]);
        }
    }

    public boolean isItemPresent(String itemName) {
        return isPresent("check for '"+ itemName + "' group item", item, itemName);
    }

    public boolean isItemInvisible(String itemName) {
        return isPresent("check for '"+ itemName + "' item visibility", itemVisibilityOff, itemName);
    }

    public boolean isItemTextPresent(String itemName) {
        return isPresent("check for '"+ itemName + "' text", itemText, itemName);
    }

    public boolean isErrorLabelPresent() {
        return isPresent("check for  error label visibility", errorLabel);
    }

    public void clickItemSearch(){
        waitToBeClickable("wait while item search option be clickable",itemSearch);
        click("click item search option", itemSearch);
    }
    public void typeTextIntoSearchField(String MRGroup){
        waitForVisibility("wait for search field be visiable", searchField);
        type("type '" + MRGroup + "' into search field",MRGroup,searchField);
    }




    public void selectOption(String optionName){
        waitToBeClickable("wait while '" + optionName + "'option be clickable", optionButton, optionName);
        click("Click on '" + optionName + "' control", optionButton, optionName);
    }

    public void selectControlType(String type) {
        waitToBeClickable("wait while '" + type + "'control type be clickable", controlType, type);
        click("Click on '" + type + "' control type", controlType, type);
    }

    public void inputTextOnPopUpField(String text, String field) {
        waitForVisibility("wait for pop up input field be visible", popUpInputField, field);
        click("click on the input field",popUpInputField, field);
        type("Input '" + text + "' into pop up window", text, popUpInputField, field);
    }

    public boolean isMRPopUpWindowPresent() {
        return isPresent("check for MR pop up window", popUpWindowForMRGroup);
    }

    public boolean isIconPresent(String iconName) {
        return isPresent("check for '" + iconName + "' icon present", settingsToolIcon, iconName);
    }

    public void clickIcon(String iconName) {
        click("Click on '" + iconName + "' icon", settingsToolIcon, iconName);
    }


    // TODO NEED FIX FOR FF
    public void selectItemsWithActions(String firstItem, String secondItem) throws AWTException {
        waitForVisibility("wait for items with name "+firstItem +" be visible",item, firstItem);
        waitForVisibility("wait for items with name "+secondItem +" be visible",item, secondItem);
        WebElement firstElement = driver().findElement(item.getLocator(firstItem));
        WebElement secondElement = driver().findElement(item.getLocator(secondItem));

       Actions builder = new Actions(driver());
        builder.keyDown(Keys.CONTROL)
                .click(firstElement)
                .click(secondElement)
                .keyUp(Keys.CONTROL)
                .build()
                .perform();
    }


    public void selectItems(String firstItem, String secondItem) throws AWTException {
        waitForVisibility("wait for items with name "+firstItem +" be visible",item, firstItem);
        waitForVisibility("wait for items with name "+secondItem +" be visible",item, secondItem);
        WebElement firstElement = driver().findElement(item.getLocator(firstItem));
        WebElement secondElement = driver().findElement(item.getLocator(secondItem));
        int height = 30;
        if (BaseTest.isDriverIE){
            height = -30;
        }
        Point p1 = firstElement.getLocation();
        int x1 = p1.getX();
        int y1 = p1.getY();
        Dimension d1 = firstElement.getSize();
        int h1 = d1.getHeight();
        int w1 = d1.getWidth();

        Point p2 = secondElement.getLocation();
        int x2 = p2.getX();
        int y2 = p2.getY();
        Dimension d2 = firstElement.getSize();
        int h2 = d2.getHeight();
        int w2 = d2.getWidth();
        Robot r = new Robot();

        r.mouseMove(x1 + (w1 / 6 - 10), y1 + (h1 / 6 + (height)) + 100);
        BaseActions.wait(2);
        r.mousePress(InputEvent.BUTTON1_MASK);
        r.mouseRelease(InputEvent.BUTTON1_MASK);
        r.keyPress(KeyEvent.VK_CONTROL);
        BaseActions.wait(2);
        r.mouseMove(x2 + (w2 / 6 - 10), y2 + (h2 / 6 + (height)) + 100);
        BaseActions.wait(2);
        r.mousePress(InputEvent.BUTTON1_MASK);
        r.mouseRelease(InputEvent.BUTTON1_MASK);
        BaseActions.wait(2);
        r.keyRelease(KeyEvent.VK_CONTROL);

    }

    public void scrollToItem(String itemName) {

        scrollToElement("scroll to item with name " + itemName, item, itemName);
    }

    public void scrollToItemText(String itemFullText) {
        scrollToElement("scroll to item with name " + itemFullText, itemText, itemFullText);
    }

    public void scrollDownToEnd(String message){
        scrollDown(message);

    }

    public boolean isItemsListPresent() {
        return isPresent("Check for items list", itemsList);
    }

    public void waitForItemsList() {
         waitForVisibility("wait for items list visibility", itemsList);
    }

    public boolean isSettingsTabOpened() {
        return getAttributeValue("Settings tab is active", "class", settingTab).contains("active");
    }

    public void waitListSettingsVisible() {
        waitForVisibility("Wait for list settings is visible", listSettings);
    }

    public boolean isListSettingsVisible() {
        return isVisible("Check is settings list visible", listSettings);
    }

    public void clickSettingsItemByName(String itemName) {
        click("Click on '" + itemName + "' item", settingsItem, itemName);
    }

    public void waitHideOnDVIToBeVisible() {
        waitForVisibility("Wait for 'Hide on DVI' to be visible", hideOnDVI);
    }

    public boolean isHideOnDVIVisible() {
        return isPresent(2, "Hide On DVI is visible", hideOnDVI);
    }

    public boolean getHideOnDVIState() {
        return isCheckboxChecked(hideOnDVI);
    }

    public void setHideOnDVIStateTrue() {
        setCheckboxState("Check 'Hide on DVI' ", true, hideOnDVI);
    }

    public void waitForDefaultWeightToBeVisible() {
        waitForVisibility("Wait 'Default weight' to be visible", defaultWeight);
    }

    public boolean isDefaultWeightVisible() {
        return isPresent(1, "Default weight is Visible", defaultWeight);
    }

    public void setStateHideOnDVIByItemName(String itemName, boolean state) {
        BaseActions.wait(3);
        setCheckboxState("Set state " + state + " to Hide on DVI option " + itemName + " item", state,
                new Locator(LocatorTypes.XPATH, "//div[@class='settingsContentBox']/div/div/input[@type='checkbox']"));
        BaseActions.wait(3);
    }

    public String getSelectedDefaultWeightValue() {
        return getSelectedOption("Get selected option", listWeight).trim();
    }

    public String getDefaultWeightValue() {
        return getText("Get default weight value", listWeight);
    }

    public void setStateRotatedItem(boolean state){
        if (!getAttributeValue("Get state rotated item", "aria-checked", rotatedItem).contains(state + "")){
            click("Set state " + state + " to rotated item", rotatedItem);
        }
    }

    public void setStateRotatedItem() {
        scrollToElement("scroll to 'rotated item'", rotatedItem);
        if (!isPresent("verify rotate item typing field present", rotatedVariableField))
        {
            click("Set state checkbox 'on' for rotated item", rotatedItem);
        }
    }

    public void moveToRotatedItem() {
        moveToElement("", rotatedVariableField, 0, 0).perform();

        (new Actions(driver()).moveToElement(driver().findElement(By.cssSelector(".rotatedBySelector")),
                0, 0)).perform();
    }

    public boolean isRotatedPopUpWindowPresent() {
        return isPresent("Verify rotate item pop-up window present", rotatedPopUpWindow);
    }


    public boolean isRotateItemCheckboxChecked() {
        return isPresent("Verify for checkbox checked", checkedRotatedItem);
    }

    public boolean isRotatedFieldVisible() {
        return isVisible("Check is rotated field visible", rotatedVariableField);
    }

    public void clickToRotatedField() {
        scrollToElement("Scroll to rotated variable field", rotatedVariableField);
        waitToBeClickable("wait for rotated field be clickable", rotatedVariableField);
        click("Set variable to rotated field", rotatedVariableField);
        BaseActions.wait(5);

    }


    public void setVariableToRotatedField(String item) throws AWTException {
        waitForVisibility("Wait for drop-dawned " + item + " item ", rotatedDropDownItem, item);
        switch (Properties.getBrowser()) {
            case FIREFOX:
                click("Click to drop-dawned " + item + " item ", rotatedDropDownItem, item);
                click("Click to drop-dawned " + item + " item ", rotatedDropDownItem, item);
                break;

            case IE:
                WebElement element = driver().findElement(rotatedDropDownItem.getLocator(item));
                Point p = element.getLocation();
                int x = p.getX();
                int y = p.getY();
                Dimension d = element.getSize();
                int h = d.getHeight();
                int w = d.getWidth();

                Robot r = new Robot();

                r.mouseMove(x + (w / 5), y + (h / 2 - 10) + 80);
                BaseActions.wait(2);
                r.mousePress(InputEvent.BUTTON1_MASK);
                BaseActions.wait(2);
                r.mouseRelease(InputEvent.BUTTON1_MASK);
                BaseActions.wait(1);
                break;
            case CHROME:
            default:
                click("Click to drop-dawned " + item + " item ", rotatedDropDownItem, item);
        }
    }

    public void clickRotatedVarByName(String varName) {
        executeJS("Clicking 'Main page' link", "arguments[0].click()", rotatedVariableInField, varName);
    }

    public void clickWDays() {
        waitForVisibility("wait for Wdays item visibility", itemWDays);
        click("select Wdays", itemWDays);
    }

    public String getSetRotatedVar() {
        return getText("Get setted rotated variable name", rotatedVariableField);
    }

    public ArrayList<String> getRelevantRotatedVariables() {
        ArrayList<String> listVariables = new ArrayList<>();

        for (WebElement wbElement : getElements("Get relevant rotated variables", rotatedVariables)) {
            listVariables.add(wbElement.getText());
        }

        return listVariables;
    }

}
/// itemWDays