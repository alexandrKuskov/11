package eu.datatiler.actions;

import com.qatestlab.base.BaseActions;
import com.qatestlab.properties.PropertiesNames;
import com.qatestlab.reporting.Reporter;
import com.qatestlab.testrail.CustomStepResult;
import com.qatestlab.testrail.TestRailAssert;
import eu.datatiler.pages.Pages;
import eu.datatiler.pages.project.ProjectPage;
//import mx4j.tools.config.DefaultConfigurationBuilder;
import eu.datatiler.pages.project.Settings.SettingsPage;
import org.apache.commons.lang3.StringUtils;
import org.frontendtest.components.ImageComparison;
import org.testng.Assert;

import java.awt.*;
import java.io.File;

public class ProjectActions extends BaseActions {

    protected ProjectActions() {
    }

    public void clickResetStateButton(){
        Pages.projectPage().categoryRegion().clickResetStateButton();
    }

    public void clickResetStateButtonIfPresent(){
      boolean result = Pages.projectPage().categoryRegion().isResetStateButtonPresent();
      if (result){
          Pages.projectPage().categoryRegion().clickResetStateButton();
      }
    }

    public void checkForItem(String firstItem, String secondItem) {
        Actions.generalActions().refreshPage();
        BaseActions.wait(3);
        boolean status = Pages.projectPage().settingsPage().settingsTab().isItemElementPresent(firstItem);
        Reporter.log("" + status);
        if (!status) {
            Reporter.log("refresh page");
            Actions.generalActions().refreshPage();
            SettingsPage.settingsTab().scrollToItem("Education");
        }
       boolean firstItemStatus = SettingsPage.settingsTab().isItemInvisibleOnThePage(firstItem);
        if (firstItemStatus){
            SettingsPage.settingsTab().makeItemVisible(firstItem);
        }
        boolean secondItemStatus = SettingsPage.settingsTab().isItemInvisibleOnThePage(secondItem);
        if (secondItemStatus){
            SettingsPage.settingsTab().makeItemVisible(secondItem);
        }
        SettingsPage.settingsTab().scrollToItem(firstItem);
        BaseActions.wait(5);
    }

    public void checkGroupBlockItem(String itemName) {
        TestRailAssert.assertTrue(
                Pages.projectPage().settingsPage().settingsTab().isSecondListItemPresent(itemName),
                new CustomStepResult(
                        "итем с наименованием '" + itemName + "' в блоке группы отсутствует",
                        "итем с наименованием '" + itemName + "' в блоке группы присутствует")
        );
    }

    public void checkMRItem(String itemText) {
        BaseActions.wait(3);

        if (!StringUtils.containsIgnoreCase(itemText,"month"))
            SettingsPage.settingsTab().scrollToItem("Education");
        BaseActions.wait(2);

        TestRailAssert.assertTrue(
                Pages.projectPage().settingsPage().settingsTab().isItemTextPresent(itemText),
                new CustomStepResult(
                        "итем с наименованием '" + itemText + "' отсутствует",
                        "итем с наименованием '" + itemText + "' присутствует")
        );
        BaseActions.wait(3);
     //   if (!StringUtils.containsIgnoreCase(itemText,"month"))
     //   SettingsPage.settingsTab().scrollToItem("Education");
     //   BaseActions.wait(2);

        TestRailAssert.assertFalse(
                Pages.projectPage().settingsPage().settingsTab().isErrorLabelPresent(),
                new CustomStepResult(
                        "присутствуют ошибки в консоли не отображается",
                        "ошибки в консоли отсутствуют")
        );
        TestRailAssert.assertTrue(
                Pages.projectPage().settingsPage().settingsTab().isItemInvisible(itemText),
                new CustomStepResult(
                        "итем с наименованием '" + itemText + "' не ' имеет статус 'hide'",
                        "итем с наименованием '" + itemText + " ' имеет статус 'hide'")
        );
    }

    public void deleteMRGroup(String itemName){
        Pages.projectPage().settingsPage().settingsTab().clickDeleteItem();
        TestRailAssert.assertTrue(
                Pages.projectPage().settingsPage().settingsTab().isDeleteConfirmationPopUpPresent(),
                new CustomStepResult(
                        "всплывающее окно с возможностью удалить '"+ itemName +"' не отображается",
                        "всплывающее окно с возможностью удалить '"+ itemName +"' успешно отображается")
        );
        Pages.projectPage().settingsPage().settingsTab().clickDeleteConfirmation();
    }

