package tech.faiz.myapplication;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.concurrent.TimeUnit;

public class SingleTimerActivity extends AppCompatActivity implements View.OnClickListener {

    private long timeCountInMilliSeconds = 1 * 60000;


    private enum TimerStatus {
        STARTED,
        PAUSED,
        STOPPED
    }

    private TimerStatus mTimerStatus = TimerStatus.STOPPED;

    private ProgressBar mProgressBarCircle;
    private EditText mEditTextMinute;
    private TextView mTextViewTime;
    private ImageView mImageViewReset;
    private ImageView mImageViewStartStop;
    private CountDownTimer mCountDownTimer;
    private EditText mTitleView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_timer);

        // method call to initialize the views
        initViews();
        // method call to initialize the listeners
        initListeners();



    }

    /**
     * method to initialize the views
     */
    private void initViews() {
        mProgressBarCircle = (ProgressBar) findViewById(R.id.progressBarCircle);
        mEditTextMinute = (EditText) findViewById(R.id.editTextMinute);
        mTextViewTime = (TextView) findViewById(R.id.textViewTime);
        mImageViewReset = (ImageView) findViewById(R.id.imageViewReset);
        mImageViewStartStop = (ImageView) findViewById(R.id.imageViewStartStop);
        mTitleView = (EditText) findViewById(R.id.titleTextView);
    }


    private void initListeners() {
        mImageViewReset.setOnClickListener(this);
        mImageViewStartStop.setOnClickListener(this);
    }



    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.imageViewReset:
                reset();
                break;
            case R.id.imageViewStartStop:
                startStop();
                break;
        }

    }



    private void reset() {
        stopCountDownTimer();
        startCountDownTimer();
    }


    /**
     * method to start and stop count down timer
     */
    private void startStop() {
        if (mTimerStatus == TimerStatus.STOPPED) {

            // call to initialize the timer values
            setTimerValues();
            // call to initialize the progress bar values
            setProgressBarValues();
            // showing the reset icon
            mImageViewReset.setVisibility(View.VISIBLE);
            // changing play icon to stop icon
            mImageViewStartStop.setImageResource(R.drawable.icon_stop);
            // making edit text not editable
            mEditTextMinute.setEnabled(false);
            // changing the timer status to started
            mTimerStatus = TimerStatus.STARTED;
            // call to start the count down timer
            startCountDownTimer();

        } else {

            // hiding the reset icon
            mImageViewReset.setVisibility(View.GONE);
            // changing stop icon to start icon
            mImageViewStartStop.setImageResource(R.drawable.icon_start);
            // making edit text editable
            mEditTextMinute.setEnabled(true);
            // changing the timer status to stopped
            mTimerStatus = TimerStatus.STOPPED;
            stopCountDownTimer();

        }

    }

    /**
     * method to initialize the values for count down timer
     */
    private void setTimerValues() {
        int time = 0;
        if (!mEditTextMinute.getText().toString().isEmpty()) {
            // fetching value from edit text and type cast to integer
            time = Integer.parseInt(mEditTextMinute.getText().toString().trim());
        } else {
            // toast message to fill edit text
            Toast.makeText(getApplicationContext(), getString(R.string.message_minutes), Toast.LENGTH_LONG).show();
        }
        // assigning values after converting to milliseconds
        timeCountInMilliSeconds = time * 60 * 1000;
    }

    /**
     * method to start count down timer
     */
    private void startCountDownTimer() {

        mCountDownTimer = new CountDownTimer(timeCountInMilliSeconds, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {

                mTextViewTime.setText(hmsTimeFormatter(millisUntilFinished));

                mProgressBarCircle.setProgress((int) (millisUntilFinished / 1000));

            }

            @Override
            public void onFinish() {

                mTextViewTime.setText(hmsTimeFormatter(timeCountInMilliSeconds));
                // call to initialize the progress bar values
                setProgressBarValues();
                // hiding the reset icon
                mImageViewReset.setVisibility(View.GONE);
                // changing stop icon to start icon
                mImageViewStartStop.setImageResource(R.drawable.icon_start);
                // making edit text editable
                mEditTextMinute.setEnabled(true);
                // changing the timer status to stopped
                mTimerStatus = TimerStatus.STOPPED;
            }

        }.start();
        mCountDownTimer.start();
    }

    /**
     * method to stop count down timer
     */
    private void stopCountDownTimer() {
        mCountDownTimer.cancel();
    }

    /**
     * method to set circular progress bar values
     */
    private void setProgressBarValues() {

        mProgressBarCircle.setMax((int) timeCountInMilliSeconds / 1000);
        mProgressBarCircle.setProgress((int) timeCountInMilliSeconds / 1000);
    }


    /**
     * method to convert millisecond to time format
     *
     * @param milliSeconds
     * @return HH:mm:ss time formatted string
     */
    private String hmsTimeFormatter(long milliSeconds) {

        String hms = String.format("%02d:%02d:%02d",
                TimeUnit.MILLISECONDS.toHours(milliSeconds),
                TimeUnit.MILLISECONDS.toMinutes(milliSeconds) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(milliSeconds)),
                TimeUnit.MILLISECONDS.toSeconds(milliSeconds) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(milliSeconds)));

        return hms;


    }

}
