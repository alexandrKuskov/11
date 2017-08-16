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

public class SettingsTests extends BaseTest {

    private String folderName = "UAT";
    private String projectName = "UAT_Project";
    private String varName = "Age and gender category";
    private String familyItem = "Familty memebers";
    private String infantsItem = "Infants under 16 y.o.";
    private String dayOfMonthItem = "Day of month";
    private String monthItem = "Month";
    private String iconMR = "MR";
    private String myGroupItem = "My_Group";
    private String categorialType = "Categorial";
    private String dichotomyType = "Dichotomy";
    private String createButton = "Create";
    private String groupNameField  = "MR-group name";
    private String intervalNameField  = "Interval";
    private String interval  = "<=10";
    private String dichotomyNumericGroup = "DICH.NUM";
    private String categorialGroup = "CAT";
    private String dichotomyCategorialGroup = "DICH.CAT";
    private String dichotomyTypeItems[] = new String[]{"1","2","3","4","5 or more"};
    private String settingsTestsFolder = "UAT_SettingsTests";
    private String accessAdminPageTest = "SettingsTests_AccessAdminPageTest";
    private String createCategorialMRSetTest = "SettingsTests_CreateCategorialMRSetTest";
    private String createDichotometricalMRSetTest = "SettingsTests_CreateDichotometricalMRSetTest";
    private String createNumericDichotometricalMRSetTest = "SettingsTests_CreateNumericDichotometricalMRSetTest";


    private final String TEST_RUN_NAME = "Settings test";


        @TestRailIssue(issueID = 714, testRunName = TEST_RUN_NAME)
        @Test(dataProvider = DataProviderPool.PROJECT_MANAGER_CREDENTIALS, dataProviderClass = DataProviderPool.class,
                description = "Возможность выбора переменной для ротации в настройках проекта")
        public void accessAdminPageTest(String login, String pass) throws Exception {

            Actions.generalActions().clearSession();
            Actions.generalActions().openHomePage();
            Actions.generalActions().login(login, pass);
            Actions.generalActions().checkHomePageLoaded();

          //  Actions.itemActions().removeItemsIfPresent(folderName);
          //  Actions.itemActions().createFolder(folderName);
            Actions.itemActions().openItem(settingsTestsFolder);
          //  Actions.itemActions().createProject(projectName);

            Actions.itemActions().selectProject(accessAdminPageTest);
            Actions.projectActions().openProjectSettings();
            Actions.projectActions().openSettingsTab();

            SettingsPage.settingsTab().setStateRotatedItem();

            TestRailAssert.assertTrue(
                    SettingsPage.settingsTab().isRotateItemCheckboxChecked() &&
                    SettingsPage.settingsTab().isRotatedFieldVisible(),
                    new CustomStepResult(
                            "Чек-бокс не отмечен и/или под чек-боксом не отображается поле для ввода",
                            "Чек-бокс отмечен. Под чек-боксом отображается поле для ввода"
                    )
            );
            SettingsPage.settingsTab().clickToRotatedField();

            TestRailAssert.assertTrue(SettingsPage.settingsTab().isRotatedPopUpWindowPresent(), new CustomStepResult(
                    "Выпадающий список с именами переменных для данного проекта не отобрахается",
                    "Выпадающий список с именами переменных для данного проекта отображается"
            ));

            SettingsPage.settingsTab().setVariableToRotatedField(varName);

            Actions.generalActions().wait(3);
            Actions.generalActions().wait(3);

            boolean isRotatedVariableRelevant = true;

            for (String str: SettingsPage.settingsTab().getRelevantRotatedVariables()) {
                isRotatedVariableRelevant = str.toLowerCase().contains(varName.substring(0, 4).toLowerCase());
            }

            TestRailAssert.assertTrue(
                    isRotatedVariableRelevant,
                    new CustomStepResult(
                            "Выпадающий список с именами переменных для данного проекта не является релевантным",
                            "Отображается выпадающий список с релевантными результатами имен переменных для данного проекта"
                    )
            );

            SettingsPage.settingsTab().clickRotatedVarByName(varName);

            TestRailAssert.assertTrue(
                    varName.equals(SettingsPage.settingsTab().getSetRotatedVar()),
                    new CustomStepResult(
                            "Кликнутая переменная не отображается в поле под чек-боксом 'Enable rotating'",
                            "Кликнутая переменная отображается в поле под чек-боксом 'Enable rotating'"
                    )
            );

           Pages.navigationBar().waitProgressBar();

            Actions.generalActions().refreshPage();


            Actions.generalActions().wait(3);

            TestRailAssert.assertTrue(
                    SettingsPage.settingsTab().isRotateItemCheckboxChecked() &&
                    varName.equals(SettingsPage.settingsTab().getSetRotatedVar()),
                    new CustomStepResult(
                            "Чек-бокс 'Enable rotating' не отмечен и/или " +
                                    "выбраная переменная не отображается в поле под чек-боксом 'Enable rotating'",
                            "Чек-бокс 'Enable rotating' отмечен. " +
                                    "Выбраная переменная отображается в поле под чек-боксом 'Enable rotating'"
                    )
            );
        }