    public void checkMRGroup(String itemGroupName, String groupKind) {
        Pages.projectPage().settingsPage().settingsTab().clickItemSearch();
        Pages.projectPage().settingsPage().settingsTab().typeTextIntoSearchField(itemGroupName);
        BaseActions.wait(2);
        TestRailAssert.assertTrue(
                Pages.projectPage().settingsPage().settingsTab().isItemPresent(itemGroupName),
                new CustomStepResult(
                        "группа с наименованием '" + itemGroupName + "' не отображается",
                        "группа с наименованием '" + itemGroupName + "' успешно создалась и отображается")
        );
        BaseActions.wait(5);
        TestRailAssert.assertTrue(
                Pages.projectPage().settingsPage().settingsTab().isKindOfGroupPresent(groupKind),
                new CustomStepResult(
                        "группа с наименованием '" + itemGroupName + "' не содержит тип '" + groupKind + "'",
                        "группа с наименованием '" + itemGroupName + "' содержит тип '" + groupKind + "'")
        );


    }

    public void openCategory(String categoryName) {
        Pages.projectPage().categoryRegion().waitForLoad();
        Pages.projectPage().categoryRegion().clickCategory(categoryName);
        wait(5);

    }

    public void checkItemPresence(String itemName) {
        Pages.navigationBar().waitLoader();
        Pages.projectPage().categoryRegion().waitForItemsRegionVisibility();
        boolean itemPresent = Pages.projectPage().categoryRegion().isItemPresent(itemName);
        Assert.assertTrue(itemPresent, String.format("Item %s isn't present", itemName));
    }

    public void exportToDashboard(String dashboardName) {
        Pages.projectPage().toolbarRegion().waitForLoad();

        Pages.projectPage().toolbarRegion().clickExportToDashboard();

        Pages.projectPage().exportDialog().waitForExportToNewDashboard();
        BaseActions.wait(5);

        TestRailAssert.assertTrue(
                Pages.projectPage().exportDialog().isExportToDashboardRegionVisible(),
                new CustomStepResult(
                        "Окно 'Export to dashboard' не отображается",
                        "Отображается окно 'Export to dashboard'")
        );

        Pages.projectPage().exportDialog().clickExportToNewDashboard();
        BaseActions.wait(5);

        Pages.projectPage().exportDialog().waitForNewDashboardNameInputField();
        BaseActions.wait(5);

        TestRailAssert.assertTrue(
                Pages.projectPage().exportDialog().isNewDashboardNameFieldVisible(),
                new CustomStepResult(
                        "Поле для ввода имени дашбоарда не отображается",
                        "Отображается поле для ввода имени нового дашбоарда")
        );

        Pages.projectPage().exportDialog().setDashboardName(dashboardName);
        BaseActions.wait(5);
        Pages.projectPage().exportDialog().clickExportButton();

        TestRailAssert.assertTrue(
                Pages.projectPage().exportDialog().isSuccessAlertVisible(),
                new CustomStepResult(
                        "Пользователь не видит сообщение 'Chart is added to Dashboard Name'",
                        "Пользователь видит сообщение 'Chart is added to Dashboard Name'")
        );
    }

    public void exportToDashboardFromChart(String dashboardName){

        Pages.projectPage().toolbarRegion().clickExportToDashboardFromChart();

        Pages.projectPage().exportDialog().waitForExportToNewDashboard();
        BaseActions.wait(5);

        TestRailAssert.assertTrue(
                Pages.projectPage().exportDialog().isExportToDashboardRegionVisible(),
                new CustomStepResult(
                        "Окно 'Export to dashboard' не отображается",
                        "Отображается окно 'Export to dashboard'")
        );

        Pages.projectPage().exportDialog().clickExportToNewDashboard();
        BaseActions.wait(5);

        Pages.projectPage().exportDialog().waitForNewDashboardNameInputField();
        BaseActions.wait(5);

        TestRailAssert.assertTrue(
                Pages.projectPage().exportDialog().isNewDashboardNameFieldVisible(),
                new CustomStepResult(
                        "Поле для ввода имени дашбоарда не отображается",
                        "Отображается поле для ввода имени нового дашбоарда")
        );

        Pages.projectPage().exportDialog().setDashboardName(dashboardName);
        BaseActions.wait(5);
        Pages.projectPage().exportDialog().clickExportButton();

        TestRailAssert.assertTrue(
                Pages.projectPage().exportDialog().isSuccessAlertVisible(),
                new CustomStepResult(
                        "Пользователь не видит сообщение 'Chart is added to Dashboard Name'",
                        "Пользователь видит сообщение 'Chart is added to Dashboard Name'")
        );
    }

