package ir.sajjadyosefi.android.xTubeless.utility;

import java.util.Date;
import java.util.Locale;

/**
 * Created by sajjad on 08/18/2016.
 */
public class DateConverterSjd {

    public String getCurrentShamsidate() {
        Locale loc = new Locale("en_US");
        SolarCalendar sc = new SolarCalendar();
        return String.valueOf(sc.year) + "/" + String.format(loc, "%02d",
                sc.month) + "/" + String.format(loc, "%02d", sc.date);
    }


    public Locale getLocaleFromString(String localeString)
    {
        if (localeString == null)
        {
            return null;
        }
        localeString = localeString.trim();
        if (localeString.toLowerCase().equals("default"))
        {
            return Locale.getDefault();
        }

        // Extract language
        int languageIndex = localeString.indexOf('_');
        String language = null;
        if (languageIndex == -1)
        {
            // No further "_" so is "{language}" only
            return new Locale(localeString, "");
        }
        else
        {
            language = localeString.substring(0, languageIndex);
        }

        // Extract country
        int countryIndex = localeString.indexOf('_', languageIndex + 1);
        String country = null;
        if (countryIndex == -1)
        {
            // No further "_" so is "{language}_{country}"
            country = localeString.substring(languageIndex+1);
            return new Locale(language, country);
        }
        else
        {
            // Assume all remaining is the variant so is "{language}_{country}_{variant}"
            country = localeString.substring(languageIndex+1, countryIndex);
            String variant = localeString.substring(countryIndex+1);
            return new Locale(language, country, variant);
        }
    }

    public String getCurrentShamsidate(String Date) {
        Locale loc = getLocaleFromString(Date);
        SolarCalendar util = new SolarCalendar();
        SolarCalendar sc = new SolarCalendar();
        return String.valueOf(sc.year) + "/" + String.format(loc, "%02d",
                sc.month) + "/" + String.format(loc, "%02d", sc.date);
    }

    public String getCurrentShamsidateModel(String Date) {
        Locale loc = getLocaleFromString(Date);
        SolarCalendar sc = new SolarCalendar();
        return String.valueOf(sc.year) + "/" + String.format(loc, "%02d", sc.month) ;
    }
}
