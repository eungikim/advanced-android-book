package kr.eungi.networkcheckfragmentsample;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private NetworkCheckFragment mFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
