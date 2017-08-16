package eu.datatiler.actions;


import com.qatestlab.base.BaseActions;
import com.qatestlab.properties.PropertiesNames;
import com.qatestlab.reporting.Reporter;
import com.qatestlab.testrail.CustomStepResult;
import com.qatestlab.testrail.TestRailAssert;
import eu.datatiler.pages.Pages;
import eu.datatiler.pages.dashboard.DashboardPage;
import eu.datatiler.pages.encoder.EncoderPage;
import eu.datatiler.pages.main.ContentRegion;
import eu.datatiler.pages.main.CreatingDialog;
import org.testng.Assert;

import java.awt.*;


public class ItemActions extends BaseActions {


    protected ItemActions() {
    }

    public void scrollDown(){
        Pages.mainPage().contentRegion().scrollToPageFooter();
    }

    public void refreshCrosstabData(String button){
        Pages.mainPage().contentRegion().clickContextButton(button);
    }

    public void clickOnContextButton(String buttonName){
        Pages.mainPage().contentRegion().clickContextButton(buttonName);
        TestRailAssert.assertTrue(
        Pages.mainPage().contentRegion().checkForButtonActivity(buttonName),
                new CustomStepResult(
                        "кнопка '" + buttonName + "' не нажалась",
                        "кнопка '" + buttonName + "' успешно нажалась и "+
                                "переменные перенеслись только на Rows"
                )
        );

    }

    public boolean checkForItem(String itemName){

        return Pages.mainPage().contentRegion().isItemPresent(itemName);
    }

    public void createFolder(String itemName) {
        Reporter.logAction(String.format("Create new folder '%s':", itemName));
        ContentRegion content = Pages.mainPage().contentRegion();
        CreatingDialog dialog = Pages.mainPage().creatingDialog();

        openCreatingDialog("Folder");
        dialog.typeName(itemName);
        dialog.clickCreateButton();
        dialog.waitForInvisibility();
        content.waitForItem(itemName);

        TestRailAssert.assertTrue(
                Pages.mainPage().contentRegion().isItemPresent(itemName),
                new CustomStepResult(
                        "Созданная папка не отображается на странице " + Pages.navigationBar().getLastFolderName(),
                        "Папка успешно создана и отображается на странице " + Pages.navigationBar().getLastFolderName()
                )
        );
    }

    /**
     * Opens creating dialog for specified item
     *
     * @value itemName
     */
    public void openCreatingDialog(String itemName) {
        Reporter.logAction(String.format("Opening creating dialog for new '%s':", itemName));
        Pages.mainPage().toolbarRegion().waitCreateButtonToBeClickable();
        Pages.mainPage().toolbarRegion().clickCreateButton();
        Pages.mainPage().toolbarRegion().waitCreateMenuVisibility();
        Pages.mainPage().toolbarRegion().waitCreateMenuToBeClickable();

        TestRailAssert.assertTrue(
                Pages.mainPage().toolbarRegion().isCreateItemPresent(itemName),
                new CustomStepResult(
                        "Дропдаун с опцией: '" + itemName + "' не отображается",
                        "Отображается дропдаун с опцией: '" + itemName + "'"
                )
        );

        Pages.mainPage().toolbarRegion().clickCreateItem(itemName);
        Pages.mainPage().creatingDialog().waitForVisibility();

        TestRailAssert.assertTrue(
                Pages.mainPage().creatingDialog().isCreatingDialogPresent(itemName),
                new CustomStepResult(
                        "Не отображается модальное окно'Creating a " + itemName.toLowerCase() + "'",
                        "Отображается модальное окно 'Creating a " + itemName.toLowerCase() + "'"
                )
        );
    }

    public void createFolderWithEmptyName() {
        openCreatingDialog("Folder");
        Pages.mainPage().creatingDialog().waitForVisibility();
        Pages.mainPage().creatingDialog().typeName("");

        TestRailAssert.assertFalse(
                Pages.mainPage().creatingDialog().isCreateButtonDisabled(),
                new CustomStepResult(
                        "Кнопка 'Create' неактивна - создать папку невозможно",
                        "Кнопка 'Create' активная"
                        )
        );

        Pages.mainPage().creatingDialog().clickCreateButton();
    }

    public void cancelFolderCreation(String name) {
        openCreatingDialog("Folder");
        Pages.mainPage().creatingDialog().waitForVisibility();
        Pages.mainPage().creatingDialog().typeName(name);
        Pages.mainPage().creatingDialog().clickCancelButton();
        Pages.mainPage().creatingDialog().waitForInvisibility();
    }

