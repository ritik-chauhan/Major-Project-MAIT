package com.example.factorialapp;

import android.content.Context;
import android.text.method.ScrollingMovementMethod;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class OffloadingConnection {
    private static final int MY_SOCKET_TIMEOUT_MS = Integer.MAX_VALUE;
    Context context;
    long startTime;
    int valn;
    OffloadingConnection(long startTime, int n, Context context){
        this.context=context;
        this.startTime=startTime;
        this.valn=n;
    }
    TextView textView;
    static String output;
    public String makeJsonObjectRequest(String code, String n)
    {
        String url = "https://b243-2405-201-5c01-a823-c2a-a880-bf72-3d9d.ngrok.io/run";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response)
                    {

                        try {
                            JSONObject respObj = new JSONObject(response);
//                           textView.setText(respObj.getString("output"));
                            output=respObj.getString("output");
                            //Toast.makeText(context, output, Toast.LENGTH_SHORT).show();
                            PopUp pop = new PopUp();
                            pop.print(startTime, valn, context,output);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("sourceCode", code);
                params.put("language", "java");
                return params;
            }
        };
        Volley.newRequestQueue(context).add(stringRequest);
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                MY_SOCKET_TIMEOUT_MS,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        return output;
    }



}