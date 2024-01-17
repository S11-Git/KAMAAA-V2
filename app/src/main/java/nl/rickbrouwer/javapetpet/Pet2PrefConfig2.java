package nl.rickbrouwer.javapetpet;

import android.content.Context;
import android.content.SharedPreferences;

public class Pet2PrefConfig2 {

    private static final String THIRST = "nl.rickbrouwer.javapetpet";
    public static final String PET2_SECOND_KEY = "PET2_SECOND_KEY";




    public static void saveTotalInPref(Context context, int thirst) {
        SharedPreferences pref = context.getSharedPreferences(THIRST, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putInt(PET2_SECOND_KEY, thirst);
        editor.apply();
    }

    public static int loadTotalFromPref(Context context) {
        SharedPreferences pref = context.getSharedPreferences(THIRST, Context.MODE_PRIVATE);
        return pref.getInt(PET2_SECOND_KEY, 100);
    }

}
