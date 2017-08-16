package eu.datatiler.tests;


import com.qatestlab.base.BasePage;
import com.qatestlab.base.BaseTest;
import com.qatestlab.properties.Properties;
import com.qatestlab.reporting.Reporter;
import com.qatestlab.testrail.CustomStepResult;
import com.qatestlab.testrail.TestRailAssert;
import com.qatestlab.testrail.TestRailIssue;
import eu.datatiler.actions.Actions;
import eu.datatiler.pages.Pages;
import eu.datatiler.utils.DataProviderPool;
import org.testng.annotations.Test;

public class HeartBeatCheck extends BaseTest {

    private final String TEST_RUN_NAME = "Login tests";

    @TestRailIssue(issueID = 43, testRunName = TEST_RUN_NAME)
    @Test(dataProvider = DataProviderPool.USER_CREDENTIALS, dataProviderClass = DataProviderPool.class,
            description = "DataTiler heart beat check (UAT-17)")
    public void checkLoginPage(String login, String pass) {

        Reporter.log("Open home page: " + BasePage.BASE_URL);

        driver().get(BasePage.BASE_URL);

        Actions.generalActions().clearSession();
        Pages.loginPage().waitForLoad();
/*
        TestRailAssert.assertTrue(
                Actions.generalActions().getFooterText().contains(Properties.getVersionNumber()) &&
                        Actions.generalActions().getFooterText().contains(Properties.getFooterReferenceText()) &&
                        "DataTile".equals(Actions.generalActions().getPageTitle()),

                new CustomStepResult(
                        "Не отображается открытая страница с формой авторизации или " +
                                "тайтл страницы во вкладке браузера не совбадает с DataTiler Login или " +
                                "В Футере отображается текст: " +
                                Actions.generalActions().getFooterText(),

                        "Отображается открытая страница с формой авторизации." +
                                "Тайтл страницы во вкладке браузера DataTiler Login. " +
                                "В Футере отображается текст: Reporting " +
                                Properties.getVersionNumber() + " " +
                                Properties.getFooterReferenceText()
                )
        );
*/
        Actions.generalActions().login(login, pass);
        Actions.generalActions().checkHomePageLoaded();

        Actions.generalActions().logOut();

        TestRailAssert.assertTrue(
                Pages.loginPage().isLoginFormVisible(),
                new CustomStepResult(
                        "Форма входа не отображается.",
                        "Отображается открытая страница с формой авторизации")
        );
    }
}