    public void openProject(String projectName){
        openItem(projectName);
        BaseActions.wait(5);
        Pages.navigationBar().waitForSearchField();
        TestRailAssert.assertTrue(
                Pages.projectPage().tableRegion().isDVIRegionVisible(),
                new CustomStepResult(
                        "DVI проекта не отображается",
                        "Отображается DVI проекта"
                )
        );
    }

    public void openDashboard(String projectName){
        openItem(projectName);

        TestRailAssert.assertTrue(
                DashboardPage.createTextWidgetDialog().isDashBoardAreaPresent(),
                new CustomStepResult(
                        "Dashboard не отображается",
                        "Dashboard отображается"
                )
        );

        TestRailAssert.assertFalse(
                DashboardPage.createTextWidgetDialog().isDashBoardAreaEmpty(),
                new CustomStepResult(
                        "Dashboard отображается не пустым",
                        "Dashboard отображается пустым"
                )
        );
    }

    public void openEncoder(String encoderName){
        openItem(encoderName);
        wait(5);
        TestRailAssert.assertTrue(
                EncoderPage.categoryRegion().isListInterviewItemVisible(),
                new CustomStepResult(
                        "Список переменных не отображается",
                        "Отображается список переменных"
                )
        );
    }

    public void openItem(String name) {
        Pages.mainPage().contentRegion().waitItemToBeClickable(name);
        Pages.mainPage().contentRegion().clickOnItemBy(name);
        Pages.mainPage().contentRegion().clickOnItemBy(name);
        BaseActions.wait(5);
        Pages.navigationBar().waitLoader();
    }

    public void openSection(String sectionName) {
        Pages.mainPage().sidebarRegion().waitForSectionVisibility(sectionName);
        Pages.mainPage().sidebarRegion().clickSection(sectionName);
    }

    public void openRootFolder() {
        Pages.mainPage().contentRegion().waitBackToRootVisibility();
        Pages.mainPage().contentRegion().clickBackToRoot();
    }

    public void remove() {
        Pages.mainPage().toolbarRegion().waitRemoveButtonToBeClickable();
        Pages.mainPage().toolbarRegion().clickRemoveButton();
        Pages.mainPage().toolbarRegion().clickDeleteButtonInDeleteModDialog();
        BaseActions.wait(5);
    }

    public void removeWithAssert() {
        Pages.mainPage().toolbarRegion().waitRemoveButtonToBeClickable();
        Pages.mainPage().toolbarRegion().clickRemoveButton();

        TestRailAssert.assertTrue(
                Pages.mainPage().toolbarRegion().isModalDialogCancelButtonVisible() &&
                        Pages.mainPage().toolbarRegion().isModalDialogDeleteButtonVisible() &&
                        "Are you sure to delete selected items?"
                                .equals(Pages.mainPage().toolbarRegion().getModalDialogHeader()),
                new CustomStepResult(
                        "Сообщение 'Are you sure to delete selected items?' не отображается или" +
                                " кнопки 'Cancel' и 'Delete' не отображаются",
                        "Отображается сообщение 'Are you sure to delete selected items?' с" +
                                " кнопками 'Cancel' и 'Delete'")
        );


        Pages.mainPage().toolbarRegion().clickDeleteButtonInDeleteModDialog();
        BaseActions.wait(5);
    }

    public void removeItem(String name) {
        Reporter.logAction(String.format("Deleting item with name '%s'", name));
        Pages.navigationBar().waitLoader();
        selectItem(name);
        remove();
        Pages.mainPage().contentRegion().waitForItemInvisibility(name);

    }

    public void selectFolder(String folderName) {
        Reporter.logAction(String.format("Selecting folder with name '%s'", folderName));
        Pages.navigationBar().waitLoader();
        selectItem(folderName);

        TestRailAssert.assertTrue(
                Pages.mainPage().toolbarRegion().isShareButtonVisible() &&
                        Pages.mainPage().toolbarRegion().isRenameButtonVisible() &&
                        Pages.mainPage().toolbarRegion().isDeleteButtonVisible(),
                new CustomStepResult(
                        "Над папкой отображаются кнопки(-ка) 'SHARE', 'RENAME', 'DELETE' не отображае(-ю)тся",
                        "Над папкой отображаются кнопки 'SHARE', 'RENAME', 'DELETE'")
        );
    }

    public void removeItemsIfPresent(String itemsName) {
        Reporter.logAction(String.format("Deleting items with name '%s'", itemsName));
        Pages.navigationBar().waitLoader();
        if (isItemPresent(itemsName)) {
            selectItems(itemsName);
            remove();
            Pages.mainPage().contentRegion().waitForItemInvisibility(itemsName);
        }
    }

    public boolean isItemPresent(String name) {
        return Pages.mainPage().contentRegion().isItemPresent(name);
    }

