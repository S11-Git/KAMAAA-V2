package nl.rickbrouwer.javapetpet;

import android.content.Context;
import android.content.SharedPreferences;

public class PrefConfig {

    private static final String HUNGER = "nl.rickbrouwer.javapetpet";
    public static final String PREF_FIRST_KEY = "PREF_FIRST_KEY";




    public static void saveTotalInPref(Context context, int hunger) {
        SharedPreferences pref = context.getSharedPreferences(HUNGER, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putInt(PREF_FIRST_KEY, hunger);
        editor.apply();
    }

    public static int loadTotalFromPref(Context context) {
        SharedPreferences pref = context.getSharedPreferences(HUNGER, Context.MODE_PRIVATE);
        return pref.getInt(PREF_FIRST_KEY, 100);
    }

}
