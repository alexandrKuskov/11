package eu.datatiler.actions;

import com.qatestlab.base.BaseActions;
import com.qatestlab.testrail.CustomStepResult;
import com.qatestlab.testrail.TestRailAssert;
import eu.datatiler.pages.Pages;
import eu.datatiler.pages.encoder.EncoderPage;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Petro on 29.02.2016.
 */
public class EncodeActions extends BaseActions {

    public void encodeInterviewItem(int idItem) {
        EncoderPage.categoryRegion().waitForLoad();
        EncoderPage.categoryRegion().waitInterviewItem(idItem);
        EncoderPage.categoryRegion().clickInterviewItemLabel(idItem);
        EncoderPage.categoryRegion().clickItemOption("Encode");
        Pages.navigationBar().waitLoader();
        EncoderPage.categoryRegion().clickInterviewItem(idItem);
    }

    public void mergeValuesInterviewItem(int amountMergedValue, String nameMergedGroup) {
        EncoderPage.itemsRegion().waitListValues();

        for (int i = 1; i <= amountMergedValue; i++)
            EncoderPage.itemsRegion().selectValueInterviewItem(i);

        EncoderPage.itemsRegion().clickMergeButton();
        BaseActions.wait(3);
        EncoderPage.itemsRegion().setMergeName(nameMergedGroup);
        BaseActions.wait(3);
        EncoderPage.itemsRegion().confirmMergeAction();
        BaseActions.wait(3);

        Assert.assertTrue(EncoderPage.itemsRegion().isMergedValuePresent(nameMergedGroup),
                "Expected value '" + nameMergedGroup + "' is not present");
    }

    public void deleteValueFromMergedGroup(int amountDeletedValues, String nameMergedGroup) {

        EncoderPage.toolbarRegion().clickSwitchSimilarShow();

        EncoderPage.similarRegion().waitForLoad(nameMergedGroup);

        TestRailAssert.assertTrue(
                EncoderPage.similarRegion().isSimilarShowBlockVisible(nameMergedGroup),
                new CustomStepResult(
                        "Список переменной не отображается",
                        "Отображается список содержимой переменной"
                )
        );

        for (int i = 1; i < amountDeletedValues; i++)
            EncoderPage.similarRegion().selectEntrySimilarShowBlock(i);

        List<String> disabledEntries = EncoderPage.similarRegion().getDisabledEntriesSimilarShowBlock();

        TestRailAssert.assertTrue(
                EncoderPage.similarRegion().isConfirmButtonVisible(),
                new CustomStepResult(
                        "Кнопка 'Confirm Removal' не отображается. Переменные перечекнутые",
                        "Отображается кнопка 'Confirm Removal'. Переменные перечекнутые"
                )
        );

        EncoderPage.similarRegion().clickConfirmRemovalButton();

        BaseActions.wait(3);

        TestRailAssert.assertFalse(
                EncoderPage.similarRegion().getEntriesSimilarShowBlock().containsAll(disabledEntries),
                new CustomStepResult(
                        "Переменные не удалены",
                        "Переменные удалены"
                )
        );
    }