    public void openMySpace() {
        Pages.projectPage().categoryRegion().waitForMySpaceLinkToBeVisible();
        Pages.projectPage().categoryRegion().waitForMySpaceLinkToBeClickable();
        Pages.projectPage().categoryRegion().clickMySpaceLink();
        wait(5);
        TestRailAssert.assertTrue(
                "My space".equals(Pages.navigationBar().getLastFolderName()),
                new CustomStepResult(
                        "Страница 'My space' не отображается",
                        "Отображается страница 'My space'")
        );
    }

    public void createSimpleSingleRequest(String data) throws Exception {
        Pages.projectPage().categoryRegion().waitForLoad();
        if (data.equalsIgnoreCase("awareness_1")) {
            Pages.projectPage().categoryRegion().fillRows(data);
        }
        else {
            Pages.projectPage().categoryRegion().fillColumns(data);
        }
        Pages.navigationBar().waitLoader();
        Pages.projectPage().toolbarRegion().clickCalculateButton();
        Pages.navigationBar().waitLoader();
        TestRailAssert.assertTrue(
                checkDataDViRows(data),
                new CustomStepResult(
                        "Кросс-таб не построен корректно",
                        "Кросс-таб построен корректно")
        );
    }

    public void createSimpleRequest(String rowsData, String columnsData) throws Exception {
        Pages.projectPage().categoryRegion().waitForLoad();
        Pages.projectPage().categoryRegion().fillRows(rowsData);
        Pages.navigationBar().waitLoader();
        Pages.projectPage().categoryRegion().fillColumns(columnsData);
        Pages.navigationBar().waitLoader();
        Pages.projectPage().toolbarRegion().clickCalculateButton();
        Pages.navigationBar().waitLoader();

        TestRailAssert.assertTrue(
                checkDataDViRows(rowsData) && checkDataDViColumns(columnsData),
                new CustomStepResult(
                        "Кросс-таб не построен корректно",
                        "Кросс-таб построен корректно")
        );
    }

    public void createAnotherRequest(String rowsData, String columnsData) throws Exception {
        Pages.projectPage().categoryRegion().waitForLoad();
        Pages.projectPage().categoryRegion().fillRows(rowsData);
        Pages.navigationBar().waitLoader();
        Pages.projectPage().categoryRegion().fillColumns(columnsData);
        Pages.navigationBar().waitLoader();
        Pages.projectPage().toolbarRegion().clickCalculateButton();
        Pages.navigationBar().waitLoader();
    }


    public boolean checkDataDViRows(String imageName) throws Exception {
        BaseActions.wait(3);
        Pages.projectPage().categoryRegion().getScreenshotDViRow("actual" + imageName);
        return new ImageComparison(10, 10, 0.15)
                .fuzzyEqual(
                        System.getProperty(PropertiesNames.UPLOAD_DIR.toString()) +
                                File.separator + imageName.toLowerCase() + ".png",
                        System.getProperty(PropertiesNames.SCREENSHOT_DIR.toString()) +
                                File.separator + "actual" + imageName + ".png",
                        System.getProperty(PropertiesNames.SCREENSHOT_DIR.toString()) +
                                File.separator + "differences.png");
    }

    public boolean checkDataDViColumns(String imageName) throws Exception {
        BaseActions.wait(3);
        if (imageName.equals("Gender"))
            imageName = "GenderHorizontal";
        Pages.projectPage().categoryRegion().getScreenshotDViColumns("actual" + imageName);

        return new ImageComparison(10, 10, 0.15)
                .fuzzyEqual(
                        System.getProperty(PropertiesNames.UPLOAD_DIR.toString()) +
                                File.separator + imageName.toLowerCase() + ".png",
                        System.getProperty(PropertiesNames.SCREENSHOT_DIR.toString()) +
                                File.separator + "actual" + imageName + ".png",
                        System.getProperty(PropertiesNames.SCREENSHOT_DIR.toString()) +
                                File.separator + "differences.png");
    }

    public void openProjectSettings() {
        Reporter.logAction("Open project settings");
        Pages.mainPage().toolbarRegion().waitSettingsButtonToBeVisible();
        Pages.mainPage().toolbarRegion().clickSettingsButton();
        BaseActions.wait(3);

        TestRailAssert.assertTrue(
                "Settings".equals(Pages.navigationBar().getLastFolderName()) &&
                        Pages.projectPage().settingsPage().navigationTabs().isSettingsPageVisible(),
                new CustomStepResult(
                        "Настройки проекта не отображаются",
                        "Отображаются настройки проекта")
        );
    }

