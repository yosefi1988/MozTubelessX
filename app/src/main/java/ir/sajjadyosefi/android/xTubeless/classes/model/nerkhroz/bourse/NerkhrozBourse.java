
package ir.sajjadyosefi.android.xTubeless.classes.model.nerkhroz.bourse;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class NerkhrozBourse implements ParentListItem  {

    @SerializedName("priceType")
    @Expose
    private String priceType;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("main")
    @Expose
    private List<Main> main = null;

    public NerkhrozBourse(NerkhrozBourse nerkhrozBourse, List<Main> asList) {
        this.name = nerkhrozBourse.getName();
        this.priceType = nerkhrozBourse.getPriceType();
        this.date = nerkhrozBourse.getDate();
        this.main = asList;
    }

    public String getPriceType() {
        return priceType;
    }

    public void setPriceType(String priceType) {
        this.priceType = priceType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<Main> getChildItemList() {
        return main;
    }

    @Override
    public boolean isInitiallyExpanded() {
        return false;
    }

    public void setMain(List<Main> main) {
        this.main = main;
    }

}
