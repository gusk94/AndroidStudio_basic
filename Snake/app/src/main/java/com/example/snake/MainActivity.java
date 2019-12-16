package com.example.snake;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    private SnakeView mSnakeView;
    private TextView mTime;
    private TextView mScore;
    private TextView mSpeed;

    private static String ICICLE_KEY = "snake-view";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // No Title bar
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.activity_main);

        mTime = findViewById(R.id.time);
        mScore = findViewById(R.id.score);
        mSpeed = findViewById(R.id.speed);

        mSnakeView = findViewById(R.id.snake);
        mSnakeView.setTextView((TextView) findViewById(R.id.text));
        mTime.setText("00 : 00 : 00");
        mScore.setText("Score : " + 0);
        mSpeed.setText("Delay : " + 0);
        mSnakeView.setTextView(mTime, mScore, mSpeed);

        if (savedInstanceState == null) {
            // We were just launched -- set up a new game
            mSnakeView.setMode(SnakeView.READY);
        } else {
            // We are being restored
            Bundle map = savedInstanceState.getBundle(ICICLE_KEY);
            if (map != null) {
                mSnakeView.restoreState(map);
            } else {
                mSnakeView.setMode(SnakeView.PAUSE);
            }
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        // Pause the game along with the activity
        mSnakeView.setMode(SnakeView.PAUSE);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        //Store the game state
        super.onSaveInstanceState(outState);
        outState.putBundle(ICICLE_KEY, mSnakeView.saveState());
    }

    public void onUpClicked(View v) {
        mSnakeView.processKey(1);
    }

    public void onDownClicked(View v) {
        mSnakeView.processKey(2);
    }

    public void onRightClicked(View v) {
        mSnakeView.processKey(3);
    }

    public void onLeftClicked(View v) {
        mSnakeView.processKey(4);
    }

}
