package kr.eungi.newgithubrepos.viewmodel;

import android.view.View;

import androidx.databinding.ObservableField;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;
import kr.eungi.newgithubrepos.contract.DetailViewContract;
import kr.eungi.newgithubrepos.model.GitHubService;

public class DetailViewModel {
    final DetailViewContract detailView;
    private final GitHubService gitHubService;
    public ObservableField<String> repoFullName = new ObservableField<>();
    public ObservableField<String> repoDetail = new ObservableField<>();
    public ObservableField<String> repoStar = new ObservableField<>();
    public ObservableField<String> repoFork = new ObservableField<>();
    public ObservableField<String> repoImageUrl = new ObservableField<>();
    private GitHubService.RepositoryItem repositoryItem;

    public DetailViewModel(DetailViewContract detailView, GitHubService gitHubService) {
        this.detailView = detailView;
        this.gitHubService = gitHubService;
    }

    /**
     * 하나의 리포지터리에 대한 정보를 가져온다
     * 기본적으로 API 액세스 방법에 대해서는 RepositoryListActivity#loadRepositories(String)와 같다
     */
    public void loadRepositories() {
        String fullRepoName = detailView.getFullRepositoryName();
        // 리포지터리 이름을 /로 나눈다
        final String[] repoData = fullRepoName.split("/");
        final String owner = repoData[0];
        final String repoName = repoData[1];
        gitHubService
                .detailRepo(owner, repoName)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        (repositoryItem) -> {
                            loadRepositoryItem(repositoryItem);
                        },
                        (throwable) -> {
                            detailView.showError("읽을 수 없습니다.");
                        },
                        () -> {
                            // 아무것도 하지 않는다
                        }
                );
    }

    private void loadRepositoryItem(GitHubService.RepositoryItem repositoryItem) {
        this.repositoryItem = repositoryItem;
        repoFullName.set(repositoryItem.full_name);
        repoDetail.set(repositoryItem.description);
        repoStar.set(repositoryItem.stargazers_count);
        repoFork.set(repositoryItem.forks_count);
        repoImageUrl.set(repositoryItem.owner.avatar_url);
    }


    public void onTitleClick(View v) {
        try {
            detailView.startBrowser(repositoryItem.html_url);
        } catch (Exception e) {
            detailView.showError("링크를 열 수 없습니다.");
        }
    }
}