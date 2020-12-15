package kr.eungi.databindingsample;

import android.view.View;

import androidx.databinding.ObservableField;
import androidx.databinding.ObservableInt;

public class User {
    public ObservableField<String> name = new ObservableField<>();
    public ObservableInt age = new ObservableInt();
    public ObservableInt likes = new ObservableInt();

    public User(String name, int age) {
        this.name.set(name);
        this.age.set(age);
        this.likes.set(0);
    }

    public void onClickLike(View view) {
        likes.set(likes.get() + 1);
    }

}
