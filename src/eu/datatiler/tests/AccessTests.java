package eu.datatiler.tests;

import com.qatestlab.base.BaseTest;
import com.qatestlab.testrail.CustomStepResult;
import com.qatestlab.testrail.TestRailAssert;
import com.qatestlab.testrail.TestRailIssue;
import eu.datatiler.actions.Actions;
import eu.datatiler.utils.DataProviderPool;
import org.testng.annotations.Test;

public class AccessTests extends BaseTest {

    private final String TEST_RUN_NAME = "Security test";

    @TestRailIssue(issueID = 15, testRunName = TEST_RUN_NAME)
    @Test(dataProvider = DataProviderPool.USER_CREDENTIALS, dataProviderClass = DataProviderPool.class,
            description = "Ensure that Viewer can't access administration page (UAT-393)")
    public void accessAdminPageTest(String login, String pass) {
        Actions.generalActions().clearSession();
        Actions.generalActions().openHomePage();
        Actions.generalActions().login(login, pass);
        Actions.generalActions().checkHomePageLoaded();
        Actions.generalActions().openAdminPage();

        TestRailAssert.assertTrue(
                "Sorry, something went wrong...".equals(Actions.generalActions().getPageTitle()),
                new CustomStepResult(
                        "Страница '404. OOPS! PAGE NOT FOUND' не отображается",
                        "Отображается страница '404. OOPS! PAGE NOT FOUND'"
                )
        );
    }
}