            @TestRailIssue(issueID = 757, testRunName = TEST_RUN_NAME)
            @Test(dataProvider = DataProviderPool.PROJECT_MANAGER_CREDENTIALS, dataProviderClass = DataProviderPool.class,
                    description = "Создание категориального mrset")
            public void createCategorialMRSetTest(String login, String pass) throws Exception {
                Actions.generalActions().clearSession();
                Actions.generalActions().openHomePage();
                Actions.generalActions().login(login, pass);
                Actions.generalActions().checkHomePageLoaded();

             //   Actions.itemActions().removeItemsIfPresent(folderName);
             //   Actions.itemActions().createFolder(folderName);
                Actions.itemActions().openItem(settingsTestsFolder);
             //   Actions.itemActions().createProject(projectName);

                Actions.itemActions().selectProject(createCategorialMRSetTest);
                Actions.projectActions().openProjectSettings();
                BaseActions.wait(3);

                TestRailAssert.assertTrue(
                        SettingsPage.settingsTab().isItemsListPresent(),
                        new CustomStepResult(
                                "Список переменных проекта не отображается",
                                "Список переменных проекта успешно отображается"
                        )
                );
                 Actions.projectActions().checkForItem(familyItem, infantsItem);
                 SettingsPage.settingsTab().selectItemsWithActions(familyItem,infantsItem);


                TestRailAssert.assertTrue(
                        SettingsPage.settingsTab().isIconPresent(iconMR),
                        new CustomStepResult(
                                "Кнопка MR не отображается",
                                "Кнопка MR видна и отображается"
                        )
                );
                BaseActions.wait(3);
                SettingsPage.settingsTab().clickIcon(iconMR);
                BaseActions.wait(5);
                TestRailAssert.assertTrue(
                        SettingsPage.settingsTab().isMRPopUpWindowPresent(),
                        new CustomStepResult(
                                "Поп-ап для создание дихотомической/категориальной мр группы не отображается",
                                "Отображается поп-ап для создание дихотомической/категориальной мр группы"
                        )
                );
                SettingsPage.settingsTab().inputTextOnPopUpField(myGroupItem, groupNameField);
                SettingsPage.settingsTab().selectControlType(categorialType);
                SettingsPage.settingsTab().selectOption(createButton);
                SettingsPage.settingsTab().waitForItemsList();

                Actions.projectActions().checkMRItem(familyItem);
                Actions.projectActions().checkMRItem(infantsItem);
                Actions.projectActions().checkMRGroup(myGroupItem, categorialGroup);
                Actions.projectActions().deleteMRGroup(myGroupItem);


            }



    @TestRailIssue(issueID = 758, testRunName = TEST_RUN_NAME)
    @Test(dataProvider = DataProviderPool.PROJECT_MANAGER_CREDENTIALS, dataProviderClass = DataProviderPool.class,
            description = "Создание дихотомического mrset")
    public void createDichotometricalMRSetTest(String login, String pass) throws Exception {
        Actions.generalActions().clearSession();
        Actions.generalActions().openHomePage();
        Actions.generalActions().login(login, pass);
        Actions.generalActions().checkHomePageLoaded();

      //  Actions.itemActions().removeItemsIfPresent(folderName);
      //  Actions.itemActions().createFolder(folderName);
        Actions.itemActions().openItem(settingsTestsFolder);
      //  Actions.itemActions().createProject(projectName);

        Actions.itemActions().selectProject(createDichotometricalMRSetTest);
        Actions.projectActions().openProjectSettings();
        BaseActions.wait(3);

        TestRailAssert.assertTrue(
                SettingsPage.settingsTab().isItemsListPresent(),
                new CustomStepResult(
                        "Список переменных проекта не отображается",
                        "Список переменных проекта успешно отображается"
                )
        );
        Actions.projectActions().checkForItem(familyItem, infantsItem);
        SettingsPage.settingsTab().selectItemsWithActions(familyItem, infantsItem);
        TestRailAssert.assertTrue(
                SettingsPage.settingsTab().isIconPresent(iconMR),
                new CustomStepResult(
                        "Кнопка MR не отображается",
                        "Кнопка MR видна и отображается"
                )
        );
        BaseActions.wait(3);
        SettingsPage.settingsTab().clickIcon(iconMR);
        BaseActions.wait(5);
        TestRailAssert.assertTrue(
                SettingsPage.settingsTab().isMRPopUpWindowPresent(),
                new CustomStepResult(
                        "Поп-ап для создание дихотомической/категориальной мр группы не отображается",
                        "Отображается поп-ап для создание дихотомической/категориальной мр группы"
                )
        );
        SettingsPage.settingsTab().inputTextOnPopUpField(myGroupItem,groupNameField);
        SettingsPage.settingsTab().selectControlType(dichotomyType);
        SettingsPage.settingsTab().selectDichotomyTypeItems(dichotomyTypeItems);
        SettingsPage.settingsTab().selectOption(createButton);
        SettingsPage.settingsTab().waitForItemsList();

        Actions.projectActions().checkMRItem(familyItem);
        Actions.projectActions().checkMRItem(infantsItem);
        Actions.projectActions().checkMRGroup(myGroupItem, dichotomyCategorialGroup);
        SettingsPage.settingsTab().clickGroup(myGroupItem);

        TestRailAssert.assertTrue(
                SettingsPage.settingsTab().isGroupItemListPresent(),
                new CustomStepResult(
                        "блок состовляющих группы не отображается",
                        "блок состовляющих группы успешно отображается"
                )
        );
        Actions.projectActions().checkGroupBlockItem(familyItem);
        Actions.projectActions().checkGroupBlockItem(infantsItem);
        Actions.projectActions().deleteMRGroup(myGroupItem);
    }


