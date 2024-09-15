package com.example.assignmenttwo;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

/**
 * The LeaderboardActivity class is responsible for displaying the leaderboard
 * by retrieving the singleton instance of the Leaderboard class and calling
 * its display method. It also ensures edge-to-edge screen layout and adjusts UI
 * based on system insets (e.g., status and navigation bars).
 */
public class LeaderboardActivity extends AppCompatActivity {

    // Reference to the Leaderboard instance
    private Leaderboard leaderboardInstance;

    /**
     * Called when the activity is first created. Sets up the layout, handles edge-to-edge display,
     * and calls the displayLeaderboard method to update the leaderboard view.
     *
     * @param savedInstanceState The saved state of the activity, if previously saved.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Enable edge-to-edge layout
        super.onCreate(savedInstanceState);

        // Set the layout for the leaderboard activity
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_leaderboard);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Retrieve the instance of leaderboard and display it
        Leaderboard leaderboardInstance = Leaderboard.getLeaderboardInstance();
        leaderboardInstance.displayLeaderboard(this);
    }


}