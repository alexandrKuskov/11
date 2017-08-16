package eu.datatiler.tests;

import com.qatestlab.base.BaseActions;
import com.qatestlab.base.BasePage;
import com.qatestlab.base.BaseTest;
import com.qatestlab.testrail.CustomStepResult;
import com.qatestlab.testrail.TestRailAssert;
import eu.datatiler.actions.Actions;
import eu.datatiler.pages.Pages;
import eu.datatiler.pages.dashboard.DashboardPage;
import eu.datatiler.pages.project.ProjectPage;
import eu.datatiler.pages.project.Settings.SettingsPage;
import eu.datatiler.utils.DataProviderPool;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static eu.datatiler.actions.Actions.itemActions;
import static org.apache.tools.ant.types.resources.MultiRootFileSet.SetType.file;


public class ProjectInit extends BaseTest {
    // widgetTests folder
    private String widgetTestsFolder = "UAT_WidgetTests";
    private String addTextWidgetWithFormatting = "WidgetTests_AddTextWidgetWithFormatting";
    private String checkTextWidgetPresent = "WidgetTests_CheckTextWidgetPresent";
    private String checkImageWidget = "WidgetTests_CheckImageWidget";
    private List<String> widgetTestsEntities = new ArrayList<String>() {
        {
            add(addTextWidgetWithFormatting);
            add(checkTextWidgetPresent);
            add(checkImageWidget);
        }
    };
    // weightSettingsTests folder
    private String weightSettingsTestsFolder = "UAT_WeightSettingsTests";
    private String sortVariables = "WeightSettings_SortVariables";
    private List<String> weightSettingsTestsEntities = new ArrayList<String>() {
        {
            add(sortVariables);
        }
    };
    // settingsTests folder
    private String settingsTestsFolder = "UAT_SettingsTests";
    private String accessAdminPageTest = "SettingsTests_AccessAdminPageTest";
    private String createCategorialMRSetTest = "SettingsTests_CreateCategorialMRSetTest";
    private String createDichotometricalMRSetTest = "SettingsTests_CreateDichotometricalMRSetTest";
    private String createNumericDichotometricalMRSetTest = "SettingsTests_CreateNumericDichotometricalMRSetTest";
    private List<String> settingsTestsEntities = new ArrayList<String>() {
        {
            add(accessAdminPageTest);
            add(createCategorialMRSetTest);
            add(createDichotometricalMRSetTest);
            add(createNumericDichotometricalMRSetTest);
        }
    };
    // crosstabTests folder
    private String crosstableTestsFolder = "UAT_CrosstableTests";
    private String ensureCategoriesLoaded = "CrosstableTests_EnsureCategoriesLoadedTest";
    private String buildContextCrossTab = "CrosstableTests_BuildContextCrossTab";
    private String clickCheckingAfterCellNameChange = "CrosstableTests_ClickCheckingAfterCellNameChange";
    private String changeSerialNameOnChartPage = "CrosstableTests_ChangeSerialNameOnChartPage";
    //  private String checkingForSavingBookmarkInProjectDVI = "CrosstableTests_CheckingForSavingBookmarkInProjectDVI";
    private List<String> crosstabTestsEntities = new ArrayList<String>() {
        {
            add(ensureCategoriesLoaded);
            add(buildContextCrossTab);
            add(clickCheckingAfterCellNameChange);
            add(changeSerialNameOnChartPage);
            //  add(checkingForSavingBookmarkInProjectDVI);
        }
    };
    // creationTests folder
    private String creationTestsFolder = "UAT_CreationTests";
    private String createDashboardFromCrosstab = "Creation_Tests_CreateDashboardFromCrosstab";
    private List<String> creationTestsEntities = new ArrayList<String>() {
        {
            add(createDashboardFromCrosstab);
        }
    };
    // clickboardTests folder
    private String copyToClipboardTest = "ClipboardTests_CopyToClipboardTest";
    private String clipboardTestsFolder = "UAT_ClipboardTests";
    private List<String> clickboardTestsEntities = new ArrayList<String>() {
        {
            add(copyToClipboardTest);
        }
    };
    // ProjectTests folder
    private String projectTestsFolder = "UAT_ProjectTests";
    private String checkDropDownMenuBookmarkButton = "ProjectTests_CheckDropDownMenuBookmarkButton";
    private String savingVariableNameInCrossTabAfterRebuildingLogicalExpression = "ProjectTests_SavingVariableNameInCrossTabAfterRebuildingLogicalExpression";
    private String checkTermOfLogicalExpressionBuilder = "ProjectTests_CheckTermOfLogicalExpressionBuilder";
    private String checkingSaveBookmarkDVIProject = "ProjectTests_CheckingSaveBookmarkDVIProject";
    private String checkChangesInCreatedBookmark = "ProjectTests_CheckChangesInCreatedBookmark";
    private String checkingRemoveBookmarkDVIProject = "ProjectTests_CheckingRemoveBookmarkDVIProject";
    private List<String> projectTestsEntities = new ArrayList<String>() {
        {
            add(checkDropDownMenuBookmarkButton);
            add(savingVariableNameInCrossTabAfterRebuildingLogicalExpression);
            add(checkTermOfLogicalExpressionBuilder);
            add(checkingRemoveBookmarkDVIProject);
            add(checkChangesInCreatedBookmark);
            add(checkingSaveBookmarkDVIProject);
        }
    };
    private String DVIFolder = "DVI";
    private String context = "Context";
    private String mayor = "Mayor";
    private String checkingForSavingBookmarkInProjectDVI = "CrosstableTests_CheckingForSavingBookmarkInProjectDVI";
    private List<String> DVITestsEntities = new ArrayList<String>() {
        {
            add(context);
            add(mayor);
            add(checkingForSavingBookmarkInProjectDVI);
        }
    };

