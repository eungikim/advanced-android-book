package kr.eungi.permissiondemob;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Objects;

public class ActivityB extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_b);

        Toast.makeText(getApplicationContext(), "B 시작!!", Toast.LENGTH_SHORT).show();

        try {
            if (Objects.requireNonNull(Util.getHash(this, getCallingPackage(), "SHA1"))
                    .equals(Util.getHash(this, getPackageName(), "SHA1"))) {
                // Do something...
            } else {
                // Key not match
                throw new NullPointerException();
            }
        } catch (NullPointerException ne) {
            // Get key hash fails.
            finish();
        }
    }


}
