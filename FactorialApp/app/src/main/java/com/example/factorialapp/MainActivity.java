package com.example.factorialapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;

import java.math.BigInteger;

public class MainActivity extends AppCompatActivity {
    Button btnokay;
    EditText editText;
    PopupWindow popupWindow;
    RelativeLayout relativeLayout;
    ImageButton button;
    TextView valueoffact;
    TextView valueofn;
    Switch simpleSwitch;
    String n;
    long startTime;
    String output;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initialize();

        btnokay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editText.getText().toString().isEmpty()) {
                    Toast.makeText(MainActivity.this, "Kindly enter the value of n", Toast.LENGTH_SHORT).show();
                    return;
                }
                Toast.makeText(MainActivity.this, "Request queued...", Toast.LENGTH_SHORT).show();

                n = editText.getText().toString();
                //calling the fact method of the factorial class to get the factorial of n
                Boolean switchState = simpleSwitch.isChecked();
                startTime = System.nanoTime();
                BigInteger ans = null;
                if (switchState) {
                    factorial fact = new factorial(startTime, Integer.parseInt(n), MainActivity.this);
                    try {
                        output = fact.factorialUtil();

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                else {
                    ans = factorial.fact(Integer.parseInt(n));
                    output = ans.toString();
                    printSolution(output);
                }
            }
        });
    }
    void printSolution(String output){
        long endTime = System.nanoTime();
        Log.e("Output", output );
//        if(!(Integer.parseInt(n)>2 )){
            LayoutInflater layoutInflater = (LayoutInflater) MainActivity.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View customView = layoutInflater.inflate(R.layout.popup,null);
            popupWindow = new PopupWindow(customView, LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);

            button = customView.findViewById(R.id.cancel);
            valueoffact = customView.findViewById(R.id.factorialans);
            valueoffact.setMovementMethod(new ScrollingMovementMethod());
            valueofn = customView.findViewById(R.id.n);
            TextView time = customView.findViewById(R.id.time);
            valueoffact.setText(output);
//            valueofn.setText(n);

            time.setText("Time taken = "+(endTime - startTime) +"ns");

            popupWindow.setTouchable(true);
            popupWindow.setHeight(1800);
            popupWindow.setWidth(800);
            //popupWindow.setFocusable(true);
            //popupWindow.update();
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    popupWindow.dismiss();
                }
            });

            popupWindow.showAtLocation(relativeLayout, Gravity.CENTER, 0, 0);
//        }
        //long endTime = System.nanoTime();
        //Toast.makeText(MainActivity.this, "Time taken = "+(endTime - startTime) +"ns", Toast.LENGTH_LONG).show();
    }
    private void initialize() {
        relativeLayout = findViewById(R.id.relativel);
        btnokay = findViewById(R.id.okbtn);
        editText = findViewById(R.id.entern);
        simpleSwitch = (Switch) findViewById(R.id.simpleSwitch);
    }
}