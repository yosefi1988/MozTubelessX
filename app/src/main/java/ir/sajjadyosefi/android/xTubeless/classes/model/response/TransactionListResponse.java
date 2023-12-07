package ir.sajjadyosefi.android.xTubeless.classes.model.response;



import java.util.ArrayList;
import java.util.List;

import ir.sajjadyosefi.android.xTubeless.classes.model.post.TransactionItem;


/**
 * Created by sajjad on 1/20/2018.
 */

public class TransactionListResponse extends ServerResponseBase {
    List<TransactionItem> Response = new ArrayList<TransactionItem>();

    public List<TransactionItem> getResponse() {
        return Response;
    }

    public void setResponse(List<TransactionItem> response) {
        Response = response;
    }
}
