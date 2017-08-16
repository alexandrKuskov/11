package eu.datatiler.tests;

import com.qatestlab.base.BaseActions;
import com.qatestlab.base.BaseTest;
import com.qatestlab.testrail.CustomStepResult;
import com.qatestlab.testrail.TestRailAssert;
import com.qatestlab.testrail.TestRailIssue;
import eu.datatiler.actions.Actions;
import eu.datatiler.actions.ProjectActions;
import eu.datatiler.pages.Pages;
import eu.datatiler.pages.dashboard.DashboardPage;
import eu.datatiler.pages.project.ProjectPage;
import eu.datatiler.utils.DataProviderPool;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class CreateDashboardTest extends BaseTest {

    private final String TEST_RUN_NAME = "My space tests";
    private String mySpaceSection = "My space";
    private String folderName = "UAT";
    private String dashboardInFolderName = "UAT_Dashboard";

    @TestRailIssue(issueID = 2, testRunName = TEST_RUN_NAME)
    @Test(dataProvider = DataProviderPool.DASHBOARD_MANAGER_CREDENTIALS, dataProviderClass = DataProviderPool.class,
            description = "Creation of a Dashboard in sub-folder (UAT-402)")
    public void createDashboardInSubFolder(String login, String pass) {

        Actions.generalActions().clearSession();
        Actions.generalActions().openHomePage();
        Actions.generalActions().login(login, pass);
        Actions.generalActions().checkHomePageLoaded();
        Actions.itemActions().removeItemsIfPresent(folderName);
        Actions.itemActions().createFolder(folderName);
        Actions.itemActions().openItem(folderName);
        Actions.itemActions().createDashboard(dashboardInFolderName);
        boolean itemPresent = Actions.itemActions().isItemPresent(dashboardInFolderName);

        TestRailAssert.assertTrue(
                Actions.itemActions().isItemPresent(dashboardInFolderName) &&
                        (folderName.equals(Pages.navigationBar().getLastFolderName())),
                new CustomStepResult(
                        "Dashboard '" + dashboardInFolderName + "' не отображается на странице " + folderName,
                        "Dashboard '" + dashboardInFolderName + "' успешно создан и" +
                                " отображается на странице " + folderName)
        );

        Actions.itemActions().openSection(mySpaceSection);
        Actions.itemActions().removeItem(folderName);
        Actions.generalActions().logOut();
    }

}
