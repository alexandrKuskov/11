package eu.datatiler.tests;

import com.qatestlab.Random;
import com.qatestlab.base.BaseTest;
import com.qatestlab.reporting.Reporter;
import com.qatestlab.testrail.CustomStepResult;
import com.qatestlab.testrail.TestRailAssert;
import com.qatestlab.testrail.TestRailIssue;
import eu.datatiler.actions.Actions;
import eu.datatiler.pages.Pages;
import eu.datatiler.utils.DataProviderPool;
import org.testng.Assert;
import org.testng.annotations.Test;


public class FolderTests extends BaseTest {

    private final String TEST_RUN_NAME = "My space tests";

    @TestRailIssue(issueID = 6, testRunName = TEST_RUN_NAME)
    @Test(description = "Create folder in personal space (UAT-31)",
            dataProvider = DataProviderPool.USER_CREDENTIALS, dataProviderClass = DataProviderPool.class)

    public void createAndDeleteFoldersTest(String login, String password) {
        Actions.generalActions().clearSession();
        Actions.generalActions().openHomePage();
        Actions.generalActions().login(login, password);
        Actions.generalActions().checkHomePageLoaded();

        String folderName = "Folder" + Random.genRandNumber();
        Actions.itemActions().createFolder(folderName);
        boolean folderPresent = Actions.itemActions().isItemPresent(folderName);
        Assert.assertTrue(folderPresent,
                String.format("Folder '%s' wasn't created", folderName));
        Actions.itemActions().openItem(folderName);
        String subFolderName = "Sub-folder" + Random.genRandNumber();
        Actions.itemActions().createFolder(subFolderName);

        Actions.itemActions().openItem(subFolderName);

        TestRailAssert.assertTrue(
                subFolderName.equals(Pages.navigationBar().getLastFolderName()),
                new CustomStepResult(
                        "Папка '" + subFolderName + "' не открыта",
                        "Папка '" + subFolderName + "' открыта")
        );

        String expectedBreadCrumbs = "/My space/" + folderName + "/" + subFolderName;

        Actions.generalActions().refreshPage();

        TestRailAssert.assertTrue(
                expectedBreadCrumbs.equals(Pages.navigationBar().getBreadCrumbsHierarchy()),
                new CustomStepResult(
                        "Вложеность папок не соблюдена",
                        "Вложеность папок соблюдена")
        );


        Actions.itemActions().openRootFolder();
        Actions.itemActions().selectFolder(folderName);
        Actions.itemActions().removeWithAssert();
        Pages.mainPage().contentRegion().waitForItemInvisibility(folderName);

        TestRailAssert.assertFalse(
                Actions.itemActions().isItemPresent(folderName),
                new CustomStepResult(
                        "Папка не удалена",
                        "Папка отсутствует")
        );

        Actions.generalActions().refreshPage();

        TestRailAssert.assertFalse(
                Actions.itemActions().isItemPresent(folderName),
                new CustomStepResult(
                        "Папка не удалена",
                        "Папка отсутствует")
        );
    }


    @TestRailIssue(issueID = 7, testRunName = TEST_RUN_NAME)
    @Test(description = "Cancel adding new folder (UAT-32)",
            dataProvider = DataProviderPool.USER_CREDENTIALS, dataProviderClass = DataProviderPool.class)
    public void cancelAddingNewFolder(String login, String password) {
        Actions.generalActions().clearSession();
        Actions.generalActions().openHomePage();
        Actions.generalActions().login(login, password);
        Actions.generalActions().checkHomePageLoaded();
        String folderName = "CanceledFolder";
        Actions.itemActions().cancelFolderCreation(folderName);
        TestRailAssert.assertFalse(
                Actions.itemActions().isItemPresent(folderName),
                new CustomStepResult(
                        "Создание папки не отменено или папка создана.",
                        "Создание папки отменено. Папка не создана."
                )
        );
    }

