package eu.datatiler.pages.main;

import com.qatestlab.base.BasePage;
import com.qatestlab.base.ObjectsCollection;

public class MainPage extends BasePage {
    private static ObjectsCollection<BasePage> regions = new ObjectsCollection<>();


    protected MainPage() { }

    public static void clearRegions() {
        regions.clear();
    }

    public static ContentRegion contentRegion() {
        return regions.getInstance(ContentRegion.class);
    }

    public static ToolbarRegion toolbarRegion() {
        return regions.getInstance(ToolbarRegion.class);
    }

    public static CreatingDialog creatingDialog() {
        return regions.getInstance(CreatingDialog.class);
    }

    public static SidebarRegion sidebarRegion(){return regions.getInstance(SidebarRegion.class);}

    public static EditDialog editDialog(){return regions.getInstance(EditDialog.class);}

    public static ShareDialog shareDialog(){return regions.getInstance(ShareDialog.class);}
}
