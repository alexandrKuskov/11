package eu.datatiler.pages.dictionary;

import com.qatestlab.base.BasePage;
import com.qatestlab.base.ObjectsCollection;

/**
 * Created by Petro on 16.03.2016.
 */
public class DictionaryPage extends BasePage{

    private static ObjectsCollection<BasePage> regions = new ObjectsCollection<>();

    DictionaryPage(){}

    public static ItemRegion itemRegion() {
        return regions.getInstance(ItemRegion.class);
    }

}
