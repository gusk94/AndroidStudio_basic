package com.example.snake;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    private SnakeView snakeView;
    private TextView time;
    private TextView score;
    private TextView speed;

    private static String ICICLE_KEY = "snake-view";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // No Title bar
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.activity_main);

        time = findViewById(R.id.time);
        score = findViewById(R.id.score);
        speed = findViewById(R.id.speed);

        snakeView = findViewById(R.id.snake);
        snakeView.setTextView((TextView) findViewById(R.id.text));
        time.setText("00 : 00 : 00");
        score.setText("Score : " + 0);
        speed.setText("Delay : " + 0);
        snakeView.setTextView(time, score, speed);

        if (savedInstanceState == null) {
            snakeView.setMode(SnakeView.READY);
        } else {
            Bundle map = savedInstanceState.getBundle(ICICLE_KEY);
            if (map != null) {
                snakeView.restoreState(map);
            } else {
                snakeView.setMode(SnakeView.PAUSE);
            }
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        snakeView.setMode(SnakeView.PAUSE);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBundle(ICICLE_KEY, snakeView.saveState());
    }

    public void onUpClicked(View v) {
        snakeView.processKey(1);
    }

    public void onDownClicked(View v) {
        snakeView.processKey(2);
    }

    public void onRightClicked(View v) {
        snakeView.processKey(3);
    }

    public void onLeftClicked(View v) {
        snakeView.processKey(4);
    }

}
