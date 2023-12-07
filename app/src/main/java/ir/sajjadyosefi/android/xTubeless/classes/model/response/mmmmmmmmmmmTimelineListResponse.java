package ir.sajjadyosefi.android.xTubeless.classes.model.response;



import java.util.ArrayList;
import java.util.List;

import ir.sajjadyosefi.android.xTubeless.classes.model.ServerStatus;
import ir.sajjadyosefi.android.xTubeless.classes.model.post.mmmmmmmmmmmmmmTimelineItem;


/**
 * Created by sajjad on 1/20/2018.
 */

public class mmmmmmmmmmmTimelineListResponse extends ServerResponseBase {
    List<mmmmmmmmmmmmmmTimelineItem> Response = new ArrayList<mmmmmmmmmmmmmmTimelineItem>();

    public ServerStatus getServerStatus() {
        return null;
    }

    public List<mmmmmmmmmmmmmmTimelineItem> getResponse() {
        return Response;
    }

    public void setResponse(List<mmmmmmmmmmmmmmTimelineItem> response) {
        this.Response = response;
    }
}
