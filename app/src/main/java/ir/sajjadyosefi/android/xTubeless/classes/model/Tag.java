package ir.sajjadyosefi.android.xTubeless.classes.model;

import java.util.List;

/**
 * Created by sajjad on 8/7/2017.
 */

public class Tag {


    public  int ID;
    public String Title;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }


    public static String getTagListAsString(List<Tag> list) {
        String result = "+";
        for (int i = 0; i < list.size(); i++) {
            result += " " + list.get(i).getTitle();
        }
        return result;
    }
}
