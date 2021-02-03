package kr.eungi.networkcheckfragmentsample;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import androidx.localbroadcastmanager.content.LocalBroadcastManager;

/**
 * 네트워크 연결을 확인하는 BroadcastReceiver
 */
public class MyReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d("MyReceiver", "onReceive");
        Intent i = new Intent(NetworkCheckFragment.ACTION_CHECK_INTERNET);
        i.putExtra(NetworkCheckFragment.KEY_CHECK_INTERNET,
                NetworkCheckFragment.isInternetConnected(context));
        // 연결 변경 알림
        LocalBroadcastManager.getInstance(context).sendBroadcast(i);
    }
}
