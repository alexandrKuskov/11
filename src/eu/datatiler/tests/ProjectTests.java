package eu.datatiler.tests;

import com.qatestlab.base.BaseTest;
import com.qatestlab.testrail.CustomStepResult;
import com.qatestlab.testrail.TestRailAssert;
import com.qatestlab.testrail.TestRailIssue;
import eu.datatiler.actions.Actions;
import eu.datatiler.pages.project.ProjectPage;
import eu.datatiler.utils.DataProviderPool;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import static com.qatestlab.reporting.Reporter.log;


public class ProjectTests extends BaseTest {

    private final String TEST_RUN_NAME = "Project tests";

    private String folderName = "UAT";
    private String bookmarkName = "BOOK_MARK";
    private String newBookmarkName = "NEW_BOOK_MARK";
    private String genderItem = "Gender";
    private String educationItem = "Education";
    private String logicalExpressionType = "OR";
    private String projectName = "UAT_Project";
    private String projectTestsFolder = "UAT_ProjectTests";
    private String checkDropDownMenuBookmarkButton = "ProjectTests_CheckDropDownMenuBookmarkButton";
    private String savingVariableNameInCrossTabAfterRebuildingLogicalExpression = "ProjectTests_SavingVariableNameInCrossTabAfterRebuildingLogicalExpression";
    private String checkTermOfLogicalExpressionBuilder = "ProjectTests_CheckTermOfLogicalExpressionBuilder";
    private String checkingSaveBookmarkDVIProject = "ProjectTests_CheckingSaveBookmarkDVIProject";
    private String checkChangesInCreatedBookmark = "ProjectTests_CheckChangesInCreatedBookmark";
    private String checkingRemoveBookmarkDVIProject = "ProjectTests_CheckingRemoveBookmarkDVIProject";
/*
    public void createProjectInSubFolder() {
        Actions.itemActions().removeItemsIfPresent(folderName);
        Actions.itemActions().createFolder(folderName);
        Actions.itemActions().openItem(folderName);
        Actions.itemActions().createProject(projectName);
        boolean itemPresent = Actions.itemActions().isItemPresent(projectName);
        Assert.assertTrue(itemPresent, String.format("'%s' isn't present in folder %s", projectName, folderName));
    }
*/

    @TestRailIssue(issueID = 24, testRunName = TEST_RUN_NAME)
    @Test(dataProvider = DataProviderPool.PROJECT_MANAGER_CREDENTIALS, dataProviderClass = DataProviderPool.class,
            description = "Availability drop-down menu at the button Bookmark (UAT-804)")
    public void checkDropDownMenuBookmarkButton(String login, String pass) throws Exception {
        Actions.generalActions().clearSession();
        Actions.generalActions().openHomePage();
        Actions.generalActions().login(login, pass);
        Actions.generalActions().checkHomePageLoaded();

        Actions.itemActions().openItem(projectTestsFolder);
      //  createProjectInSubFolder();

        Actions.itemActions().openProject(checkDropDownMenuBookmarkButton);
        Actions.projectActions().clickResetStateButtonIfPresent();
        Actions.projectActions().createSimpleRequest(genderItem, educationItem);
        Actions.projectActions().addToBookmark(bookmarkName);

        ProjectPage.toolbarRegion().clickChartButton();
        ProjectPage.chartRegion().waitToLoad();

        TestRailAssert.assertTrue(
                ProjectPage.chartRegion().isChartContainerPresent(),
                new CustomStepResult(
                        "Дефлотный чарт проекта не отображается",
                        "Отображается дефлотный чарт проекта")
        );

        ProjectPage.toolbarRegion().clickTableButton();
        ProjectPage.toolbarRegion().waitForLoad();

        TestRailAssert.assertTrue(
                ProjectPage.chartRegion().isChartContainerPresent(),
                new CustomStepResult(
                        "Дефлотный чарт проекта не отображается",
                        "Отображается дефлотный чарт проекта")
        );

        ProjectPage.toolbarRegion().clickBookMarkerDropDownButton();
        ProjectPage.toolbarRegion().waitBookMarkerList();

        TestRailAssert.assertTrue(
                ProjectPage.toolbarRegion().isBookMarkerItemPresent(bookmarkName),
                new CustomStepResult(
                        "У кнопки 'Save state bookmark' выпадающий список не отображается и/или" +
                                " в списке не отображается ранее созданная закладка",
                        "У кнопки 'Save state bookmark' отображается выпадающий список;" +
                                " в списке есть ранее созданная закладка")
        );
        ProjectPage.toolbarRegion().clickRemoveBookMark(bookmarkName);

    }


