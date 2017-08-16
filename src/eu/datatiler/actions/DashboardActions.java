package eu.datatiler.actions;

import com.qatestlab.base.BaseActions;
import com.qatestlab.properties.PropertiesNames;
import com.qatestlab.reporting.Reporter;
import com.qatestlab.testrail.CustomStepResult;
import com.qatestlab.testrail.TestRailAssert;
import eu.datatiler.pages.Pages;
import eu.datatiler.pages.dashboard.DashboardPage;
import org.frontendtest.components.ImageComparison;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.io.File;
import java.util.List;

public class DashboardActions extends BaseActions {

    public void clickEmptySpace(){
        DashboardPage.contentPage().clickOnEmptySpace();
        BaseActions.wait(5);
    }

    public void clickViewAndCheckWidgetLabels(){
        DashboardPage.toolbarRegion().clickViewButton();
        BaseActions.wait(1);
        TestRailAssert.assertFalse(
                DashboardPage.toolbarRegion().isDashboardSettingsLabelPresent(),
                new CustomStepResult(
                        "на текущей странице присутствуют настройки виджетов",
                        "выводится превью дашборда, без настроек виджетов")
        );
    }

    public void clickTextWidget(){
        BaseActions.wait(2);
        DashboardPage.contentPage().clickOnInactiveTextWidget();
        BaseActions.wait(2);

            TestRailAssert.assertTrue(
                    DashboardPage.contentPage().isTextWidgetUntouched(),
                    new CustomStepResult(
                            "текстовый виджет не выбран",
                            "текстовый виджет выбран")
            );

        BaseActions.wait(2);
        TestRailAssert.assertTrue(
                DashboardPage.toolbarRegion().isStatesButtonPresent(),
                new CustomStepResult(
                        "в настройках виджета не видно 'States'",
                        "в настройках виджета видно 'States'")
        );
    }

    public void clickControlByNameAndCheckText(String controlName, String text){
        DashboardPage.contentPage().clickControlByName(controlName);
        BaseActions.wait(3);
        TestRailAssert.assertTrue(
                Actions.dashboardActions().isFilledTextWidgetPresent(text),
                new CustomStepResult(
                        "текстовый виджет c текстом '" + text + "' не отображается",
                        "текстовый виджет c текстом '" + text + "' отображается")
        );
    }

    public void clickDropDownControlByNameAndCheckText(String controlName, String text){
        checkDropDownControlAvailabilityAndClick(controlName);
        BaseActions.wait(3);
        TestRailAssert.assertTrue(
                Actions.dashboardActions().isFilledTextWidgetPresent(text),
                new CustomStepResult(
                        "текстовый виджет c текстом '" + text + "' не отображается",
                        "текстовый виджет c текстом '" + text + "' отображается")
        );

    }

    public void clickCondition(String condition){
        DashboardPage.toolbarRegion().clickOnCondition(condition);
    }

    public boolean isFilledTextWidgetPresent(String text){
        return DashboardPage.contentPage().isFulFilledTextWidgetPresent(text);
    }

    public void moveTextWidgetToRight(){
        DashboardPage.contentPage().moveTextWidget();
    }

    public void moveControlToLeft(){
        DashboardPage.contentPage().moveControl();
    }

    public void createTextPatternForAnotherControlState(String text){
        DashboardPage.contentPage().doubleClickOnTextWidget();
        TestRailAssert.assertTrue(
                Pages.dashboardPage().createTextWidgetDialog().isDialogAreaPresent(),
                new CustomStepResult(
                        "Пользователь не видит поле для вода текста",
                        "Пользователь видит поле для вода текста")
        );

        DashboardPage.createTextWidgetDialog().waitForDialogLoad();
        DashboardPage.createTextWidgetDialog().typeWidgetText(text);
    }

    public void checkControlAvailabilityAndClick(String state){
        TestRailAssert.assertTrue(
                DashboardPage.contentPage().checkControlAvailability(state),
                new CustomStepResult(
                        "'control' c переменной '" + state + "' не отображается или не доступен",
                        "'control' c переменной '" + state + "' отображается и доступен")
        );
        DashboardPage.contentPage().clickControlByName(state);
    }

