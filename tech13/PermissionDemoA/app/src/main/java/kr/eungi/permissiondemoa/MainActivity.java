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
                    startActivity(new Intent(
                            "kr.eungi.permissiondemob.ACTION_LAUNCH_APP_B"));
                } catch (ActivityNotFoundException anfe) {
                    Toast.makeText(
                            MainActivity.this,
                            "PermissionDemoB 앱을 찾을 수 없습니다.",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}