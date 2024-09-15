package com.example.assignmenttwo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;


import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

/**
 * The MainActivity class serves as the entry point to the application.
 * It includes options to navigate to the game (GameActivity) or the leaderboard (LeaderboardActivity).
 * The activity supports edge-to-edge display and handles system insets.
 */
public class MainActivity extends AppCompatActivity {

    /**
     * Called when the activity is first created. Sets up the layout, enables edge-to-edge display,
     * and manages system insets for the status bar and navigation bar.
     *
     * @param savedInstanceState The saved state of the activity, if previously saved.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    /**
     * Handles the play button click event. Navigates to the GameActivity.
     *
     * @param v The view (button) that was clicked.
     */
    public void onclickPlay(View v){
        Intent intent = new Intent(MainActivity.this, GameActivity.class);
        startActivity(intent);
    }

    /**
     * Handles the leaderboard button click event. Navigates to the LeaderboardActivity.
     *
     * @param v The view (button) that was clicked.
     */
    public void onclickLeaderboard(View v){
        Intent intent = new Intent(MainActivity.this, LeaderboardActivity.class);
        startActivity(intent);
    }
}