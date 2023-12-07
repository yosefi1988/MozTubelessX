package ir.sajjadyosefi.android.xTubeless.classes.model;
import ir.sajjadyosefi.android.xTubeless.classes.model.user.User;

/**
 * Created by sajjad on 1/20/2018.
 */

public class BlogItem {
    private int ID;
    private int ApplicationID;

    private String Title;
    private String TitlePicture;
    private String Statement;
    private String TextPicture;
    private String Text;
    //private Datetim Date
    private int ViewCount;
    private boolean ViewInList;
    private boolean InMyFavList;
    private boolean IsOwner ;
    private User User;
    private int UserID;
    private int CategoryID;

//    private Category category;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getApplicationID() {
        return ApplicationID;
    }

    public void setApplicationID(int applicationID) {
        ApplicationID = applicationID;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getTitlePicture() {
        return TitlePicture;
    }

    public void setTitlePicture(String titlePicture) {
        TitlePicture = titlePicture;
    }

    public String getStatement() {
        return Statement;
    }

    public void setStatement(String statement) {
        Statement = statement;
    }

    public String getTextPicture() {
        return TextPicture;
    }

    public void setTextPicture(String textPicture) {
        TextPicture = textPicture;
    }


    public boolean isOwner() {
        return IsOwner;
    }

    public void setOwner(boolean owner) {
        IsOwner = owner;
    }

    public String getText() {
        return Text;
    }

    public void setText(String text) {
        Text = text;
    }

    public int getViewCount() {
        return ViewCount;
    }

    public void setViewCount(int viewCount) {
        ViewCount = viewCount;
    }

    public boolean isViewInList() {
        return ViewInList;
    }

    public void setViewInList(boolean viewInList) {
        ViewInList = viewInList;
    }

    public boolean isInMyFavList() {
        return InMyFavList;
    }

    public void setInMyFavList(boolean inMyFavList) {
        InMyFavList = inMyFavList;
    }

    public ir.sajjadyosefi.android.xTubeless.classes.model.user.User getUser() {
        return User;
    }

    public void setUser(User user) {
        User = user;
    }

    public int getUserID() {
        return UserID;
    }

    public void setUserID(int userID) {
        UserID = userID;
    }

    public int getCategoryID() {
        return CategoryID;
    }

    public void setCategoryID(int categoryID) {
        CategoryID = categoryID;
    }

//    public Category getCategory() {
//        return category;
//    }
//
//    public void setCategory(Category category) {
//        this.category = category;
//    }



}
