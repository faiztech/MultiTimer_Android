package tech.faiz.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    private ImageButton mTimerButton;
    private ImageButton mMultiTimerButton;
    private ImageButton mStopwatchButton;
    private ImageButton mAlarmButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        initListeners();


    }

    private void initViews() {

        mTimerButton = (ImageButton) findViewById(R.id.timer_button);
        mMultiTimerButton = (ImageButton) findViewById(R.id.multitimer_button);
        mStopwatchButton = (ImageButton) findViewById(R.id.stopwatch_button);
        mAlarmButton = (ImageButton) findViewById(R.id.alarm_button);

    }

    private void initListeners() {
        mTimerButton.setOnClickListener(this);
        mMultiTimerButton.setOnClickListener(this);
        mStopwatchButton.setOnClickListener(this);
        mAlarmButton.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.timer_button:
                Intent intent = new Intent(getApplicationContext(), SingleTimerActivity.class);
                startActivity(intent);
                break;
            case R.id.multitimer_button:
                Intent intent2 = new Intent(getApplicationContext(), MultiTimerActivity.class);
                startActivity(intent2);
                break;
            case R.id.stopwatch_button:
                Toast.makeText(this, "Coming Soon...", Toast.LENGTH_LONG).show();
                break;
            case R.id.alarm_button:
                Toast.makeText(this, "Coming Soon...", Toast.LENGTH_LONG).show();
                break;

        }
    }

}