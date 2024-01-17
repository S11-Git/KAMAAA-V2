package nl.rickbrouwer.javapetpet;

import android.content.Context;
import android.content.SharedPreferences;

public class Pet2PrefConfig5 {

    private static final String COINS = "nl.rickbrouwer.javapetpet";
    public static final String PET2_FIFTH_KEY = "PET2_FIFTH_KEY";




    public static void saveTotalInPref(Context context, int coins) {
        SharedPreferences pref = context.getSharedPreferences(COINS, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putInt(PET2_FIFTH_KEY, coins);
        editor.apply();
    }

    public static int loadTotalFromPref(Context context) {
        SharedPreferences pref = context.getSharedPreferences(COINS, Context.MODE_PRIVATE);
        return pref.getInt(PET2_FIFTH_KEY, 0);
    }

}