    private Map<String, List<String>> foldersWithProjects = new HashMap<String, List<String>>() {
        {
            put(projectTestsFolder, projectTestsEntities);
            put(clipboardTestsFolder, clickboardTestsEntities);
            put(creationTestsFolder, creationTestsEntities);
            put(crosstableTestsFolder, crosstabTestsEntities);
            put(settingsTestsFolder, settingsTestsEntities);
            put(weightSettingsTestsFolder, weightSettingsTestsEntities);
            put(widgetTestsFolder, widgetTestsEntities);
            put(DVIFolder, DVITestsEntities);
        }
    };
    //dashboards
    private String dashboardProjectCreator = "DASHBOARDS_CREATOR";

    private String buttonMultiSelectDashboard = "UAT_ButtonMultiSelectControl";
    private String dashboardForAbortBindTest = "UAT_DashboardForAbortBindTest";
    private String dashboardForControlEdit = "UAT_DashboardForControlEdit";
    private String dashboardForDefaultControl = "UAT_DashboardForDefaultControl";
    private String newDashboard = "New Dashboard";
    private String textWidgetWithControl = "UAT_DashboardForTextWidgetWithControl";

    private String dashboardFoChartRedraw = "UAT_DashboardForChartRedraw";

    private String dashboardForSortTest = "UAT_DashboardForSortTest";

    //  private String dashboardWithText = "UAT_DashboardWithTextWidget";
    private List<String> allDashboards = new ArrayList<String>() {
        {
            add(buttonMultiSelectDashboard);
            add(dashboardForAbortBindTest);
            add(dashboardForControlEdit);
            add(dashboardForDefaultControl);
            add(newDashboard);
            add(textWidgetWithControl);

            add(dashboardFoChartRedraw);
            add(dashboardForSortTest);
            //   add(dashboardWithText);
        }
    };


    // variables
    private String genderItem = "Gender";
    private String educationItem = "Education";
    private String typeOfEnterpriseItem = "Type of enterprise";
    private String mayorFile = "Mayor+Elections+2013";
    private String cognatesFile = "cognates.xlsx";
    private String bigFile = "Test_File_BIG.xlsx";
    private String mayorFileFullName = "Mayor+Elections+2013%2C+ENG+-+weeks+encoded%2C+MR-groups.sav.zip";

