package eu.datatiler.tests;

import com.qatestlab.base.BaseTest;
import com.qatestlab.testrail.CustomStepResult;
import com.qatestlab.testrail.TestRailAssert;
import com.qatestlab.testrail.TestRailIssue;
import eu.datatiler.actions.Actions;
import eu.datatiler.pages.Pages;
import eu.datatiler.pages.project.ProjectPage;
import eu.datatiler.utils.DataProviderPool;
import org.testng.annotations.Test;

public class CrosstableTests extends BaseTest {

    private String folderName = "UAT";
    private String bookmarkName = "BOOK_MARK";
    private String projectName = "UAT_Project";
    private String genderItem = "Gender";
    private String educationItem = "Education";
    private String dviItem = "DVI";
    private String contextItem = "Context";
    private String mayorItem = "Mayor";
    private String contextRows = "Rows";
    private String contextColumns = "Columns";
    private String firstAwareness = "AWARENESS_1";
    private String secondAwareness = "AWARENESS_2";
    private String thirdAwareness = "AWARENESS_3";
    private String maleFemaleState = "male_female";
    private String crosstableTestsFolder = "UAT_CrosstableTests";
    private String ensureCategoriesLoaded = "CrosstableTests_EnsureCategoriesLoadedTest";
    private String buildContextCrossTab = "CrosstableTests_BuildContextCrossTab";
    private String clickCheckingAfterCellNameChange = "CrosstableTests_ClickCheckingAfterCellNameChange";
    private String changeSerialNameOnChartPage = "CrosstableTests_ChangeSerialNameOnChartPage";
    private String checkingForSavingBookmarkInProjectDVI = "CrosstableTests_CheckingForSavingBookmarkInProjectDVI";


    private final String TEST_RUN_NAME = "Crosstab tests";


    @TestRailIssue(issueID = 35, testRunName = TEST_RUN_NAME)
    @Test(dataProvider = DataProviderPool.PROJECT_MANAGER_CREDENTIALS, dataProviderClass = DataProviderPool.class,
            description = "Ensure that categories are loaded for variables on DVI (UAT-396)")
    public void ensureCategoriesLoadedTest(String login, String password) {
        Actions.generalActions().clearSession();
        Actions.generalActions().openHomePage();
        Actions.generalActions().login(login, password);
        Actions.generalActions().checkHomePageLoaded();

        //  Actions.itemActions().removeItemsIfPresent(folderName);
        //  Actions.itemActions().createFolder(folderName);
        //  Actions.itemActions().openItem(folderName);
        Actions.itemActions().openItem(crosstableTestsFolder);


        //  Actions.itemActions().createProject(projectName);

        Actions.itemActions().openProject(ensureCategoriesLoaded);

        Actions.projectActions().openCategory("Education");

        Pages.projectPage().categoryRegion().waitForItemsRegionVisibility();

        TestRailAssert.assertTrue(
                Pages.projectPage().categoryRegion().isItemPresent("Grad School") &&
                        Pages.projectPage().categoryRegion().isItemPresent("Spednem Education") &&
                        Pages.projectPage().categoryRegion().isItemPresent("High Education") &&
                        Pages.projectPage().categoryRegion().isItemPresent("Difficult to answer"),
                new CustomStepResult(
                        "Категории:*Grad School*Spednem Education*High Education*Difficult to answer не отображаются",
                        "Пользователь видит категории:*Grad School*Spednem Education*High Education*Difficult to answer")
        );

        Actions.projectActions().openCategory("Gender");

        Pages.projectPage().categoryRegion().waitForItemsRegionVisibility();

        Actions.generalActions().wait(3);

        TestRailAssert.assertTrue(
                Pages.projectPage().categoryRegion().isItemPresent("male") &&
                        Pages.projectPage().categoryRegion().isItemPresent("female"),
                new CustomStepResult(
                        "Категории:*Male*Female не отображаются",
                        "Пользователь видит категории:*Male*Female")
        );
    }


