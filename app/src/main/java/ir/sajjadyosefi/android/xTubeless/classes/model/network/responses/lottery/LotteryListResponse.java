package ir.sajjadyosefi.android.xTubeless.classes.model.network.responses.lottery;


import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import ir.sajjadyosefi.android.xTubeless.classes.model.category.CategoryItem;
import ir.sajjadyosefi.android.xTubeless.classes.model.response.ServerResponseBase;


/**
 * Created by sajjad on 1/20/2018.
 */

public class LotteryListResponse extends ServerResponseBase {
    @SerializedName("data")
    @Expose
    private List<LotterySearchResponseItem> data;

    public List<LotterySearchResponseItem> getData() {
        return data;
    }

    public void setData(List<LotterySearchResponseItem> data) {
        this.data = data;
    }
}