    public void addToDictionary(String dictionaryName) {
        EncoderPage.toolbarRegion().waitToLoad();
        EncoderPage.toolbarRegion().clickEncoderDictionaryButton();

        EncoderPage.itemsRegion().waitPopUpToBeLoaded();

        TestRailAssert.assertTrue(
                "Dictionary".equals(EncoderPage.itemsRegion().getPopUpHeader()) &&
                        EncoderPage.itemsRegion().isCancelButtonPresent() &&
                        EncoderPage.itemsRegion().isCreateDictionaryButtonPresent()
                ,
                new CustomStepResult(
                        "Поп-ап 'Dictionary' не отображается и/или кнопка 'Cancel'" +
                                " не отображается и/или кнопка '+' не отображается",
                        "Отображается поп-ап 'Dictionary'. Отображается кнопка 'Cancel'. Отображается кнопка '+'"
                )
        );

        EncoderPage.itemsRegion().clickCreateDictionaryButton();

        wait(2);
        EncoderPage.itemsRegion().waitPopUpToBeLoaded();

        TestRailAssert.assertTrue(
                "Add dictionary".equals(EncoderPage.itemsRegion().getPopUpHeader()) &&
                        EncoderPage.itemsRegion().isCancelButtonPresent() &&
                        EncoderPage.itemsRegion().isAddDictionaryButtonPresent() &&
                        EncoderPage.itemsRegion().isFieldDictionaryNamePresent()
                ,
                new CustomStepResult(
                        "Попап 'Add dictionary' не отображается и/или кнопки 'Cancel', 'Add' не отображаются " +
                                "и/или поле для ввода имени словаря не отображается.",
                        "Отображается попап 'Add dictionary'. Отображаются кнопки 'Cancel', 'Add'." +
                                " Отображается поле для ввода имени словаря."
                )
        );

        EncoderPage.itemsRegion().setDictionaryName(dictionaryName);

        TestRailAssert.assertTrue(
                dictionaryName.equals(EncoderPage.itemsRegion().getValueFromInputDictionaryNameField()),
                new CustomStepResult(
                        "Введенное имя не отображается",
                        "Отображается введенное имя в поле"
                )
        );

        EncoderPage.itemsRegion().clickAddDictionaryButton();

        wait(2);
        EncoderPage.itemsRegion().waitPopUpToBeLoaded();

        //TODO Отображается дропдаун

        TestRailAssert.assertTrue(
                "Dictionary".equals(EncoderPage.itemsRegion().getPopUpHeader()) &&
                        EncoderPage.itemsRegion().isCancelButtonPresent() &&
                        EncoderPage.itemsRegion().isCreateDictionaryButtonPresent()
                ,
                new CustomStepResult(
                        "Введенное имя не отображается",
                        "Отображается поп-ап 'Dictionary'. Отображается дропдаун. " +
                                "Отображается кнопка 'Cancel'. Отображается кнопка '+'."
                )
        );

        EncoderPage.itemsRegion().selectDictionary(dictionaryName);

        TestRailAssert.assertTrue(
                EncoderPage.itemsRegion().isFromDictionaryButtonPresent() &&
                        EncoderPage.itemsRegion().isToDictionaryButtonPresent()
                ,
                new CustomStepResult(
                        "Кнопка 'From Dictionary' не отображается и/или кнпока 'To dictionary' не отображается",
                        "Отображается кнопка 'From Dictionary'. Отображается кнпока 'To dictionary'"
                )
        );

        EncoderPage.itemsRegion().clickToDictionaryButton();

        TestRailAssert.assertTrue(
                Pages.projectPage().exportDialog().isSuccessAlertVisible()
                ,
                new CustomStepResult(
                        "Переменные/переменная с ее содержимым не экспортирована в словарь",
                        "Переменные/переменная с ее содержимым экспортирована в словарь"
                )
        );



        EncoderPage.toolbarRegion().waitExportDictionaryConfirmationBar();
    }

    public void mergeCusterItem(int idItem, String clusterItemName) {
        EncoderPage.clusterRegion().waitForLoad();
        EncoderPage.clusterRegion().setClusterItemName(idItem, clusterItemName);
        EncoderPage.clusterRegion().clickMergeClusterItem(idItem);
    }

    public String getCountClusterItemValue(String clusterItemValue) {

        Pattern pattern = Pattern.compile("(.)(\\()(-?\\d+)(\\))");
        Matcher matcher = pattern.matcher(clusterItemValue);

        return (matcher.find()) ? matcher.group(3).trim() : null;
    }

    public int getSumClusterItemValues(ArrayList<String> listValues) {
        int sum = 0;

        for (String ch : listValues) {
            sum += Integer.parseInt(getCountClusterItemValue(ch));
        }

        return sum;
    }
}
