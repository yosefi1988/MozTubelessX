package ir.sajjadyosefi.android.xTubeless.classes.model.response;



import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import ir.sajjadyosefi.android.xTubeless.classes.model.user.UserItem;

/**
 * Created by sajjad on 1/20/2018.
 */

public class UserListResponse extends ServerResponseBase {

    @SerializedName("Response")
    @Expose
    private List<UserItem> response = null;
    public List<UserItem> getResponse() {
        return response;
    }

    public void setResponse(List<UserItem> response) {
        this.response = response;
    }
}
