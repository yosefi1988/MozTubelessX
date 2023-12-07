package ir.sajjadyosefi.android.xTubeless.utility;


import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import java.lang.reflect.Field;

import static ir.sajjadyosefi.android.xTubeless.widget.CustomEditText.FONT_IRANSANS_MOBILE_NORMAL_TTF;


/**
 * Class used to replace default typeface with custom typeface
 * <p>
 * author Radheshyam
 * date 04/December/2018
 */
public class FontChanger {

    /**
     * Method used to override the default fonts with custom fonts.
     *
     */
    public static void overrideDefaultFont(Context context,  String staticTypefaceFieldName, String fontAssetName) {
        final Typeface regular = Typeface.createFromAsset(context.getAssets(),
                fontAssetName);
        replaceFont(staticTypefaceFieldName, regular);
    }

    /**
     * This method uses reflection to access the typeface information
     * and then override the same.
     */
    private static void replaceFont(String staticTypefaceFieldName, final Typeface newTypeface) {
        try {
            final Field staticField = Typeface.class
                    .getDeclaredField(staticTypefaceFieldName);
            staticField.setAccessible(true);
            staticField.set(null, newTypeface);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }




    //font 2
    public static void overrideFonts(final Context context, final View v) {
        try {
            if (v instanceof ViewGroup) {
                ViewGroup vg = (ViewGroup) v;
                for (int i = 0; i < vg.getChildCount(); i++) {
                    View child = vg.getChildAt(i);
                    overrideFonts(context, child);
                }
            } else if (v instanceof EditText) {
                ((TextView) v).setTypeface(Typeface.createFromAsset(context.getAssets(), FONT_IRANSANS_MOBILE_NORMAL_TTF));
            }
        } catch (Exception e) {
        }
    }

}