package eu.datatiler.pages.encoder;

import com.qatestlab.base.BasePage;
import com.qatestlab.base.ObjectsCollection;

/**
 * Created by Petro on 29.02.2016.
 */
public class EncoderPage extends BasePage {

    private static ObjectsCollection<BasePage> regions = new ObjectsCollection<>();

    protected EncoderPage() {
    }

    public static CategoryRegion categoryRegion() {
        return regions.getInstance(CategoryRegion.class);
    }

    public static ItemsRegion itemsRegion() {
        return regions.getInstance(ItemsRegion.class);
    }

    public static SimilarRegion similarRegion() {
        return regions.getInstance(SimilarRegion.class);
    }

    public static ToolbarRegion toolbarRegion() {
        return regions.getInstance(ToolbarRegion.class);
    }

    public static ClusterRegion clusterRegion() {
        return regions.getInstance(ClusterRegion.class);
    }

}
