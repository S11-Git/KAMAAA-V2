package nl.rickbrouwer.javapetpet;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class MainActivity2 extends AppCompatActivity {
    private int Hunger;
    private int Thirst;
    private int Mood;
    private int DeathCounter;
    private int Coins;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Button Foodbutton = findViewById(R.id.FoodButton);
        Button Drinkbutton = findViewById(R.id.DrinkButton);
        Button Ticklebutton = findViewById(R.id.TickleButton);
        TextView Hungertext = findViewById(R.id.HungerText);
        TextView Thirsttext = findViewById(R.id.ThirstText);
        TextView Moodtext = findViewById(R.id.MoodText);
        TextView DeathCountText = findViewById(R.id.DeathCountText);
        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) ImageView Charimg = findViewById(R.id.CharImg);

        Button gotoma1 = findViewById(R.id.GoToMA1);



        Hunger = Pet2PrefConfig.loadTotalFromPref(this);
        Thirst = Pet2PrefConfig2.loadTotalFromPref(this);
        Mood = Pet2PrefConfig3.loadTotalFromPref(this);
        DeathCounter = Pet2PrefConfig4.loadTotalFromPref(this);
        Coins = Pet2PrefConfig5.loadTotalFromPref(this);

        Hungertext.setText("Hunger: " + Hunger);
        Thirsttext.setText("Thirst: " + Thirst);
        Moodtext.setText("Mood: " + Mood);

        Foodbutton.setOnClickListener(v -> {
            if (Coins < 10) {
                Toast.makeText(this, "Not enough coins", Toast.LENGTH_SHORT).show();
                return;
            } else {
                Hunger += 10;
                Coins -= 10;
                HungerSave();
                CoinsSave();
            }
        });

        Drinkbutton.setOnClickListener(v -> {
            if (Coins < 10) {
                Toast.makeText(this, "Not enough coins", Toast.LENGTH_SHORT).show();
                return;
            } else {
                Thirst += 10;
                Coins -= 10;
                ThirstSave();
                CoinsSave();
            }
        });

        Ticklebutton.setOnClickListener(v -> {
            Mood += 10;
            MoodSave();
        });

        ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
        scheduler.scheduleAtFixedRate(() -> runOnUiThread(() -> {
            Hunger -= 2;
            Thirst -= 2;
            Mood -= 1;
            CoinsBOM();
            HungerSave();
            ThirstSave();
            MoodSave();
            Log.d("MainActivity2", "Running the NANALAN");
        }), 0, 2, TimeUnit.SECONDS);

        ScheduledExecutorService scheduler2 = Executors.newSingleThreadScheduledExecutor();
        scheduler2.scheduleAtFixedRate(() -> runOnUiThread(() -> {
            DeathCheck();
            DrawCharacter();
            Prevent101();
            DrawText();
        }), 0, 5, TimeUnit.MILLISECONDS);

        gotoma1.setOnClickListener(v -> {
            // Stop the schedulers before switching to MainActivity2
            if (scheduler != null && !scheduler.isShutdown()) {
                scheduler.shutdown();
            }
            if (scheduler2 != null && !scheduler2.isShutdown()) {
                scheduler2.shutdown();
            }

            // Create an Intent to start MainActivity2
            Intent intent = new Intent(v.getContext(), MainActivity.class);

            // Start the new activity
            v.getContext().startActivity(intent);

            // Finish the current activity
            if (v.getContext() instanceof Activity) {
                ((Activity) v.getContext()).finish();
            } else {
                Log.d("MainActivity2", "Not an instance of Activity");
            }
        });

    }


    @SuppressLint("SetTextI18n")
    public void DeathCount() {
        DeathCounter += 1;
        DeathSave();
        Death();
    }

    public void Death() {
        Toast.makeText(this, "He fucking dead...", Toast.LENGTH_LONG).show();
        Hunger = 100;
        Thirst = 100;
        Mood = 100;
        Coins = 0;
        HungerSave();
        ThirstSave();
        MoodSave();
        CoinsSave();
    }

    public void HungerSave() {
        Pet2PrefConfig.saveTotalInPref(this, Hunger);
    }

    public void ThirstSave() {
        Pet2PrefConfig2.saveTotalInPref(this, Thirst);
    }

    public void MoodSave() {
        Pet2PrefConfig3.saveTotalInPref(this, Mood);
    }

    public void DeathSave() {
        Pet2PrefConfig4.saveTotalInPref(this, DeathCounter);
    }

    public void CoinsSave() {
        Pet2PrefConfig5.saveTotalInPref(this, Coins);
    }

    public void DrawCharacter() {
        ImageView Charimg = findViewById(R.id.CharImg);
        if(Mood == 0) {
            Charimg.setImageResource(R.drawable.dead2);
        } else if (Mood < 25) {
            Charimg.setImageResource(R.drawable.sad2);
        } else if (Mood < 75) {
            Charimg.setImageResource(R.drawable.neutral2);
        } else {
            Charimg.setImageResource(R.drawable.happy2);
        }
    }

    public void DeathCheck() {
        if (Hunger < 1 || Thirst < 1) {
            DeathCount();
        }
    }

    public void Prevent101() {
        Hunger = Math.min(Hunger, 100);
        Thirst = Math.min(Thirst, 100);
        Mood = Math.min(Mood, 100);
    }

    public void DrawText() {
        TextView Hungertext = findViewById(R.id.HungerText);
        TextView Thirsttext = findViewById(R.id.ThirstText);
        TextView Moodtext = findViewById(R.id.MoodText);
        TextView DeathCountText = findViewById(R.id.DeathCountText);
        TextView CoinText = findViewById(R.id.CoinText2);
        Hungertext.setText("Hunger: " + Hunger);
        Thirsttext.setText("Thirst: " + Thirst);
        Moodtext.setText("Mood: " + Mood);
        DeathCountText.setText("Death Count: " + DeathCounter);
        CoinText.setText("Coins: " + Coins);
    }

    public void CoinsBOM() {
        if (Mood < 25) {
            Coins += 2; // Adjusted from 1
        } else if (Mood < 75) {
            Coins += 4; // Adjusted from 3
        } else {
            Coins += 5; // Adjusted from 4
        }
        CoinsSave();
    }
}