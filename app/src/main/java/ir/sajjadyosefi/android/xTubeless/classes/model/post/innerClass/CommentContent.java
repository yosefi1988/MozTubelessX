package ir.sajjadyosefi.android.xTubeless.classes.model.post.innerClass;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CommentContent {

    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("model")
    @Expose
    private String model;
    @SerializedName("mobile")
    @Expose
    private String mobile;
    @SerializedName("description")
    @Expose
    private String description;

    @SerializedName("text")
    @Expose
    private String text;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

}