package ir.sajjadyosefi.android.xTubeless.classes.model.config;

import ir.sajjadyosefi.android.xTubeless.classes.model.ServerStatus;
import ir.sajjadyosefi.android.xTubeless.classes.model.exception.TubelessException;

/**
 * Created by sajjad on 10/31/2016.
 */
public class ServerResponseStatus {
    private TubelessResponseStatus State;

    public TubelessResponseStatus getState() {
        return State;
    }

    public void setState(TubelessResponseStatus state) {
        State = state;
    }



}
