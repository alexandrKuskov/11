package eu.datatiler.pages.dashboard;

import com.qatestlab.base.BasePage;
import com.qatestlab.base.ObjectsCollection;

public class DashboardPage extends BasePage {

    private static ObjectsCollection<BasePage> regions = new ObjectsCollection<>();

    protected DashboardPage(){}

    public static CategoryRegion categoryRegion(){return regions.getInstance(CategoryRegion.class);}

    public static ContentPage contentPage(){return regions.getInstance(ContentPage.class);}

    public static ControlPage controlPage(){return regions.getInstance(ControlPage.class);}

    public static ToolbarRegion toolbarRegion(){return regions.getInstance(ToolbarRegion.class);}

    public static TextWidgetDialog createTextWidgetDialog(){return regions.getInstance(TextWidgetDialog.class);}

    public static LeftToolBarRegion leftToolBarRegion(){return regions.getInstance(LeftToolBarRegion.class);}

}