    @TestRailIssue(issueID = 713, testRunName = TEST_RUN_NAME)
    @Test(dataProvider = DataProviderPool.PROJECT_MANAGER_CREDENTIALS, dataProviderClass = DataProviderPool.class,
            description = "Построение контекстного кросстаба")
    public void buildContextCrossTab(String login, String password) throws Exception {
        Actions.generalActions().clearSession();
        Actions.generalActions().openHomePage();
        Actions.generalActions().login(login, password);
        Actions.generalActions().checkHomePageLoaded();

        //  Actions.itemActions().removeItemsIfPresent(folderName);
        //  Actions.itemActions().createFolder(folderName);
        //  Actions.itemActions().openItem(folderName);
        Actions.itemActions().openItem(crosstableTestsFolder);
        //  Actions.itemActions().createProject(projectName);

        Actions.itemActions().openProject(buildContextCrossTab);

        Pages.navigationBar().waitProjectSettingsButton();
        Pages.navigationBar().clickProjectSettings();

        Actions.projectActions().openSettingsTab();
        Actions.projectActions().openSettingsItemByName("Context");

        Pages.projectPage().settingsPage().settingsTab().setStateHideOnDVIByItemName("Context", false);

        Actions.generalActions().openMainPage();

        Actions.itemActions().openItem(crosstableTestsFolder);
        Actions.itemActions().openProject(buildContextCrossTab);
        Actions.projectActions().clickResetStateButtonIfPresent();
        Pages.projectPage().categoryRegion().setContextByName("Rows");

        TestRailAssert.assertTrue(
                Actions.projectActions().checkDataDViColumns("emptyColumns"),
                new CustomStepResult(
                        "Кросстаб не очищен.",
                        "Кросстаб очищен.")
        );

        Pages.navigationBar().waitLoader();
        Pages.projectPage().categoryRegion().fillColumns(educationItem);

        TestRailAssert.assertTrue(
                Actions.projectActions().checkDataDViColumns("emptyColumns"),
                new CustomStepResult(
                        "Перетащить категорию в поле Columns при выделенном контроле Rows возможно",
                        "Перетащить категорию в поле Columns при выделенном контроле Rows невозможно")
        );

        Actions.itemActions().wait(2);
        Pages.projectPage().categoryRegion().fillRows(genderItem);

        TestRailAssert.assertTrue(
                Actions.projectActions().checkDataDViColumns("genderRowContext"),
                new CustomStepResult(
                        "Переменные не добавлены в поле Rows",
                        "Переменные успешно добавлены в поле Rows")
        );

        Pages.navigationBar().waitLoader();
        Pages.projectPage().toolbarRegion().clickCalculateButton();
        Actions.itemActions().wait(2);


        Pages.projectPage().categoryRegion().setContextByName("Columns");

        TestRailAssert.assertTrue(
                Actions.projectActions().checkDataDViColumns("emptyColumns"),
                new CustomStepResult(
                        "Кросстаб не очищен.",
                        "Кросстаб очищен.")
        );

        Actions.itemActions().wait(2);
        Pages.projectPage().categoryRegion().fillRows(genderItem);

        TestRailAssert.assertTrue(
                Actions.projectActions().checkDataDViColumns("emptyColumns"),
                new CustomStepResult(
                        "Перетащить категорию в поле Rows при выделенном контроле Columns возможно",
                        "Перетащить категорию в поле Rows при выделенном контроле Columns невозможно")
        );

        Actions.itemActions().wait(2);
        Pages.projectPage().categoryRegion().fillColumns(genderItem);

        TestRailAssert.assertTrue(
                Actions.projectActions().checkDataDViColumns("genderColumnContext"),
                new CustomStepResult(
                        "Переменные не добавлены в поле Columns",
                        "Переменные успешно добавлены в поле Columns")
        );



        Pages.navigationBar().waitLoader();
        Pages.projectPage().toolbarRegion().clickCalculateButton();
        Actions.itemActions().wait(2);

    }



