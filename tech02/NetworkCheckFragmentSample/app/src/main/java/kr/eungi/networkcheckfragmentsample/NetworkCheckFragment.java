package kr.eungi.networkcheckfragmentsample;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

public class NetworkCheckFragment extends Fragment {
    public static final String TAG = NetworkCheckFragment.class.getSimpleName();
    public static final String ACTION_CHECK_INTERNET = "ACTION_CHECK_INTERNET";
    public static final String KEY_CHECK_INTERNET = "KEY_CHECK_INTERNET";

    private IntentFilter mIntentFilter;

    public static NetworkCheckFragment newInstance() {
        return new NetworkCheckFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate");
        setRetainInstance(true);
    }


    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "onResume");
        if (mIntentFilter == null) {
            mIntentFilter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        }
        getActivity().registerReceiver(mReceiver, mIntentFilter);
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(TAG, "onPause");
        getActivity().unregisterReceiver(mReceiver);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy");
    }

    public static boolean isInternetConnected(Context context) {
        return isWifiConnected(context) || isMobileConnected(context);
    }

    public static boolean isWifiConnected(Context context) {
        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = manager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);

        return info != null && info.isConnected();
    }

    public static boolean isMobileConnected(Context context) {
        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = manager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);

        return info != null && info.isConnected();
    }


    private BroadcastReceiver mReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();

            boolean noConnectivity = intent.getBooleanExtra(ConnectivityManager.EXTRA_NO_CONNECTIVITY, false);
            boolean isFailover = intent.getBooleanExtra(ConnectivityManager.EXTRA_IS_FAILOVER, false);
            String reason = intent.getStringExtra(ConnectivityManager.EXTRA_REASON);
            Log.d(TAG, String.format("NO_CONNECTIVITY %b, IS_FAILOVER %b, REASON %s", noConnectivity, isFailover, reason));

            if (ConnectivityManager.CONNECTIVITY_ACTION.equals(action)) {
                // 네트워크 연결 변경에 따른 공통 처리
                boolean isConnected = NetworkCheckFragment.isInternetConnected(context);
                if (isConnected) {
                    // 인터넷 연결이 있는 경우
                    Toast.makeText(context, "인터넷 연결 있음", Toast.LENGTH_SHORT).show();
                } else {
                    // 인터넷 연결이 없는 경우
                    Toast.makeText(context, "인터넷 연결 없음", Toast.LENGTH_SHORT).show();
                }
            }
        }
    };


}

