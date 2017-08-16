package eu.datatiler.actions;

import com.qatestlab.base.BaseActions;
import com.qatestlab.testrail.CustomStepResult;
import com.qatestlab.testrail.TestRailAssert;
import eu.datatiler.pages.Pages;

public class UserActions extends BaseActions {

    protected UserActions() {
    }

    public void changeUserName(String firstName) {
        Pages.navigationBar().waitNavigationBar();
        Pages.navigationBar().clickToggleButton();
        Pages.navigationBar().waitProfileButton();
        Pages.navigationBar().clickProfileButton();
        Pages.profilePage().waitFirstNameVisibility();

        TestRailAssert.assertTrue(
                Pages.profilePage().isProfileHeaderVisible(),
                new CustomStepResult(
                        "Страница 'Profile' не отображается",
                        "Отображается страница 'Profile'")
        );

        Pages.profilePage().typeFirstName(firstName);

        TestRailAssert.assertTrue(
                firstName.equals(Pages.profilePage().getEnteredNewFirstName()),
                new CustomStepResult(
                        "Новое имя в поле 'First Name' не отображается",
                        "Отображается новое имя в поле 'First Name'")
        );

        Pages.profilePage().clickSaveButton();
        Pages.navigationBar().waitSuccessAlertVisibility();

        TestRailAssert.assertTrue(
                Pages.navigationBar().isSuccessAlertVisible(),
                new CustomStepResult(
                        "Нотификейшн 'Your profile was updated successfully' не отображается",
                        "Отображается нотификейшн 'Your profile was updated successfully'")
        );
    }

    public String getUserName() {
        Pages.navigationBar().waitNavigationBar();
        Pages.navigationBar().clickToggleButton();
        Pages.navigationBar().waitProfileButton();
        return Pages.navigationBar().getUserName();
    }


}
