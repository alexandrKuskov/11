package eu.datatiler.tests;

import com.qatestlab.base.BaseActions;
import com.qatestlab.base.BaseTest;
import com.qatestlab.testrail.CustomStepResult;
import com.qatestlab.testrail.TestRailAssert;
import com.qatestlab.testrail.TestRailIssue;
import eu.datatiler.actions.Actions;
import eu.datatiler.pages.Pages;
import eu.datatiler.pages.dashboard.DashboardPage;
import eu.datatiler.pages.project.ProjectPage;
import eu.datatiler.utils.DataProviderPool;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class ControlTests extends BaseTest {

    private final String TEST_RUN_NAME = "Dashboard tests";
    private String textWidgetWithControl = "UAT_DashboardForTextWidgetWithControl";
    private String buttonMultiSelectDashboard = "UAT_ButtonMultiSelectControl";
    private String dashboardForControlEdit = "UAT_DashboardForControlEdit";
    private String newDashboard = "New Dashboard";
    private String dashboardForDefaultControl = "UAT_DashboardForDefaultControl";
    private String dashboardFoChartRedraw = "UAT_DashboardForChartRedraw";
    private String dashboardForSortTest = "UAT_DashboardForSortTest";
    private String dashboardForAbortBindTest = "UAT_DashboardForAbortBindTest";
    private String dashboardForMovingFolderVar = "UAT_Project_moving_folder_with_var";
    private String widgetTitle = "Table";
    private String verticalOrientation = "vertical";
    private String maleState = "male";
    private String femaleState = "female";
    private String maleFemaleState = "male_female";
    private String controlName = "Control";
    private String genderCategory = "Gender";
    private String newFolderCategory = "new folder";
    private String educationCategory = "Education";
    private String buttonStyle = "Button";
    private String dropdownStyle = "DropDown";
    private String singleSelect = "Single";
    private String multiSelect = "Multi";
    private String highEducationState = "High Education";
    private List<String> itemsName = new ArrayList<>();
    private String folderName = "UAT";
    private String dashboardInFolderName = "UAT_Dashboard";
    private String text = "text";
    private String anotherText = "anotherText";
    private String finalText = "finalText";
    private String firstState = "State 1";
    private String secondState = "State 2";
    private String addCondition = "Add condition";
    private String deleteCondition = "Delete";
    private String dviItem = "DVI";
    private String contextItem = "Context";
    private String mayorItem = "Mayor";
    private String dataLabelsOption = "Data labels";
    private String dataLabelsItem = "Show data labels";
    private String controlTestsFolder = "UAT_ControlTests";
    private String genderItem = "Gender";
    private String educationItem = "Education";

        @TestRailIssue(issueID = 51, testRunName = TEST_RUN_NAME)
        @Test(dataProvider = DataProviderPool.PROJECT_MANAGER_CREDENTIALS, dataProviderClass = DataProviderPool.class,
                description = "Checking if new button controlName with multi select has correct state (UAT-407)", alwaysRun = true)
    public void addingButtonControlWithMultipleSelect(String login, String pass) throws Exception {

            Actions.generalActions().clearSession();
            Actions.generalActions().openHomePage();
            Actions.generalActions().login(login, pass);
            Actions.generalActions().checkHomePageLoaded();

        Actions.generalActions().openMainPage();
        Actions.itemActions().openItem(buttonMultiSelectDashboard);

        Actions.dashboardActions().addCustomizedControl(controlName, genderCategory, buttonStyle, multiSelect,
                maleState);

        Pages.dashboardPage().contentPage().clickControlByName(maleState);
        Pages.dashboardPage().contentPage().clickControlByName(femaleState);

        Actions.dashboardActions().checkDataCrossTabRows(maleState);

        Pages.dashboardPage().contentPage().clickControlByName(femaleState);
        Pages.dashboardPage().contentPage().clickControlByName(maleState);

        Actions.dashboardActions().checkDataCrossTabRows(femaleState);

        Pages.dashboardPage().contentPage().clickControlByName(maleState);

        Actions.dashboardActions().checkDataCrossTabRows(maleFemaleState);

       Actions.dashboardActions().removeButtonControl(buttonMultiSelectDashboard, controlName, maleState);
    }



                @TestRailIssue(issueID = 52, testRunName = TEST_RUN_NAME)
                @Test(dataProvider = DataProviderPool.PROJECT_MANAGER_CREDENTIALS, dataProviderClass = DataProviderPool.class,
                        description = "Add controlName with default state (UAT-405)", alwaysRun = true)
                public void addControlWithDefaultState(String login, String pass) {

                    Actions.generalActions().clearSession();
                    Actions.generalActions().openHomePage();
                    Actions.generalActions().login(login, pass);
                    Actions.generalActions().checkHomePageLoaded();

                    Actions.generalActions().openMainPage();
                    Actions.itemActions().openItem(dashboardForDefaultControl);
                    Actions.dashboardActions().addCustomizedControl(controlName, genderCategory, buttonStyle, singleSelect, femaleState);
                    boolean controlPresent = Actions.dashboardActions().isControlPresent(femaleState);
                    Assert.assertTrue(controlPresent, "Control with default state wasn't created");

                    Actions.dashboardActions().removeButtonControl(dashboardForDefaultControl, controlName, femaleState);
               }




                @TestRailIssue(issueID = 49, testRunName = TEST_RUN_NAME)
                @Test(dataProvider = DataProviderPool.PROJECT_MANAGER_CREDENTIALS, dataProviderClass = DataProviderPool.class,
                        description = "Delete controlName from dashboard (UAT-406)", alwaysRun = true)
                public void deleteControlFromDashboard(String login, String pass) {

                    Actions.generalActions().clearSession();
                    Actions.generalActions().openHomePage();
                    Actions.generalActions().login(login, pass);
                    Actions.generalActions().checkHomePageLoaded();

                    Actions.generalActions().openMainPage();
                    Actions.itemActions().openItem(dashboardForDefaultControl);
                    Actions.dashboardActions().addCustomizedControl(controlName, genderCategory, buttonStyle, singleSelect, femaleState);
                    boolean controlPresent = Actions.dashboardActions().isControlPresent(femaleState);
                    Assert.assertTrue(controlPresent, "Control with default state wasn't created");

                    Actions.dashboardActions().removeButtonControl(dashboardForDefaultControl, controlName, femaleState);
                }




                @TestRailIssue(issueID = 50, testRunName = TEST_RUN_NAME)
                @Test(dataProvider = DataProviderPool.PROJECT_MANAGER_CREDENTIALS, dataProviderClass = DataProviderPool.class,
                        description = "Delete controlName from dashboard (UAT-406)", alwaysRun = true)
                public void editExistingControl(String login, String pass) {

                    Actions.generalActions().clearSession();
                    Actions.generalActions().openHomePage();
                    Actions.generalActions().login(login, pass);
                    Actions.generalActions().checkHomePageLoaded();

                    Actions.generalActions().openMainPage();
                    Actions.itemActions().openItem(dashboardForControlEdit);

                    Actions.dashboardActions()
                            .addCustomizedControl(controlName, educationCategory, buttonStyle, singleSelect, highEducationState);
                    Actions.dashboardActions().setVerticalButtonOrientation(controlName, verticalOrientation);

                    Actions.dashboardActions().removeButtonControl(dashboardForControlEdit, controlName, highEducationState);
               }




               @TestRailIssue(issueID = 712, testRunName = TEST_RUN_NAME)
               @Test(dataProvider = DataProviderPool.PROJECT_MANAGER_CREDENTIALS, dataProviderClass = DataProviderPool.class,
                       description = "Проверка перерисовки чарта на дешборде при клике на контролл", alwaysRun = true)
               public void checkChartRedraw(String login, String pass) throws Exception {

                   Actions.generalActions().clearSession();
                   Actions.generalActions().openHomePage();
                   Actions.generalActions().login(login, pass);
                   Actions.generalActions().checkHomePageLoaded();

                    Actions.generalActions().openMainPage();
                   Actions.itemActions().openItem(dashboardFoChartRedraw);

                    Actions.dashboardActions().addCustomizedControl(controlName, genderCategory, buttonStyle, singleSelect, femaleState);

                    Actions.dashboardActions().checkChartData(  femaleState + "Chart");

                    Pages.dashboardPage().contentPage().clickControlByName(maleState);

                    Actions.dashboardActions().checkChartData(maleState + "Chart");

                    Actions.dashboardActions().removeButtonControl(dashboardFoChartRedraw, controlName, maleState);
                }



                @TestRailIssue(issueID = 715, testRunName = TEST_RUN_NAME)
                @Test(dataProvider = DataProviderPool.PROJECT_MANAGER_CREDENTIALS, dataProviderClass = DataProviderPool.class,
                        description = "Отмена примененной роли контрола к виджету", alwaysRun = true)
                public void abortBindButtonRole(String login, String pass) throws Exception {

                    Actions.generalActions().clearSession();
                    Actions.generalActions().openHomePage();
                    Actions.generalActions().login(login, pass);
                    Actions.generalActions().checkHomePageLoaded();

                    Actions.generalActions().openMainPage();

                    Actions.itemActions().openItem(dashboardForAbortBindTest);
                    Actions.dashboardActions().openEditMode();

                    Pages.dashboardPage().leftToolBarRegion().waitForLoad();
                    Pages.dashboardPage().leftToolBarRegion().clickAddControlButton();
                    Actions.dashboardActions().customizeControl(controlName, genderCategory, buttonStyle, singleSelect, femaleState);
                    Pages.dashboardPage().controlPage().waitAddButtonToBeClickable();
                    Pages.dashboardPage().controlPage().clickAddControl();
                    Actions.dashboardActions().selectButtonControl(controlName);
                    Pages.dashboardPage().toolbarRegion().waitViewButtonToBeClickable();
                    Pages.dashboardPage().contentPage().waitControlRoleRegion();
                    Pages.dashboardPage().contentPage().clickControlRoleButtonByName("Columns");

                    Pages.dashboardPage().toolbarRegion().clickViewButton();
                    BaseActions.wait(3);


                    Actions.dashboardActions().checkDataCrossTab("columnBindedCossTab");

                    Actions.dashboardActions().selectButtonControl(controlName);
                    Pages.dashboardPage().contentPage().clickControlRoleButtonByName("Columns");

                    Pages.dashboardPage().toolbarRegion().clickViewButton();
                    BaseActions.wait(3);

                    Actions.dashboardActions().checkDataCrossTab("columnUnBindedCrossTab");

                    Actions.dashboardActions().removeButtonControl(dashboardForAbortBindTest, controlName, femaleState);
                }


                @TestRailIssue(issueID = 716, testRunName = TEST_RUN_NAME)
                @Test(dataProvider = DataProviderPool.PROJECT_MANAGER_CREDENTIALS, dataProviderClass = DataProviderPool.class,
                        description = "Сортировка чартов на дэшборде", alwaysRun = true)
                public void checkTableSorting(String login, String pass) throws Exception {

                    Actions.generalActions().clearSession();
                    Actions.generalActions().openHomePage();
                    Actions.generalActions().login(login, pass);
                    Actions.generalActions().checkHomePageLoaded();

                    Actions.generalActions().openMainPage();

                    Actions.itemActions().openItem(dashboardForSortTest);
                    Actions.dashboardActions().openEditMode();
                    Actions.dashboardActions().selectTable();
                    Actions.dashboardActions().switchSortingToggleByFlag(true);
                    Actions.dashboardActions().closeEditMode();

                    Pages.dashboardPage().contentPage().sortCrossTab();

                    Actions.dashboardActions().checkDataCrossTab("ascSortedCrossTab");
                    Pages.dashboardPage().contentPage().sortCrossTab();
                    Actions.dashboardActions().checkDataCrossTab("descSortedCrossTab");
               }



                @TestRailIssue(issueID = 717, testRunName = TEST_RUN_NAME)
                @Test(dataProvider = DataProviderPool.PROJECT_MANAGER_CREDENTIALS, dataProviderClass = DataProviderPool.class,
                        description = "Перенос папки с переменными на странице редактора контролов", alwaysRun = true)
                public void checkMovingFolderWithVar(String login, String pass) throws Exception {

                    Actions.generalActions().clearSession();
                    Actions.generalActions().openHomePage();
                    Actions.generalActions().login(login, pass);
                    Actions.generalActions().checkHomePageLoaded();

                    Actions.generalActions().openMainPage();
                    Actions.itemActions().scrollDown();
                    Actions.itemActions().openItem(dashboardForMovingFolderVar);
                    Actions.projectActions().exportToDashboard("Dash"  + dashboardForMovingFolderVar);

                    Actions.generalActions().openMainPage();

                    Actions.itemActions().openItem("Dash"  + dashboardForMovingFolderVar);

                    Actions.dashboardActions().addCustomizedControl(controlName, newFolderCategory, buttonStyle, multiSelect,
                            maleState);


                    List<String> list = new ArrayList<>();

                    list.add("Grad School");
                    list.add("Spednem Education");
                    list.add("High Education");
                    list.add("Difficult to answer");
                    list.add("Age");

                    Assert.assertEquals(Pages.dashboardPage().contentPage().getControlNames() , list,
                            "Expected controls aren't found");


                    Actions.dashboardActions().removeButtonControl("Dash"  + dashboardForMovingFolderVar, controlName, "Age");
                    Actions.generalActions().openMainPage();
                    Actions.itemActions().removeItem("Dash"  + dashboardForMovingFolderVar);
                }


/*
                @TestRailIssue(issueID = 753, testRunName = TEST_RUN_NAME)
                @Test(dataProvider = DataProviderPool.DASHBOARD_MANAGER_CREDENTIALS, dataProviderClass = DataProviderPool.class,
                        description = "Cloning image in the dashboard")
                public void createImageClone(String login, String pass) {
                    itemsName.add("Text");
                    itemsName.add("Image");
                    itemsName.add("Attachment");

                    Actions.generalActions().clearSession();
                    Actions.generalActions().openHomePage();
                    Actions.generalActions().login(login, pass);
                    Actions.generalActions().checkHomePageLoaded();
                  //  Actions.itemActions().removeItemsIfPresent(folderName);
                  //  Actions.itemActions().createFolder(folderName);
                    Actions.itemActions().openItem(controlTestsFolder);
                  //  Actions.itemActions().createDashboard(dashboardInFolderName);

                    TestRailAssert.assertTrue(
                            Actions.itemActions().isItemPresent(dashboardInFolderName) &&
                                    (controlTestsFolder.equals(Pages.navigationBar().getLastFolderName())),
                            new CustomStepResult(
                                    "Dashboard '" + dashboardInFolderName + "' не отображается на странице " + controlTestsFolder,
                                    "Dashboard '" + dashboardInFolderName + "' успешно создан и" +
                                            " отображается на странице " + controlTestsFolder)
                    );
                    Actions.itemActions().openItem(dashboardInFolderName);
                    Actions.dashboardActions().openEditMode();


                    Actions.dashboardActions().checkForLeftActionsItemsPresentAndVisible(itemsName);
                    /*
                    Actions.dashboardActions().clickIcon("Image", itemsName);

                    TestRailAssert.assertTrue(
                            DashboardPage.createTextWidgetDialog().isModalWindowPresent(),
                            new CustomStepResult(
                                    "Модальное окно 'Create image widget' не отображается",
                                    "Модальное окно 'Create image widget' отображается")
                    );

                    Actions.dashboardActions().uploadImage();
                    */
/*
                    Actions.dashboardActions().clickOnImageArea();
                    Actions.dashboardActions().checkForToolbarIcons();
                    DashboardPage.createTextWidgetDialog().clickCloneIcon();
                    BaseActions.wait(3);
                    TestRailAssert.assertTrue(
                            DashboardPage.createTextWidgetDialog().isCloningImagePresent(),
                            new CustomStepResult(
                                    "зображение не склонировалось или не отображается в дашборде",
                                    "зображение склонировалось и отображается в дашборде")
                    );
                    DashboardPage.toolbarRegion().clickViewButton();
                    TestRailAssert.assertTrue(
                            DashboardPage.createTextWidgetDialog().IsBothImagesPresent(),
                            new CustomStepResult(
                                    "одна или обе картинки не отображаются корректно",
                                    "обе картинки отображаеются на дашборде корректно")
                    );
                    Actions.dashboardActions().openEditMode();
                    Actions.dashboardActions().removeCloneImage();
                }
*/
                /*
                @TestRailIssue(issueID = 24237, testRunName = TEST_RUN_NAME)
                @Test(dependsOnMethods = {"login"}, description = "UAT-409")
                public void relatedWidgetsTest() {

                    Map<String, String> listHeightsOfWidgets = new HashMap<>();

                    Actions.generalActions().openMainPage();
                    Actions.itemActions().openItem(dashboardWithRelatedWidgets);
                    Actions.dashboardActions().resetButtonControlState(firstControl);
                   Actions.dashboardActions().resetButtonControlState(secondControl);

                    listHeightsOfWidgets.put(firstWidget, Actions.dashboardActions().getControlHeightRows(firstWidget));
                    listHeightsOfWidgets.put(secondWidget, Actions.dashboardActions().getControlHeightRows(secondWidget));
                    listHeightsOfWidgets.put(thirdWidget, Actions.dashboardActions().getControlHeightRows(thirdWidget));
                   listHeightsOfWidgets.put(fourthWidget, Actions.dashboardActions().getControlHeightRows(fourthWidget));

                   Actions.dashboardActions().selectButtonControlState(firstControl, femaleState);
                    Actions.dashboardActions().checkWidgetChanged(firstWidget, listHeightsOfWidgets.get(firstWidget));
                    Actions.dashboardActions().checkWidgetChanged(secondWidget, listHeightsOfWidgets.get(secondWidget));
                    Actions.dashboardActions().checkWidgetNotChanged(thirdWidget, listHeightsOfWidgets.get(thirdWidget));
                    Actions.dashboardActions().checkWidgetNotChanged(fourthWidget, listHeightsOfWidgets.get(fourthWidget));
                   Actions.dashboardActions().resetButtonControlState(firstControl);
                    Actions.dashboardActions().selectButtonControlState(secondControl, femaleState);
                    Actions.dashboardActions().checkWidgetNotChanged(firstWidget, listHeightsOfWidgets.get(firstWidget));
                    Actions.dashboardActions().checkWidgetChanged(secondWidget, listHeightsOfWidgets.get(secondWidget));
                    Actions.dashboardActions().checkWidgetChanged(thirdWidget, listHeightsOfWidgets.get(thirdWidget));
                    Actions.dashboardActions().checkWidgetNotChanged(fourthWidget, listHeightsOfWidgets.get(fourthWidget));
               }
            */

    @TestRailIssue(issueID = 48, testRunName = TEST_RUN_NAME)
    @Test(dataProvider = DataProviderPool.PROJECT_MANAGER_CREDENTIALS, dataProviderClass = DataProviderPool.class,
            description = "UAT-440 Public dashboard is accessible by authorized non-owner", alwaysRun = true)
    public void getAccessToShareDashboardByPublicModForAnotherAuthUser(String login, String password) {

        Actions.generalActions().clearSession();
        Actions.generalActions().openHomePage();
        Actions.generalActions().login(login,password);
        Actions.generalActions().checkHomePageLoaded();

       Actions.generalActions().openMainPage();
        Actions.itemActions().selectItem(dashboardForControlEdit);
        Actions.itemActions().openShareDialog();

       String dashboardPublicURL = Actions.itemActions().copyToClipboardWithPublicMod();

        Actions.itemActions().closeShareDialog();
        Actions.generalActions().logOut();
       Actions.generalActions().login(login, password);
       Actions.generalActions().checkHomePageLoaded();
        Actions.generalActions().openPage(dashboardPublicURL);

       boolean exceptedDashboardOpened = Actions.dashboardActions()
                .isPresentExceptedDashboard(dashboardForControlEdit);

       Assert.assertTrue(exceptedDashboardOpened, "Dashboard '" + dashboardForControlEdit + "' isn't opened.");
        Assert.assertTrue(Actions.dashboardActions().isWidgetPresent(), "Widget with title '" + widgetTitle + "' isn't present.");

        Pages.navigationBar().waitLogoutButtonFromClickboard();
        Pages.navigationBar().clickLogoutButtonFromClickboard();
   }



    @TestRailIssue(issueID = 47, testRunName = TEST_RUN_NAME)
    @Test(dataProvider = DataProviderPool.PROJECT_MANAGER_CREDENTIALS, dataProviderClass = DataProviderPool.class,
            description = "UAT-438 Public dashboard is anonymously accessible", alwaysRun = true)
    public void getAccessToShareDashboardByPublicModForNonAuthUser(String login, String pass) {
        Actions.generalActions().clearSession();
        Actions.generalActions().openHomePage();
        Actions.generalActions().login(login, pass);
        Actions.generalActions().checkHomePageLoaded();

        Actions.generalActions().openMainPage();
        Actions.itemActions().selectItem(newDashboard);


        Actions.itemActions().openShareDialog();

        String dashboardPublicURL = Actions.itemActions().copyToClipboardWithPublicMod();

        Actions.itemActions().closeShareDialog();
        Actions.generalActions().logOut();
        Actions.generalActions().openPage(dashboardPublicURL);

        boolean exceptedDashboardOpened = Actions.dashboardActions()
                .isPresentExceptedDashboard(newDashboard);

        Assert.assertTrue(exceptedDashboardOpened, "Dashboard '" + newDashboard + "' isn't opened.");
        Assert.assertTrue(Actions.dashboardActions().isWidgetPresent(), "Widget with title '" + widgetTitle + "' isn't present.");
   }




    @TestRailIssue(issueID = 766, testRunName = TEST_RUN_NAME)
    @Test(dataProvider = DataProviderPool.PROJECT_MANAGER_CREDENTIALS, dataProviderClass = DataProviderPool.class,
            description = "Привязывание контрола к текстовому виджету(button)", alwaysRun = true)
    public void correspondButtonControlAndTextWidget(String login, String pass) throws Exception {
        Actions.generalActions().clearSession();
        Actions.generalActions().openHomePage();
        Actions.generalActions().login(login, pass);
        Actions.generalActions().checkHomePageLoaded();
        Actions.generalActions().openMainPage();
        Actions.itemActions().openItem(textWidgetWithControl);
        Actions.dashboardActions().addCustomizedControl(controlName, genderCategory, buttonStyle, singleSelect, femaleState);
        Actions.generalActions().openMainPage();
        Actions.itemActions().openItem(textWidgetWithControl);
        Actions.dashboardActions().addTextWidget(text);
       // Actions.dashboardActions().clickEmptySpace();
        Pages.dashboardPage().contentPage().clickOnWidgetArea();
        TestRailAssert.assertTrue(
                Actions.dashboardActions().isFilledTextWidgetPresent(text),
                new CustomStepResult(
                        "текстовый виджет c текстом '" + text + "' не отображается",
                        "текстовый виджет c текстом '" + text + "' отображается")
        );
        Actions.dashboardActions().moveTextWidgetToRight();
        Actions.dashboardActions().moveControlToLeft();

        Pages.dashboardPage().contentPage().selectControlByName();
        Actions.dashboardActions().checkForTextWidgetAssingAttributes();
        Actions.dashboardActions().checkAssignButtonFunctionality();
        Actions.dashboardActions().clickOnStatesButton();
        Actions.dashboardActions().clickAddState(firstState);
        Actions.dashboardActions().executeOption(firstState, addCondition);
        TestRailAssert.assertTrue(
                DashboardPage.toolbarRegion().isConditionStatePresent(firstState),
                new CustomStepResult(
                        "принадлежаший к '" + firstState + "' 'Condition 1' не отображается",
                        "принадлежаший к '" + firstState + "' 'Condition 1' добавлен и отображается")
        );
        Actions.dashboardActions().clickCondition(firstState);
        TestRailAssert.assertFalse(
                Actions.dashboardActions().isFilledTextWidgetPresent(text),
                new CustomStepResult(
                        "текстовый виджет c текстом '" + text + "' отображается",
                        "текстовый виджет c текстом " + text + "' не отображается")
        );
        Actions.dashboardActions().checkControlAvailabilityAndClick(maleState);
        Actions.dashboardActions().checkDataCrossTab("chartWithMaleDrop");
        Actions.dashboardActions().createTextPatternForAnotherControlState(anotherText);
      //  Actions.dashboardActions().clickEmptySpace();
        Pages.dashboardPage().contentPage().clickOnWidgetArea();
        TestRailAssert.assertTrue(
                Actions.dashboardActions().isFilledTextWidgetPresent(text),
                new CustomStepResult(
                        "текстовый виджет c текстом '" + text + "' не отображается",
                        "текстовый виджет c текстом '" + text + "' отображается")
        );
        Actions.dashboardActions().clickViewAndCheckWidgetLabels();

        Actions.dashboardActions().clickControlByNameAndCheckText(maleState, anotherText);
        Actions.dashboardActions().clickControlByNameAndCheckText(femaleState, text);
        Actions.dashboardActions().openEditMode();
        Actions.dashboardActions().clickTextWidget();
        Actions.dashboardActions().clickOnStatesButton();
        TestRailAssert.assertTrue(
                DashboardPage.toolbarRegion().isStatePresent(firstState),
                new CustomStepResult(
                        "'" + firstState + "' не отображается с левом меню настроек",
                        "'" + firstState + "' отображается с левом меню настроек")
        );
        Actions.dashboardActions().clickAddState(secondState);
        Actions.dashboardActions().executeOption(secondState, addCondition);
        TestRailAssert.assertTrue(
                DashboardPage.toolbarRegion().isConditionStatePresent(secondState),
                new CustomStepResult(
                        "принадлежаший к '" + secondState + "' 'Condition 1' не отображается",
                        "принадлежаший к '" + secondState + "' 'Condition 1' добавлен и отображается")
        );
        Actions.dashboardActions().clickCondition(secondState);
        TestRailAssert.assertFalse(
                Actions.dashboardActions().isFilledTextWidgetPresent(text),
                new CustomStepResult(
                        "текстовый виджет c текстом '" + text + "' отображается",
                        "текстовый виджет c текстом " + text + "' не отображается")
        );
        TestRailAssert.assertTrue(
                DashboardPage.contentPage().checkControlAvailability(maleState),
                new CustomStepResult(
                        "'control' c переменной '" + maleState + "' не отображается или не доступен",
                        "'control' c переменной '" + maleState + "' отображается и доступен")
        );
        Actions.dashboardActions().createTextPatternForAnotherControlState(finalText);
       // Actions.dashboardActions().clickEmptySpace();
        Pages.dashboardPage().contentPage().clickOnWidgetArea();
        TestRailAssert.assertTrue(
                Actions.dashboardActions().isFilledTextWidgetPresent(text),
                new CustomStepResult(
                        "текстовый виджет c текстом '" + text + "' не отображается",
                        "текстовый виджет c текстом '" + text + "' отображается")
        );
        Actions.dashboardActions().clickViewAndCheckWidgetLabels();
        Actions.dashboardActions().clickControlByNameAndCheckText(femaleState, finalText);
        Actions.dashboardActions().openEditMode();
        Actions.dashboardActions().clickTextWidget();
        Actions.dashboardActions().clickOnStatesButton();
        TestRailAssert.assertTrue(
                DashboardPage.toolbarRegion().isStatePresent(firstState),
                new CustomStepResult(
                        "'" + firstState + "' не отображается с левом меню настроек",
                        "'" + firstState + "' отображается с левом меню настроек")
        );
        TestRailAssert.assertTrue(
                DashboardPage.toolbarRegion().isStatePresent(secondState),
                new CustomStepResult(
                        "'" + secondState + "' не отображается с левом меню настроек",
                        "'" + secondState + "' отображается с левом меню настроек")
        );
        Actions.dashboardActions().deleteCondition(secondState, deleteCondition);
        Actions.dashboardActions().clickViewAndCheckWidgetLabels();

        Actions.dashboardActions().clickControlByNameAndCheckText(maleState, anotherText);
        Actions.dashboardActions().clickControlByNameAndCheckText(femaleState, text);

        Actions.dashboardActions().openEditMode();
        DashboardPage.contentPage().clickOnDefaultTouchedTextWidget();
        Actions.dashboardActions().clickOnStatesButton();
        TestRailAssert.assertTrue(
                DashboardPage.toolbarRegion().isStatePresent(firstState),
                new CustomStepResult(
                        "'" + firstState + "' не отображается с левом меню настроек",
                        "'" + firstState + "' отображается с левом меню настроек")
        );
        TestRailAssert.assertTrue(
                DashboardPage.toolbarRegion().isStatePresent(secondState),
                new CustomStepResult(
                        "'" + secondState + "' не отображается с левом меню настроек",
                        "'" + secondState + "' отображается с левом меню настроек")
        );
        Actions.dashboardActions().executeOption(firstState, deleteCondition);
        BaseActions.wait(3);
        Actions.generalActions().refreshPage();
        Actions.dashboardActions().clickOnStatesButton();
        TestRailAssert.assertFalse(
                DashboardPage.toolbarRegion().isConditionStatePresent(firstState),
                new CustomStepResult(
                        "принадлежаший к '" + firstState + "' 'Condition 1' отображается",
                        "принадлежаший к '" + firstState + "' 'Condition 1' не отображается ")
        );
        DashboardPage.toolbarRegion().clickDefaultLink();
        TestRailAssert.assertTrue(
                Actions.dashboardActions().isFilledTextWidgetPresent(text),
                new CustomStepResult(
                        "текстовый виджет c текстом '" + text + "' не отображается",
                        "текстовый виджет c текстом " + text + "' успешно отображается")
        );
        Actions.dashboardActions().removeTextWidgetIfPresent(text);
        Actions.dashboardActions().removeButtonControl(textWidgetWithControl, controlName, maleState);
    }




    @TestRailIssue(issueID = 766, testRunName = TEST_RUN_NAME)
    @Test(dataProvider = DataProviderPool.PROJECT_MANAGER_CREDENTIALS, dataProviderClass = DataProviderPool.class,
            description = "Привязывание контрола к текстовому виджету(dropdown)", alwaysRun = true)
    public void correspondDropdownControlAndTextWidget(String login, String pass) throws Exception {
        Actions.generalActions().clearSession();
        Actions.generalActions().openHomePage();
        Actions.generalActions().login(login, pass);
        Actions.generalActions().checkHomePageLoaded();
        Actions.generalActions().openMainPage();
        Actions.itemActions().openItem(textWidgetWithControl);
        Actions.dashboardActions().addCustomizedControl(controlName, genderCategory, dropdownStyle, singleSelect, femaleState);
        Actions.generalActions().openMainPage();
        Actions.itemActions().openItem(textWidgetWithControl);
        Actions.dashboardActions().addTextWidget(text);
       // Actions.dashboardActions().clickEmptySpace();
        Pages.dashboardPage().contentPage().clickOnWidgetArea();
        TestRailAssert.assertTrue(
                Actions.dashboardActions().isFilledTextWidgetPresent(text),
                new CustomStepResult(
                        "текстовый виджет c текстом '" + text + "' не отображается",
                        "текстовый виджет c текстом '" + text + "' отображается")
        );
        Actions.dashboardActions().moveTextWidgetToRight();
        Actions.dashboardActions().moveControlToLeft();

        Pages.dashboardPage().contentPage().selectControlByName();
        Actions.dashboardActions().checkForTextWidgetAssingAttributes();
        Actions.dashboardActions().checkAssignButtonFunctionality();
        Actions.dashboardActions().clickOnStatesButton();
        Actions.dashboardActions().clickAddState(firstState);
        Actions.dashboardActions().executeOption(firstState, addCondition);
        TestRailAssert.assertTrue(
                DashboardPage.toolbarRegion().isConditionStatePresent(firstState),
                new CustomStepResult(
                        "принадлежаший к '" + firstState + "' 'Condition 1' не отображается",
                        "принадлежаший к '" + firstState + "' 'Condition 1' добавлен и отображается")
        );
        Actions.dashboardActions().clickCondition(firstState);
        TestRailAssert.assertFalse(
                Actions.dashboardActions().isFilledTextWidgetPresent(text),
                new CustomStepResult(
                        "текстовый виджет c текстом '" + text + "' отображается",
                        "текстовый виджет c текстом " + text + "' не отображается")
        );
        Actions.dashboardActions().checkDropDownControlAvailabilityAndClick(maleState);
        Actions.dashboardActions().checkDataCrossTab("chartWithMaleDrop");
        Actions.dashboardActions().createTextPatternForAnotherControlState(anotherText);
      //  Actions.dashboardActions().clickEmptySpace();
        Pages.dashboardPage().contentPage().clickOnWidgetArea();
        TestRailAssert.assertTrue(
                Actions.dashboardActions().isFilledTextWidgetPresent(text),
                new CustomStepResult(
                        "текстовый виджет c текстом '" + text + "' не отображается",
                        "текстовый виджет c текстом '" + text + "' отображается")
        );
        Actions.dashboardActions().clickViewAndCheckWidgetLabels();

        Actions.dashboardActions().clickDropDownControlByNameAndCheckText(femaleState, text);
        Actions.dashboardActions().clickDropDownControlByNameAndCheckText(maleState, anotherText);
        Actions.dashboardActions().clickDropDownControlByNameAndCheckText(femaleState, text);
        Actions.dashboardActions().openEditMode();
        Actions.dashboardActions().clickTextWidget();
        Actions.dashboardActions().clickOnStatesButton();
        TestRailAssert.assertTrue(
                DashboardPage.toolbarRegion().isStatePresent(firstState),
                new CustomStepResult(
                        "'" + firstState + "' не отображается с левом меню настроек",
                        "'" + firstState + "' отображается с левом меню настроек")
        );
        Actions.dashboardActions().clickAddState(secondState);
        Actions.dashboardActions().executeOption(secondState, addCondition);
        TestRailAssert.assertTrue(
                DashboardPage.toolbarRegion().isConditionStatePresent(secondState),
                new CustomStepResult(
                        "принадлежаший к '" + secondState + "' 'Condition 1' не отображается",
                        "принадлежаший к '" + secondState + "' 'Condition 1' добавлен и отображается")
        );
        Actions.dashboardActions().clickCondition(secondState);
        TestRailAssert.assertFalse(
                Actions.dashboardActions().isFilledTextWidgetPresent(text),
                new CustomStepResult(
                        "текстовый виджет c текстом '" + text + "' отображается",
                        "текстовый виджет c текстом " + text + "' не отображается")
        );

        Actions.dashboardActions().createTextPatternForAnotherControlState(finalText);
       // Actions.dashboardActions().clickEmptySpace();
        Pages.dashboardPage().contentPage().clickOnWidgetArea();
        TestRailAssert.assertTrue(
                Actions.dashboardActions().isFilledTextWidgetPresent(text),
                new CustomStepResult(
                        "текстовый виджет c текстом '" + text + "' не отображается",
                        "текстовый виджет c текстом '" + text + "' отображается")
        );
        Actions.dashboardActions().clickViewAndCheckWidgetLabels();
        Actions.dashboardActions().clickDropDownControlByNameAndCheckText(maleState, anotherText);
        Actions.dashboardActions().clickDropDownControlByNameAndCheckText(femaleState, finalText);
        Actions.dashboardActions().openEditMode();
        Actions.dashboardActions().clickTextWidget();
        Actions.dashboardActions().clickOnStatesButton();
        TestRailAssert.assertTrue(
                DashboardPage.toolbarRegion().isStatePresent(firstState),
                new CustomStepResult(
                        "'" + firstState + "' не отображается с левом меню настроек",
                        "'" + firstState + "' отображается с левом меню настроек")
        );
        TestRailAssert.assertTrue(
                DashboardPage.toolbarRegion().isStatePresent(secondState),
                new CustomStepResult(
                        "'" + secondState + "' не отображается с левом меню настроек",
                        "'" + secondState + "' отображается с левом меню настроек")
        );
        Actions.dashboardActions().deleteCondition(secondState, deleteCondition);
        Actions.dashboardActions().clickViewAndCheckWidgetLabels();

        Actions.dashboardActions().clickDropDownControlByNameAndCheckText(maleState, anotherText);
        Actions.dashboardActions().clickDropDownControlByNameAndCheckText(femaleState, text);


        Actions.dashboardActions().openEditMode();
        DashboardPage.contentPage().clickOnDefaultTouchedTextWidget();
        Actions.dashboardActions().clickOnStatesButton();
        TestRailAssert.assertTrue(
                DashboardPage.toolbarRegion().isStatePresent(firstState),
                new CustomStepResult(
                        "'" + firstState + "' не отображается с левом меню настроек",
                        "'" + firstState + "' отображается с левом меню настроек")
        );
        TestRailAssert.assertTrue(
                DashboardPage.toolbarRegion().isStatePresent(secondState),
                new CustomStepResult(
                        "'" + secondState + "' не отображается с левом меню настроек",
                        "'" + secondState + "' отображается с левом меню настроек")
        );
        Actions.dashboardActions().executeOption(firstState, deleteCondition);
        BaseActions.wait(5);
        Actions.generalActions().refreshPage();
        Actions.dashboardActions().clickOnStatesButton();
        TestRailAssert.assertFalse(
                DashboardPage.toolbarRegion().isConditionStatePresent(firstState),
                new CustomStepResult(
                         "'" + firstState + "' отображается",
                        "'" + firstState + "' не отображается")
        );
        DashboardPage.toolbarRegion().clickDefaultLink();
        TestRailAssert.assertTrue(
                Actions.dashboardActions().isFilledTextWidgetPresent(text),
                new CustomStepResult(
                        "текстовый виджет c текстом '" + text + "' не отображается",
                        "текстовый виджет c текстом " + text + "' успешно отображается")
        );
        Actions.dashboardActions().removeTextWidgetIfPresent(text);
        Actions.dashboardActions().removeButtonControl(textWidgetWithControl, controlName, maleState);

    }


    @TestRailIssue(issueID = 781, testRunName = TEST_RUN_NAME)
    @Test(dataProvider = DataProviderPool.PROJECT_MANAGER_CREDENTIALS, dataProviderClass = DataProviderPool.class,
            description = "Проверка заданного цвета для data labels при публикации чарта на дашборд")
    public void checkingChartDataLabelsColor(String login, String password) throws Exception {
        Actions.generalActions().clearSession();
        Actions.generalActions().openHomePage();
        Actions.generalActions().login(login, password);
        Actions.generalActions().checkHomePageLoaded();
        Actions.itemActions().openItem(dviItem);

        TestRailAssert.assertTrue(
                Actions.itemActions().checkForItem(contextItem) &&
                        Actions.itemActions().checkForItem(mayorItem),
                new CustomStepResult(
                        "проект Mayor/Context не отображается",
                        "Отображается проект Mayor/Context")
        );

      //  Actions.itemActions().openProject(mayorItem);
        Actions.itemActions().openProject(mayorItem);
        Actions.projectActions().clickResetStateButtonIfPresent();
        Actions.projectActions().createAnotherRequest(genderItem, educationItem);
        Actions.projectActions().createAnotherRequest(genderItem, educationItem);
        ProjectPage.toolbarRegion().clickChartButton();
        ProjectPage.chartRegion().waitToLoad();

        TestRailAssert.assertTrue(
                ProjectPage.chartRegion().isChartContainerPresent() &&
                          ProjectPage.chartRegion().isChartToolBarPresent(),
                new CustomStepResult(
                        "Дефлотный чарт проекта не отображается",
                        "Отображается дефлотный чарт проекта")
        );

        ProjectPage.chartRegion().clickOnChartToolBarOption(dataLabelsOption);

        TestRailAssert.assertTrue(
                ProjectPage.chartRegion().isOptionRegionPresent(dataLabelsOption),
                new CustomStepResult(
                        "блок опции '"+ dataLabelsOption +"' не отображается",
                        "блок опции '"+ dataLabelsOption +"' отображается и доступен")
        );

        ProjectPage.chartRegion().clickOnChartToolBarOptionItem(dataLabelsItem);
        BaseActions.wait(5);
        ProjectPage.chartRegion().clickOnOptionColorCheckbox(dataLabelsOption);

        TestRailAssert.assertTrue(
                ProjectPage.chartRegion().isColorPopUpWindowVisible(),
                new CustomStepResult(
                        "всплывающее окно с выбором цветовой гаммы елементов не отображается",
                        "всплывающее окно с выбором цветовой гаммы елементов успешно отображается")
        );

        ProjectPage.chartRegion().clickOnGreenColor();

        TestRailAssert.assertTrue(
                ProjectPage.chartRegion().isGreenDataLabelPresent(),
                new CustomStepResult(
                        "data labels не отображаются на чарте с заданным цветом",
                        "data labels отображаются на чарте с заданным цветом(зеленый)")
        );


}


}

