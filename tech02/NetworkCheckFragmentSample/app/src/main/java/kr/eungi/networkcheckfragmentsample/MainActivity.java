package kr.eungi.networkcheckfragmentsample;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = MainActivity.class.getSimpleName();

    private NetworkCheckFragment mFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate");
        setContentView(R.layout.activity_main);

        mFragment = (NetworkCheckFragment) getSupportFragmentManager().findFragmentByTag(NetworkCheckFragment.TAG);

        if (mFragment == null) {
            mFragment = NetworkCheckFragment.newInstance();
            getSupportFragmentManager().beginTransaction()
                    .add(mFragment, NetworkCheckFragment.TAG)
                    .commit();
        }
    }
}