    public void checkDropDownControlAvailabilityAndClick(String state){
        TestRailAssert.assertTrue(
                DashboardPage.contentPage().isControlDropdownFieldPresent(),
                new CustomStepResult(
                        "поле для выбора 'state' в дроп даун контроле не отображается",
                        "поле для выбора 'state' в дроп даун контроле отображается ")
        );
        DashboardPage.contentPage().clickControlDropdownField();
        TestRailAssert.assertTrue(
                DashboardPage.contentPage().isExpandedAreaPresent(),
                new CustomStepResult(
                        "выпадающее окно дроп даун контролере не отображается",
                        "выпадающее окно дроп даун контролере отображается")
        );
        DashboardPage.contentPage().clickDropdownOption(state);

    }

    public void deleteCondition(String stateName, String conditionName){
        DashboardPage.toolbarRegion().clickConditionMoreLink(stateName);
        TestRailAssert.assertTrue(
                DashboardPage.toolbarRegion().isConditionMorePopUpWindowPresent(),
                new CustomStepResult(
                        "всплывающее окно в 'Condition' с функционалом добавлени/удаления 'condition' не отображается",
                        "всплывающее окно в 'Condition' с функционалом добавлени/удаления 'condition' отображается")
        );
        DashboardPage.toolbarRegion().clickConditionOption(conditionName);
        TestRailAssert.assertFalse(
                DashboardPage.toolbarRegion().isConditionStatePresent(stateName),
                new CustomStepResult(
                        "принадлежаший к '" + stateName + "' 'Condition 1' не удален",
                        "принадлежаший к '" + stateName + "' 'Condition 1' успешно удален")
        );
    }

    public void executeOption(String stateName, String conditionName){
        DashboardPage.toolbarRegion().clickStateMoreLink(stateName);
        TestRailAssert.assertTrue(
                DashboardPage.toolbarRegion().isStateMorePopUpWindowPresent(),
                new CustomStepResult(
                        "всплывающее окно в 'State' с функционалом добавлени/удаления 'condition' не отображается",
                        "всплывающее окно в 'State' с функционалом добавлени/удаления 'condition' отображается")
        );
        DashboardPage.toolbarRegion().clickConditionOption(conditionName);
        }

    public void clickAddState(String stateName){
        DashboardPage.toolbarRegion().clickAddState();
        wait(1);
        TestRailAssert.assertTrue(
                DashboardPage.toolbarRegion().isStateLinkPresent(stateName),
                new CustomStepResult(
                        "State 1 не отображается",
                        "State 1 добавлен и отображается")
        );
    }

    public void clickOnStatesButton(){
        DashboardPage.toolbarRegion().clickStates();
        wait(2);

        TestRailAssert.assertTrue(
                DashboardPage.toolbarRegion().isAddStateLinkPresent() &
                        DashboardPage.toolbarRegion().isDefaultLinkLinkPresent(),
                new CustomStepResult(
                        "'Add State' и 'Default' не отображаются",
                        "Выводится меню с 'Add State' и 'Default'")
        );
    }

    public void checkAssignButtonFunctionality(){
        DashboardPage.contentPage().clickAssignButton();
        BaseActions.wait(1);

        TestRailAssert.assertFalse(
                DashboardPage.contentPage().isConfirmationWindowPresent(),
                new CustomStepResult(
                        "Присутствуют ошибки в консоли",
                        "Контрол привязан к текстовому виджету. Ошибки в консоли не присутствуют")
        );
        BaseActions.wait(2);
        /*
        TestRailAssert.assertFalse(
                DashboardPage.contentPage().isTextWidgetTouched(),
                new CustomStepResult(
                        "текстовый виджет выбран",
                        "текстовый виджет не выбран")
        );
        */

        DashboardPage.contentPage().clickOnBlueTextWidget();

        BaseActions.wait(4);
        TestRailAssert.assertTrue(
                DashboardPage.contentPage().isTextWidgetTouched(),
                new CustomStepResult(
                        "текстовый виджет не выбран",
                        "текстовый виджет выбран")
        );

        BaseActions.wait(2);
        TestRailAssert.assertTrue(
                DashboardPage.toolbarRegion().isStatesButtonPresent(),
                new CustomStepResult(
                        "в настройках виджета не видно 'States'",
                        "в настройках виджета видно 'States'")
        );

    }

