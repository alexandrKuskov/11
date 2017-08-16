package eu.datatiler.tests;

import com.qatestlab.base.BaseActions;
import com.qatestlab.base.BaseTest;
import com.qatestlab.testrail.CustomStepResult;
import com.qatestlab.testrail.TestRailAssert;
import com.qatestlab.testrail.TestRailIssue;
import eu.datatiler.actions.Actions;
import eu.datatiler.pages.Pages;
import eu.datatiler.pages.project.Settings.SettingsPage;
import eu.datatiler.utils.DataProviderPool;
import org.testng.annotations.Test;

import java.text.Collator;
import java.util.Comparator;
import java.util.List;

import static eu.datatiler.pages.project.Settings.SettingsPage.metaEditorTab;

public class WeightSettings extends BaseTest {

    private final String TEST_RUN_NAME = "Project tests";
    private String folderName = "UAT";
    private String projectName = "UAT_Project";
    private String variableName = "Age category";
    private String weightSettingsTestsFolder = "UAT_WeightSettingsTests";
    private String sortVariables = "WeightSettings_SortVariables";
    private String dashboardProjectCreator = "DASHBOARDS_CREATOR";


    @TestRailIssue(issueID = 34, testRunName = TEST_RUN_NAME)
    @Test(dataProvider = DataProviderPool.PROJECT_MANAGER_CREDENTIALS, dataProviderClass = DataProviderPool.class,
            description = "Saving settings exposed scales (UAT-720)")
    public void checkWeightSettings(String login, String pass) {
        Actions.generalActions().clearSession();
        Actions.generalActions().openHomePage();
        Actions.generalActions().login(login, pass);
        Actions.generalActions().checkHomePageLoaded();

        Actions.itemActions().openProject(projectName);
        Pages.navigationBar().waitProjectSettingsButton();
        Pages.navigationBar().clickProjectSettings();

        TestRailAssert.assertTrue(
                "Settings".equals(Pages.navigationBar().getLastFolderName()) &&
                        Pages.projectPage().settingsPage().navigationTabs().isSettingsPageVisible(),
                new CustomStepResult(
                        "Настройки проекта не отображаются",
                        "Отображаются настройки проекта")
        );

        Actions.projectActions().openSettingsTab();
        Actions.projectActions().openSettingsItemByName("Weights");

        TestRailAssert.assertTrue(
                "W_Days".equals(SettingsPage.settingsTab().getDefaultWeightValue()) &&
                        SettingsPage.settingsTab().getHideOnDVIState(),
                new CustomStepResult(
                        "'Hide on DVI' чек-бокс не отображается и/или вариан дропдауна 'W_Days' не выбран",
                        "Отображается 'Hide on DVI' чек-бокс. Отображается дропдаун с 'W_Days' по-умолчанию")
        );

    }

/*

    @TestRailIssue(issueID = 330, testRunName = TEST_RUN_NAME)
    @Test(dataProvider = DataProviderPool.PROJECT_MANAGER_CREDENTIALS, dataProviderClass = DataProviderPool.class,
            description = "UAT-961")
    public void checkingSortedVariableProject(String login, String pass) {
        Actions.generalActions().clearSession();
        Actions.generalActions().openHomePage();
        Actions.generalActions().login(login, pass);
        Actions.generalActions().checkHomePageLoaded();

      //  Actions.itemActions().removeItemsIfPresent(folderName);
      //  Actions.itemActions().createFolder(folderName);
        Actions.itemActions().openItem(weightSettingsTestsFolder);
      //  Actions.itemActions().createProject(projectName);

        Actions.itemActions().selectProject(sortVariables);
        Actions.projectActions().openProjectSettings();

        SettingsPage.metaEditorTab().clickSortTool();
        SettingsPage.metaEditorTab().clickDirectSortOption();

        List<String> listVariable = SettingsPage.metaEditorTab().getVariablesProject();
       // List<String> expectedListVariable = SettingsPage.metaEditorTab().getVariablesProject();
        List<String> expectedListVariable = listVariable;

        expectedListVariable.sort(Collator.getInstance());

        TestRailAssert.assertTrue(
                listVariable.equals(expectedListVariable),
                new CustomStepResult(
                        "Переменные в таблице не выстроены в алфавитном порядке (A - Z)",
                        "Переменные в таблице выстроены в алфавитном порядке (A - Z)")
        );

        SettingsPage.metaEditorTab().clickSortTool();
        SettingsPage.metaEditorTab().clickReverseSortOption();

         listVariable = SettingsPage.metaEditorTab().getVariablesProject();
       //  expectedListVariable = SettingsPage.metaEditorTab().getVariablesProject();

         expectedListVariable.sort(Comparator.reverseOrder());

        TestRailAssert.assertTrue(
                listVariable.equals(expectedListVariable),
                new CustomStepResult(
                        "Переменные в таблице не выстроены в порядке обратном алфавитному (Z - A)",
                        "Переменные в таблице выстроены в порядке обратном алфавитному (Z - A)")
        );
    }
*/
}