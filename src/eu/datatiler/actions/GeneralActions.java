package eu.datatiler.actions;

import com.qatestlab.base.BaseActions;
import com.qatestlab.base.BasePage;
import com.qatestlab.reporting.Reporter;
import com.qatestlab.testrail.CustomStepResult;
import com.qatestlab.testrail.TestRailAssert;
import eu.datatiler.pages.LoginPage;
import eu.datatiler.pages.NavigationBar;
import eu.datatiler.pages.Pages;

public class GeneralActions extends BaseActions {


    protected GeneralActions() {
    }

    public void openHomePage() {
        Reporter.log("Open home page: " + BasePage.BASE_URL);
        driver().get(BasePage.BASE_URL);

        Pages.loginPage().waitForLoad();

        TestRailAssert.assertTrue(
                Pages.loginPage().isLoginFormVisible(),
                new CustomStepResult(
                        "Форма входа не отображается.",
                        "Отображается открытая страница с формой авторизации")
        );
    }

    public void openAdminPage() {
        Reporter.log("Open admin page: " + BasePage.ADMIN_URL);
        driver().get(BasePage.ADMIN_URL);
    }

    public void openPage(String url) {
        Reporter.log("Open page: " + url);
        driver().get(url);
    }

    public void refreshPage() {
        Reporter.log("Refreshing page");
        driver().navigate().refresh();
        wait(5);
    }

    private void loginAction(String login, String password) {
        Reporter.logAction(String.format("Log in (login: '%s', password: '%s'):", login, password));

        LoginPage page = Pages.loginPage();
        page.waitForLoad();
        page.typeLogin(login);

        TestRailAssert.assertTrue(
                login.trim().equals(Pages.loginPage().getLoginText().trim()),
                new CustomStepResult(
                        "Введенные данные не отображается в поле в 'E-mail'",
                        "Введенные данные отображается в поле в 'E-mail'"
                )
        );

        page.typePassword(password);

        TestRailAssert.assertTrue(
                (password.length()) == (Pages.loginPage().getPasswordText().length()),
                new CustomStepResult(
                        "Введенные данные не отображается в поле в 'Password'",
                        "Введенные данные отображается в поле в 'Password'"
                )
        );

        page.clickLoginButton();
    }

    public void login(String login, String password) {
        loginAction(login, password);
        Pages.loginPage().waitForLoginInvisibility();
    }

    public void incorrectLogin(String login, String password) {
        loginAction(login, password);
    }

    public void loginWithLineBreaks() {
        Pages.loginPage().waitForLoad();
        Pages.loginPage().typeLineBreakLogin();

        TestRailAssert.assertTrue(
                Pages.loginPage().getLoginText().length() == 0,
                new CustomStepResult(
                        "Введенные данные отображается в поле в 'E-mail'",
                        "Введенные данные не отображается в поле в 'E-mail'"
                )
        );

        Pages.loginPage().typeLineBreakPass();

        TestRailAssert.assertTrue(
                Pages.loginPage().getPasswordText().length() == 0,
                new CustomStepResult(
                        "Введенные данные отображается в поле в 'Password'",
                        "Введенные данные не отображается в поле в 'Password'"
                )
        );

        Pages.loginPage().clickLoginButton();
    }

    public void logOut() {
        Reporter.log("Log out:");
        NavigationBar navBar = Pages.navigationBar();
        navBar.waitNavigationBar();
        navBar.clickToggleButton();
        navBar.waitLogoutButton();
        navBar.waitLoader();
        navBar.clickLogoutButton();
        Pages.loginPage().waitForLoad();
    }

    public String getFooterText() {
        Pages.loginPage().waitForLoad();
        Pages.loginPage().waitFooterTextVisibility();
        String footerText = Pages.loginPage().getFooterText();
        Reporter.log("Footer text is: " + footerText);
        return footerText;
    }

    public String getPageTitle() {
        BaseActions.wait(2);
        String title = driver().getTitle();
        Reporter.log("Page title is " + title);
        return title;
    }

    public void checkHomePageLoaded() {
        Pages.navigationBar().waitNavigationBar();
        Pages.navigationBar().clickToggleButton();
        Pages.navigationBar().waitLogoutButton();
        String expectedPageTitle = "DataTile";

        TestRailAssert.assertTrue(
                expectedPageTitle.equals(Actions.generalActions().getPageTitle()) &&
                        Pages.navigationBar().isLogoutButtonPresent(),
                new CustomStepResult(
                        "Пользователь залогинился не успешно или тайтл страницы '" + expectedPageTitle +
                                "' во вкладке браузера не отображается",
                        "Пользователь успешно залогинился. Тайтл страницы во вкладке браузера: " +
                                expectedPageTitle + ""
                )
        );

        Pages.navigationBar().clickToggleButton();
        Pages.mainPage().sidebarRegion().clickSection("My space");
    }

    public String getErrorMessage() {
        Pages.loginPage().waitErrorMessage();
        return Pages.loginPage().getErrorMessageText();
    }

    public boolean isAdminButtonPresent() {
        Pages.navigationBar().waitNavigationBar();
        Pages.navigationBar().clickToggleButton();
        Pages.navigationBar().waitLogoutButton();
        Pages.navigationBar().waitLoader();
        return Pages.navigationBar().isAdminButtonPresent();
    }

    public void openMainPage() {
        Pages.navigationBar().waitMainPageLink();
        Pages.navigationBar().clickMainPageLink();
        Pages.navigationBar().waitLoader();
    }
}