    public void checkForTextWidgetAssingAttributes(){
        BaseActions.wait(3);
        TestRailAssert.assertTrue(
                DashboardPage.contentPage().isAssignHeaderPresent(),
                new CustomStepResult(
                        "Над текстовым виджетом не видно синее выделение",
                        "Над текстовым виджетом видно синее выделение с надписью 'Assign as states controller'")
        );
        BaseActions.wait(3);

        TestRailAssert.assertTrue(
                DashboardPage.contentPage().isAssignButtonOnTextWidgetPresent(),
                new CustomStepResult(
                        "В текстовом виджете не видно  кнопки 'Assign'",
                        "В текстовом виджете видно синее выделение с кнопкой 'Assign'")
        );
    }

    public void checkForLeftActionsItemsPresentAndVisible(List<String>itemsName){
        TestRailAssert.assertTrue(
                DashboardPage.createTextWidgetDialog().isDashboardLeftActionsPresent(),
                new CustomStepResult(
                        "Форма для создания виджетов не отображается",
                        "Форма для создания виджетов отображается")
        );

        TestRailAssert.assertTrue(
                DashboardPage.createTextWidgetDialog().isLeftActionsItemPresent(itemsName),
                new CustomStepResult(
                        "Кнопки для создания виджетов не отображается",
                        "Кнопки для создания виджетов отображается")
        );

    }

    public void checkForToolbarIcons(){
        TestRailAssert.assertTrue(
                DashboardPage.createTextWidgetDialog().isToolbarPresent(),
                new CustomStepResult(
                        "Сайд бар не отображается",
                        "Сайд бар видим и отображается")
        );

        TestRailAssert.assertTrue(
                DashboardPage.createTextWidgetDialog().isSettingsWidgetHeaderPresent(),
                new CustomStepResult(
                        "Панель в сайд баре отлична от названия 'WIDGET SETTINGS'",
                        "Панель в сайд баре имеет название 'WIDGET SETTINGS'")
        );

        TestRailAssert.assertTrue(
                DashboardPage.createTextWidgetDialog().isPositionalIconsPresent(),
                new CustomStepResult(
                        "конки позиционирования не отображаются",
                        "конки позиционирования видимы и отображаются")
        );

        TestRailAssert.assertTrue(
                DashboardPage.createTextWidgetDialog().isCloneIconPresent(),
                new CustomStepResult(
                        "конка клонирования  не отображается",
                        "конка клонирования  видима и отображается")
        );

        TestRailAssert.assertTrue(
                DashboardPage.createTextWidgetDialog().isDeleteIconPresent(),
                new CustomStepResult(
                        "конка удаления виджета не отображается",
                        "конка удаления виджета видима и отображается")
        );
    }

    public void clickIcon(String itemName, List<String> itemsName){
        DashboardPage.createTextWidgetDialog().clickLeftToolsIcon(itemName, itemsName);

    }

    public void checkDashboardTablePresence() {
        Pages.dashboardPage().contentPage().waitForDashboardVisibility();

        TestRailAssert.assertTrue(
                Pages.dashboardPage().contentPage().isTablePresent(),
                new CustomStepResult(
                        "Дашбоард не содержит таблицу",
                        "Отображается таблица на странице дашбоарда ")
        );

        Pages.dashboardPage().contentPage().waitMySpaceLinkVisibility();
        Pages.dashboardPage().contentPage().waitMySpaceLinkClickable();
        Pages.dashboardPage().contentPage().clickMySpaceLink();
    }

    public void openEditMode() {
        Pages.navigationBar().waitLoader();
        Pages.dashboardPage().toolbarRegion().waitEditDashboardButton();
        boolean editDashboardButtonPresent = Pages.dashboardPage().toolbarRegion().isEditDashboardButtonVisibility();
        if (editDashboardButtonPresent)
            Pages.dashboardPage().toolbarRegion().clickEditDashboard();
        Pages.dashboardPage().toolbarRegion().waitViewButtonToBeClickable();
    }

    public void closeEditMode() {
        Pages.navigationBar().waitLoader();
        boolean saveButtonPresent = Pages.dashboardPage().toolbarRegion().isViewButtonPresent();
        if (saveButtonPresent) {
            Pages.dashboardPage().toolbarRegion().clickViewButton();
        }
        Pages.dashboardPage().toolbarRegion().waitEditDashboardButton();
    }

