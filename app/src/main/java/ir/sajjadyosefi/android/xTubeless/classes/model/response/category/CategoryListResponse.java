package ir.sajjadyosefi.android.xTubeless.classes.model.response.category;



import java.util.ArrayList;
import java.util.List;


import ir.sajjadyosefi.android.xTubeless.classes.model.category.CategoryItem;
import ir.sajjadyosefi.android.xTubeless.classes.model.response.ServerResponseBase;


/**
 * Created by sajjad on 1/20/2018.
 */

public class CategoryListResponse extends ServerResponseBase {
    List<CategoryItem> catlist = new ArrayList<CategoryItem>();
    public List<CategoryItem> getCatlist() {
        return catlist;
    }
    public void setCatlist(List<CategoryItem> catlist) {
        this.catlist = catlist;
    }
}
