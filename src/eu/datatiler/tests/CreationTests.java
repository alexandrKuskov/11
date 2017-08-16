package eu.datatiler.tests;

import com.qatestlab.Random;
import com.qatestlab.base.BaseTest;
import com.qatestlab.testrail.CustomStepResult;
import com.qatestlab.testrail.TestRailAssert;
import com.qatestlab.testrail.TestRailIssue;
import eu.datatiler.actions.Actions;
import eu.datatiler.pages.Pages;
import eu.datatiler.utils.DataProviderPool;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CreationTests extends BaseTest {

    private final String TEST_RUN_NAME = "My space tests";

    private String folderName = "UAT";
    private String projectName = "UAT_Project";
    private String genderItem = "Gender";
    private String educationItem = "Education";
    private String dashboardInFolderName = "Dashboard for UAT_Project";
    private String exportedDashboardName = "Dashboard for " + projectName;
    private String creationTestsFolder = "UAT_CreationTests";
    private String createDashboardFromCrosstab = "Creation_Tests_CreateDashboardFromCrosstab";

    @TestRailIssue(issueID = 3, testRunName = TEST_RUN_NAME)
    @Test(description = "Creation of a dashboard on demand when exporting chart from Crosstab(UAT-403)",
            dataProvider = DataProviderPool.PROJECT_MANAGER_CREDENTIALS, dataProviderClass = DataProviderPool.class)
    public void createDashboardFromCrosstab(String login, String pass) throws Exception {

        Actions.generalActions().clearSession();
        Actions.generalActions().openHomePage();
        Actions.generalActions().login(login, pass);

        Actions.generalActions().checkHomePageLoaded();

      //  Actions.itemActions().removeItemsIfPresent(folderName);
      //  Actions.itemActions().removeItemsIfPresent(exportedDashboardName);
      //  Actions.itemActions().createFolder(folderName);
        Actions.itemActions().openItem(creationTestsFolder);
        Actions.itemActions().removeItemsIfPresent(dashboardInFolderName);
      //  Actions.itemActions().createProject(projectName);

        boolean itemPresent = Actions.itemActions().isItemPresent(createDashboardFromCrosstab);
        Assert.assertTrue(itemPresent, String.format("'%s' isn't present in folder %s", createDashboardFromCrosstab, creationTestsFolder));

        Actions.itemActions().openItem(createDashboardFromCrosstab);

        TestRailAssert.assertTrue(
                Pages.projectPage().categoryRegion().isDVIRegionVisible(),
                new CustomStepResult(
                        "DVI проекта не отображается ",
                        "Отображается DVI проекта")
        );
        Actions.projectActions().clickResetStateButtonIfPresent();
        Actions.projectActions().createSimpleRequest(genderItem, educationItem);
        Actions.projectActions().exportToDashboard(dashboardInFolderName);
        Actions.projectActions().openMySpace();

        Actions.itemActions().openItem(creationTestsFolder);

        TestRailAssert.assertTrue(
                Actions.itemActions().isItemPresent(createDashboardFromCrosstab) &&
                        Actions.itemActions().isItemPresent(dashboardInFolderName) &&
                        (creationTestsFolder.equals(Pages.navigationBar().getLastFolderName())),
                new CustomStepResult(
                        "Dashboard '" + dashboardInFolderName + "' и/или проект " + createDashboardFromCrosstab +
                                " не отображается на странице " + creationTestsFolder,
                        "Проект " + createDashboardFromCrosstab + " отображается. Dashboard '" + dashboardInFolderName +
                                "' успешно экспортирован и отображается на странице " + creationTestsFolder)
        );
        Actions.itemActions().openItem(dashboardInFolderName);
        Actions.dashboardActions().checkDashboardTablePresence();
        Actions.itemActions().openItem(creationTestsFolder);
        Actions.itemActions().removeItem(dashboardInFolderName);
    }


    @TestRailIssue(issueID = 5, testRunName = "My space tests")
    @Test(description = "Rename entity in my space (UAT-395)",
            dataProvider = DataProviderPool.PROJECT_MANAGER_CREDENTIALS, dataProviderClass = DataProviderPool.class)
    public void renameEntityTest(String login, String pass) {
        String itemName = "UAT_RenameTestFolder";
        Actions.generalActions().clearSession();
        Actions.generalActions().openHomePage();

        Actions.generalActions().login(login, pass);
        Actions.generalActions().checkHomePageLoaded();

        Actions.itemActions().removeItemsIfPresent(itemName);

        String newName = itemName + Random.genShortRandNumber();

        Actions.itemActions().createFolder(itemName);
        Actions.itemActions().selectFolder(itemName);

        Actions.itemActions().renameItem(itemName, newName);
        Actions.generalActions().refreshPage();
        Actions.itemActions().checkItemPresence(newName);
        Actions.itemActions().removeItem(newName);
    }

}
