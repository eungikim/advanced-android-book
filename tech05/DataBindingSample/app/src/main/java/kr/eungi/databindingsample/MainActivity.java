package kr.eungi.databindingsample;

import android.os.Bundle;
import android.text.format.DateFormat;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import java.util.Calendar;

import kr.eungi.databindingsample.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Binding 오브젝트를 얻는다
        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        // Binding 오브젝트에 User를 설정한다
        binding.setUser(new User("kim", 25));

        String date = (String) DateFormat.format("yyyy/MM/dd kk:mm:ss", Calendar.getInstance());
        binding.textTime.setText(date);
        //뷰에 id가 지정돼 있으면, Binding 오브젝트로부터 뷰에 대한 참조를 얻을 수 있다
    }
}