    public void openSettingsTab() {
        Reporter.logAction("Open settings tab");
        Pages.projectPage().settingsPage().navigationTabs().waitTabGroupToBeVisible();
        Pages.projectPage().settingsPage().navigationTabs().clickOnSettingsTab();
        Pages.projectPage().settingsPage().settingsTab().waitListSettingsVisible();

        TestRailAssert.assertTrue(
                Pages.projectPage().settingsPage().settingsTab().isListSettingsVisible(),
                new CustomStepResult(
                        "Страница 'Settings' со списком функционала не отображается",
                        "Отображается страница 'Settings' со списком функционала")
        );
    }

    public void openSettingsItemByName(String itemName) {
        Reporter.logAction("Open " + itemName + " settings");
        Pages.projectPage().settingsPage().settingsTab().waitListSettingsVisible();
        Pages.projectPage().settingsPage().settingsTab().clickSettingsItemByName(itemName);
    }

    public void setCheckBoxHideOnDVI() {
        Pages.projectPage().settingsPage().settingsTab().waitHideOnDVIToBeVisible();
        Pages.projectPage().settingsPage().settingsTab().setHideOnDVIStateTrue();
        Assert.assertTrue(Pages.projectPage().settingsPage().settingsTab().isDefaultWeightVisible(),
                "Default weight is not visible.");
    }

    public void addToBookmark(String bookmarkName) {
        ProjectPage.toolbarRegion().waitBookMarkerButton();
        ProjectPage.toolbarRegion().clickBookMarkerButton();

        wait(3);

        TestRailAssert.assertTrue(
                ProjectPage.toolbarRegion().isBookMarkerModalWindow() &&
                        ProjectPage.toolbarRegion().isBookMarkerModWindowButtonVisible("OK") &&
                        ProjectPage.toolbarRegion().isBookMarkerModWindowButtonVisible("Cancel"),
                new CustomStepResult(
                        "Попап с полем для ввода названия закладки и/или кнопки 'OK', 'Cancel' не отображается",
                        "Отображается попап с полем для ввода названия закладки и кнопки 'OK', 'Cancel'")
        );

        ProjectPage.toolbarRegion().waitBookMarkerModalWindow();
        ProjectPage.toolbarRegion().setBookMarkerName(bookmarkName);
        ProjectPage.toolbarRegion().clickBookMarkerModWindowButton("OK");

        wait(3);
        TestRailAssert.assertTrue(ProjectPage.tableRegion().isDVIRegionVisible(),
                new CustomStepResult(
                        "Попап не закрыт и/или DVI проекта не отображается",
                        "Попап закрыт, отображается DVI проекта")
        );

        ProjectPage.toolbarRegion().clickBookMarkerDropDownButton();

        ProjectPage.toolbarRegion().waitBookMarkerList();

        TestRailAssert.assertTrue(
                ProjectPage.toolbarRegion().isBookMarkerItemPresent(bookmarkName),
                new CustomStepResult(
                        "Созданая закладка не отображается в выпадающем списке",
                        "Созданая закладка отображается в выпадающем списке")
        );

        ProjectPage.toolbarRegion().clickBookMarkerDropDownButton();
    }

    public void selectBookMarkAndRestoreCrosstab(String bookmarkName){
        ProjectPage.toolbarRegion().clickBookMarkerDropDownButton();
        ProjectPage.toolbarRegion().waitBookMarkerList();
        ProjectPage.toolbarRegion().clickBookMarkerItemByName(bookmarkName);
        ProjectPage.toolbarRegion().waitBookMarkerListInvisibility();
    }


    public void removeBookmark(String bookmarkName) {

        ProjectPage.toolbarRegion().clickBookMarkerDropDownButton();
        ProjectPage.toolbarRegion().waitBookMarkerList();
        ProjectPage.toolbarRegion().clickRemoveBookMark(bookmarkName);

        Actions.generalActions().wait(5);

        TestRailAssert.assertTrue(
                (!ProjectPage.toolbarRegion().isBookMarkerDropDownButtonPresent()) &&
                        Pages.projectPage().tableRegion().isDVIRegionVisible(),
                new CustomStepResult(
                        "DVI проекта не отображается и/или возле кнопки букмарка отображается кнопка выпадающего списка",
                        "Отображается DVI проекта. Возле кнопки букмарка не отображается кнопка выпадающего списка")
        );
    }

