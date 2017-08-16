package eu.datatiler.pages.dictionary;

import com.qatestlab.base.BasePage;
import com.qatestlab.base.Locator;
import com.qatestlab.base.LocatorTypes;

/**
 * Created by Petro on 16.03.2016.
 */
public class ItemRegion extends BasePage {

    private Locator dictionaryPage = new Locator(LocatorTypes.XPATH,
            "//body[contains(@class, 'page-dictionary')]");

    private Locator dictionaryItemByName = new Locator(LocatorTypes.XPATH,
            "//div[@class='dt-grid-container']//td[text()='%s']");


    public void waitToLoad(){
        waitForVisibility("Wait for dictionary page to be loaded", dictionaryPage);
    }

    public void waitDictionaryItemByName(String itemName){
        waitForVisibility("Wait for dictionary item with name '" + itemName + "' to be loaded",
                dictionaryItemByName, itemName);
    }

    public boolean isDictionaryItemPresent(String itemName){
        return isPresent("Item '" + itemName + "' is present", dictionaryItemByName, itemName);
    }
}