    @TestRailIssue(issueID = 756, testRunName = TEST_RUN_NAME)
    @Test(dataProvider = DataProviderPool.PROJECT_MANAGER_CREDENTIALS, dataProviderClass = DataProviderPool.class,
            description = "Проверка кликабельности DVI после сохранения/отмены нового имени для ячейки")
    public void clickCheckingAfterCellNameChange(String login, String password) throws Exception {
        Actions.generalActions().clearSession();
        Actions.generalActions().openHomePage();
        Actions.generalActions().login(login, password);
        Actions.generalActions().checkHomePageLoaded();

        //  Actions.itemActions().removeItemsIfPresent(folderName);
        // Actions.itemActions().createFolder(folderName);
        Actions.itemActions().openItem(crosstableTestsFolder);
        // Actions.itemActions().createProject(projectName);

        Actions.itemActions().openProject(clickCheckingAfterCellNameChange);
        Actions.projectActions().clickResetStateButtonIfPresent();
        Actions.projectActions().createSimpleRequest(genderItem, educationItem);
        ProjectPage.tableRegion().clickOnVariableName();

        TestRailAssert.assertTrue(
                ProjectPage.tableRegion().getVariableName().equals("female"),
                new CustomStepResult(
                        "поле для редактированиея/изменения отличается от слова 'female'",
                        "поле для редактированиея/изменения названия эквивалентно 'female'")
        );


        TestRailAssert.assertTrue(
                ProjectPage.tableRegion().isCanvasEditFieldPresent(),
                new CustomStepResult(
                        "поле для редактированиея/изменения названия ячейки не отображается",
                        "поле для редактированиея/изменения названия ячейки отображается")
        );

        ProjectPage.tableRegion().setCrossTabVariableName("test");
        // Actions.projectActions().clickEscape();
        ProjectPage.tableRegion().pressEscapeOnVariableName();
        ProjectPage.tableRegion().clickOnVariableName();



        TestRailAssert.assertTrue(
                ProjectPage.tableRegion().getVariableName().equals("female"),
                new CustomStepResult(
                        "поле для редактированиея/изменения названия эквивалентно 'test'",
                        "поле для редактированиея/изменения названия эквивалентно 'female'")
        );
        Actions.projectActions().clickEscape();
        ProjectPage.tableRegion().clickOnVariableName();
        ProjectPage.tableRegion().setCrossTabVariableName("newValue");
        // Actions.projectActions().clickEnter();
        ProjectPage.tableRegion().pressEnterOnCanvasField();


        ProjectPage.tableRegion().clickOnVariableName();
        TestRailAssert.assertTrue(
                ProjectPage.tableRegion().getVariableName().equals("newValue"),
                new CustomStepResult(
                        "поле для редактированиея/изменения названия эквивалентно 'female'",
                        "поле для редактированиея/изменения названия эквивалентно 'newValue'")
        );

        ProjectPage.tableRegion().pressEnterOnCanvasField();
    }



