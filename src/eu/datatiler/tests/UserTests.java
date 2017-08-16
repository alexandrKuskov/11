package eu.datatiler.tests;

import com.qatestlab.Random;
import com.qatestlab.base.BaseTest;
import com.qatestlab.testrail.CustomStepResult;
import com.qatestlab.testrail.TestRailAssert;
import com.qatestlab.testrail.TestRailIssue;
import eu.datatiler.actions.Actions;
import eu.datatiler.pages.Pages;
import eu.datatiler.utils.DataProviderPool;
import org.testng.annotations.Test;


public class UserTests extends BaseTest {

    private final String TEST_RUN_NAME = "My space tests";

    @TestRailIssue(issueID = 12, testRunName = TEST_RUN_NAME)
    @Test(dataProvider = DataProviderPool.USER_CREDENTIALS, dataProviderClass = DataProviderPool.class,
            description = "Change user name on profile page (UAT-27)")
    public void changeUserName(String login, String pass) {
        Actions.generalActions().clearSession();
        Actions.generalActions().openHomePage();
        Actions.generalActions().login(login, pass);
        Actions.generalActions().checkHomePageLoaded();
        String newUsername = "Viewer" + Random.genShortRandNumber();
        Actions.userActions().changeUserName(newUsername);
        Actions.generalActions().openMainPage();

        TestRailAssert.assertTrue(
                "My space".equals(Pages.navigationBar().getLastFolderName()) &&
                        newUsername.equals(Actions.userActions().getUserName()),
                new CustomStepResult(
                        "Страница " + Pages.navigationBar().getLastFolderName() + " не отображается или " +
                                "в правом верхнем углну не отображается новое имя пользователя.",
                        "Отображается " + Pages.navigationBar().getLastFolderName() + " страница. " +
                                "В правом верхнем углну отображается новое имя пользователя.")
        );
    }

}
