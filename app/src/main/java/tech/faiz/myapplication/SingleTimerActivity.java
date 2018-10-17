package tech.faiz.myapplication;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
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
        STOPPED
    }


    private TimerStatus mTimerStatus = TimerStatus.STOPPED;

    private ProgressBar mProgressBarCircle;
    private EditText mEditTextMinute;
    private TextView mTextViewTime;
    private ImageView mImageViewReset;
    private ImageView mImageViewStartStop;
    private CountDownTimer mCountDownTimer;

    public MediaPlayer mMediaPlayer;

    private Button mHelpButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_timer);

        // method call to initialize the views
        initViews();
        // method call to initialize the listeners
        initListeners();

        mMediaPlayer = new MediaPlayer();
        mMediaPlayer = MediaPlayer.create(getBaseContext(), R.raw.sound_timer_end);

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
        mHelpButton = (Button) findViewById(R.id.help_button);
    }


    private void initListeners() {
        mImageViewReset.setOnClickListener(this);
        mImageViewStartStop.setOnClickListener(this);
        mHelpButton.setOnClickListener(this);
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
            case R.id.help_button:
                showHelpDialog();
                break;
        }

    }

    void showHelpDialog() {

        AlertDialog.Builder ImageDialog = new AlertDialog.Builder(this);

        ImageDialog.setTitle("Instructions");
        ImageView showImage = new ImageView(this);
        showImage.setImageResource(R.drawable.image_help);
        ImageDialog.setView(showImage);

        ImageDialog.setNegativeButton("ok", new DialogInterface.OnClickListener()
        {
            public void onClick(DialogInterface arg0, int arg1)
            {
            }
        });
ImageDialog.show();
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

                mMediaPlayer.start();

                Toast.makeText(SingleTimerActivity.this, "Times up...", Toast.LENGTH_LONG).show();

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

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        View v = getCurrentFocus();

        if (v != null &&
                (ev.getAction() == MotionEvent.ACTION_UP || ev.getAction() == MotionEvent.ACTION_MOVE) &&
                v instanceof EditText &&
                !v.getClass().getName().startsWith("android.webkit.")) {
            int scrcoords[] = new int[2];
            v.getLocationOnScreen(scrcoords);
            float x = ev.getRawX() + v.getLeft() - scrcoords[0];
            float y = ev.getRawY() + v.getTop() - scrcoords[1];

            if (x < v.getLeft() || x > v.getRight() || y < v.getTop() || y > v.getBottom())
                hideKeyboard(this);
        }
        return super.dispatchTouchEvent(ev);
    }

    public static void hideKeyboard(Activity activity) {
        if (activity != null && activity.getWindow() != null && activity.getWindow().getDecorView() != null) {
            InputMethodManager imm = (InputMethodManager)activity.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(activity.getWindow().getDecorView().getWindowToken(), 0);
        }
    }

}
