package com.example.assignmenttwo;


import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

/**
 * The GameActivity class represents the main activity where the game is played.
 * It initializes the game environment and handles the setup for the gameplay.
 */
public class GameActivity extends AppCompatActivity {

    private GamePlay gamePlay; // Holds the GamePlay object to manage the game logic

    /**
     * Called when the activity is first created.
     * It sets up the edge-to-edge layout and initializes the game setup.
     *
     * @param savedInstanceState If the activity is being re-initialized after previously
     * being shut down, this contains the data it most recently supplied.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this); // Enables edge-to-edge display

        // Set the layout for the activity
        setContentView(R.layout.activity_game);

        // Adjust the layout to account for system bars (status bar, navigation bar, etc.)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        startGame();
    }

    /**
     * Initializes the GamePlay object and starts the game by setting it up.
     */
    public void startGame(){
        // Initialize the gamePlay object and setup the game
        gamePlay = new GamePlay(GameActivity.this);
        gamePlay.setupGame();
    }
}