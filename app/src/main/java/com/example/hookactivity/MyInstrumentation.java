package com.example.hookactivity;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;

import java.lang.reflect.Method;

public class MyInstrumentation extends Instrumentation {
    private static final String TAG = "MyInstrumentation";
    Instrumentation mOriginInstrumentation;
    public MyInstrumentation(Instrumentation instrumentation) {
        mOriginInstrumentation = instrumentation;
    }

    public ActivityResult execStartActivity(Context who,
                                            IBinder contextThread, IBinder token, Activity target,
                                            Intent intent, int requestCode, Bundle options){
        Log.d(TAG, "I'm coming");

        try {
            //方法是隐藏的，需要反射找到
            Method execStartActivity = Instrumentation.class.getDeclaredMethod(
                    "execStartActivity",
                    Context.class, IBinder.class, IBinder.class, Activity.class,
                    Intent.class, int.class, Bundle.class);
            execStartActivity.setAccessible(true);
            return (ActivityResult) execStartActivity.invoke(mOriginInstrumentation, who,
                    contextThread, token, target, intent, requestCode, options);
        } catch (Exception e) {
            throw new RuntimeException("do not support!");
        }
    }
}
