package eu.datatiler.actions;

import com.qatestlab.base.BaseActions;
import com.qatestlab.base.ObjectsCollection;

public final class Actions {
    private static ObjectsCollection<BaseActions> actions = new ObjectsCollection<>();

    public static void clear() {
        actions.clear();
    }

    public static GeneralActions generalActions() {
        return actions.getInstance(GeneralActions.class);
    }

    public static ItemActions itemActions() {
        return actions.getInstance(ItemActions.class);
    }

    public static UserActions userActions(){return actions.getInstance(UserActions.class);}

    public static ProjectActions projectActions(){return actions.getInstance(ProjectActions.class);}

    public static DashboardActions dashboardActions(){return actions.getInstance(DashboardActions.class);}

    public static EncodeActions encodeActions(){return actions.getInstance(EncodeActions.class);}

}
