package kr.eungi.newgithubrepos.viewmodel;

import android.view.View;

import androidx.databinding.ObservableField;

import kr.eungi.newgithubrepos.contract.RepositoryListViewContract;
import kr.eungi.newgithubrepos.model.GitHubService;


/**
 * ViewModel 클래스
 */
public class RepositoryItemViewModel {
  public ObservableField<String> repoName = new ObservableField<>();
  public ObservableField<String> repoDetail = new ObservableField<>();
  public ObservableField<String> repoStar = new ObservableField<>();
  public ObservableField<String> repoImageUrl = new ObservableField<>();

  RepositoryListViewContract view;
  private String fullName;

  public RepositoryItemViewModel(RepositoryListViewContract view) {
    this.view = view;
  }

  public void loadItem(GitHubService.RepositoryItem item) {
    fullName = item.full_name;
    repoDetail.set(item.description);
    repoName.set(item.name);
    repoStar.set(item.stargazers_count);
    repoImageUrl.set(item.owner.avatar_url);
  }

  public void onItemClick(View itemView) {
    view.startDetailActivity(fullName);
  }
}
