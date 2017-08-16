package eu.datatiler.tests;

import com.qatestlab.Random;
import com.qatestlab.base.BaseActions;
import com.qatestlab.base.BaseTest;
import com.qatestlab.testrail.CustomStepResult;
import com.qatestlab.testrail.TestRailAssert;
import com.qatestlab.testrail.TestRailIssue;
import eu.datatiler.actions.Actions;
import eu.datatiler.pages.dictionary.DictionaryPage;
import eu.datatiler.pages.encoder.EncoderPage;
import eu.datatiler.utils.DataProviderPool;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by Petro on 29.02.2016.
 */
public class EncoderTest extends BaseTest {

    private final String TEST_RUN_NAME = "Encoder tests";

    private String folderName = "UAT";
    private String encoderName = "Test_File_BIG.xlsx";
    private String nameMergedGroup = "MERGED_GROUP";
    private String dictionaryName = "CUSTOM_DICTIONARY";
    private String clusterItemName = "CUSTOM_CLUSTER";
    private int amountValue = 9;
    private int idItem = 1;

    public void createEncoder() {
        Actions.itemActions().removeItemsIfPresent(folderName);
        Actions.itemActions().createFolder(folderName);
        Actions.itemActions().openItem(folderName);
        Actions.itemActions().createEncoder(encoderName);
        boolean itemPresent = Actions.itemActions().isItemPresent(encoderName);
        Assert.assertTrue(itemPresent, String.format("'%s' isn't present in folder %s", encoderName, folderName));
    }



    @TestRailIssue(issueID = 324, testRunName = TEST_RUN_NAME)
    @Test(dataProvider = DataProviderPool.ENCODER_CREDENTIALS, dataProviderClass = DataProviderPool.class,
            description = "Removing the variable in 'Show similar' block (UAT-770)")
    public void removingVariableInShowSimilarBlock(String login, String password) {
        Actions.generalActions().clearSession();
        Actions.generalActions().openHomePage();
        Actions.generalActions().login(login, password);
        Actions.generalActions().checkHomePageLoaded();

       // createEncoder();

        Actions.itemActions().openEncoder(encoderName);
        EncoderPage.categoryRegion().waitForLoad();
        Actions.encodeActions().encodeInterviewItem(idItem);

        EncoderPage.itemsRegion().waitListValues();

        TestRailAssert.assertTrue(
                EncoderPage.itemsRegion().isListValuesVisible(),
                new CustomStepResult(
                        "Список переменных не отображается",
                        "Отображается список переменных"
                )
        );
        String newMergedGroup = nameMergedGroup + Random.genShortRandNumber();
        Actions.encodeActions().mergeValuesInterviewItem(amountValue, newMergedGroup);

        EncoderPage.itemsRegion().clickValueInterviewItem(newMergedGroup);
        BaseActions.wait(3);
        TestRailAssert.assertTrue(
                EncoderPage.itemsRegion().isMergeButtonClickable() &&
                        EncoderPage.itemsRegion().isValueInterviewItemSelected(newMergedGroup),
                new CustomStepResult(
                        "Переменная невыделена и/или кнопка 'Merge' не отображается в кликабельном состоянии",
                        "Переменная выделена отображается кнопка 'Merge' в кликабельном состоянии"
                )
        );

        Actions.encodeActions().deleteValueFromMergedGroup(amountValue, newMergedGroup);
    }



    @TestRailIssue(issueID = 325, testRunName = TEST_RUN_NAME)
    @Test(dataProvider = DataProviderPool.ENCODER_CREDENTIALS, dataProviderClass = DataProviderPool.class,
            description = "Exporting to the dictionary (UAT- 754)")
    public void exportingToDictionary(String login, String password) {
        Actions.generalActions().clearSession();
        Actions.generalActions().openHomePage();
        Actions.generalActions().login(login, password);
        Actions.generalActions().checkHomePageLoaded();

      //  createEncoder();
        Actions.itemActions().removeItemsIfPresent(dictionaryName);
        Actions.itemActions().openEncoder(encoderName);
        EncoderPage.categoryRegion().waitForLoad();
        Actions.encodeActions().encodeInterviewItem(idItem);
        EncoderPage.itemsRegion().waitListValues();

        TestRailAssert.assertTrue(
                EncoderPage.itemsRegion().isListValuesVisible(),
                new CustomStepResult(
                        "Список переменных не отображается",
                        "Отображается список переменных"
                )
        );
        String newMergedGroup = nameMergedGroup + Random.genShortRandNumber();
        Actions.encodeActions().mergeValuesInterviewItem(amountValue, newMergedGroup);
        EncoderPage.itemsRegion().clickValueInterviewItem(newMergedGroup);
        Assert.assertTrue(EncoderPage.itemsRegion().isValueInterviewItemSelected(newMergedGroup),
                "Merged group '" + newMergedGroup + "' is not selected");


        Actions.encodeActions().addToDictionary(dictionaryName);
        Actions.generalActions().openMainPage();
      //  Actions.itemActions().openItem(folderName);
        Actions.itemActions().openItem(dictionaryName);
        DictionaryPage.itemRegion().waitToLoad();
        DictionaryPage.itemRegion().waitDictionaryItemByName(newMergedGroup);

        TestRailAssert.assertTrue(
                DictionaryPage.itemRegion().isDictionaryItemPresent(newMergedGroup),
                new CustomStepResult(
                        "Экспортированная переменная/переменные не отображаєтся",
                        "Отображается экспортированная переменная/переменные"
                )
        );
        Actions.generalActions().openMainPage();
        Actions.itemActions().removeItem(dictionaryName);
    }


/*
//    @TestRailIssue(issueID = 25158, testRunName = TEST_RUN_NAME)
//    @Test(description = "Displaying correct count of variable content after merging in 'Clusters' tab (UAT-764)",
// dependsOnMethods = "login")
//    public void displayingCorrectCountVariableAfterMergInClusterTab() {
//        Actions.generalActions().openHomePage();
//        createEncoder();
//        Actions.itemActions().openItem(encoderName);
//
//        EncoderPage.categoryRegion().waitForLoad();
//        Assert.assertTrue(EncoderPage.categoryRegion().isListInterviewItemVisible(),
//                "List interview item is not present");
//
//        Actions.encodeActions().encodeInterviewItem(idItem);
//
//        EncoderPage.itemsRegion().waitListValues();
//        Assert.assertTrue(EncoderPage.itemsRegion().isListValuesVisible(), "List values interview item not present");
//
//        EncoderPage.toolbarRegion().clickToolbarTab("Clusters");
//
//        int expectedCountValues = Actions.encodeActions()
//                .getSumClusterItemValues(EncoderPage.clusterRegion().getClusterItemValues(idItem));
//
//        Actions.encodeActions().mergeCusterItem(idItem, clusterItemName);
//
//        EncoderPage.toolbarRegion().clickToolbarTab("Items");
//
//        int foundedCountValues = Integer.parseInt(EncoderPage.itemsRegion().getCountValueInterviewItem(clusterItemName));
//
//        Assert.assertEquals(foundedCountValues, expectedCountValues, "The number of values cluster item is incorrect");
//    }
*/
}
