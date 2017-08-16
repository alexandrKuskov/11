package eu.datatiler.tests;

import com.qatestlab.Random;
import com.qatestlab.base.BaseTest;
import com.qatestlab.testrail.CustomStepResult;
import com.qatestlab.testrail.TestRailAssert;
import com.qatestlab.testrail.TestRailIssue;
import eu.datatiler.actions.Actions;
import eu.datatiler.pages.Pages;
import eu.datatiler.utils.DataProviderPool;
import org.testng.Reporter;
import org.testng.annotations.Test;

public class LoginTests extends BaseTest {

    private final String TEST_RUN_NAME = "Login tests";

    @TestRailIssue(issueID = 42, testRunName = TEST_RUN_NAME)
    @Test(description = "Log in with incorrect login and valid password (UAT-3)",
            dataProvider = DataProviderPool.USER_CREDENTIALS,
            dataProviderClass = DataProviderPool.class)
    public void logInWithIncorrectLogin(String login, String password) {
        Actions.generalActions().clearSession();
        Actions.generalActions().openHomePage();
        Actions.generalActions().incorrectLogin(Random.genEmail(), password);

        TestRailAssert.assertTrue(
                "Bad credentials".equals(Actions.generalActions().getErrorMessage()),
                new CustomStepResult(
                        "Тултип 'Bad credentials' не отображается.",
                        "Пользователь не залогинен; Отображается тултип 'Wrong credentials'."
                )
        );
    }

    @TestRailIssue(issueID = 41, testRunName = TEST_RUN_NAME)
    @Test(description = "Log in with registered e-mail and invalid password (UAT-5)",
            dataProvider = DataProviderPool.USER_CREDENTIALS,
            dataProviderClass = DataProviderPool.class)
    public void logInWithIncorrectPassword(String login, String password) {
        Actions.generalActions().clearSession();
        Actions.generalActions().openHomePage();
        Actions.generalActions().incorrectLogin(login, Random.genStreet());

        TestRailAssert.assertTrue(
                "Bad credentials".equals(Actions.generalActions().getErrorMessage()),
                new CustomStepResult(
                        "Тултип 'Wrong credentials.' не отображается.",
                        "Пользователь не залогинен; Отображается тултип 'Wrong credentials'."
                )
        );
    }

    @TestRailIssue(issueID = 40, testRunName = TEST_RUN_NAME)
    @Test(description = "Log in with switched login and password (UAT-12)",
            dataProvider = DataProviderPool.USER_CREDENTIALS,
            dataProviderClass = DataProviderPool.class)
    public void logInWithSwitchedData(String login, String password) {
        Actions.generalActions().clearSession();
        Actions.generalActions().openHomePage();
        Actions.generalActions().incorrectLogin(password, login);

        TestRailAssert.assertTrue(
                Pages.loginPage().isLoginFormVisible(),
                new CustomStepResult(
                        "Форма входа не отображается.",
                        "Пользователь не залогинен. Отображается открытая страница с формой авторизации")
        );
    }

    @TestRailIssue(issueID = 36, testRunName = TEST_RUN_NAME)
    @Test(description = "Log in with empty fields (UAT-13)")
    public void loginWithoutData() {
        Actions.generalActions().clearSession();
        Actions.generalActions().openHomePage();
        Actions.generalActions().incorrectLogin("", "");

        TestRailAssert.assertTrue(
                Pages.loginPage().isLoginFormVisible(),
                new CustomStepResult(
                        "Форма входа не отображается.",
                        "Пользователь не залогинен. Отображается открытая страница с формой авторизации")
        );
    }

    @TestRailIssue(issueID = 37, testRunName = TEST_RUN_NAME)
    @Test(description = "Log in with filled with spaces fields (UAT-14)")
    public void loginWithSpacesFields() {
        Actions.generalActions().clearSession();
        Actions.generalActions().openHomePage();
        Actions.generalActions().incorrectLogin("    ", "    ");
        TestRailAssert.assertTrue(
                Pages.loginPage().isLoginFormVisible(),
                new CustomStepResult(
                        "Форма входа не отображается.",
                        "Пользователь не залогинен. Отображается открытая страница с формой авторизации")
        );
    }

    @TestRailIssue(issueID = 38, testRunName = TEST_RUN_NAME)
    @Test(description = "Log in with filled with new lines fields (UAT-15)")
    public void loginWithLineBreaksTest() {
        Actions.generalActions().clearSession();
        Actions.generalActions().openHomePage();
        Reporter.log("Logging in with line breaks", true);
        Actions.generalActions().loginWithLineBreaks();

        TestRailAssert.assertTrue(
                Pages.loginPage().isLoginFormVisible(),
                new CustomStepResult(
                        "Форма входа не отображается.",
                        "Пользователь не залогинен. Отображается открытая страница с формой авторизации")
        );
    }

    @TestRailIssue(issueID = 39, testRunName = TEST_RUN_NAME)
    @Test(dataProvider = DataProviderPool.BLOCKED_USER_CREDENTIALS, dataProviderClass = DataProviderPool.class,
            description = "Try to log in as a blocked user (UAT-30)")
    public void loginWithBlockedUser(String login, String pass) {
        Actions.generalActions().clearSession();
        Actions.generalActions().openHomePage();
        Actions.generalActions().incorrectLogin(login, pass);

        TestRailAssert.assertTrue(
                "User is disabled".equals(Actions.generalActions().getErrorMessage()),
                new CustomStepResult(
                        "Тултип 'Wrong credentials' не отображается.",
                        "Пользователь не залогинен; Отображается тултип 'Wrong credentials'."
                )
        );
    }

}
