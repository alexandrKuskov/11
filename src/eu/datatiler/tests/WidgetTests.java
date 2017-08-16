package eu.datatiler.tests;

import com.qatestlab.base.BaseTest;
import com.qatestlab.testrail.CustomStepResult;
import com.qatestlab.testrail.TestRailAssert;
import com.qatestlab.testrail.TestRailIssue;
import eu.datatiler.actions.Actions;
import eu.datatiler.pages.Pages;
import eu.datatiler.utils.DataProviderPool;
import org.testng.annotations.Test;

public class WidgetTests extends BaseTest {

    private final String TEST_RUN_NAME = "Dashboard tests";

    private String folderName = "UAT";
    private String projectName = "UAT_Project";

    private final String genderItem = "Gender";
    private final String educationItem = "Education";

    private String dashboardWithText = "UAT_DashboardWithTextWidget";
    private String dashboardWIthImage = "UAT_DashboardWithImage";
    private String text = "Text formatted";
    private String widgetTestsFolder = "UAT_WidgetTests";
    private String addTextWidgetWithFormatting = "WidgetTests_AddTextWidgetWithFormatting";
    private String checkTextWidgetPresent = "WidgetTests_CheckTextWidgetPresent";
    private String checkImageWidget = "WidgetTests_CheckImageWidget";
/*
    public void createProjectInSubFolder() {
        Actions.itemActions().removeItemsIfPresent(folderName);
        Actions.itemActions().createFolder(folderName);
        Actions.itemActions().openItem(folderName);
        Actions.itemActions().createProject(projectName);
    }
    */



    @TestRailIssue(issueID = 46, testRunName = TEST_RUN_NAME)
    @Test(dataProvider = DataProviderPool.PROJECT_MANAGER_CREDENTIALS, dataProviderClass = DataProviderPool.class,
            description = "Create text widget with formatting (UAT-411)")
    public void addTextWidgetWithFormatting(String login, String pass) throws Exception {
        Actions.generalActions().clearSession();
        Actions.generalActions().openHomePage();
        Actions.generalActions().login(login, pass);
        Actions.generalActions().checkHomePageLoaded();

        Actions.itemActions().openItem(widgetTestsFolder);

       // createProjectInSubFolder();

        Actions.itemActions().openItem(addTextWidgetWithFormatting);
        //Actions.projectActions().createSimpleRequest(genderItem, educationItem);
        Actions.projectActions().exportToDashboard(dashboardWithText);
        Actions.generalActions().openMainPage();

        TestRailAssert.assertTrue(
                Actions.itemActions().isItemPresent(widgetTestsFolder) &&
                        ("My space".equals(Pages.navigationBar().getLastFolderName())),
                new CustomStepResult(
                        "My space страница не отображается и/или ранее созданная папка не тображается",
                        "Отображается My space страница. Отображается ранее созданная папка"
                )
        );

        Actions.itemActions().openItem(widgetTestsFolder);

        TestRailAssert.assertTrue(
                Actions.itemActions().isItemPresent(dashboardWithText) &&
                        Actions.itemActions().isItemPresent(addTextWidgetWithFormatting),
                new CustomStepResult(
                        "Созданный Dashboard и/или загруженный проект не отображается",
                        "Отображается созданный Dashboard и загруженный проект"
                )
        );

        Actions.itemActions().openItem(dashboardWithText);

        Pages.dashboardPage().contentPage().waitForDashboardVisibility();

        TestRailAssert.assertTrue(
                Pages.dashboardPage().contentPage().isTablePresent(),
                new CustomStepResult(
                        "Дашбоард не содержит таблицу",
                        "Отображается таблица на странице дашбоарда ")
        );

        Actions.dashboardActions().addTextWidget(text);

       // Pages.dashboardPage().contentPage().clickOnEmptySpace();
        Pages.dashboardPage().contentPage().clickOnWidgetArea();
        Actions.generalActions().wait(5);

        TestRailAssert.assertTrue(
                !(Pages.dashboardPage().contentPage().isTextWidgetSelected()) &&
                        text.equals(Pages.dashboardPage().contentPage().getTextWidgetContent()),
                new CustomStepResult(
                        "Введенный текст не отображается в текстовом поле и/или фокус с виджета не снят",
                        "Введенный текст отображается в текстовом поле. Фокус с виджета снят")
        );

        Pages.dashboardPage().contentPage().selectTextWidget(text);
        Actions.generalActions().wait(5);

        TestRailAssert.assertTrue(
                Pages.dashboardPage().leftToolBarRegion().isEditWidgetButtonPresent() &&
                        Pages.dashboardPage().leftToolBarRegion().isDeleteWidgetButtonPresent(),
                new CustomStepResult(
                        "Пользователь не видит в левом верхнем углу (возле кнопки 'ADD') " +
                                "две иконки - Редактировать и Удалить",
                        "Пользователь видит в левом верхнем углу (возле кнопки 'ADD') " +
                                "две иконки - Редактировать и Удалить")
        );

        Pages.dashboardPage().leftToolBarRegion().clickEditWidgetButton();

        Pages.dashboardPage().createTextWidgetDialog().waitForDialogLoad();
        Pages.dashboardPage().createTextWidgetDialog().typeWidgetText(text + text);

        Pages.dashboardPage().contentPage().clickOnWidgetArea();
        Actions.generalActions().wait(5);

        TestRailAssert.assertTrue(
                !(Pages.dashboardPage().contentPage().isTextWidgetSelected()) &&
                        (text + text).equals(Pages.dashboardPage().contentPage().getTextWidgetContent()),
                new CustomStepResult(
                        "'фокус' с текстового поля не снят и/или изменения внесены",
                        "'фокус' с текстового поля снят, изменения внесены")
        );

        Actions.dashboardActions().closeEditMode();

        TestRailAssert.assertTrue(
                (text + text).equals(Pages.dashboardPage().contentPage().getTextWidgetContent()),
                new CustomStepResult(
                        "Nзменения в текстовом виджете не сохранены",
                        "Nзменения в текстовом виджете сохранены")
        );
        Actions.generalActions().openMainPage();
        Actions.itemActions().openItem(widgetTestsFolder);
        Actions.itemActions().removeItem(dashboardWithText);
    }




