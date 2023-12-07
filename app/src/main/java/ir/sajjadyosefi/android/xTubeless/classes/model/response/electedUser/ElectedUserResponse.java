package ir.sajjadyosefi.android.xTubeless.classes.model.response.electedUser;



import java.util.ArrayList;
import java.util.List;

import ir.sajjadyosefi.android.xTubeless.classes.model.response.ServerResponseBase;
import ir.sajjadyosefi.android.xTubeless.classes.model.user.ElectedUserItem;


/**
 * Created by sajjad on 1/20/2018.
 */

public class ElectedUserResponse extends ServerResponseBase {
    List<ElectedUserItem> electedUserList = new ArrayList<ElectedUserItem>();

    public List<ElectedUserItem> getElectedUserList() {
        return electedUserList;
    }

    public void setElectedUserList(List<ElectedUserItem> electedUserList) {
        this.electedUserList = electedUserList;
    }
}