    @TestRailIssue(issueID = 19, testRunName = TEST_RUN_NAME)
    @Test(dataProvider = DataProviderPool.PROJECT_MANAGER_CREDENTIALS, dataProviderClass = DataProviderPool.class,
            description = "Saving variable name in a cross-tab cell after rebuilding the logical expression" +
                    " in the 'Expression builder' (UAT-846)")
    public void savingVariableNameInCrossTabAfterRebuildingLogicalExpression(String login, String pass) throws Exception {
        String crossTabVariableName = "CUSTOM_NAME";

        Actions.generalActions().clearSession();
        Actions.generalActions().openHomePage();
        Actions.generalActions().login(login, pass);
        Actions.generalActions().checkHomePageLoaded();

        Actions.itemActions().openItem(projectTestsFolder);
      //  createProjectInSubFolder();

        Actions.itemActions().openProject(savingVariableNameInCrossTabAfterRebuildingLogicalExpression);
        Actions.projectActions().clickResetStateButtonIfPresent();
        Actions.projectActions().createSimpleRequest(genderItem, educationItem);
        Actions.projectActions().setSimpleLogicalExpression(genderItem, logicalExpressionType);

        ProjectPage.tableRegion().clickOnExpressionBuilderIcon();
        ProjectPage.tableRegion().waitExpressionRegion();

        String logExpression = Actions.projectActions().getLogicalExpressionFromExpressionBuilder();

        TestRailAssert.assertTrue(
                logExpression.equals(
                        Actions.projectActions().getCrossTabVariableName().replaceAll("\\s", "").toLowerCase()
                ),
                new CustomStepResult(
                        "Условие не применилось к соответствующей ячейке и/или не отображается в ней",
                        "Условие применилось к соответствующей ячейке и отображается в ней")
        );


        ProjectPage.tableRegion().clickOnExpressionBuilderButton("Save");
        ProjectPage.tableRegion().waitForExpressionBuilderInvisible();
        ProjectPage.tableRegion().clickOnVariableName();
        ProjectPage.tableRegion().setCrossTabVariableName(crossTabVariableName);

        TestRailAssert.assertTrue(
                crossTabVariableName.equals(
                        ProjectPage.tableRegion().getVariableName()
                ),
                new CustomStepResult(
                        "Nмя не отображается в поле редактирования внутри ячейки",
                        "Nмя отображается в поле редактирования внутри ячейки")
        );
        ProjectPage.tableRegion().pressEnterOnCanvasField();
/*
        Robot r = new Robot();
        r.keyPress(KeyEvent.VK_ENTER);
        r.delay(2000);
        r.keyRelease(KeyEvent.VK_ENTER);
        r.delay(2000);
*/

        boolean isFieldEditable = ProjectPage.tableRegion().isCanvasEditFieldPresent();

        TestRailAssert.assertTrue(
                (!(isFieldEditable) && crossTabVariableName.equals(Actions.projectActions().getCrossTabVariableName()))
                ,
                new CustomStepResult(
                        "Nмя переменной не изменилось и/или поле редактирования отображается",
                        "Nмя переменной изменилось, поле редактирования не отображается")
        );


        Actions.projectActions().setSimpleLogicalExpression(genderItem, logicalExpressionType, 2);

        TestRailAssert.assertTrue(
                crossTabVariableName.equals(
                        Actions.projectActions().getCrossTabVariableName()
                ),
                new CustomStepResult(
                        "Nмя не отображается в поле редактирования внутри ячейки",
                        "Nмя отображается в поле редактирования внутри ячейки")
        );
    }



