package tech.faiz.myapplication;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

public class MultiTimerActivity extends AppCompatActivity implements View.OnClickListener {

    private long timeCountInMilliSeconds = 1 * 60000;

    private enum TimerStatus {
        STARTED,
        STOPPED
    }

    private TimerStatus mTimerStatus1 = TimerStatus.STOPPED;

    private ProgressBar mProgressBarCircle1;
    private EditText mEditTextMinute1;
    private TextView mTextViewTime1;
    private ImageView mImageViewReset1;
    private ImageView mImageViewStartStop1;
    private CountDownTimer mCountDownTimer1;

    private TimerStatus mTimerStatus2 = TimerStatus.STOPPED;

    private ProgressBar mProgressBarCircle2;
    private EditText mEditTextMinute2;
    private TextView mTextViewTime2;
    private ImageView mImageViewReset2;
    private ImageView mImageViewStartStop2;
    private CountDownTimer mCountDownTimer2;

    private TimerStatus mTimerStatus3 = TimerStatus.STOPPED;

    private ProgressBar mProgressBarCircle3;
    private EditText mEditTextMinute3;
    private TextView mTextViewTime3;
    private ImageView mImageViewReset3;
    private ImageView mImageViewStartStop3;
    private CountDownTimer mCountDownTimer3;

    private TimerStatus mTimerStatus4 = TimerStatus.STOPPED;

    private ProgressBar mProgressBarCircle4;
    private EditText mEditTextMinute4;
    private TextView mTextViewTime4;
    private ImageView mImageViewReset4;
    private ImageView mImageViewStartStop4;
    private CountDownTimer mCountDownTimer4;

    private Button mHelpButton;


