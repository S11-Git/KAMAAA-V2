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
import java.util.concurrent.atomic.AtomicInteger;

public class MainActivity extends AppCompatActivity {

    private AtomicInteger Hunger;
    private AtomicInteger Thirst;
    private AtomicInteger Mood;

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

        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) ImageView Charimg = findViewById(R.id.CharImg);

        Hunger = new AtomicInteger(100);
        Thirst = new AtomicInteger(100);
        Mood = new AtomicInteger(100);

        Hungertext.setText("Hunger: " + Hunger);
        Thirsttext.setText("Thirst: " + Thirst);
        Moodtext.setText("Mood: " + Mood);

        Foodbutton.setOnClickListener(v -> {
            Hunger.addAndGet(10);
        });

        Drinkbutton.setOnClickListener(v -> {
            Thirst.addAndGet(10);
        });

        Ticklebutton.setOnClickListener(v -> {
            Mood.addAndGet(10);
        });

        ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
        scheduler.scheduleAtFixedRate(() -> runOnUiThread(() -> {
            Hunger.addAndGet(-1);
            Thirst.addAndGet(-1);
            Mood.addAndGet(-1);
        }), 0, 1000, TimeUnit.MILLISECONDS);

        ScheduledExecutorService scheduler2 = Executors.newSingleThreadScheduledExecutor();
        scheduler2.scheduleAtFixedRate(() -> runOnUiThread(() -> {
            if (Hunger.get() > 100) {
                Hunger.set(100);
            }

            if (Thirst.get() > 100) {
                Thirst.set(100);
            }

            if (Mood.get() > 100) {
                Mood.set(100);
            }

            if (Hunger.get() < 0) {
                DeathCount();
            }

            if (Thirst.get() < 0) {
                DeathCount();
            }

            if (Mood.get() < 0) {
                DeathCount();
            }

            Hungertext.setText("Hunger: " + Hunger);
            Thirsttext.setText("Thirst: " + Thirst);
            Moodtext.setText("Mood: " + Mood);
        }), 0, 5, TimeUnit.MILLISECONDS);
    }

    @SuppressLint("SetTextI18n")
    public void DeathCount() {
        TextView DeathCountText = findViewById(R.id.DeathCountText);
        int DeathCounter = 0;
        DeathCounter++;
        DeathCountText.setText("Death Count: " + DeathCounter);
        Death();
    }

    public void Death() {
        Toast.makeText(this, "He fucking dead...", Toast.LENGTH_LONG).show();
        Hunger.set(100);
        Thirst.set(100);
        Mood.set(100);
    }
}
