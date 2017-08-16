package eu.datatiler.pages.project;

import com.qatestlab.base.BasePage;
import com.qatestlab.base.ObjectsCollection;
import eu.datatiler.pages.project.Settings.SettingsPage;

public class ProjectPage extends BasePage {
    private static ObjectsCollection<BasePage> regions = new ObjectsCollection<>();

    protected ProjectPage(){}

    public static void clearRegions() {
        regions.clear();
    }

    public static CategoryRegion categoryRegion() {
        return regions.getInstance(CategoryRegion.class);
    }

    public static ToolbarRegion toolbarRegion(){
        return regions.getInstance(ToolbarRegion.class);
    }

    public static ExportDialog exportDialog(){return regions.getInstance(ExportDialog.class);}

    public static SettingsPage settingsPage() {return regions.getInstance(SettingsPage.class);}

    public static ChartRegion chartRegion() {return regions.getInstance(ChartRegion.class);}

    public static TableRegion tableRegion() {return regions.getInstance(TableRegion.class);}

}
