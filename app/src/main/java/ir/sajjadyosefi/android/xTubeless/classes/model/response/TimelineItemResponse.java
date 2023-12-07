package ir.sajjadyosefi.android.xTubeless.classes.model.response;


import ir.sajjadyosefi.accountauthenticator.model.AWallet;
import ir.sajjadyosefi.android.xTubeless.classes.model.post.MainItem;
import ir.sajjadyosefi.android.xTubeless.classes.model.user.UserItem;

/**
 * Created by sajjad on 1/20/2018.
 */


public class TimelineItemResponse extends ServerResponseBase {

    public Object PostItem;
    public AWallet wallet;
    public UserItem creator;

    public UserItem getCreator() {
        return creator;
    }

    public void setCreator(UserItem creator) {
        this.creator = creator;
    }



    public AWallet getWallet() {
        return wallet;
    }

    public void setWallet(AWallet wallet) {
        this.wallet = wallet;
    }

    public Object getPostItem() {
        return PostItem;
    }

    public void setPostItem(Object postItem) {
        PostItem = postItem;
    }

}
