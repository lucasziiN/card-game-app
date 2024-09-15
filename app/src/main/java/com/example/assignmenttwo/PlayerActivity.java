package com.example.assignmenttwo;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class PlayerActivity extends AppCompatActivity {

    private int avatarID, playerScore;  // The selected avatar ID and player's score
    private String playerName;  // The player's name
    private Intent intent;  // Intent for passing data between activities
    private Leaderboard leaderboardInstance;  // Singleton instance of the leaderboard

    private ArrayList<String> avatarList = new ArrayList<>(); // List of avatar names

    public Player currPlayer; // The current player

    /**
     * Called when the activity is first created. Sets up the layout, retrieves the player's score,
     * and initializes the leaderboard instance and avatar list.
     *
     * @param savedInstanceState The saved state of the activity, if previously saved.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_player);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Retrieve the totalGuesses (playerScore) from the Intent
        Intent intent = getIntent();
        playerScore = intent.getIntExtra("playerScore", 0);  // 0 is the default value if nothing is passed

        // Display the player's score in a TextView
        TextView textViewPlayerScore = findViewById(R.id.tv_playerscore);
        textViewPlayerScore.setText("Score: " + playerScore);

        // Add avatar options to the avatarList
        avatarList.add("artist");
        avatarList.add("astronaut");
        avatarList.add("coder");
        avatarList.add("doctor");
        avatarList.add("police");
        avatarList.add("scientist");

        // Initialize the leaderboard instance
        leaderboardInstance = Leaderboard.getLeaderboardInstance();
    }

    /**
     * Called when the submit button is clicked. It retrieves the player's name, selected avatar, and score.
     * The information is then submitted to the leaderboard, and the LeaderboardActivity is started.
     *
     * @param view The view (button) that was clicked.
     */
    public void onclickSubmit(View view){

        // Retrieve the player's name from an EditText
        EditText editTextPlayerName = findViewById(R.id.et_playername);
        playerName = editTextPlayerName.getText().toString();  // Get the player's name

        // Retrieve the selected avatar from the RadioGroup
        RadioGroup radioGroupAvatar = findViewById(R.id.rg_avatar);
        int selectedAvatarRadioId = radioGroupAvatar.getCheckedRadioButtonId();  // Get the ID of the selected radio button

        // Ensure that an avatar was selected
        if (selectedAvatarRadioId == -1) {
            Toast.makeText(this, "Please select an avatar.", Toast.LENGTH_SHORT).show();
            return;  // Exit if no selection was made
        }

        // Retrieve the selected RadioButton and get the avatar tag
        RadioButton selectedRadioButton = findViewById(selectedAvatarRadioId);
        String avatarTag = selectedRadioButton.getTag().toString();

        // Convert the avatarTag to an integer (the avatar index)
        avatarID = Integer.parseInt(avatarTag);

        // Create a Player object using the mapped avatar index
        currPlayer = new Player(this, playerName, avatarID, playerScore);

        // Update the leaderboard
        leaderboardInstance.updateLeaderboard(currPlayer);


        // Create an intent to start the LeaderboardActivity and pass player data
        Intent intent = new Intent(PlayerActivity.this, LeaderboardActivity.class);
        intent.putExtra("playerName", playerName);
        intent.putExtra("playerScore", playerScore);
        intent.putExtra("avatarID", avatarID);  // Pass the avatar index

        // Start LeaderboardActivity
        startActivity(intent);
    }


}