    public void checkItemPresence(String itemName) {
        boolean itemPresent = Pages.mainPage().contentRegion().isItemPresent(itemName);
        Assert.assertTrue(itemPresent, String.format("Item '%s' not found", itemName));
    }

    public int getIdenticalFoldersCount(String folderName) {
        int foldersCount = Pages.mainPage().contentRegion().getIdenticalItemsCount(folderName);
        return foldersCount;
    }

    public void renameItem(String itemName, String newName) {
        Pages.mainPage().contentRegion().waitEditButtonToBeClickable(itemName);
        Pages.mainPage().contentRegion().clickEditName(itemName);
        Pages.mainPage().editDialog().waitForVisibility();

        TestRailAssert.assertTrue(
                "Edit name".equals(Pages.mainPage().editDialog().getModalDialogHeader()) &&
                        Pages.mainPage().editDialog().isModalDialogCancelButtonVisible(),
                new CustomStepResult(
                        "Пользователь модальное окно 'Edit name' не отображается",
                        "Пользователь отображается модальное окно 'Edit name'")
        );

        Pages.mainPage().editDialog().typeName(newName);
        wait(com.qatestlab.base.BasePage.ELEMENT_EXTRASMALL_TIMEOUT_SECONDS);
        Pages.mainPage().editDialog().clickSaveButton();

        TestRailAssert.assertTrue(
                "Item was successfully renamed".equals(Pages.projectPage().exportDialog().getSuccessAlertText()) &&
                        Pages.mainPage().contentRegion().isItemPresent(newName),
                new CustomStepResult(
                        "Пользователь не видит сообщение 'Item was successfully renamed' вверху экрана " +
                                "или отредактированное название не сохранено",
                        "Пользователь видит сообщение 'Item was successfully renamed' вверху экрана -" +
                                " отредактированное название успешно сохранено")
        );


        Pages.mainPage().editDialog().waitForInvisibility();
    }

    public void selectItem(String itemName) {
        Reporter.logAction(String.format("Selecting item with name '%s'", itemName));
        Pages.mainPage().contentRegion().waitForItem(itemName);
        Pages.mainPage().contentRegion().clickOnItemBy(itemName);
    }

    public void selectDashboard(String dashboardName) {
        Reporter.logAction(String.format("Selecting dashboard with name '%s'", dashboardName));
        Pages.mainPage().contentRegion().waitForItem(dashboardName);
        Pages.mainPage().contentRegion().clickOnItemBy(dashboardName);


        TestRailAssert.assertTrue(
                Pages.mainPage().toolbarRegion().isShareButtonVisible() &&
                        Pages.mainPage().toolbarRegion().isRenameButtonVisible() &&
                        Pages.mainPage().toolbarRegion().isDeleteButtonVisible() &&
                        Pages.mainPage().toolbarRegion().isRefreshButtonVisible() &&
                        Pages.mainPage().toolbarRegion().isStarButtonVisible(),
                new CustomStepResult(
                        "Над dashboard(-ом) кнопки(-ка) 'SHARE', 'RENAME', 'DELETE' и/или" +
                                " иконки 'Refresh' и 'Star' не отображае(-ю)тся",
                        "Над dashboard(-ом) отображаются кнопки 'SHARE', 'RENAME', 'DELETE'" +
                                ", а также иконки 'Refresh' и 'Star'")
        );

    }

    public void selectProject(String projectName) {
        Reporter.logAction(String.format("Selecting project with name '%s'", projectName));
        Pages.mainPage().contentRegion().waitForItem(projectName);
        Pages.mainPage().contentRegion().clickOnItemBy(projectName);


        TestRailAssert.assertTrue(
                Pages.mainPage().toolbarRegion().isShareButtonVisible() &&
                        Pages.mainPage().toolbarRegion().isRenameButtonVisible() &&
                        Pages.mainPage().toolbarRegion().isDeleteButtonVisible() &&
                        Pages.mainPage().toolbarRegion().isRefreshButtonVisible() &&
                        Pages.mainPage().toolbarRegion().isSettingsButtonVisible() &&
                        Pages.mainPage().toolbarRegion().isStarButtonVisible(),
                new CustomStepResult(
                        "Над dashboard(-ом) кнопки(-ка) 'SHARE', 'RENAME', 'DELETE', 'SETTINGS' и/или" +
                                " иконки 'Refresh' и 'Star' не отображае(-ю)тся",
                        "Над dashboard(-ом) отображаются кнопки 'SHARE', 'RENAME', 'DELETE', 'SETTINGS'" +
                                ", а также иконки 'Refresh' и 'Star'")
        );

    }

