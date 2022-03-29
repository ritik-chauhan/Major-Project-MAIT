package com.example.factorialapp;

import android.content.Context;

import org.json.JSONException;

import java.math.BigInteger;

public class factorial {
    int n;
    Context context;
    long startTime;

    factorial(long startTime, int n, Context context){
        this.n=n;
        this.startTime=startTime;
        this.context=context;
    }
    String code=""+
    "static void fact(int N)"+
    "{"+
        "BigInteger f = new BigInteger("+"\"1\""+");"+
        "for (int i = 2; i <= N; i++)"+
            "f = f.multiply(BigInteger.valueOf(i));"+
        "System.out.print(f);"+
    "}";
    String factorialUtil() throws JSONException {
        OffloadCode offload = new OffloadCode(startTime,n, code, n + "",context);
        String output=offload.getConnection();
        return output;
    }
    static BigInteger fact(int N)
    {
        // Initialize result
        BigInteger f = new BigInteger("1"); // Or BigInteger.ONE

        // Multiply f with 2, 3, ...N
        for (int i = 2; i <= N; i++)
            f = f.multiply(BigInteger.valueOf(i));

        return f;
    }
}

