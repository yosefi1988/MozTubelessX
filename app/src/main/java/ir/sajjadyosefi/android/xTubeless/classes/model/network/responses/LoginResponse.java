package ir.sajjadyosefi.android.xTubeless.classes.model.network.responses;


import ir.sajjadyosefi.android.xTubeless.classes.model.exception.TubelessException;
import ir.sajjadyosefi.android.xTubeless.classes.model.user.User;

/**
 * Created by sajjad on 10/31/2016.
 */
public class LoginResponse  {
    private TubelessException State;
    private User Response;


    public User getResponse() {
        return Response;
    }

    public void setResponse(User response) {
        Response = response;
    }

    public TubelessException getState() {
        return State;
    }

    public void setState(TubelessException state) {
        this.State = state;
    }
}