    private String directDownload = "Прямое скачивание";
    private String projectWithFolder = "UAT_Project_moving_folder_with_var";
    private String imageDashboardFolder = "UAT_ControlTests";
    private String imageDashboard = "UAT_Dashboard";
    private String uatProject = "UAT_Project";



    @Test(description = "init encoder ",
            dataProvider = DataProviderPool.ENCODER_CREDENTIALS, dataProviderClass = DataProviderPool.class, priority = 2)
    public void downloadEncoderEntity(String login, String pass) throws Exception {
        Actions.generalActions().clearSession();
        Actions.generalActions().openHomePage();
        Actions.generalActions().login(login, pass);

        Actions.itemActions().removeItemsIfPresent(bigFile);
        Actions.itemActions().createEncoder(bigFile);
        BaseActions.wait(5);
        boolean itemPresent = Actions.itemActions().isItemPresent(bigFile);
        Assert.assertTrue(itemPresent, String.format("'%s' isn't present", bigFile));
    }


    @Test(description = "init all PROJECT_MANAGER projects ",
            dataProvider = DataProviderPool.PROJECT_MANAGER_CREDENTIALS, dataProviderClass = DataProviderPool.class, priority = 3)
    public void initProjectManagerProjects(String login, String pass) throws Exception {
        Actions.generalActions().clearSession();
        Actions.generalActions().openHomePage();
        Actions.generalActions().login(login, pass);
        Actions.generalActions().checkHomePageLoaded();

// create folders with projects
        for (Map.Entry entry : foldersWithProjects.entrySet()) {
            String folder = (String) entry.getKey();
            List<String> projectEntities = (List<String>) entry.getValue();
            itemActions().removeItemsIfPresent(folder);
            itemActions().createFolder(folder);
            itemActions().openItem(folder);

            for (String entityName : projectEntities) {
                if (entityName.equals(context)) {
                    itemActions().createProject(entityName, cognatesFile);
                } else {
                    itemActions().createProject(entityName, mayorFileFullName);
                }
                TestRailAssert.assertTrue(
                        itemActions().isItemPresent(entityName),
                        new CustomStepResult(
                                "Проект с именем '" + entityName + "' не создался",
                                "Проект с именем '" + entityName + "' успешно создался и отображается на странице")
                );
            }
            Actions.generalActions().openMainPage();
        }

// create project with folder inside
        itemActions().removeItemsIfPresent(projectWithFolder);
        itemActions().createProject(projectWithFolder, mayorFileFullName);
        Actions.itemActions().selectProject(projectWithFolder);
        Actions.projectActions().openProjectSettings();
        SettingsPage.settingsTab().waitForProjectFolder("root");
        SettingsPage.settingsTab().createSubfolder();
        SettingsPage.settingsTab().waitForCreateFolderModalWindowBeVisible();
        SettingsPage.settingsTab().typeTextIntoInputField("new folder");
        SettingsPage.settingsTab().selectModalWindowOption("Create");
        SettingsPage.settingsTab().waitForCreateFolderModalWindowBeInvisible();
        SettingsPage.settingsTab().waitForProjectFolder("new folder");
        SettingsPage.settingsTab().selectFolder("root");
        SettingsPage.settingsTab().selectItem("Education");
        SettingsPage.settingsTab().addItemIntoFolder("new folder");
        SettingsPage.settingsTab().selectItem("Age");
        SettingsPage.settingsTab().addItemIntoFolder("new folder");
        Actions.generalActions().openMainPage();

// create project with weight parameter
        itemActions().removeItemsIfPresent(uatProject);
        itemActions().createProject(uatProject, mayorFileFullName);
        Actions.itemActions().selectProject(uatProject);
        Actions.projectActions().openProjectSettings();
        SettingsPage.settingsTab().waitForProjectFolder("root");
        Pages.projectPage().settingsPage().settingsTab().clickItemSearch();
        Pages.projectPage().settingsPage().settingsTab().typeTextIntoSearchField("W_Days");
        SettingsPage.settingsTab().clickWDays();
        SettingsPage.settingsTab().clickCurrentlySelectedItemDropDown();
        SettingsPage.settingsTab().clickCurrentlySelectedItemOption();
        Actions.projectActions().openSettingsTab();
        Actions.projectActions().openSettingsItemByName("Weights");
        BaseActions.wait(3);
        SettingsPage.settingsTab().clickDVICheckbox();
        SettingsPage.settingsTab().clickDVIWeightPopUp();
        SettingsPage.settingsTab().waitForDVIWeightPopUp();
        SettingsPage.settingsTab().selectDVIWeightItem("W_Days");
        SettingsPage.settingsTab().waitForSelectedDVIWeightItem("W_Days");
        BaseActions.wait(5);
        Actions.generalActions().openMainPage();

// create context project with "context" parameter
        Actions.itemActions().openItem(DVIFolder);
        Actions.itemActions().selectProject(context);
        Actions.projectActions().openProjectSettings();
        Actions.projectActions().openSettingsTab();
        Actions.projectActions().openSettingsItemByName("Context");
        BaseActions.wait(3);
        SettingsPage.settingsTab().clickDVICheckbox();
        BaseActions.wait(5);
        Actions.generalActions().openMainPage();


//create dashboards
        itemActions().removeItemsIfPresent(dashboardProjectCreator);
        itemActions().createProject(dashboardProjectCreator, mayorFileFullName);
        TestRailAssert.assertTrue(
                itemActions().isItemPresent(dashboardProjectCreator),
                new CustomStepResult(
                        "Проект с именем '" + dashboardProjectCreator + "' не создался",
                        "Проект с именем '" + dashboardProjectCreator + "' успешно создался и отображается на странице")
        );

        for (String dashboardEntity : allDashboards) {
            itemActions().removeItemsIfPresent(dashboardEntity);
            Actions.itemActions().openItem(dashboardProjectCreator);
            Actions.projectActions().clickResetStateButtonIfPresent();
            // sortTest
            if (dashboardEntity.equals("UAT_DashboardForSortTest")) {
                Actions.projectActions().createSimpleRequest(typeOfEnterpriseItem, genderItem);
            } else {
                Actions.projectActions().createSimpleRequest(genderItem, educationItem);
            }
            // chartRedraw
            if (dashboardEntity.equals("UAT_DashboardForChartRedraw")) {
                ProjectPage.toolbarRegion().clickChartButton();
                ProjectPage.chartRegion().waitToLoad();
                Actions.projectActions().exportToDashboardFromChart(dashboardEntity);
                Actions.generalActions().openMainPage();
                continue;
            }
            Actions.projectActions().exportToDashboard(dashboardEntity);
            Actions.generalActions().openMainPage();
        }
    }


/*
    @Test(description = "init all DASHBOARD_MANAGER entities ",
            dataProvider = DataProviderPool.DASHBOARD_MANAGER_CREDENTIALS, dataProviderClass = DataProviderPool.class)
    public void downloadProjectEntity(String login, String pass) throws Exception {
        itemsName.add("Text");
        itemsName.add("Image");
        itemsName.add("Attachment");

        Actions.generalActions().clearSession();
        Actions.generalActions().openHomePage();
        Actions.generalActions().login(login, pass);
        Actions.generalActions().checkHomePageLoaded();
        itemActions().removeItemsIfPresent(imageDashboardFolder);
        itemActions().createFolder(imageDashboardFolder);
        itemActions().openItem(imageDashboardFolder);
        Actions.itemActions().createDashboard(imageDashboard);
        Actions.itemActions().openItem(imageDashboard);
        Actions.dashboardActions().openEditMode();
        Actions.dashboardActions().checkForLeftActionsItemsPresentAndVisible(itemsName);


        Actions.dashboardActions().clickIcon("Image", itemsName);

        TestRailAssert.assertTrue(
                DashboardPage.createTextWidgetDialog().isModalWindowPresent(),
                new CustomStepResult(
                        "Модальное окно 'Create image widget' не отображается",
                        "Модальное окно 'Create image widget' отображается")
        );

        Actions.dashboardActions().uploadImage();
*/


}

