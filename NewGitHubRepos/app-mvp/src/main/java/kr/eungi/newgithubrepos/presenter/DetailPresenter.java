package kr.eungi.newgithubrepos.presenter;


import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;
import kr.eungi.newgithubrepos.contract.DetailContract;
import kr.eungi.newgithubrepos.model.GitHubService;

public class DetailPresenter implements DetailContract.UserActions {
    final DetailContract.View detailView;
    private final GitHubService gitHubService;
    private GitHubService.RepositoryItem repositoryItem;

    public DetailPresenter(DetailContract.View detailView, GitHubService gitHubService) {
        this.detailView = detailView;
        this.gitHubService = gitHubService;
    }

    /**
     * 하나의 리포지토리에 관한 정보를 가져온다
     * 기본적으로 API 액세스 방법은 RepositoryListActivity#loadRepositories(String)와 같다
     */
    private void loadRepositories() {
        String fullRepoName = detailView.getFullRepositoryName();
        // 리포지토리 이름을 /로 분할한다
        final String[] repoData = fullRepoName.split("/");
        final String owner = repoData[0];
        final String repoName = repoData[1];
        gitHubService
                .detailRepo(owner, repoName)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        (response) -> {
                            repositoryItem = response;
                            detailView.showRepositoryInfo(response);
                        },
                        (throwable) -> {
                            detailView.showError("읽을 수 없습니다.");
                        }
                );
    }

    @Override
    public void prepare() {
        loadRepositories();
    }

    @Override
    public void titleClick() {
        try {
            detailView.startBrowser(repositoryItem.html_url);
        } catch (Exception e) {
            detailView.showError("링크를 열수 없습니다.");
        }
    }

}
