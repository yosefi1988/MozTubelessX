
package ir.sajjadyosefi.android.xTubeless.classes.model.nerkhroz.bourse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Main {

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("fPrice")
    @Expose
    private String fPrice;
    @SerializedName("diff")
    @Expose
    private String diff;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("isTop")
    @Expose
    private Boolean isTop;
    @SerializedName("pic")
    @Expose
    private String pic;

    public Main(String inception) {
        this.name = inception;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFPrice() {
        return fPrice;
    }

    public void setFPrice(String fPrice) {
        this.fPrice = fPrice;
    }

    public String getDiff() {
        return diff;
    }

    public void setDiff(String diff) {
        this.diff = diff;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Boolean getIsTop() {
        return isTop;
    }

    public void setIsTop(Boolean isTop) {
        this.isTop = isTop;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

}
