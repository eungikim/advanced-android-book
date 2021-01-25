package kr.eungi.leakcanarydemo;

import android.content.Context;

public class LeakInducer {

    private static LeakInducer singleton;
    private Context context;

    private LeakInducer(Context context) {
        this.context = context;
    }

    public synchronized static LeakInducer getInstance(Context context) {
        if (singleton == null) {
            singleton = new LeakInducer(context);
        }
        return singleton;
    }
}