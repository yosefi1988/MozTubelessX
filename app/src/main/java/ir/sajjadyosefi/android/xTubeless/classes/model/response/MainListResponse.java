package ir.sajjadyosefi.android.xTubeless.classes.model.response;



import java.util.ArrayList;
import java.util.List;

import ir.sajjadyosefi.android.xTubeless.classes.model.mainList.MainListItem;


/**
 * Created by sajjad on 1/20/2018.
 */

public class MainListResponse extends ServerResponseBase {

    List<MainListItem> mainListItems = new ArrayList<MainListItem>();

    public List<MainListItem> getMainListItems() {
        return mainListItems;
    }

    public void setMainListItems(List<MainListItem> mainListItems) {
        this.mainListItems = mainListItems;
    }

}
