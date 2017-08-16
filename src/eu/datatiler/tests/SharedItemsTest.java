package eu.datatiler.tests;

import com.qatestlab.base.BaseTest;
import com.qatestlab.testrail.CustomStepResult;
import com.qatestlab.testrail.TestRailAssert;
import com.qatestlab.testrail.TestRailIssue;
import eu.datatiler.actions.Actions;
import eu.datatiler.pages.Pages;
import eu.datatiler.utils.DataProviderPool;
import org.testng.annotations.Test;

public class SharedItemsTest extends BaseTest {

    private final String TEST_RUN_NAME = "My space tests";

    @TestRailIssue(issueID = 13, testRunName = TEST_RUN_NAME)
    @Test(dataProvider = DataProviderPool.USER_CREDENTIALS, dataProviderClass = DataProviderPool.class,
            description = "Check Shared with me section (UAT-23)")
    public void checkSharedSection(String login, String pass) {
        Actions.generalActions().clearSession();
        Actions.generalActions().openHomePage();
        Actions.generalActions().login(login, pass);
        Actions.generalActions().checkHomePageLoaded();
        String sectionName = "Shared With Me";
        String projectName = "SharedProject";
        String innerProjectName = "InnerSharedProject";
        String folderName = "SharedFolder";
        String dashboardName = "SharedDashboard";
        Actions.itemActions().openSection(sectionName);

        TestRailAssert.assertTrue(
                        Pages.mainPage().contentRegion().isItemPresent(folderName) &&
                                  sectionName.equals(Pages.navigationBar().getLastFolderName())
                ,
                new CustomStepResult(
                        "Открыта страница 'Shared With Me' с проектом и папкой",
                        "Открыта страница 'Shared With Me' с проектом и папкой"
                )
        );

        Actions.itemActions().openItem(folderName);

        TestRailAssert.assertTrue(
                Pages.mainPage().contentRegion().isItemPresent(innerProjectName) &&
                        Pages.mainPage().contentRegion().isItemPresent(dashboardName) &&
                        folderName.equals(Pages.navigationBar().getLastFolderName())
                ,
                new CustomStepResult(
                        "Папка открыта; В папке отображаются вложенные проект и дешборд",
                        "Папка открыта; В папке отображаются вложенные проект и дешборд"
                )
        );
    }
}
