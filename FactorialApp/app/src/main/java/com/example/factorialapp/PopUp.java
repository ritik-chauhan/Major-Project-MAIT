package com.example.factorialapp;

import android.app.Activity;
import android.content.Context;
import android.text.method.ScrollingMovementMethod;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class PopUp {
    PopUp(){

    }
    void print(long startTime, int n, Context context, String output) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View customView = layoutInflater.inflate(R.layout.popup,null);
        PopupWindow popupWindow = new PopupWindow(customView, LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);

        ImageButton button = customView.findViewById(R.id.cancel);
        TextView valueoffact = customView.findViewById(R.id.factorialans);
        TextView time = customView.findViewById(R.id.time);
        valueoffact.setMovementMethod(new ScrollingMovementMethod());
        TextView valueofn = customView.findViewById(R.id.n);

        valueoffact.setText(output);
        //valueofn.setText(n);
        long endTime = System.nanoTime();
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
        RelativeLayout relativeLayout = ((Activity)context).findViewById(R.id.relativel);
        popupWindow.showAtLocation(relativeLayout, Gravity.CENTER, 0, 0);

        //long endTime = System.nanoTime();
        //Toast.makeText(context, "Time taken = "+(endTime - startTime) +"ns", Toast.LENGTH_LONG).show();
    }
}