    @TestRailIssue(issueID = 759, testRunName = TEST_RUN_NAME)
    @Test(dataProvider = DataProviderPool.PROJECT_MANAGER_CREDENTIALS, dataProviderClass = DataProviderPool.class,
            description = "Создание дихотомического numeric mrset")
    public void createNumericDichotometricalMRSetTest(String login, String pass) throws Exception {
        Actions.generalActions().clearSession();
        Actions.generalActions().openHomePage();
        Actions.generalActions().login(login, pass);
        Actions.generalActions().checkHomePageLoaded();

      //  Actions.itemActions().removeItemsIfPresent(folderName);
      //  Actions.itemActions().createFolder(folderName);
        Actions.itemActions().openItem(settingsTestsFolder);
      //  Actions.itemActions().createProject(projectName);

        Actions.itemActions().selectProject(createNumericDichotometricalMRSetTest);
        Actions.projectActions().openProjectSettings();
        BaseActions.wait(5);

        TestRailAssert.assertTrue(
                SettingsPage.settingsTab().isItemsListPresent(),
                new CustomStepResult(
                        "Список переменных проекта не отображается",
                        "Список переменных проекта успешно отображается"
                )
        );
        Actions.projectActions().checkForItem(dayOfMonthItem, monthItem);
        SettingsPage.settingsTab().selectItemsWithActions(dayOfMonthItem, monthItem);
        TestRailAssert.assertTrue(
                SettingsPage.settingsTab().isIconPresent(iconMR),
                new CustomStepResult(
                        "Кнопка MR не отображается",
                        "Кнопка MR видна и отображается"
                )
        );
        BaseActions.wait(3);
        SettingsPage.settingsTab().clickIcon(iconMR);
        BaseActions.wait(5);
        TestRailAssert.assertTrue(
                SettingsPage.settingsTab().isMRPopUpWindowPresent(),
                new CustomStepResult(
                        "Поп-ап для создание дихотомической/категориальной мр группы не отображается",
                        "Отображается поп-ап для создание дихотомической/категориальной мр группы"
                )
        );
        SettingsPage.settingsTab().inputTextOnPopUpField(myGroupItem, groupNameField);
        SettingsPage.settingsTab().inputTextOnPopUpField(interval, intervalNameField);
        SettingsPage.settingsTab().selectControlType(dichotomyType);
        SettingsPage.settingsTab().selectOption(createButton);
        SettingsPage.settingsTab().waitForItemsList();

        Actions.projectActions().checkMRItem(dayOfMonthItem);
        Actions.projectActions().checkMRItem(monthItem);
        Actions.projectActions().checkMRGroup(myGroupItem, dichotomyNumericGroup);
        SettingsPage.settingsTab().clickGroup(myGroupItem);

        TestRailAssert.assertTrue(
                SettingsPage.settingsTab().isGroupItemListPresent(),
                new CustomStepResult(
                        "блок состовляющих группы не отображается",
                        "блок состовляющих группы успешно отображается"
                )
        );
        Actions.projectActions().checkGroupBlockItem(dayOfMonthItem);
        Actions.projectActions().checkGroupBlockItem(monthItem);
        Actions.projectActions().deleteMRGroup(myGroupItem);
    }

}