package eu.datatiler.tests;

import com.qatestlab.base.BaseTest;
import com.qatestlab.testrail.CustomStepResult;
import com.qatestlab.testrail.TestRailAssert;
import com.qatestlab.testrail.TestRailIssue;
import eu.datatiler.actions.Actions;
import eu.datatiler.pages.Pages;
import eu.datatiler.utils.DataProviderPool;
import org.testng.annotations.Test;

public class ClipboardTest extends BaseTest {

    private final String TEST_RUN_NAME = "My space tests";
    private final String folderName = "UAT";
    private final String projectName = "projectForClipboardTest";
    private final String genderItem = "Gender";
    private final String educationItem = "Education";
    private final String dashboardName = "dashboardForClipboardTest";
    private final String copyToClipboardTest = "ClipboardTests_CopyToClipboardTest";
    private final String clipboardTestsFolder = "UAT_ClipboardTests";

    @TestRailIssue(issueID = 4, testRunName = TEST_RUN_NAME)
    @Test(dataProvider = DataProviderPool.PROJECT_MANAGER_CREDENTIALS, dataProviderClass = DataProviderPool.class,
    description = "Ensure that link to public dashboard is copied to clipboard with button pressed (UAT-397)")
    public void copyToClipboardTest(String login, String pass) throws Exception {
        Actions.generalActions().clearSession();
        Actions.generalActions().openHomePage();
        Actions.generalActions().login(login, pass);
        Actions.generalActions().checkHomePageLoaded();

      //  Actions.itemActions().removeItemsIfPresent(folderName);

      //  Actions.itemActions().createFolder(folderName);
        Actions.itemActions().openItem(clipboardTestsFolder);
     //   Actions.itemActions().createProject(projectName);
        Actions.itemActions().openItem(copyToClipboardTest);
        Actions.projectActions().clickResetStateButtonIfPresent();
        Actions.projectActions().createSimpleRequest(genderItem, educationItem);
        Actions.projectActions().exportToDashboard(dashboardName);
        Actions.generalActions().openMainPage();

        Actions.itemActions().openItem(clipboardTestsFolder);

        TestRailAssert.assertTrue(
                Actions.itemActions().isItemPresent(dashboardName) &&
                        Actions.itemActions().isItemPresent(copyToClipboardTest) &&
                        (clipboardTestsFolder.equals(Pages.navigationBar().getLastFolderName())),
                new CustomStepResult(
                        "Dashboard '" + dashboardName + "' или проект " + copyToClipboardTest
                                + " не отображается на странице '" + clipboardTestsFolder + "'",
                        "Dashboard '" + dashboardName + "' и проект " + copyToClipboardTest +
                                " успешно создан и отображается на странице '" + clipboardTestsFolder + "'")
        );

        Actions.itemActions().selectDashboard(dashboardName);
        Actions.itemActions().openShareDialog();
        Actions.itemActions().copyToClipboard();

        Actions.itemActions().closeShareDialog();

        Actions.generalActions().openMainPage();
        Actions.itemActions().openItem(clipboardTestsFolder);
        Actions.itemActions().removeItem(dashboardName);
    }
}
