package com.example.hookactivity;

import android.app.Activity;
import android.app.Instrumentation;
import android.util.Log;

import java.lang.reflect.Field;

public class HookUtil {

    private static final String TAG = "HookUtil";

    public static void hook(Activity activity){
        try {
            Class currentActivity = Class.forName("android.app.Activity");
            Field instrumentationField = currentActivity.getDeclaredField("mInstrumentation");
            instrumentationField.setAccessible(true);
            Instrumentation mInstrumentation = (Instrumentation) instrumentationField.get(activity);


            MyInstrumentation myInstrumentation = new MyInstrumentation(mInstrumentation);
            instrumentationField.set(activity,myInstrumentation);
        }catch (Exception e){
            Log.e(TAG,"hook e = "+e);
        }
    }
}