    public void openItemCreationDialog(String itemName) {
        Pages.dashboardPage().toolbarRegion().waitAddButtonToBeClickable();
        Pages.dashboardPage().toolbarRegion().clickAddButton();
        Pages.dashboardPage().toolbarRegion().waitForAddMenuVisibility();
        Pages.dashboardPage().toolbarRegion().clickOnItem(itemName);
    }

    public void addControl() {
        Pages.dashboardPage().controlPage().waitAddButtonToBeClickable();
        Pages.dashboardPage().controlPage().clickAddControl();
        Pages.dashboardPage().toolbarRegion().waitViewButtonToBeClickable();
        Pages.dashboardPage().toolbarRegion().clickViewButton();
    }

    public void addImage() {
        openEditMode();
        Pages.dashboardPage().leftToolBarRegion().waitForLoad();

        TestRailAssert.assertTrue(
                Pages.dashboardPage().leftToolBarRegion().isAddControlButtonVisible() &&
                        Pages.dashboardPage().leftToolBarRegion().isAddImageButtonVisible() &&
                        Pages.dashboardPage().leftToolBarRegion().isAddTextButtonVisible(),
                new CustomStepResult(
                        "Пользователь не видит кнопки для добавления 'Image', 'Text' и 'Control'",
                        "Пользователь видит кнопки для добавления 'Image', 'Text' и 'Control'")
        );

        Pages.dashboardPage().leftToolBarRegion().clickAddImageButton();

        wait(3);

        TestRailAssert.assertTrue(
                Pages.mainPage().creatingDialog().isCreatingDialogPresent("Create image widget"),
                new CustomStepResult(
                        "Не отображается модальное окно'Create image widget'",
                        "Отображается модальное окно 'Create image widget'"
                )
        );


        Pages.mainPage().creatingDialog().uploadImageFile();

        TestRailAssert.assertTrue(
                Pages.mainPage().creatingDialog().isFileUploaded(PropertiesNames.IMAGE_NAME.toString()),
                new CustomStepResult(
                        "Графический файл не подгружен",
                        "Графический файл подгружен")
        );

        Pages.mainPage().creatingDialog().waitForCreateButtonToBeClickable();
        Pages.mainPage().creatingDialog().clickCreateButton();
    }

    public void uploadImage(){
        Pages.mainPage().creatingDialog().uploadImageFile();

        TestRailAssert.assertTrue(
                Pages.mainPage().creatingDialog().isFileUploaded(PropertiesNames.IMAGE_NAME.toString()),
                new CustomStepResult(
                        "Графический файл не подгружен",
                        "Графический файл подгружен")
        );

        Pages.mainPage().creatingDialog().waitForCreateButtonToBeClickable();
        Pages.mainPage().creatingDialog().clickCreateButton();
        wait(3);
    }

    public void clickOnImageArea(){
        DashboardPage.createTextWidgetDialog().clickOnImage();
    }

    public void addCustomizedControl(String itemName, String categoryName, String type,
                                     String selectionStyle, String stateName) {

        openEditMode();
        Pages.dashboardPage().leftToolBarRegion().waitForLoad();
        Pages.dashboardPage().leftToolBarRegion().clickAddControlButton();
        customizeControl(itemName, categoryName, type, selectionStyle, stateName);
        Pages.dashboardPage().controlPage().waitAddButtonToBeClickable();
        Pages.dashboardPage().controlPage().clickAddControl();
        selectButtonControl(itemName);

        Pages.dashboardPage().toolbarRegion().waitViewButtonToBeClickable();
        Pages.dashboardPage().contentPage().waitControlRoleRegion();
        //TODO
        Pages.dashboardPage().contentPage().clickControlRoleButtonByName("Rows");
        Pages.dashboardPage().toolbarRegion().clickViewButton();
        BaseActions.wait(3);
    }

    public void setVerticalButtonOrientation(String controlName, String orientation) {
        openEditMode();
        selectButtonControl(controlName);
        Pages.dashboardPage().leftToolBarRegion().clickEditWidgetButton();
        Pages.dashboardPage().controlPage().waitForControlEditorLoad();
       // selectControlStyle(orientation);
        clickStyle(orientation);

        Actions.generalActions().wait(5);

        Assert.assertTrue(Pages.dashboardPage().controlPage().isVerticalOrientation());
        addControl();
    }