    public void saveAsBookmark(String bookmarkName, String newBookmarkName) {

        ProjectPage.toolbarRegion().clickBookMarkerDropDownButton();
        ProjectPage.toolbarRegion().waitBookMarkerList();
        ProjectPage.toolbarRegion().clickSaveAsBookMark(bookmarkName);
        ProjectPage.toolbarRegion().waitBookMarkerModalWindow();

        TestRailAssert.assertTrue(
                ProjectPage.toolbarRegion().isBookMarkerModalWindow() &&
                        ProjectPage.toolbarRegion().isBookMarkerModWindowButtonVisible("OK") &&
                        ProjectPage.toolbarRegion().isBookMarkerModWindowButtonVisible("Cancel"),
                new CustomStepResult(
                        "Попап с полем для ввода названия закладки и/или кнопки 'OK', 'Cancel' не отображается",
                        "Отображается попап с полем для ввода названия закладки и кнопки 'OK', 'Cancel'")
        );

        ProjectPage.toolbarRegion().setBookMarkerName(newBookmarkName);

        Actions.generalActions().wait(2);

        ProjectPage.toolbarRegion().clickBookMarkerModWindowButton("OK");
        ProjectPage.toolbarRegion().clickBookMarkerDropDownButton();
        ProjectPage.toolbarRegion().waitBookMarkerList();


        TestRailAssert.assertTrue(
                ProjectPage.toolbarRegion().isBookMarkerItemPresent(newBookmarkName),
                new CustomStepResult(
                        "Созданая закладка не отображается в выпадающем списке",
                        "Созданая закладка отображается в выпадающем списке")
        );

        ProjectPage.toolbarRegion().clickBookMarkerDropDownButton();
    }

    public void setFilter(String variableNameForFilter) {
        ProjectPage.toolbarRegion().waitForLoad();
        ProjectPage.tableRegion().setFilter(variableNameForFilter);
        wait(5);
        TestRailAssert.assertTrue(
                "(male Or female)".equals(ProjectPage.tableRegion().getFilterValue()),
                new CustomStepResult(
                        "Nмя переменной не отображается в поле 'Filter'",
                        "Nмя переменной отображается в поле 'Filter'")
        );
    }

    public void setSimpleLogicalExpression(String itemName, String expressionType, int... args) {
        openExpressionBuilder();
        setLogicalExpression(itemName, expressionType, args);

        TestRailAssert.assertTrue(
                expressionType.equals(ProjectPage.tableRegion().getCurrentLogicalType()),
                new CustomStepResult(
                        "Выражение в окне 'Expression builder' не перестроилось согласно выбранного условия",
                        "Выражение в окне 'Expression builder' перестроилось согласно выбранного условия")
        );

        ProjectPage.tableRegion().clickOnExpressionBuilderButton("Save");
    }

    public String getCrossTabVariableName() {
        ProjectPage.tableRegion().clickOnVariableName();
        String varName = ProjectPage.tableRegion().getVariableName();
        /*
        Robot robot = null;
        try {
            robot = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }

        robot.keyPress(KeyEvent.VK_ENTER);
        wait(1);
        robot.keyRelease(KeyEvent.VK_ENTER);
        */
        ProjectPage.tableRegion().pressEnterOnCanvasField();
        return varName;
    }

    public String getLogicalExpressionFromExpressionBuilder() {
        ProjectPage.tableRegion().waitExpressionRegion();
        Assert.assertTrue(ProjectPage.tableRegion().isExpressionRegionVisible(),
                "Expression builder region is not present.");
        ProjectPage.tableRegion().clickDropableItem();
        ProjectPage.tableRegion().getLogicalExpressionFromExpressionBuilder();
        return ProjectPage.tableRegion().getLogicalExpressionFromExpressionBuilder().toLowerCase();
    }

    public void openExpressionBuilder() {
        ProjectPage.tableRegion().clickOnExpressionBuilderIcon();
        ProjectPage.tableRegion().waitExpressionRegion();

        TestRailAssert.assertTrue(
                ProjectPage.tableRegion().isExpressionRegionVisible(),
                new CustomStepResult(
                        "Диалоговое окно 'Expression builder' не отображается",
                        "Отображается диалоговое окно 'Expression builder'")
        );
    }

    public void setLogicalExpression(String itemName, String expressionType, int... args) {
        ProjectPage.tableRegion().fillExpressionBuilderRegion(itemName, args);
        wait(3);
        TestRailAssert.assertTrue(
                ProjectPage.tableRegion().isLogicalExpressionButtonsPresent("AND", "OR"),
                new CustomStepResult(
                        "Группа кнопок для выбора условия 'And/Or' в окне 'Expression builder' не отображается",
                        "Отображается группа кнопок для выбора условия 'And/Or' в окне 'Expression builder'")
        );

        ProjectPage.tableRegion().clickOnExpressionType(expressionType, args);
    }

    public void clickEscape() throws AWTException {
        ProjectPage.tableRegion().escape();

    }

    public void clickEnter() throws AWTException {
        ProjectPage.tableRegion().enter();

    }

}

