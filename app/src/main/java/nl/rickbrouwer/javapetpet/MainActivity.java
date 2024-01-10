package nl.rickbrouwer.javapetpet;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button Foodbutton = findViewById(R.id.FoodButton);
        Button Drinkbutton = findViewById(R.id.DrinkButton);
        Button Ticklebutton = findViewById(R.id.TickleButton);
        TextView Hungertext = findViewById(R.id.HungerText);
        TextView Thirsttext = findViewById(R.id.ThirstText);
        TextView Healthtext = findViewById(R.id.HealthText);
        TextView Moodtext = findViewById(R.id.MoodText);
        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) ImageView Charimg = findViewById(R.id.CharImg);

         AtomicInteger Hunger = new AtomicInteger(100);
         AtomicInteger Thirst = new AtomicInteger(100);
         int Health = 100;
         AtomicInteger Mood = new AtomicInteger(100);

        Hungertext.setText("Hunger: " + Hunger);
        Thirsttext.setText("Thirst: " + Thirst);
        Healthtext.setText("Health: " + Health);
        Moodtext.setText("Mood: " + Mood);

        Foodbutton.setOnClickListener(v -> {
            Hunger.addAndGet(10);
            Hungertext.setText("Hunger: " + Hunger);
        });

        Drinkbutton.setOnClickListener(v -> {
            Thirst.addAndGet(10);
            Thirsttext.setText("Thirst: " + Thirst);
        });

        Ticklebutton.setOnClickListener(v -> {
            Mood.addAndGet(10);
            Moodtext.setText("Mood: " + Mood);
        });

        ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
        scheduler.scheduleAtFixedRate(() -> {
            runOnUiThread(() -> {
                Hunger.addAndGet(-1);
                Thirst.addAndGet(-1);
                Mood.addAndGet(-1);
                Hungertext.setText("Hunger: " + Hunger);
                Thirsttext.setText("Thirst: " + Thirst);
                Moodtext.setText("Mood: " + Mood);
            });
        }, 0, 1000, TimeUnit.MILLISECONDS);


    }
}