package kr.eungi.newgithubrepos;

import android.app.Application;
import android.util.Log;

import org.jetbrains.annotations.NotNull;

import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NewGitHubReposApplication extends Application {
  private GitHubService gitHubService;

    @Override
    public void onCreate() {
        super.onCreate();
        // 어느 액티비티에서나 API를 이용할 수 있게 이 클래스에서 API를 이용한다
        setupAPIClient();
    }

    private void setupAPIClient() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(@NotNull String message) {
                Log.d(Constant.TAG + "_API_LOG", message);
            }
        });

        logging.setLevel(HttpLoggingInterceptor.Level.BASIC);

        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(logging).build();

      Retrofit retrofit = new Retrofit.Builder()
              .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
              .baseUrl("https://api.github.com")
              .addConverterFactory(GsonConverterFactory.create())
              .client(client)
              .build();

        gitHubService = retrofit.create(GitHubService.class);
    }

    public GitHubService getGitHubService() {
        return gitHubService;
    }
}
