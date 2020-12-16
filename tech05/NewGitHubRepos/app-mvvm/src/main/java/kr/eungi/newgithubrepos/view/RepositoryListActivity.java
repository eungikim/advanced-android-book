package kr.eungi.newgithubrepos.view;

import android.content.Context;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;

import kr.eungi.newgithubrepos.Constant;
import kr.eungi.newgithubrepos.NewGitHubReposApplication;
import kr.eungi.newgithubrepos.R;
import kr.eungi.newgithubrepos.contract.RepositoryListViewContract;
import kr.eungi.newgithubrepos.databinding.ActivityRepositoryListBinding;
import kr.eungi.newgithubrepos.model.GitHubService;
import kr.eungi.newgithubrepos.viewmodel.RepositoryListViewModel;

public class RepositoryListActivity extends AppCompatActivity implements RepositoryListViewContract {
    private static final String TAG = Constant.TAG + RepositoryListActivity.class.getSimpleName();
    private static final String[] SEARCH_LANGUAGE = new String[]
            {"java", "objective-c", "swift", "groovy", "python", "ruby", "c"};

    private Spinner languageSpinner;
    private CoordinatorLayout coordinatorLayout;
    private RepositoryAdapter repositoryAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityRepositoryListBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_repository_list);
        final GitHubService gitHubService = ((NewGitHubReposApplication) getApplication()).getGitHubService();
        binding.setViewModel(new RepositoryListViewModel((RepositoryListViewContract) this, gitHubService));

        // 뷰를 셋업
        setupViews(binding);
    }

    private void setupViews(ActivityRepositoryListBinding binding) {
        // 툴바 설정
        setSupportActionBar(binding.toolbar);
        // Recycler View 이거 binding 에서 가져오기가 쉽지가 않네
        RecyclerView recyclerView = findViewById(R.id.recycler_repos);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        repositoryAdapter = new RepositoryAdapter(this, this);
        recyclerView.setAdapter(repositoryAdapter);

        // SnackBar 표시에서 이용한다
        coordinatorLayout = binding.coordinatorLayout;

        // Spinner
        languageSpinner = binding.languageSpinner;
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item);
        adapter.addAll(SEARCH_LANGUAGE);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        languageSpinner.setAdapter(adapter);
    }

    // =====RepositoryListViewContract 구현=====
    // 여기서 viewModel 로부터 지시를 받아 뷰의 변경 등을 한다

    @Override
    public void startDetailActivity(String full_name) {
        DetailActivity.start(this, full_name);
    }

    @Override
    public void showRepositories(GitHubService.Repositories repositories) {
        repositoryAdapter.setItemsAndRefresh(repositories.items);
    }

    @Override
    public void showError() {
        Snackbar.make(coordinatorLayout, "읽을 수 없습니다", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
    }
}
