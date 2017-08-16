package eu.datatiler.pages.encoder;

import com.qatestlab.base.BasePage;
import com.qatestlab.base.Locator;
import com.qatestlab.base.LocatorTypes;

import java.awt.*;

/**
 * Created by Petro on 07.03.2016.
 */
public class ItemsRegion extends BasePage {

    private Locator blockValuesInterviewItem = new Locator(LocatorTypes.XPATH,
            "//md-content[contains(@class, 'dtGridContainer')]//div[@class='dt-grid-container']//div[1]");

    private Locator listValuesInterviewItem = blockValuesInterviewItem.concat(new Locator(LocatorTypes.XPATH,
            "//tr"));

    private Locator valueInterviewItemByName = listValuesInterviewItem.concat(new Locator(LocatorTypes.XPATH,
            "[//td[text()='%s']]"));

    private Locator valueInterviewItem = blockValuesInterviewItem.concat(new Locator(LocatorTypes.XPATH,
            "//tr[%s]//md-checkbox"));

    private Locator countValueInterviewItem = blockValuesInterviewItem.concat(new Locator(LocatorTypes.XPATH,
            "//tr[td[text()='%s']]/td[4]"));

    private Locator nameValueItem = blockValuesInterviewItem.concat(new Locator(LocatorTypes.XPATH,
            "//td[text()='%s']"));

    private Locator mergeButton = new Locator(LocatorTypes.XPATH,
            "//button[@id='mergeBtn']");

    private Locator fieldSetNameMerge = new Locator(LocatorTypes.XPATH,
            "//md-autocomplete[@id='mergeautocomplete']//input");

    private Locator confirmMergeButton = new Locator(LocatorTypes.XPATH,
            "//md-dialog-actions//span[text()='Ok']");

    private Locator abortMergeButton = new Locator(LocatorTypes.XPATH,
            "//md-dialog-actions//span[text()='Cancel']");

    private Locator modalWindowAddToDictionary = new Locator(LocatorTypes.XPATH,
            "//md-dialog[contains(@class, 'dictionary')]");

    private Locator createDictionaryButton = new Locator(LocatorTypes.XPATH,
            "//button[contains(@ng-click, 'openCreateDictionaryDialog')]");

    private Locator closeModalWindowButton = new Locator(LocatorTypes.XPATH,
            "//button/span[text()='Cancel']");

    private Locator addDictionaryButton = new Locator(LocatorTypes.XPATH,
            "//button/span[text()='Add']");

    private Locator modalWindowHeader = new Locator(LocatorTypes.XPATH,
            "//md-dialog-content/h3");

    private Locator inputFieldModalWindow = new Locator(LocatorTypes.XPATH,
            "//md-dialog//md-input-container/input");

    private Locator selectOptionModalWindow = new Locator(LocatorTypes.XPATH,
            "//md-dialog//md-select");

    private Locator optionModalWindow = new Locator(LocatorTypes.XPATH,
            "//md-option/div[text()='%s']");

    private Locator fromDictionaryButton = new Locator(LocatorTypes.XPATH,
            "//button/span[text()='From dictionary']");

    private Locator toDictionaryButton = new Locator(LocatorTypes.XPATH,
            "//button/span[text()='To dictionary']");

    public void waitListValues() {
        waitForVisibility("Waiting for list of models car", blockValuesInterviewItem);
    }

    public boolean isListValuesVisible() {
        return isVisible("List values is visible", blockValuesInterviewItem);
    }

    public boolean isValueInterviewItemSelected(String nameMergeValuesGroup) {
        return getAttributeValue("Get attribute class", "class", valueInterviewItemByName, nameMergeValuesGroup)
                .contains("selected");
    }

    public int getCountValuesInterviewItem() {
        return getCount("Get count values interview Item", listValuesInterviewItem);
    }

    public void selectValueInterviewItem(int id) {
        click("Select '" + id + "' value interview item", valueInterviewItem, id);
    }

    public void clickMergeButton() {
        click("Click 'Merge' button", mergeButton);
    }

    public boolean isMergeButtonClickable(){
        return !isPresent("Check is 'Merge' button clickable", new Locator(LocatorTypes.XPATH,
                "//button[@id='mergeBtn']/../../div[contains(@class,'disabled')][1]"));
    }

    public void setMergeName(String nameMergeValuesGroup){
        type("Set name", nameMergeValuesGroup, fieldSetNameMerge);
    }

    public void confirmMergeAction() {
        click("Click on the 'Ok' button for confirm merge action", confirmMergeButton);
    }

    public boolean isMergedValuePresent(String nameValue) {
        waitForVisibility("Wait for item '" + nameValue + "' to be visibility", nameValueItem, nameValue);
        return isPresent(nameValue + "value is present", nameValueItem, nameValue);
    }

    public String getCountValueInterviewItem(String nameValue) {
        waitForVisibility("Wait for item '" + nameValue + "' to be visibility", countValueInterviewItem, nameValue);
        return getText("Get count value interview item", countValueInterviewItem, nameValue);
    }

    public void clickValueInterviewItem(String nameValue) {
        waitToBeClickable("Wait for item '" + nameValue + "' to be clickable", nameValueItem, nameValue);
        click("Click on '" + nameValue + "' item", nameValueItem, nameValue);
    }

    public void clickCreateDictionaryButton() {
        waitToBeClickable("Wait for create dictionary button to be clickable", createDictionaryButton);
        click("Click on create dictionary button", createDictionaryButton);
    }

    public void setDictionaryName(String dictionaryName) {
        waitForVisibility("Wait for input field to be visible", inputFieldModalWindow);
        type("Set dictionary name: " + dictionaryName, dictionaryName, inputFieldModalWindow);
    }

    public void clickAddDictionaryButton() {
        waitToBeClickable("Wait for add dictionary button to be clickable", addDictionaryButton);
        click("Click on add dictionary button", addDictionaryButton);
    }

    public void selectDictionary(String optionName) {
        waitForVisibility("Wait for select option to be visible", selectOptionModalWindow);
        click("Click on select option ", selectOptionModalWindow);
        waitToBeClickable("Wait for option with name '" + optionName + "' to be clickable",
                optionModalWindow, optionName);
        click("Click on option with name '" + optionName, optionModalWindow, optionName);
    }

    public void clickToDictionaryButton() {
        waitToBeClickable("Wait for 'To dictionary' button to be clickable", toDictionaryButton);
        click("Click on 'To dictionary' button", toDictionaryButton);
    }

    public String getPopUpHeader() {
        return getText("Get pop up header", modalWindowHeader);
    }

    public void waitPopUpToBeLoaded() {
        waitForVisibility("Wait for pop up to be visibility", modalWindowHeader);
    }

    public boolean isCancelButtonPresent() {
        return isPresent("Cancel button is present", closeModalWindowButton);
    }

    public boolean isCreateDictionaryButtonPresent() {
        return isPresent("'+' button is present", createDictionaryButton);
    }

    public boolean isAddDictionaryButtonPresent() {

        return isPresent("'Add' button is present", addDictionaryButton);
    }

    public boolean isFieldDictionaryNamePresent() {
        return isPresent("Field dictionary name is present", inputFieldModalWindow);
    }

    public String getValueFromInputDictionaryNameField() {
        return getText("Get text from dictionary name field", inputFieldModalWindow).trim();
    }

    public boolean isToDictionaryButtonPresent() {
        return isPresent("'To Dictionary' button is present", toDictionaryButton);
    }

    public boolean isFromDictionaryButtonPresent() {
        return isPresent("'From Dictionary' button is present", fromDictionaryButton);
    }

}