    @TestRailIssue(issueID = 23, testRunName = TEST_RUN_NAME)
    @Test(dataProvider = DataProviderPool.PROJECT_MANAGER_CREDENTIALS, dataProviderClass = DataProviderPool.class,
            description = "Showing previously exposed in terms of logical expression 'Expression builder'" +
                    " for cross-tab cell after adding new (UAT-847)")
    public void checkTermOfLogicalExpressionBuilder(String login, String pass) throws Exception {
        Actions.generalActions().clearSession();
        Actions.generalActions().openHomePage();
        Actions.generalActions().login(login, pass);
        Actions.generalActions().checkHomePageLoaded();

        Actions.itemActions().openItem(projectTestsFolder);
     //   createProjectInSubFolder();

        Actions.itemActions().openProject(checkTermOfLogicalExpressionBuilder);
        Actions.projectActions().clickResetStateButtonIfPresent();
        Actions.projectActions().createSimpleRequest(genderItem, educationItem);
        Actions.projectActions().setSimpleLogicalExpression(genderItem, logicalExpressionType,1);

        ProjectPage.tableRegion().clickOnExpressionBuilderIcon();
        ProjectPage.tableRegion().waitExpressionRegion();

        ProjectPage.tableRegion().setNoExprTypeForItemByIndexInExpressionBuilder(1);

        TestRailAssert.assertTrue(
                "NOT".equals(ProjectPage.tableRegion().getExprTypeItemByIndexInExpressionBuilder(1)),
                new CustomStepResult(
                        "Галочка не заменена выражением 'NOT'",
                        "Галочка заменена выражением 'NOT'")
        );


        String logExpression = Actions.projectActions().getLogicalExpressionFromExpressionBuilder();



        ProjectPage.tableRegion().clickOnExpressionBuilderButton("Save");

        TestRailAssert.assertTrue(
                logExpression.equals(
                        Actions.projectActions().getCrossTabVariableName().replaceAll("\\s", "").toLowerCase()
                ),
                new CustomStepResult(
                        "Условие не применилось к соответствующей ячейке и/или не отображается в ней",
                        "Условие применилось к соответствующей ячейке и отображается в ней")
        );


        Actions.projectActions().setSimpleLogicalExpression(genderItem, logicalExpressionType, 2);
        ProjectPage.tableRegion().clickOnExpressionBuilderIconTest();



        ProjectPage.tableRegion().clickOnExpressionBuilderIcon();
        ProjectPage.tableRegion().waitExpressionRegion();

        logExpression = Actions.projectActions().getLogicalExpressionFromExpressionBuilder();


        ProjectPage.tableRegion().clickOnExpressionBuilderButton("Save");

        TestRailAssert.assertTrue(
                logExpression.equals(
                        Actions.projectActions().getCrossTabVariableName().replaceAll("\\s", "").toLowerCase()
                ),
                new CustomStepResult(
                        "Условие не применилось к соответствующей ячейке и/или не отображается в ней",
                        "Условие применилось к соответствующей ячейке и отображается в ней")
        );


        ProjectPage.tableRegion().clickOnExpressionBuilderIcon();
        ProjectPage.tableRegion().waitExpressionRegion();

        TestRailAssert.assertTrue(
                (logExpression.equals(Actions.projectActions().getLogicalExpressionFromExpressionBuilder())) &&
                        ProjectPage.tableRegion().isExpressionRegionVisible(),
                new CustomStepResult(
                        "Диалоговое окно 'Expression builder' с ранее построенным" +
                                " логическим выражением не отображается",
                        "Отображается диалоговое окно 'Expression builder' с ранее построенным логическим выражением")
        );

    }


    @TestRailIssue(issueID = 21, testRunName = TEST_RUN_NAME)
    @Test(dataProvider = DataProviderPool.PROJECT_MANAGER_CREDENTIALS, dataProviderClass = DataProviderPool.class,
            description = "Checking save the bookmark in DVI Project (UAT-851)")
    public void checkingSaveBookmarkDVIProject(String login, String pass) throws Exception {
        Actions.generalActions().clearSession();
        Actions.generalActions().openHomePage();
        Actions.generalActions().login(login, pass);
        Actions.generalActions().checkHomePageLoaded();

        Actions.itemActions().openItem(projectTestsFolder);
      //  createProjectInSubFolder();


        Actions.itemActions().openProject(checkingSaveBookmarkDVIProject);
        Actions.projectActions().clickResetStateButtonIfPresent();
        Actions.projectActions().createSimpleRequest(genderItem, educationItem);

        Actions.projectActions().addToBookmark(bookmarkName);
        Actions.projectActions().removeBookmark(bookmarkName);

    }