    public MediaPlayer mMediaPlayer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multi_timer);

        initViews();
        initListeners();


        mMediaPlayer = new MediaPlayer();
        mMediaPlayer = MediaPlayer.create(getBaseContext(), R.raw.sound_timer_end);

    }


    private void initViews() {
        mProgressBarCircle1 = (ProgressBar) findViewById(R.id.progressBarCircle1);
        mEditTextMinute1 = (EditText) findViewById(R.id.editTextMinute1);
        mTextViewTime1 = (TextView) findViewById(R.id.textViewTime1);
        mImageViewReset1 = (ImageView) findViewById(R.id.imageViewReset1);
        mImageViewStartStop1 = (ImageView) findViewById(R.id.imageViewStartStop1);

        mProgressBarCircle2 = (ProgressBar) findViewById(R.id.progressBarCircle2);
        mEditTextMinute2 = (EditText) findViewById(R.id.editTextMinute2);
        mTextViewTime2 = (TextView) findViewById(R.id.textViewTime2);
        mImageViewReset2 = (ImageView) findViewById(R.id.imageViewReset2);
        mImageViewStartStop2 = (ImageView) findViewById(R.id.imageViewStartStop2);

        mProgressBarCircle3 = (ProgressBar) findViewById(R.id.progressBarCircle3);
        mEditTextMinute3 = (EditText) findViewById(R.id.editTextMinute3);
        mTextViewTime3 = (TextView) findViewById(R.id.textViewTime3);
        mImageViewReset3 = (ImageView) findViewById(R.id.imageViewReset3);
        mImageViewStartStop3 = (ImageView) findViewById(R.id.imageViewStartStop3);

        mProgressBarCircle4 = (ProgressBar) findViewById(R.id.progressBarCircle4);
        mEditTextMinute4 = (EditText) findViewById(R.id.editTextMinute4);
        mTextViewTime4 = (TextView) findViewById(R.id.textViewTime4);
        mImageViewReset4 = (ImageView) findViewById(R.id.imageViewReset4);
        mImageViewStartStop4 = (ImageView) findViewById(R.id.imageViewStartStop4);
        mHelpButton = (Button) findViewById(R.id.help_button2);

    }


    private void initListeners() {
        mImageViewReset1.setOnClickListener(this);
        mImageViewStartStop1.setOnClickListener(this);

        mImageViewReset2.setOnClickListener(this);
        mImageViewStartStop2.setOnClickListener(this);

        mImageViewReset3.setOnClickListener(this);
        mImageViewStartStop3.setOnClickListener(this);

        mImageViewReset4.setOnClickListener(this);
        mImageViewStartStop4.setOnClickListener(this);
        mHelpButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.imageViewReset1:
                reset1();
                break;
            case R.id.imageViewStartStop1:
                startStop1();
                break;

            case R.id.imageViewReset2:
                reset2();
                break;
            case R.id.imageViewStartStop2:
                startStop2();
                break;

            case R.id.imageViewReset3:
                reset3();
                break;
            case R.id.imageViewStartStop3:
                startStop3();
                break;

            case R.id.imageViewReset4:
                reset4();
                break;
            case R.id.imageViewStartStop4:
                startStop4();
                break;
            case R.id.help_button2:
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

    private void reset1() {
        stopCountDownTimer1();
        startCountDownTimer1();
    }

    private void reset2() {
        stopCountDownTimer2();
        startCountDownTimer2();
    }

    private void reset3() {
        stopCountDownTimer3();
        startCountDownTimer3();
    }

    private void reset4() {
        stopCountDownTimer4();
        startCountDownTimer4();
    }

    private void startStop1() {
        if (mTimerStatus1 == TimerStatus.STOPPED) {

            // call to initialize the timer values
            setTimerValues1();
            // call to initialize the progress bar values
            setProgressBarValues1();
            // showing the reset icon
            mImageViewReset1.setVisibility(View.VISIBLE);
            // changing play icon to stop icon
            mImageViewStartStop1.setImageResource(R.drawable.icon_stop_yellow);
            // making edit text not editable
            mEditTextMinute1.setEnabled(false);
            // changing the timer status to started
            mTimerStatus1 = TimerStatus.STARTED;
            // call to start the count down timer
            startCountDownTimer1();

        } else {

            // hiding the reset icon
            mImageViewReset1.setVisibility(View.GONE);
            // changing stop icon to start icon
            mImageViewStartStop1.setImageResource(R.drawable.icon_start);
            // making edit text editable
            mEditTextMinute1.setEnabled(true);
            // changing the timer status to stopped
            mTimerStatus1 = TimerStatus.STOPPED;
            stopCountDownTimer1();
        }
    }

    private void startStop2() {
        if (mTimerStatus2 == TimerStatus.STOPPED) {

            // call to initialize the timer values
            setTimerValues2();
            // call to initialize the progress bar values
            setProgressBarValues2();
            // showing the reset icon
            mImageViewReset2.setVisibility(View.VISIBLE);
            // changing play icon to stop icon
            mImageViewStartStop2.setImageResource(R.drawable.icon_stop);
            // making edit text not editable
            mEditTextMinute2.setEnabled(false);
            // changing the timer status to started
            mTimerStatus2 = TimerStatus.STARTED;
            // call to start the count down timer
            startCountDownTimer2();

        } else {

            // hiding the reset icon
            mImageViewReset2.setVisibility(View.GONE);
            // changing stop icon to start icon
            mImageViewStartStop2.setImageResource(R.drawable.icon_play_red);
            // making edit text editable
            mEditTextMinute2.setEnabled(true);
            // changing the timer status to stopped
            mTimerStatus2 = TimerStatus.STOPPED;
            stopCountDownTimer2();

        }

    }

    private void startStop3() {
        if (mTimerStatus3 == TimerStatus.STOPPED) {

            // call to initialize the timer values
            setTimerValues3();
            // call to initialize the progress bar values
            setProgressBarValues3();
            // showing the reset icon
            mImageViewReset3.setVisibility(View.VISIBLE);
            // changing play icon to stop icon
            mImageViewStartStop3.setImageResource(R.drawable.icon_stop);
            // making edit text not editable
            mEditTextMinute3.setEnabled(false);
            // changing the timer status to started
            mTimerStatus3 = TimerStatus.STARTED;
            // call to start the count down timer
            startCountDownTimer3();

        } else {

            // hiding the reset icon
            mImageViewReset3.setVisibility(View.GONE);
            // changing stop icon to start icon
            mImageViewStartStop3.setImageResource(R.drawable.icon_play_green);
            // making edit text editable
            mEditTextMinute3.setEnabled(true);
            // changing the timer status to stopped
            mTimerStatus3 = TimerStatus.STOPPED;
            stopCountDownTimer3();

        }

    }

    private void startStop4() {
        if (mTimerStatus4 == TimerStatus.STOPPED) {

            // call to initialize the timer values
            setTimerValues4();
            // call to initialize the progress bar values
            setProgressBarValues4();
            // showing the reset icon
            mImageViewReset4.setVisibility(View.VISIBLE);
            // changing play icon to stop icon
            mImageViewStartStop4.setImageResource(R.drawable.icon_stop);
            // making edit text not editable
            mEditTextMinute4.setEnabled(false);
            // changing the timer status to started
            mTimerStatus4 = TimerStatus.STARTED;
            // call to start the count down timer
            startCountDownTimer4();

        } else {

            // hiding the reset icon
            mImageViewReset4.setVisibility(View.GONE);
            // changing stop icon to start icon
            mImageViewStartStop4.setImageResource(R.drawable.icon_play_blue);
            // making edit text editable
            mEditTextMinute4.setEnabled(true);
            // changing the timer status to stopped
            mTimerStatus4 = TimerStatus.STOPPED;
            stopCountDownTimer4();

        }

    }

    /**
     * method to initialize the values for count down timer
     */
    private void setTimerValues1() {
        int time = 0;
        if (!mEditTextMinute1.getText().toString().isEmpty()) {
            // fetching value from edit text and type cast to integer
            time = Integer.parseInt(mEditTextMinute1.getText().toString().trim());
        } else {
            // toast message to fill edit text
            Toast.makeText(getApplicationContext(), getString(R.string.message_minutes), Toast.LENGTH_LONG).show();
        }
        // assigning values after converting to milliseconds
        timeCountInMilliSeconds = time * 60 * 1000;
    }

    private void setTimerValues2() {
        int time = 0;
        if (!mEditTextMinute2.getText().toString().isEmpty()) {
            // fetching value from edit text and type cast to integer
            time = Integer.parseInt(mEditTextMinute2.getText().toString().trim());
        } else {
            // toast message to fill edit text
            Toast.makeText(getApplicationContext(), getString(R.string.message_minutes), Toast.LENGTH_LONG).show();
        }
        // assigning values after converting to milliseconds
        timeCountInMilliSeconds = time * 60 * 1000;
    }

    private void setTimerValues3() {
        int time = 0;
        if (!mEditTextMinute3.getText().toString().isEmpty()) {
            // fetching value from edit text and type cast to integer
            time = Integer.parseInt(mEditTextMinute3.getText().toString().trim());
        } else {
            // toast message to fill edit text
            Toast.makeText(getApplicationContext(), getString(R.string.message_minutes), Toast.LENGTH_LONG).show();
        }
        // assigning values after converting to milliseconds
        timeCountInMilliSeconds = time * 60 * 1000;
    }

    private void setTimerValues4() {
        int time = 0;
        if (!mEditTextMinute4.getText().toString().isEmpty()) {
            // fetching value from edit text and type cast to integer
            time = Integer.parseInt(mEditTextMinute4.getText().toString().trim());
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
    private void startCountDownTimer1() {


        mCountDownTimer1 = new CountDownTimer(timeCountInMilliSeconds, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {

                mTextViewTime1.setText(hmsTimeFormatter(millisUntilFinished));

                mProgressBarCircle1.setProgress((int) (millisUntilFinished / 1000));
            }

            @Override
            public void onFinish() {

                mTextViewTime1.setText(hmsTimeFormatter(timeCountInMilliSeconds));
                // call to initialize the progress bar values
                setProgressBarValues1();
                // hiding the reset icon
                mImageViewReset1.setVisibility(View.GONE);
                // changing stop icon to start icon
                mImageViewStartStop1.setImageResource(R.drawable.icon_start);
                // making edit text editable
                mEditTextMinute1.setEnabled(true);
                // changing the timer status to stopped
                mTimerStatus1 = TimerStatus.STOPPED;

                mMediaPlayer.start();
                Toast.makeText(MultiTimerActivity.this, "Times up...", Toast.LENGTH_LONG).show();


            }

        }.start();
        mCountDownTimer1.start();
    }

    private void startCountDownTimer2() {


        mCountDownTimer2 = new CountDownTimer(timeCountInMilliSeconds, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {

                mTextViewTime2.setText(hmsTimeFormatter(millisUntilFinished));

                mProgressBarCircle2.setProgress((int) (millisUntilFinished / 1000));
            }

            @Override
            public void onFinish() {

                mTextViewTime2.setText(hmsTimeFormatter(timeCountInMilliSeconds));
                // call to initialize the progress bar values
                setProgressBarValues2();
                // hiding the reset icon
                mImageViewReset2.setVisibility(View.GONE);
                // changing stop icon to start icon
                mImageViewStartStop2.setImageResource(R.drawable.icon_play_red);
                // making edit text editable
                mEditTextMinute2.setEnabled(true);
                // changing the timer status to stopped
                mTimerStatus2 = TimerStatus.STOPPED;

                mMediaPlayer.start();
                Toast.makeText(MultiTimerActivity.this, "Times up...", Toast.LENGTH_LONG).show();

            }

        }.start();
        mCountDownTimer2.start();
    }

    private void startCountDownTimer3() {


        mCountDownTimer3 = new CountDownTimer(timeCountInMilliSeconds, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {

                mTextViewTime3.setText(hmsTimeFormatter(millisUntilFinished));

                mProgressBarCircle3.setProgress((int) (millisUntilFinished / 1000));
            }

            @Override
            public void onFinish() {

                mTextViewTime3.setText(hmsTimeFormatter(timeCountInMilliSeconds));
                // call to initialize the progress bar values
                setProgressBarValues3();
                // hiding the reset icon
                mImageViewReset3.setVisibility(View.GONE);
                // changing stop icon to start icon
                mImageViewStartStop3.setImageResource(R.drawable.icon_play_green);
                // making edit text editable
                mEditTextMinute3.setEnabled(true);
                // changing the timer status to stopped
                mTimerStatus3 = TimerStatus.STOPPED;

                mMediaPlayer.start();
                Toast.makeText(MultiTimerActivity.this, "Times up...", Toast.LENGTH_LONG).show();


            }

        }.start();
        mCountDownTimer3.start();
    }

    private void startCountDownTimer4() {


        mCountDownTimer4 = new CountDownTimer(timeCountInMilliSeconds, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {

                mTextViewTime4.setText(hmsTimeFormatter(millisUntilFinished));

                mProgressBarCircle4.setProgress((int) (millisUntilFinished / 1000));
            }

            @Override
            public void onFinish() {

                mTextViewTime4.setText(hmsTimeFormatter(timeCountInMilliSeconds));
                // call to initialize the progress bar values
                setProgressBarValues4();
                // hiding the reset icon
                mImageViewReset4.setVisibility(View.GONE);
                // changing stop icon to start icon
                mImageViewStartStop4.setImageResource(R.drawable.icon_play_blue);
                // making edit text editable
                mEditTextMinute4.setEnabled(true);
                // changing the timer status to stopped
                mTimerStatus4 = TimerStatus.STOPPED;

                mMediaPlayer.start();
                Toast.makeText(MultiTimerActivity.this, "Times up...", Toast.LENGTH_LONG).show();

            }

        }.start();
        mCountDownTimer4.start();
    }



    /**
     * method to stop count down timer
     */
    private void stopCountDownTimer1() {
        mCountDownTimer1.cancel();
    }
    private void stopCountDownTimer2() {
        mCountDownTimer2.cancel();
    }
    private void stopCountDownTimer3() {
        mCountDownTimer3.cancel();
    }
    private void stopCountDownTimer4() {
        mCountDownTimer4.cancel();
    }


    private void setProgressBarValues1() {

        mProgressBarCircle1.setMax((int) timeCountInMilliSeconds / 1000);
        mProgressBarCircle1.setProgress((int) timeCountInMilliSeconds / 1000);
    }

    private void setProgressBarValues2() {

        mProgressBarCircle2.setMax((int) timeCountInMilliSeconds / 1000);
        mProgressBarCircle2.setProgress((int) timeCountInMilliSeconds / 1000);
    }

    private void setProgressBarValues3() {

        mProgressBarCircle3.setMax((int) timeCountInMilliSeconds / 1000);
        mProgressBarCircle3.setProgress((int) timeCountInMilliSeconds / 1000);
    }

    private void setProgressBarValues4() {

        mProgressBarCircle4.setMax((int) timeCountInMilliSeconds / 1000);
        mProgressBarCircle4.setProgress((int) timeCountInMilliSeconds / 1000);
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
