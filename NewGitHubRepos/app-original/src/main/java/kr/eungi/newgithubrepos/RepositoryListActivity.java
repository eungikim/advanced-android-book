package kr.eungi.newgithubrepos;

import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

public class RepositoryListActivity extends AppCompatActivity implements RepositoryAdapter.OnRepositoryItemClickListener {


    private Spinner languageSpinner;
    private ProgressBar progressBar;
    private CoordinatorLayout coordinatorLayout;

    private RepositoryAdapter repositoryAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repository_list);

        // View를 설정한다
        setupViews();
    }

    private void setupViews() {
    }

    @Override
    public void onRepositoryItemClick(GitHubService.RepositoryItem item) {

    }
}
