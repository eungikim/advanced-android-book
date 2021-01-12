package kr.eungi.permissiondemoa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    startActivityForResult(new Intent(
                            "permissiondemob.ACTION_LAUNCH_APP_B_ACTIVITY_B"), 0);
                } catch (ActivityNotFoundException anfe) {
                    Toast.makeText(
                            MainActivity.this,
                            "PermissionDemoB 앱을 찾을 수 없습니다.",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

        // TODO: 거절됩니다.
        findViewById(R.id.btn2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    startActivity(new Intent(
                            "permissiondemob.ACTION_LAUNCH_APP_B_ACTIVITY_C"));
                } catch (ActivityNotFoundException anfe) {
                    Toast.makeText(
                            MainActivity.this,
                            "PermissionDemoB 앱을 찾을 수 없습니다.",
                            Toast.LENGTH_SHORT).show();
                } catch (SecurityException se) {
                    Toast.makeText(
                            MainActivity.this,
                            "PermissionDemoB 앱을 실행하기 위한 권한이 없습니다.",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}