    public void selectItems(String itemsName) {
        Reporter.logAction(String.format("Selecting items with name '%s'", itemsName));
        Pages.mainPage().contentRegion().waitForItem(itemsName);
        try {
            Pages.mainPage().contentRegion().selectAllDuplicatedItemsBy(itemsName);
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }

    public void openShareDialog() {
        Pages.mainPage().toolbarRegion().waitShareButtonToBeClickable();
        Pages.mainPage().toolbarRegion().clickShareButton();
        Pages.mainPage().shareDialog().waitForLoad();

        TestRailAssert.assertTrue(
                Pages.mainPage().shareDialog().isShareWindowVisible(),
                new CustomStepResult(
                        "Пользователь не видит модальное окно '   :Share with others'",
                        "Пользователь видит модальное окно '   :Share with others'")
        );
    }

    public void copyToClipboard() {
        Pages.mainPage().shareDialog().waitForLoad();
        Pages.mainPage().shareDialog().checkPublicCheckbox();

        TestRailAssert.assertTrue(
                Pages.mainPage().shareDialog().isShareURLFieldVisible(),
                new CustomStepResult(
                        "Строка, содержащая ссылку на соответствующий Дэшборд, не отображается",
                        "Отображается строка, содержащая ссылку на соответствующий Дэшборд")
        );

        Pages.mainPage().shareDialog().waitCopyButtonClickable();
        Pages.mainPage().shareDialog().clickCopyToClipboard();
        Actions.generalActions().wait(2);
        Pages.mainPage().shareDialog().pasteToEmailInput();
        Actions.generalActions().wait(2);

        TestRailAssert.assertTrue(
                Pages.mainPage().shareDialog().getEmailInputText()
                        .equals(Pages.mainPage().shareDialog().getPublicReference()),
                new CustomStepResult(
                        "Соответствующая ссылка успешно не скопирована",
                        "Соответствующая ссылка успешно скопирована")
        );
    }

    public String copyToClipboardWithPublicMod() {
        Pages.mainPage().shareDialog().waitForLoad();
        Pages.mainPage().shareDialog().checkPublicCheckbox();
        Pages.mainPage().shareDialog().waitCopyButtonClickable();
        Pages.mainPage().shareDialog().clickCopyToClipboard();
        return Pages.mainPage().shareDialog().getPublicReference();
    }

    public void closeShareDialog() {
        Pages.mainPage().shareDialog().clickCloseButton();
        Pages.navigationBar().waitSuccessAlertInvisibility();
    }

    public void createProject(String projectName, String file) {
        openCreatingDialog("Database");
        Pages.mainPage().creatingDialog().uploadProjectFile(file);

        TestRailAssert.assertTrue(
                Pages.mainPage().creatingDialog().isFileUploaded(file),
                new CustomStepResult(
                        "Архив с данными не подгружен",
                        "Архив с данными подгружен")
        );
// PropertiesNames.PROJECT_NAME.toString()
        Pages.mainPage().creatingDialog().typeName(projectName);
        Pages.mainPage().creatingDialog().waitForCreateButtonToBeClickable();
        Pages.mainPage().creatingDialog().clickCreateButton();
        Pages.mainPage().creatingDialog().waitForInvisibility();
        Pages.navigationBar().waitProgressBar();

        TestRailAssert.assertTrue(
                Actions.itemActions().isItemPresent(projectName),
                new CustomStepResult(
                        "Проект создан неуспешно или не отображается на странице",
                        "Проект успешно создан и отображается")
        );
    }

    public void createEncoder(String encoderName) {
        openCreatingDialog("Text refiner");
        Pages.mainPage().creatingDialog().uploadEncodeFile(encoderName);

        TestRailAssert.assertTrue(
                Pages.mainPage().creatingDialog().isFileUploaded(encoderName),
                new CustomStepResult(
                        "Файл с данными не подгружен",
                        "Файл с данными подгружен")
        );
// PropertiesNames.ENCODER_NAME.toString()
        Pages.mainPage().creatingDialog().typeName(encoderName);
        Pages.mainPage().creatingDialog().waitForCreateButtonToBeClickable();
        Pages.mainPage().creatingDialog().clickCreateButton();
        Pages.mainPage().creatingDialog().waitForInvisibility();
        Pages.navigationBar().waitProgressBar();

        TestRailAssert.assertTrue(
                Actions.itemActions().isItemPresent(encoderName),
                new CustomStepResult(
                        "Енкодер создан неуспешно или не отображается на странице",
                        "Енкодер успешно создан и отображается")
        );
    }

    public void createDashboard(String dashboardName) {
        openCreatingDialog("Dashboard");
        Pages.mainPage().creatingDialog().typeName(dashboardName);
        Pages.mainPage().creatingDialog().clickCreateButton();
        Pages.mainPage().creatingDialog().waitForInvisibility();
    }

}