    @TestRailIssue(issueID = 45, testRunName = TEST_RUN_NAME)
    @Test(dataProvider = DataProviderPool.PROJECT_MANAGER_CREDENTIALS, dataProviderClass = DataProviderPool.class,
            description = "Check text widget is present on dashboard (UAT-412)")
    public void checkTextWidgetPresent(String login, String pass) throws Exception {
        Actions.generalActions().clearSession();
        Actions.generalActions().openHomePage();
        Actions.generalActions().login(login, pass);
        Actions.generalActions().checkHomePageLoaded();

        Actions.itemActions().openItem(widgetTestsFolder);

       // createProjectInSubFolder();

        Actions.itemActions().openItem(checkTextWidgetPresent);
        Actions.projectActions().clickResetStateButtonIfPresent();
        Actions.projectActions().createSimpleRequest(genderItem, educationItem);
        Actions.projectActions().exportToDashboard(dashboardWithText);
        Actions.generalActions().openMainPage();

        TestRailAssert.assertTrue(
                Actions.itemActions().isItemPresent(widgetTestsFolder) &&
                        ("My space".equals(Pages.navigationBar().getLastFolderName())),
                new CustomStepResult(
                        "My space страница не отображается и/или ранее созданная папка не тображается",
                        "Отображается My space страница. Отображается ранее созданная папка"
                )
        );

        Actions.itemActions().openItem(widgetTestsFolder);

        Actions.generalActions().wait(3);

        TestRailAssert.assertTrue(
                Actions.itemActions().isItemPresent(dashboardWithText) &&
                        Actions.itemActions().isItemPresent(checkTextWidgetPresent),
                new CustomStepResult(
                        "Созданный Dashboard и/или загруженный проект не отображается",
                        "Отображается созданный Dashboard и загруженный проект"
                )
        );

        Actions.itemActions().openItem(dashboardWithText);

        Pages.dashboardPage().contentPage().waitForDashboardVisibility();

        TestRailAssert.assertTrue(
                Pages.dashboardPage().contentPage().isTablePresent(),
                new CustomStepResult(
                        "Дашбоард не содержит таблицу",
                        "Отображается таблица на странице дашбоарда ")
        );

        Actions.dashboardActions().addTextWidget(text);

        Pages.dashboardPage().contentPage().clickOnWidgetArea();
        Actions.generalActions().wait(5);

        TestRailAssert.assertTrue(
                !(Pages.dashboardPage().contentPage().isTextWidgetSelected()) &&
                        text.equals(Pages.dashboardPage().contentPage().getTextWidgetContent()),
                new CustomStepResult(
                        "'фокус' с текстового поля не снят и/или изменения внесены",
                        "'фокус' с текстового поля снят, изменения внесены")
        );

        Actions.dashboardActions().closeEditMode();

        TestRailAssert.assertTrue(
                text.equals(Pages.dashboardPage().contentPage().getTextWidgetContent()),
                new CustomStepResult(
                        "Nзменения в текстовом виджете не сохранены",
                        "Nзменения в текстовом виджете сохранены")
        );
        Actions.generalActions().openMainPage();
        Actions.itemActions().openItem(widgetTestsFolder);
        Actions.itemActions().removeItem(dashboardWithText);
    }



/*
    // TODO NEED FIX WITH CREATING NEW DASH NAME INTO UAT PROJECT (IE)
    @TestRailIssue(issueID = 44, testRunName = TEST_RUN_NAME)
    @Test(dataProvider = DataProviderPool.PROJECT_MANAGER_CREDENTIALS, dataProviderClass = DataProviderPool.class,
            description = "Check image is present on dashboard (UAT-413)")
    public void checkImageWidget(String login, String pass) throws Exception {
        Actions.generalActions().clearSession();
        Actions.generalActions().openHomePage();
        Actions.generalActions().login(login, pass);
        Actions.generalActions().checkHomePageLoaded();

      //  createProjectInSubFolder();
     //1   Actions.itemActions().openItem(widgetTestsFolder);
        Actions.itemActions().openItem(checkImageWidget);
        Actions.projectActions().createSimpleRequest(genderItem, educationItem);
        Actions.projectActions().exportToDashboard(dashboardWIthImage);
        Actions.generalActions().openMainPage();
/*
        TestRailAssert.assertTrue(
                Actions.itemActions().isItemPresent(widgetTestsFolder) &&
                        ("My space".equals(Pages.navigationBar().getLastFolderName())),
                new CustomStepResult(
                        "My space страница не отображается и/или ранее созданная папка не тображается",
                        "Отображается My space страница. Отображается ранее созданная папка"
                )
        );
*/
     //1   Actions.itemActions().openItem(widgetTestsFolder);
/*
        TestRailAssert.assertTrue(
                Actions.itemActions().isItemPresent(dashboardWIthImage) &&
                        Actions.itemActions().isItemPresent(checkImageWidget),
                new CustomStepResult(
                        "Созданный Dashboard и/или загруженный проект не отображается",
                        "Отображается созданный Dashboard и загруженный проект"
                )
        );
*/
/*
        Actions.itemActions().openItem(dashboardWIthImage);

        Pages.dashboardPage().contentPage().waitForDashboardVisibility();

        TestRailAssert.assertTrue(
                Pages.dashboardPage().contentPage().isTablePresent(),
                new CustomStepResult(
                        "Дашбоард не содержит таблицу",
                        "Отображается таблица на странице дашбоарда ")
        );


        Actions.dashboardActions().addImage();

        Actions.dashboardActions().closeEditMode();

        TestRailAssert.assertTrue(
                Pages.dashboardPage().contentPage().isImageWidgetPresent(),
                new CustomStepResult(
                        "Nзменения успешно не сохранены и/или картинка отображается на Дэшборде некорректно",
                        "Nзменения успешно сохранены - картинка отображается на Дэшборде корректно")
        );
    }
    */
}
