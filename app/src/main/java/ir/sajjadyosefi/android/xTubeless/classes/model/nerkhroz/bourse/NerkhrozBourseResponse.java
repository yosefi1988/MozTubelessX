
package ir.sajjadyosefi.android.xTubeless.classes.model.nerkhroz.bourse;

import com.google.gson.annotations.Expose;

public class NerkhrozBourseResponse {

    @Expose
    private NerkhrozBourse[] main = null;

    public NerkhrozBourse[] getMain() {
        return main;
    }

    public void setMain(NerkhrozBourse[] main) {
        this.main = main;
    }



}
