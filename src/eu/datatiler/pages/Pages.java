package eu.datatiler.pages;

import com.qatestlab.base.BasePage;
import com.qatestlab.base.ObjectsCollection;
import eu.datatiler.pages.dashboard.DashboardPage;
import eu.datatiler.pages.encoder.EncoderPage;
import eu.datatiler.pages.main.MainPage;
import eu.datatiler.pages.main.SidebarRegion;
import eu.datatiler.pages.project.ProjectPage;

public final class Pages {
    private static ObjectsCollection<BasePage> pages = new ObjectsCollection<>();

    public static void clear() {
        pages.clear();
    }

    public static NavigationBar navigationBar() {
        return pages.getInstance(NavigationBar.class);
    }

    public static LoginPage loginPage() {
        return pages.getInstance(LoginPage.class);
    }

    public static MainPage mainPage() {
        return pages.getInstance(MainPage.class);
    }

    public static ProfilePage profilePage(){return pages.getInstance(ProfilePage.class);}

    public static SidebarRegion sidebarRegion(){return pages.getInstance(SidebarRegion.class);}

    public static ProjectPage projectPage(){return pages.getInstance(ProjectPage.class);}

    public static DashboardPage dashboardPage(){return pages.getInstance(DashboardPage.class);}

    public static EncoderPage encodePage(){return pages.getInstance(EncoderPage.class);}

    public static DropBoxPage dropBoxPage(){return pages.getInstance(DropBoxPage.class);}

}
