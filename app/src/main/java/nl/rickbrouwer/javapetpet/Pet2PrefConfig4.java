package nl.rickbrouwer.javapetpet;

import android.content.Context;
import android.content.SharedPreferences;

public class Pet2PrefConfig4 {

    private static final String DEATHS = "nl.rickbrouwer.javapetpet";
    public static final String PET2_FOURTH_KEY = "PET2_FOURTH_KEY";




    public static void saveTotalInPref(Context context, int deaths) {
        SharedPreferences pref = context.getSharedPreferences(DEATHS, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putInt(PET2_FOURTH_KEY, deaths);
        editor.apply();
    }

    public static int loadTotalFromPref(Context context) {
        SharedPreferences pref = context.getSharedPreferences(DEATHS, Context.MODE_PRIVATE);
        return pref.getInt(PET2_FOURTH_KEY, 0);
    }

}
