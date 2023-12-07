package ir.sajjadyosefi.android.xTubeless.classes.model.network.request.post;

import ir.sajjadyosefi.android.xTubeless.Global;

public class PostMessagesRequest {

    private String PageIndex;
    private String PageSize;
    private String Ui;
    private String CreatorUi;
    private String Id;
    private String PostId;

    public String getPageIndex() {
        return PageIndex;
    }

    public void setPageIndex(String pageIndex) {
        PageIndex = pageIndex;
    }

    public String getPageSize() {
        return PageSize;
    }

    public void setPageSize(String pageSize) {
        PageSize = pageSize;
    }

    public String getUi() {
        return Ui;
    }

    public void setUi(String ui) {
        Ui = ui;
    }

    public String getCreatorUi() {
        return CreatorUi;
    }

    public void setCreatorUi(String creatorUi) {
        CreatorUi = creatorUi;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getPostId() {
        return PostId;
    }

    public void setPostId(String postId) {
        PostId = postId;
    }

}