    @TestRailIssue(issueID = 754, testRunName = TEST_RUN_NAME)
    @Test(dataProvider = DataProviderPool.PROJECT_MANAGER_CREDENTIALS, dataProviderClass = DataProviderPool.class,
            description = "Редактирование имени серии на 'Chart' странице")
    public void changeSerialNameOnChartPage(String login, String password) throws Exception {
        Actions.generalActions().clearSession();
        Actions.generalActions().openHomePage();
        Actions.generalActions().login(login, password);
        Actions.generalActions().checkHomePageLoaded();


        //  Actions.itemActions().removeItemsIfPresent(folderName);
        //  Actions.itemActions().createFolder(folderName);
        Actions.itemActions().openItem(crosstableTestsFolder);
        //  Actions.itemActions().createProject(projectName);

        Actions.itemActions().openProject(changeSerialNameOnChartPage);
        Actions.projectActions().clickResetStateButtonIfPresent();

        TestRailAssert.assertTrue(
                ProjectPage.tableRegion().isChartDisabled(),
                new CustomStepResult(
                        "кнопка 'chart' актива",
                        "кнопка 'chart' не актива")
        );

        Actions.projectActions().createSimpleRequest(genderItem, educationItem);

        TestRailAssert.assertTrue(
                ProjectPage.tableRegion().isChartActive(),
                new CustomStepResult(
                        "кнопка 'chart' не актива",
                        "кнопка 'chart' актива")
        );

        ProjectPage.tableRegion().clickChartButton();


        TestRailAssert.assertTrue(
                ProjectPage.tableRegion().isDesignChartPageOpen(),
                new CustomStepResult(
                        "страница дизайнера чартов  не открыта",
                        "страница дизайнера чартов открыта")
        );

        ProjectPage.chartRegion().waitToLoad();

        TestRailAssert.assertTrue(
                ProjectPage.tableRegion().isGradSchoolChartAndItemPresent() &&
                        ProjectPage.tableRegion().isHighEducationChartAndItemPresent() &&
                        ProjectPage.tableRegion().isSpednemEducationChartAndItemPresent() &&
                        ProjectPage.tableRegion().isDifficultToAnswerChartAndItemPresent(),
                new CustomStepResult(
                        "Построеный чарт не соответствует значениеям в кросс-табе",
                        "Построен дефолтный чарт согласно значений в кросс-табе")
        );

        TestRailAssert.assertTrue(
                ProjectPage.tableRegion().isChartDefaultChose(),
                new CustomStepResult(
                        "кнопка 'chart' не выбрана по умолчанию",
                        "кнопка 'chart' выбрана по умолчанию")
        );

        ProjectPage.tableRegion().clickSeriesButton();

        TestRailAssert.assertTrue(
                ProjectPage.tableRegion().isSeriesGroupNamePresent("Default"),
                new CustomStepResult(
                        "В левом сайд баре отображаеться дефолтная группа серий с именем отличным от 'Default' ",
                        "В левом сайд баре отображаеться дефолтная группа серий с именем 'Default'")
        );

        TestRailAssert.assertTrue(
                ProjectPage.tableRegion().isSeriesGroupItemPresent("male") &&
                        ProjectPage.tableRegion().isSeriesGroupItemPresent("female"),
                new CustomStepResult(
                        "название серии не совпадает с ожидаемым",
                        "название серии совпадает с ожидаемым")
        );

        TestRailAssert.assertTrue(
                ProjectPage.tableRegion().isButtonPresent("Add group") &&
                        ProjectPage.tableRegion().isButtonPresent("Reset"),
                new CustomStepResult(
                        "кнопки 'Add group' и 'Reset' отсутствуют",
                        "кнопки 'Add group' и 'Reset' присутствуют")
        );

        ProjectPage.tableRegion().editSeriesName("male", "testName");
        // Actions.projectActions().clickEnter();
        ProjectPage.tableRegion().pressEnterOnChartSerialName("testName");

        TestRailAssert.assertTrue(
                ProjectPage.tableRegion().isSeriesGroupItemPresent("testName"),
                new CustomStepResult(
                        "название серии не совпадает с ожидаемым",
                        "название серии совпадает с ожидаемым")
        );

        ProjectPage.tableRegion().clickOnSomePlace();

        TestRailAssert.assertTrue(
                ProjectPage.tableRegion().isSeriesGroupItemPresent("testName"),
                new CustomStepResult(
                        "название серии не совпадает с ожидаемым",
                        "название серии совпадает с ожидаемым")
        );
    }



