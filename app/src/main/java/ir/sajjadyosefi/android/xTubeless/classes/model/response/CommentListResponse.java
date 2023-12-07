package ir.sajjadyosefi.android.xTubeless.classes.model.response;



import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

import ir.sajjadyosefi.android.xTubeless.classes.model.post.blog.CommentItem;

/**
 * Created by sajjad on 1/20/2018.
 */

public class CommentListResponse extends ServerResponseBase {

    @SerializedName("Response")
    @Expose
    private List<CommentItem> response = null;
    public List<CommentItem> getResponse() {
        return response;
    }

    public void setResponse(List<CommentItem> response) {
        this.response = response;
    }
}
