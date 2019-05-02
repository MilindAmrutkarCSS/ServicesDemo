package com.example.servicesdemo;

import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;
import android.os.ResultReceiver;
import android.util.Log;

import javax.xml.transform.Result;

public class MyIntentService extends IntentService {

    private static final String TAG = "MyIntentService";

    public MyIntentService() {
        super("MyWorkerThread"); // Give the name to the worker thread
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(TAG, "onCreate, Thread name: " + Thread.currentThread().getName());
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Log.i(TAG, "onHandleIntent, Thread name: " + Thread.currentThread().getName());

        int sleepTime = intent.getIntExtra("sleepTime", 1);

        ResultReceiver resultReceiver = intent.getParcelableExtra("receiver");

        int ctr = 1;

        //Dummy Long Operation
        while (ctr <= sleepTime) {
            Log.i(TAG, "Counter is now  " + ctr);
            try {
                Thread.sleep(sleepTime * 10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            ctr++;
        }

        Bundle bundle = new Bundle();
        bundle.putString("resultIntentService", "Counter stopped at " + ctr + " seconds");
        resultReceiver.send(18, bundle);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy, Thread name: " + Thread.currentThread().getName());
    }
}
