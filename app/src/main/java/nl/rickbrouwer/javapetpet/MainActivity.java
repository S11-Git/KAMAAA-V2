package nl.rickbrouwer.javapetpet;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    private int Hunger;
    private int Thirst;
    private int Mood;
    private int DeathCounter;
    private int Coins;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button Foodbutton = findViewById(R.id.FoodButton);
        Button Drinkbutton = findViewById(R.id.DrinkButton);
        Button Ticklebutton = findViewById(R.id.TickleButton);
        TextView Hungertext = findViewById(R.id.HungerText);
        TextView Thirsttext = findViewById(R.id.ThirstText);
        TextView Moodtext = findViewById(R.id.MoodText);
        TextView DeathCountText = findViewById(R.id.DeathCountText);
        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) ImageView Charimg = findViewById(R.id.CharImg);

        Hunger = PrefConfig.loadTotalFromPref(this);
        Thirst = PrefConfig2.loadTotalFromPref(this);
        Mood = PrefConfig3.loadTotalFromPref(this);
        DeathCounter = PrefConfig4.loadTotalFromPref(this);
        Coins = PrefConfig5.loadTotalFromPref(this);

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
        }), 0, 2, TimeUnit.SECONDS);

        ScheduledExecutorService scheduler2 = Executors.newSingleThreadScheduledExecutor();
        scheduler2.scheduleAtFixedRate(() -> runOnUiThread(() -> {
            DeathCheck();
            DrawCharacter();
            Prevent101();
            DrawText();
        }), 0, 5, TimeUnit.MILLISECONDS);
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
        PrefConfig.saveTotalInPref(this, Hunger);
    }

    public void ThirstSave() {
        PrefConfig2.saveTotalInPref(this, Thirst);
    }

    public void MoodSave() {
        PrefConfig3.saveTotalInPref(this, Mood);
    }

    public void DeathSave() {
        PrefConfig4.saveTotalInPref(this, DeathCounter);
    }

    public void CoinsSave() {
        PrefConfig5.saveTotalInPref(this, Coins);
    }

    public void DrawCharacter() {
        ImageView Charimg = findViewById(R.id.CharImg);
        if(Mood == 0) {
            Charimg.setImageResource(R.drawable.dead);
        } else if (Mood < 25) {
            Charimg.setImageResource(R.drawable.sad);
        } else if (Mood < 75) {
            Charimg.setImageResource(R.drawable.neutral);
        } else {
            Charimg.setImageResource(R.drawable.happy);
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