    public void addDefaultControl(String itemName, String categoryName) {
        openEditMode();
        openItemCreationDialog(itemName);
        Pages.dashboardPage().categoryRegion().waitForLoad();
        Pages.dashboardPage().categoryRegion().fillGroup(categoryName);
        addControl();
    }

    public void openControlConstructor(String state) {
        Pages.dashboardPage().toolbarRegion().waitViewButtonToBeClickable();
        Pages.dashboardPage().contentPage().selectDropdownControl(state);
        Pages.dashboardPage().toolbarRegion().waitEditWidgetButtonToBeClickable();
        Pages.dashboardPage().toolbarRegion().clickEditWidgetButton();
    }

    public void customizeControl(String itemName, String categoryName, String type,
                                 String selectionStyle, String stateName) {

        Pages.dashboardPage().categoryRegion().waitForLoad();
        Pages.dashboardPage().categoryRegion().fillGroup(categoryName);
        wait(10);
//        Pages.dashboardPage().controlPage().waitForControlRegionLoad();
        selectControlStyle(type);
        selectControlStyle(selectionStyle);
//        Pages.dashboardPage().controlPage().setAvailableSetNameCheckBox();
        Pages.dashboardPage().controlPage().setControlName(itemName);
//        if (type.equals("Button")) {
//            Pages.dashboardPage().controlPage().selectButtonState(stateName);
//        } else {
//            Pages.dashboardPage().controlPage().openStateDropdown();
//            Pages.dashboardPage().controlPage().selectDropdownState(stateName);
//        }
    }

    public void selectControlStyle(String styleName) {
        Pages.dashboardPage().controlPage().waitForControlEditorLoad();
        Pages.dashboardPage().controlPage().selectStyle(styleName);
    }

    public void clickStyle(String styleName){
        Pages.dashboardPage().controlPage().waitForControlEditorLoad();
        Pages.dashboardPage().controlPage().clickOnStyle(styleName);
    }

    public void selectButtonControl(String controlName) {
        Pages.navigationBar().waitLoader();
        Actions.generalActions().wait(10);
        if (Pages.dashboardPage().toolbarRegion().isEditDashboardButtonVisibility())
            openEditMode();
        Pages.dashboardPage().contentPage().selectControlByName();
    }


    public void checkDataCrossTabRows(String imageName) throws Exception {
        BaseActions.wait(5);
        Pages.dashboardPage().contentPage().getScreenshotCrosstabRow("actual" + imageName);

        Assert.assertTrue(new ImageComparison(10, 10, 0.25)
                .fuzzyEqual(System.getProperty(PropertiesNames.UPLOAD_DIR.toString()) + File.separator + imageName + ".png",
                        System.getProperty(PropertiesNames.SCREENSHOT_DIR.toString()) + File.separator + "actual" + imageName + ".png",
                        System.getProperty(PropertiesNames.SCREENSHOT_DIR.toString()) + File.separator + "differences.png"));

    }

    public void selectDropdownControl(String stateName) {
        Pages.navigationBar().waitLoader();
        openEditMode();
        Pages.dashboardPage().contentPage().selectDropdownControl(stateName);
    }

    public void selectButtonControlState(String controlName, String stateName) {
        Pages.navigationBar().waitLoader();
        if (!isButtonControlStateActive(stateName)) {
            Pages.dashboardPage().contentPage().selectControlState(controlName, stateName);
            Pages.navigationBar().waitLoader();
        }

    }

    public void resetButtonControlState(String controlName) {
        closeEditMode();
        boolean active;
        Pages.navigationBar().waitLoader();
        List<WebElement> buttonControlStates = Pages.dashboardPage().contentPage().getButtonControlStates(controlName);
        for (WebElement state : buttonControlStates) {
            active = Pages.dashboardPage().contentPage().isButtonControlStateActive(state);
            if (active) {
                state.click();
                Pages.navigationBar().waitLoader();
            }
        }

    }

    public boolean isPresentExceptedDashboard(String dashboardName) {
        return Pages.dashboardPage().contentPage().checkDashboardName(dashboardName);
    }

    public void removeButtonControl(String itemName, String controlName, String stateName) {
        Actions.generalActions().openMainPage();
        Actions.itemActions().openItem(itemName);
        selectButtonControl(controlName);
        remove();
        Pages.navigationBar().waitLoader();
        Actions.generalActions().refreshPage();
        Pages.navigationBar().waitLoader();
        boolean controlPresent = Pages.dashboardPage().contentPage().isControlStatePresent(stateName);
        Assert.assertFalse(controlPresent, String.format("Control with state '%s' is still present", stateName));
    }

