package ir.sajjadyosefi.android.xTubeless.classes.model.response;



import java.util.ArrayList;
import java.util.List;

import ir.sajjadyosefi.android.xTubeless.classes.model.post.MainItem;


/**
 * Created by sajjad on 1/20/2018.
 */

public class NewTimelineListResponse extends ServerResponseBase {
    List<Object> postList = new ArrayList<Object>();

    public List<Object> getPostList() {
        return postList;
    }

    public void setPostList(List<Object> postList) {
        this.postList = postList;
    }


}