    @TestRailIssue(issueID = 10, testRunName = TEST_RUN_NAME)
    @Test(description = "Add new folder with empty name (UAT-34)",
            dataProvider = DataProviderPool.USER_CREDENTIALS, dataProviderClass = DataProviderPool.class)
    public void addFolderWithEmptyName(String login, String password) {
        Actions.generalActions().clearSession();
        Actions.generalActions().openHomePage();
        Actions.generalActions().login(login, password);
        Actions.generalActions().checkHomePageLoaded();

        Actions.itemActions().createFolderWithEmptyName();

        TestRailAssert.assertTrue(
                Pages.mainPage().contentRegion().isItemPresentByNamePattern("New Folder"),
                new CustomStepResult(
                        "С именем New Folder не отображается  \n" +
                                "и отображается число рядом с текстом.",
                        "Папка создается с именем New Folder \n" +
                                "и отображается число рядом с текстом."
                )
        );

        Actions.generalActions().wait(5);

        Pages.mainPage().contentRegion().clickOnItemByNamePattern("New Folder");

        Actions.itemActions().remove();
    }


    @TestRailIssue(issueID = 11, testRunName = TEST_RUN_NAME)
    @Test(description = "Add new folder with Cyrillic name (UAT-36)",
            dataProvider = DataProviderPool.USER_CREDENTIALS, dataProviderClass = DataProviderPool.class)
    public void addFolderWithCyrillic(String login, String password) {
        Actions.generalActions().clearSession();
        Actions.generalActions().openHomePage();
        Actions.generalActions().login(login, password);
        Actions.generalActions().checkHomePageLoaded();

        String folderName = "\u041A" + "\u0438" + "\u0440" + "\u0438" + "\u043B" + "\u043B" + "\u0438" + "\u0446" + "\u0430" +
                Random.genRandNumber();
        Actions.itemActions().createFolder(folderName);

        TestRailAssert.assertTrue(
                Actions.itemActions().isItemPresent(folderName),
                new CustomStepResult(
                        "Папка не создана и/или отображается с заданным Кириллицей именем",
                        "Папка создана и отображается с заданным Кириллицей именем"
                )
        );

        Actions.itemActions().removeItemsIfPresent(folderName);
    }



    @TestRailIssue(issueID = 8, testRunName = TEST_RUN_NAME)
    @Test(description = "Add two folders with the same names (UAT-42)",
            dataProvider = DataProviderPool.USER_CREDENTIALS, dataProviderClass = DataProviderPool.class)
    public void addFoldersWithSameName(String login, String password) {
        Actions.generalActions().clearSession();
        Actions.generalActions().openHomePage();
        Actions.generalActions().login(login, password);
        Actions.generalActions().checkHomePageLoaded();

        String folderName = "DuplicateFolder" + Random.genRandNumber();
        Actions.itemActions().createFolder(folderName);
        Actions.itemActions().createFolder(folderName);
        int foldersCount = Actions.itemActions().getIdenticalFoldersCount(folderName);

        TestRailAssert.assertTrue(
                Actions.itemActions().getIdenticalFoldersCount(folderName) > 1,
                new CustomStepResult(
                        "Две папки с одним и тем же именем не отображаются",
                        "Отображаются две созданные папки с одним и тем же именем"
                )
        );
        for (int i = 0; i < 2; i++) {
            Actions.itemActions().selectItems(folderName);
            Actions.itemActions().remove();
        }

    }


    @TestRailIssue(issueID = 9, testRunName = TEST_RUN_NAME)
    @Test(description = "Add two folders with the same names one into another (UAT-44)",
            dataProvider = DataProviderPool.USER_CREDENTIALS, dataProviderClass = DataProviderPool.class)
    public void addFoldersOneIntoOther(String login, String password) {
        Actions.generalActions().clearSession();
        Actions.generalActions().openHomePage();
        Actions.generalActions().login(login, password);
        Actions.generalActions().checkHomePageLoaded();

        String folderName = "Folder" + Random.genRandNumber();
        Actions.itemActions().createFolder(folderName);
        Actions.itemActions().openItem(folderName);
        Actions.itemActions().createFolder(folderName);
        Actions.itemActions().removeItem(folderName);
        Actions.itemActions().openRootFolder();
        Actions.itemActions().removeItem(folderName);
    }

}
