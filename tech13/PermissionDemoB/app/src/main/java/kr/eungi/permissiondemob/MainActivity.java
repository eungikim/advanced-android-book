package kr.eungi.permissiondemob;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        byte[] msg = new byte[]{(byte) 0x04};
        byte[] encryptedMsg = Util.cipherEncrypt(msg, "asdf", "asd");

        Log.d("TAG", "msg" + Util.byteArrayToHexString(msg, ' ', true));
        if (encryptedMsg != null) {
            Log.d("TAG", "encryptedMsg" + Util.byteArrayToHexString(encryptedMsg, ' ', true));
        } else {
            Log.d("TAG", "encryptedMsg null");
        }
    }
}