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

        Hungertext.setText("Hunger: " + Hunger);
        Thirsttext.setText("Thirst: " + Thirst);
        Moodtext.setText("Mood: " + Mood);


        Foodbutton.setOnClickListener(v -> {
            Hunger = Hunger + 10;
            HungerSave();
        });

        Drinkbutton.setOnClickListener(v -> {
            Thirst = Thirst + 10;
            ThirstSave();
        });

        Ticklebutton.setOnClickListener(v -> {
            Mood = Mood + 10;
            MoodSave();
        });

        ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
        scheduler.scheduleAtFixedRate(() -> runOnUiThread(() -> {
            Hunger = Hunger - 2;
            Thirst = Thirst - 2;
            Mood = Mood - 1;
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
        HungerSave();
        ThirstSave();
        MoodSave();
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

    public void DrawCharacter() {
        ImageView Charimg = findViewById(R.id.CharImg);
        if(Mood == 0) {
            Charimg.setImageResource(R.drawable.dead);
        } else if (Mood < 25) {
            Charimg.setImageResource(R.drawable.sad);
        } else if (Mood < 50) {
            Charimg.setImageResource(R.drawable.neutral);
        } else {
            Charimg.setImageResource(R.drawable.happy);
        }
    }

    public void DeathCheck() {
        if (Hunger < 1 || Thirst < 1 || Mood < 1) {
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
        Hungertext.setText("Hunger: " + Hunger);
        Thirsttext.setText("Thirst: " + Thirst);
        Moodtext.setText("Mood: " + Mood);
        DeathCountText.setText("Death Count: " + DeathCounter);
    }

}
