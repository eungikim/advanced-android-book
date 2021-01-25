package kr.eungi.leakcanarydemo;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

import leakcanary.AppWatcher;

public class LeakService extends Service {
    private static final String TAG = LeakService.class.getSimpleName();

    @Nullable
    @Override
    public IBinder onBind(Intent intent) { return null; }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate() called");

        // 메모리 leak 유발
        LeakInducer.getInstance(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy() called");
        AppWatcher.INSTANCE.getObjectWatcher().expectWeaklyReachable(this,
                "LeakService onDestroy() callback");
    }
}
