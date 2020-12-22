package kr.eungi.bmicalculator;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;

import androidx.annotation.VisibleForTesting;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import java.io.Serializable;
import java.util.Random;

public class SaveBmiService extends IntentService {

    public static final String ACTION_RESULT = SaveBmiService.class.getName() + ".ACTION_RESULT";
    public static final String PARAM_RESULT = "param_result";

    static final String PARAM_KEY_BMI_VALUE = "bmi_value";

    private LocalBroadcastManager mLocalBroadcastManager;

    public SaveBmiService() {
        super(SaveBmiService.class.getName());
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mLocalBroadcastManager = LocalBroadcastManager.getInstance(getApplicationContext());
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent == null) {
            return;
        }

        Serializable extra = intent.getSerializableExtra(PARAM_KEY_BMI_VALUE);
        if (extra == null || !(extra instanceof BmiValue)) {
            return;
        }

        BmiValue bmiValue = (BmiValue) extra;
        boolean result = saveToRemoteServer(bmiValue);
        sendLocalBroadcast(result);
    }

    @VisibleForTesting
    boolean saveToRemoteServer(BmiValue bmiValue) {
        try {
            Thread.sleep(3000 + new Random().nextInt(2000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //--------------
        //사실은 여기에 서버에 저장하는 처리를 한다
        //--------------

        return new Random().nextBoolean();
    }

    @VisibleForTesting
    void sendLocalBroadcast(boolean result) {
        Intent resultIntent = new Intent(ACTION_RESULT);
        resultIntent.putExtra(PARAM_RESULT, result);
        mLocalBroadcastManager.sendBroadcast(resultIntent);
    }

    @VisibleForTesting
    void setLocalBroadcastManager(LocalBroadcastManager manager) {
        mLocalBroadcastManager = manager;
    }

    public static void start(Context context, BmiValue bmiValue) {
        Intent intent = new Intent(context, SaveBmiService.class);
        intent.putExtra(PARAM_KEY_BMI_VALUE, bmiValue);
        context.startService(intent);
    }

}
