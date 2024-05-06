package ir.sajjadyosefi.android.xTubeless.classes.model.request;


public class CategoriesLookUpRequest {
    private long CategoryCode;
    private long ParentId;

    public long getCategoryCode() {
        return CategoryCode;
    }

    public void setCategoryCode(long categoryCode) {
        CategoryCode = categoryCode;
    }

    public long getParentId() {
        return ParentId;
    }

    public void setParentId(long parentId) {
        ParentId = parentId;
    }

}
