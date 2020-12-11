package kr.eungi.newgithubrepos.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ProgressBar;
import android.widget.Spinner;

import com.google.android.material.snackbar.Snackbar;

import kr.eungi.newgithubrepos.Constant;
import kr.eungi.newgithubrepos.NewGitHubReposApplication;
import kr.eungi.newgithubrepos.R;
import kr.eungi.newgithubrepos.contract.RepositoryListContract;
import kr.eungi.newgithubrepos.model.GitHubService;
import kr.eungi.newgithubrepos.presenter.RepositoryListPresenter;

/**
 * 리포지토리 목록을 표시하는 Activity
 * MVP의 View 역할을 가진다
 */
public class RepositoryListActivity extends AppCompatActivity implements
        RepositoryAdapter.OnRepositoryItemClickListener, RepositoryListContract.View {
    private static final String TAG = Constant.TAG + RepositoryListActivity.class.getSimpleName();
    private static final String[] SEARCH_LANGUAGE = new String[]
            {"java", "objective-c", "swift", "groovy", "python", "ruby", "c"};

    private Spinner languageSpinner;
    private ProgressBar progressBar;
    private CoordinatorLayout coordinatorLayout;

    private RepositoryAdapter repositoryAdapter;

    private RepositoryListContract.UserActions repositoryListPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repository_list);

        // View를 설정
        setupViews();

        // ① Presenter의 인스턴스를 생성
        final GitHubService gitHubService = ((NewGitHubReposApplication) getApplication()).getGitHubService();
        repositoryListPresenter = new RepositoryListPresenter(this, gitHubService);
    }

    /**
     * 목록 등의 화면 요소를 만든다
     */
    private void setupViews() {
        // 툴바 설정
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Recycler View
        RecyclerView recyclerView = findViewById(R.id.recycler_repos);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        repositoryAdapter = new RepositoryAdapter(this, this);
        recyclerView.setAdapter(repositoryAdapter);

        // ProgressBar
        progressBar = findViewById(R.id.progress_bar);

        // SnackBar 표시로 이용한다
        coordinatorLayout = findViewById(R.id.coordinator_layout);

        // Spinner
        languageSpinner = findViewById(R.id.language_spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item);
        adapter.addAll(SEARCH_LANGUAGE);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        languageSpinner.setAdapter(adapter);
        languageSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //  스피너의 선택 내용이 바뀌면 호출된다
                Log.d(TAG, "languageSpinner selected: " + position);
                String language = (String) languageSpinner.getItemAtPosition(position);
                // ② Presenter에 프로그래밍 언어를 선택했다고 알린다
                repositoryListPresenter.selectLanguage(language);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    /**
     * RecyclerView에서 클릭됐다
     *
     * @see RepositoryAdapter.OnRepositoryItemClickListener#onRepositoryItemClick
     */
    @Override
    public void onRepositoryItemClick(GitHubService.RepositoryItem item) {
        repositoryListPresenter.selectRepositoryItem(item);
    }

    // =====RepositoryListContract.View 구현=====
    // 이곳에서 Presenter로부터 지시를 받아 View의 변경 등을 한다

    @Override
    public void startDetailActivity(String full_name) {
        DetailActivity.start(this, full_name);
    }

    @Override
    public String getSelectedLanguage() {
        return (String) languageSpinner.getSelectedItem();
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void showRepositories(GitHubService.Repositories repositories) {
        // ③ 리포지토리 목록을 Adapter에 설정한다
        repositoryAdapter.setItemsAndRefresh(repositories.items);
    }

    @Override
    public void showError() {
        Snackbar.make(coordinatorLayout, "읽을 수 없습니다", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
    }

}
