package nl.rickbrouwer.javapetpet;

import android.content.Context;
import android.content.SharedPreferences;

public class Pet2PrefConfig3 {

    private static final String MOOD = "nl.rickbrouwer.javapetpet";
    public static final String PET2_THIRD_KEY = "PET2_THIRD_KEY";




    public static void saveTotalInPref(Context context, int mood) {
        SharedPreferences pref = context.getSharedPreferences(MOOD, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putInt(PET2_THIRD_KEY, mood);
        editor.apply();
    }

    public static int loadTotalFromPref(Context context) {
        SharedPreferences pref = context.getSharedPreferences(MOOD, Context.MODE_PRIVATE);
        return pref.getInt(PET2_THIRD_KEY, 100);
    }

}
