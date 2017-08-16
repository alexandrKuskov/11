package eu.datatiler.pages.project.Settings;

import com.qatestlab.base.BasePage;
import com.qatestlab.base.ObjectsCollection;

/**
 * Created by Petro on 15.02.2016.
 */
public class SettingsPage  extends BasePage {
    private static ObjectsCollection<BasePage> regions = new ObjectsCollection<>();

    protected SettingsPage(){}

    public static void clearRegions() {
        regions.clear();
    }

    public static SettingsTab settingsTab() {return regions.getInstance(SettingsTab.class);}

    public static NavigationTabs navigationTabs() {return regions.getInstance(NavigationTabs.class);}

    public static MetaEditorTab metaEditorTab() {return regions.getInstance(MetaEditorTab.class);}
}