    @TestRailIssue(issueID = 25, testRunName = TEST_RUN_NAME)
    @Test(dataProvider = DataProviderPool.PROJECT_MANAGER_CREDENTIALS, dataProviderClass = DataProviderPool.class,
            description = "Check the changes in the current tab to DVI Project (UAT-852)")
    public void checkChangesInCreatedBookmark(String login, String pass) throws Exception {
        Actions.generalActions().clearSession();
        Actions.generalActions().openHomePage();
        Actions.generalActions().login(login, pass);
        Actions.generalActions().checkHomePageLoaded();

        Actions.itemActions().openItem(projectTestsFolder);
      //  createProjectInSubFolder();

        Actions.itemActions().openProject(checkChangesInCreatedBookmark);
        Actions.projectActions().clickResetStateButtonIfPresent();
        Actions.projectActions().createSimpleRequest(genderItem, educationItem);
        Actions.projectActions().addToBookmark(bookmarkName);

        Actions.projectActions().setFilter(genderItem);

        ProjectPage.toolbarRegion().clickBookMarkerDropDownButton();
        ProjectPage.toolbarRegion().waitBookMarkerList();

        TestRailAssert.assertTrue(
                ProjectPage.toolbarRegion().isBookMarkerItemPresent(bookmarkName),
                new CustomStepResult(
                        "Созданая ранее закладка не отображается в выпадающем списке",
                        "Созданая ранее закладка отображается в выпадающем списке")
        );
        ProjectPage.toolbarRegion().clickBookMarkerDropDownButton();

        String filterValue = ProjectPage.tableRegion().getFilterValue();

        Actions.projectActions().saveAsBookmark(bookmarkName, newBookmarkName);

        ProjectPage.tableRegion().clickResetFilterButton();

        TestRailAssert.assertTrue(
                ProjectPage.tableRegion().isFilterBodyFieldPresent(),
                new CustomStepResult(
                        "Поле 'Filter' не отображается пустым",
                        "Поле 'Filter' отображается пустым")
        );

        ProjectPage.toolbarRegion().clickBookMarkerDropDownButton();
        ProjectPage.toolbarRegion().waitBookMarkerList();

        TestRailAssert.assertTrue(
                ProjectPage.toolbarRegion().isBookMarkerItemPresent(newBookmarkName),
                new CustomStepResult(
                        "Созданая ранее закладка не отображается в выпадающем списке",
                        "Созданая ранее закладка отображается в выпадающем списке")
        );


        ProjectPage.toolbarRegion().clickBookMarkerItemByName(newBookmarkName);

        TestRailAssert.assertTrue(
                filterValue.equals(ProjectPage.tableRegion().getFilterValue()),
                new CustomStepResult(
                        "Nмя переменной не отображается в поле 'Filter'",
                        "Nмя переменной отображается в поле 'Filter'")
        );
        Actions.projectActions().removeBookmark(newBookmarkName);
    }



    @TestRailIssue(issueID = 22, testRunName = TEST_RUN_NAME)
    @Test(dataProvider = DataProviderPool.PROJECT_MANAGER_CREDENTIALS, dataProviderClass = DataProviderPool.class,
            description = "Checking delete an existing bookmark in DVI Project (UAT-854)")
    public void checkingRemoveBookmarkDVIProject(String login, String pass) throws Exception {
        Actions.generalActions().clearSession();
        Actions.generalActions().openHomePage();
        Actions.generalActions().login(login, pass);
        Actions.generalActions().checkHomePageLoaded();

        Actions.itemActions().openItem(projectTestsFolder);
       // createProjectInSubFolder();

        Actions.itemActions().openProject(checkingRemoveBookmarkDVIProject);
        Actions.projectActions().clickResetStateButtonIfPresent();
        Actions.projectActions().createSimpleRequest(genderItem, educationItem);
        Actions.projectActions().addToBookmark(bookmarkName);
        Actions.projectActions().removeBookmark(bookmarkName);

    }

}
