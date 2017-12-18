package com.example.oudam.workouttime;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText mWork, mRest, mRepeat;
    int i=1;
    Button mStart_btn, mStop_btn;
    TextView mTotal;
    String total_val;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        mWork = findViewById(R.id.WorkeditText);
        mRest = findViewById(R.id.ResteditText);
        mRepeat = findViewById(R.id.RepeateditText);
        mRepeat.setText("x" + String.valueOf(i));

        mStart_btn = findViewById(R.id.Start_button);
        mStop_btn = findViewById(R.id.Stop_button);
        mStop_btn.setVisibility(View.GONE);
        mStart_btn.setVisibility(View.VISIBLE);
        mTotal = findViewById(R.id.TotalSecondtextView);
        totalSeconds();
        mTotal.setText(totalSeconds());



        mWork.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (!hasFocus) {
                    try{
                        final String mSecond_val = secondsToString(Integer.valueOf(mWork.getText().toString()));
                        mWork.setText(mSecond_val);
                        totalSeconds();
                        mTotal.setText(totalSeconds());
                    }
                    catch(Exception e) {
                        e.printStackTrace();
                        mWork.setText(mWork.getText().toString());
                        totalSeconds();
                        mTotal.setText(totalSeconds());
                        Log.d("_____OUDAM_LOG_Error", "Err_____" + e.getMessage().toString());
                    }

                }
            }
        });

        mRest.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    try{
                        final String mSecond_val = secondsToString(Integer.valueOf(mRest.getText().toString()));
                        mRest.setText(mSecond_val);
                        totalSeconds();
                        mTotal.setText(totalSeconds());
                    }
                    catch(Exception e) {
                        e.printStackTrace();
                        mRest.setText(mRest.getText().toString());
                        totalSeconds();
                        mTotal.setText(totalSeconds());
                        Log.d("_____OUDAM_LOG_Error", "Err_____" + e.getMessage().toString());
                    }

                }
            }
        });

        mRepeat.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    try{
                        String[] units = mRepeat.getText().toString().split("x");
                        if ((units.length)>0){
                            mRepeat.setText("x" + (units[units.length-1]));
                        }
                        else{
                            mRepeat.setText("x" + (units[0]));
                        }
                        totalSeconds();
                        mTotal.setText(totalSeconds());
                    }
                    catch(Exception e) {
                        e.printStackTrace();
                        //length == 1;
                        String[] units = mRepeat.getText().toString().split("x");
                        if ((units.length)>0){
                            mRepeat.setText("x" + (units[units.length-1]));
                        }
                        else{
                            mRepeat.setText("x" + (units[0]));
                        }

                        //Log.d("_____OUDAM_LOG_Error", "Err_____" + e.getMessage().toString()+ (units[1]));
                        //if (TextUtils.isEmpty(units[1])){
                        //    mRepeat.setText("x" + (units[0]));
                        //}
                        //else{
                        //    mRepeat.setText("x" + (units[1]));
                        //}

                        totalSeconds();
                        mTotal.setText(totalSeconds());

                    }

                }
            }
        });

        mStart_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mStart_btn.setVisibility(View.GONE);
                mStop_btn.setVisibility(View.VISIBLE);
            }
        });
        mStop_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mStop_btn.setVisibility(View.GONE);
                mStart_btn.setVisibility(View.VISIBLE);
            }
        });

    }
    private String totalSeconds(){

        int vWork;
        int vRest;
        int vRepeat;
        int vTotalSeconds;

        if (TextUtils.isEmpty(mWork.getText().toString())){
            vWork =0;
        }
        else {
            vWork = textToSeconds(mWork.getText().toString());
        }
        if (TextUtils.isEmpty(mRest.getText().toString())){
            vRest = 0;
        }
        else {
            vRest = textToSeconds(mRest.getText().toString());
        }


        String[] units = mRepeat.getText().toString().split("x");

        if (TextUtils.isEmpty(units[1])){
            vRepeat = Integer.valueOf(units[0]);
        }
        else{
            vRepeat = Integer.valueOf(units[1]);
        }

        if ((units.length)>0){
            vRepeat = Integer.valueOf(units[units.length-1]);
        }
        else{
            vRepeat = Integer.valueOf(units[0]);
        }

        vTotalSeconds = (vWork + vRest)*vRepeat;

        total_val =  secondsToString(vTotalSeconds);
        return total_val;


    }

    private String secondsToString(int seconds) {
        if (seconds==0){
            return "";
        }
        else {
            return String.format("%02d:%02d", seconds / 60, seconds % 60);
        }
    }
    private int textToSeconds(String minuts){
        String time = minuts; //mm:ss
        String[] units = time.split(":"); //will break the string up into an array
        int minutes = Integer.parseInt(units[0]); //first element
        int seconds = Integer.parseInt(units[1]); //second element
        int duration = 60 * minutes + seconds; //add up our values
        return duration;
    }

}