    public void removeDropdownControl(String itemName, String stateName) {
        Actions.generalActions().openMainPage();
        Actions.itemActions().openItem(itemName);
        selectDropdownControl(stateName);
        remove();
        Pages.navigationBar().waitLoader();
        Actions.generalActions().refreshPage();
        Pages.navigationBar().waitLoader();
        boolean controlPresent = Pages.dashboardPage().contentPage().isControlStatePresent(stateName);
        Assert.assertFalse(controlPresent, String.format("Control with state '%s' is still present", stateName));
    }

    public void removeCategoryFromDropdownControl(String stateName, String categoryToRemove) {
        openEditMode();
        Pages.navigationBar().waitLoader();
        openControlConstructor(stateName);
        Pages.dashboardPage().controlPage().waitForControlEditorLoad();
        Pages.dashboardPage().controlPage().removeCategory(categoryToRemove);
        Pages.dashboardPage().controlPage().clickAddControl();
        Pages.navigationBar().waitSuccessAlertInvisibility();
        Pages.dashboardPage().toolbarRegion().waitViewButtonToBeClickable();
        Pages.dashboardPage().toolbarRegion().clickViewButton();
        Pages.navigationBar().waitLoader();
        Pages.dashboardPage().contentPage().openDropdownControlList();
        boolean controlStatePresent = Pages.dashboardPage().contentPage().isControlStatePresent(categoryToRemove);
        Assert.assertFalse(controlStatePresent,
                String.format("Category '%s' wasn't removed from control", categoryToRemove));
    }


    public void removeTextWidgetIfPresent(String text){
        Pages.navigationBar().waitLoader();
        if (isTextLabelPresent(text)) {
            Pages.dashboardPage().contentPage().selectTextWidget(text);
            remove();
            Actions.generalActions().refreshPage();
        }
    }

    public void removeCloneImage(){
        Pages.navigationBar().waitLoader();
        Pages.dashboardPage().contentPage().selectCloneImage();
        remove();
        Actions.generalActions().refreshPage();
    }

    public void remove() {
        Pages.dashboardPage().leftToolBarRegion().waitForLoad();
        Pages.dashboardPage().leftToolBarRegion().clickDeleteWidgetButton();
        wait(5);
        Pages.dashboardPage().toolbarRegion().acceptAlert();
        Pages.navigationBar().waitSuccessAlertInvisibility();
        Pages.dashboardPage().toolbarRegion().waitViewButtonToBeClickable();
        Pages.dashboardPage().toolbarRegion().clickViewButton();
    }

    public boolean isControlPresent(String stateName) {
        Pages.navigationBar().waitLoader();
        boolean controlPresent = Pages.dashboardPage().contentPage().isControlStatePresent(stateName);
        return controlPresent;
    }

    public void addTextWidget(String text) {
        openEditMode();
        Pages.navigationBar().waitLoader();

        TestRailAssert.assertTrue(
                Pages.dashboardPage().leftToolBarRegion().isAddControlButtonVisible() &&
                        Pages.dashboardPage().leftToolBarRegion().isAddImageButtonVisible() &&
                        Pages.dashboardPage().leftToolBarRegion().isAddTextButtonVisible(),
                new CustomStepResult(
                        "Пользователь не видит кнопки для добавления 'Image', 'Text' и 'Control'",
                        "Пользователь видит кнопки для добавления 'Image', 'Text' и 'Control'")
        );

        Pages.dashboardPage().leftToolBarRegion().waitForLoad();
        Pages.dashboardPage().leftToolBarRegion().clickAddTextButton();

        wait(5);

        TestRailAssert.assertTrue(
                Pages.dashboardPage().createTextWidgetDialog().isDialogAreaPresent(),
                new CustomStepResult(
                        "Пользователь не видит поле для вода текста",
                        "Пользователь видит поле для вода текста")
        );

        Pages.dashboardPage().createTextWidgetDialog().waitForDialogLoad();
        Pages.dashboardPage().createTextWidgetDialog().typeWidgetText(text);
    }

    public boolean isTextWidgetPresent(String text, String header) {
        Pages.navigationBar().waitLoader();
        boolean textPresent = Pages.dashboardPage().contentPage().isTextPresent(text, header);
        return textPresent;
    }

