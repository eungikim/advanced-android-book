package kr.eungi.leakcanarydemo;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LeakActivity extends AppCompatActivity {

    static View.OnClickListener sListener = new View.OnClickListener() {

        private Button prevClickedView;

        @Override
        public void onClick(View v) {
            String text = "버튼이 눌렸습니다. 지난 번엔(" + (prevClickedView == null ? "없음" : prevClickedView.getText()) + ")을 눌렀습니다";
            Toast.makeText(v.getContext(), text, Toast.LENGTH_SHORT).show();
            prevClickedView = (Button) v;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leak);

        findViewById(R.id.button1).setOnClickListener(sListener);
        findViewById(R.id.button2).setOnClickListener(sListener);
    }

}
