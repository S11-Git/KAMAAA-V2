package nl.rickbrouwer.javapetpet;

import android.content.Context;
import android.content.SharedPreferences;

public class Pet2PrefConfig {

    private static final String HUNGER = "nl.rickbrouwer.javapetpet";
    public static final String PET2_FIRST_KEY = "PET2_FIRST_KEY";




    public static void saveTotalInPref(Context context, int hunger) {
        SharedPreferences pref = context.getSharedPreferences(HUNGER, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putInt(PET2_FIRST_KEY, hunger);
        editor.apply();
    }

    public static int loadTotalFromPref(Context context) {
        SharedPreferences pref = context.getSharedPreferences(HUNGER, Context.MODE_PRIVATE);
        return pref.getInt(PET2_FIRST_KEY, 100);
    }

}