    public boolean isTextLabelPresent(String text){
        Pages.navigationBar().waitLoader();
        boolean textPresent = Pages.dashboardPage().contentPage().isTextLabelPresent(text);
        return textPresent;
    }





    public void selectFormatting(String format) {
        Pages.dashboardPage().createTextWidgetDialog().clickFormatsButton();
        Pages.dashboardPage().createTextWidgetDialog().moveToHeadingsButton();
        Pages.dashboardPage().createTextWidgetDialog().selectFormatting(format);

    }

    public void checkImageWidgetPresence() {
        Pages.navigationBar().waitLoader();
        boolean imageWidgetPresent = Pages.dashboardPage().contentPage().isImageWidgetPresent();
        Assert.assertTrue(imageWidgetPresent, "Image widget isn't present");
    }

    public void checkImageWidgetDimension(Dimension expected) {
        Pages.navigationBar().waitLoader();
        Dimension actualDimension = Pages.dashboardPage().contentPage().getImageDimension();
        Assert.assertEquals(actualDimension, expected, "Image size is not correct");
    }

    /*
    * control active state checks
    * */
    public boolean isButtonControlStateActive(String stateName) {
        Pages.navigationBar().waitLoader();
        boolean controlStateActive = Pages.dashboardPage().contentPage().isButtonControlStateActive(stateName);
        return controlStateActive;
    }

    public boolean isSingleDropdownControlStateActive(String stateName) {
        Pages.navigationBar().waitLoader();
        boolean controlStateActive = Pages.dashboardPage().contentPage().isSingleDropdownControlStateActive(stateName);
        return controlStateActive;
    }

    public boolean isMultipleDropdownControlStateActive(String stateName) {
        Pages.navigationBar().waitLoader();
        boolean controlStateActive = Pages.dashboardPage().contentPage().isMultiDropdownControlStateActive(stateName);
        return controlStateActive;
    }

    public boolean isWidgetWithTitlePresent(String title) {
        Pages.navigationBar().waitLoader();
        return Pages.dashboardPage().contentPage().isWidgetPresentWithTitle(title);
    }

    public boolean isWidgetPresent() {
        Pages.navigationBar().waitLoader();
        return Pages.dashboardPage().contentPage().isWidgetPresent();
    }

    public void checkDataCrossTab(String imageName) throws Exception {
        BaseActions.wait(3);
        Pages.dashboardPage().contentPage().getScreenshotCrossTab("actual" + imageName);
        BaseActions.wait(1);
        Assert.assertTrue(new ImageComparison(10, 10, 0.25)
                .fuzzyEqual(System.getProperty(PropertiesNames.UPLOAD_DIR.toString()) + File.separator + imageName + ".png",
                        System.getProperty(PropertiesNames.SCREENSHOT_DIR.toString()) + File.separator + "actual" + imageName + ".png",
                        System.getProperty(PropertiesNames.SCREENSHOT_DIR.toString()) + File.separator + "differences.png"));

    }

    public void checkChartData(String imageName) throws Exception {
        BaseActions.wait(3);
        Pages.dashboardPage().contentPage().getScreenshotChartRegion("actual" + imageName);

        Assert.assertTrue(new ImageComparison(10, 10, 0.3)
                .fuzzyEqual(System.getProperty(PropertiesNames.UPLOAD_DIR.toString()) + File.separator + imageName + ".png",
                        System.getProperty(PropertiesNames.SCREENSHOT_DIR.toString()) + File.separator + "actual" + imageName + ".png",
                        System.getProperty(PropertiesNames.SCREENSHOT_DIR.toString()) + File.separator + "differences.png"));

    }

    public void selectTable() {
        Pages.navigationBar().waitLoader();
        Actions.generalActions().wait(5);
        if (Pages.dashboardPage().toolbarRegion().isEditDashboardButtonVisibility())
            openEditMode();
        Pages.dashboardPage().contentPage().selectTable();

    }


    public void switchSortingToggleByFlag(boolean flag){
        if ("rgba(245, 245, 245, 1)".equals(Pages.dashboardPage().leftToolBarRegion().getToggleBackground()) && flag)
            Pages.dashboardPage().leftToolBarRegion().clickTableSortingSwitcher();
    }
}
