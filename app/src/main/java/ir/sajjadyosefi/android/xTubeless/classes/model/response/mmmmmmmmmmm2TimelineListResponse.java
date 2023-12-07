package ir.sajjadyosefi.android.xTubeless.classes.model.response;



import java.util.ArrayList;
import java.util.List;

import ir.sajjadyosefi.android.xTubeless.classes.model.ServerStatus;
import ir.sajjadyosefi.android.xTubeless.classes.model.post.blog.UserMessageItem;
import ir.sajjadyosefi.android.xTubeless.classes.model.post.mmmmmmmmmmmmmmTimelineItem;


/**
 * Created by sajjad on 1/20/2018.
 */

public class mmmmmmmmmmm2TimelineListResponse extends ServerResponseBase {
    List<UserMessageItem> Response = new ArrayList<UserMessageItem>();

    public ServerStatus getServerStatus() {
        return null;
    }

    public List<UserMessageItem> getResponse() {
        return Response;
    }

    public void setResponse(List<UserMessageItem> response) {
        this.Response = response;
    }
}
