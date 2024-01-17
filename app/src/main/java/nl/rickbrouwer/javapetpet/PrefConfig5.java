package nl.rickbrouwer.javapetpet;

import android.content.Context;
import android.content.SharedPreferences;

public class PrefConfig5 {

    private static final String COINS = "nl.rickbrouwer.javapetpet";
    public static final String PREF_FIFTH_KEY = "PREF_FIFTH_KEY";




    public static void saveTotalInPref(Context context, int coins) {
        SharedPreferences pref = context.getSharedPreferences(COINS, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putInt(PREF_FIFTH_KEY, coins);
        editor.apply();
    }

    public static int loadTotalFromPref(Context context) {
        SharedPreferences pref = context.getSharedPreferences(COINS, Context.MODE_PRIVATE);
        return pref.getInt(PREF_FIFTH_KEY, 0);
    }

}
