package kr.eungi.permissiondemob;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        byte[] msg = "Hello World!".getBytes();
        byte[] encryptedMsg = Util.cipherEncrypt(msg, "dlq345kdna+32!@dda", "92719248772324");

        Log.d("TAG", "msg" + Util.byteArrayToHexString(msg));
        if (encryptedMsg != null) {
            Log.d("TAG", "encryptedMsg" + Util.byteArrayToHexString(encryptedMsg));
        } else {
            Log.d("TAG", "encryptedMsg null");
        }
    }
}