    @TestRailIssue(issueID = 780, testRunName = TEST_RUN_NAME)
    @Test(dataProvider = DataProviderPool.PROJECT_MANAGER_CREDENTIALS, dataProviderClass = DataProviderPool.class,
            description = "Построение 'Context' запросов на DVI")
    public void createContextRequestsForDVI(String login, String password) throws Exception {
        Actions.generalActions().clearSession();
        Actions.generalActions().openHomePage();
        Actions.generalActions().login(login, password);
        Actions.generalActions().checkHomePageLoaded();
        Actions.itemActions().openItem(dviItem);

        TestRailAssert.assertTrue(
                Actions.itemActions().checkForItem(contextItem) &&
                        Actions.itemActions().checkForItem(mayorItem),
                new CustomStepResult(
                        "проект Mayor/Context не отображается",
                        "Отображается проект Mayor/Context")
        );

        Actions.itemActions().openProject(contextItem);
        Actions.itemActions().refreshCrosstabData(contextColumns);
        Actions.itemActions().clickOnContextButton(contextRows);
        Actions.projectActions().createSimpleSingleRequest(firstAwareness);
        Actions.itemActions().clickOnContextButton(contextColumns);
        Actions.projectActions().createSimpleSingleRequest(thirdAwareness);
    }



    @TestRailIssue(issueID = 21, testRunName = TEST_RUN_NAME)
    @Test(dataProvider = DataProviderPool.PROJECT_MANAGER_CREDENTIALS, dataProviderClass = DataProviderPool.class,
            description = "Проверка сохранения закладки в DVI проекта")
    public void checkingForSavingBookmarkInProjectDVI(String login, String password) throws Exception {
        Actions.generalActions().clearSession();
        Actions.generalActions().openHomePage();
        Actions.generalActions().login(login, password);
        Actions.generalActions().checkHomePageLoaded();
        Actions.itemActions().openItem(dviItem);

        TestRailAssert.assertTrue(
                Actions.itemActions().checkForItem(contextItem) &&
                        Actions.itemActions().checkForItem(mayorItem),
                new CustomStepResult(
                        "проект Mayor/Context не отображается",
                        "Отображается проект Mayor/Context")
        );
        Actions.itemActions().openProject(checkingForSavingBookmarkInProjectDVI);
        Actions.projectActions().clickResetStateButtonIfPresent();
        Actions.projectActions().createSimpleRequest(genderItem, educationItem);
        Actions.projectActions().addToBookmark(bookmarkName);
        Actions.projectActions().clickResetStateButton();

        TestRailAssert.assertTrue(
                Actions.projectActions().checkDataDViColumns("emptyColumns"),
                new CustomStepResult(
                        "Кросстаб не очищен.",
                        "Кросстаб очищен.")
        );
        Actions.projectActions().selectBookMarkAndRestoreCrosstab(bookmarkName);
        TestRailAssert.assertTrue(
                Actions.projectActions().checkDataDViRows(maleFemaleState),
                new CustomStepResult(
                        "Кросстаб не заполнен",
                        "Кросстаб успешно заполнен")
        );
    }
/*
        @TestRailIssue(issueID = 779, testRunName = TEST_RUN_NAME)
        @Test(dataProvider = DataProviderPool.PROJECT_MANAGER_CREDENTIALS, dataProviderClass = DataProviderPool.class,
                description = "Экспорт кросс-таба в pptx/xlsx")
        public void exportCrosstabToPPTX (String login, String password) throws Exception {
            Actions.generalActions().clearSession();
            Actions.generalActions().openHomePage();
            Actions.generalActions().login(login, password);
            Actions.generalActions().checkHomePageLoaded();
            Actions.itemActions().openItem(dviItem);

            TestRailAssert.assertTrue(
                    Actions.itemActions().checkForItem(contextItem) &&
                            Actions.itemActions().checkForItem(mayorItem),
                    new CustomStepResult(
                            "проект Mayor/Context не отображается",
                            "Отображается проект Mayor/Context")
            );

            Actions.itemActions().openProject(mayorItem);
            Actions.projectActions().createSimpleRequest(genderItem, educationItem);
        }
        */
    }
