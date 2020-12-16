package kr.eungi.newgithubrepos.view;


import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import kr.eungi.newgithubrepos.contract.RepositoryListViewContract;
import kr.eungi.newgithubrepos.model.GitHubService;
import kr.eungi.newgithubrepos.viewmodel.RepositoryItemViewModel;

public class RepositoryAdapter extends RecyclerView.Adapter<RepositoryAdapter.RepoViewHolder> {
    private final RepositoryListViewContract view;
    private final Context context;
    private List<GitHubService.RepositoryItem> items;

    public RepositoryAdapter(Context context, RepositoryListViewContract view) {
        this.context = context;
        this.view = view;
    }

    @NonNull
    @Override
    public RepoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RepoViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }


    /**
     * 뷰를 보존해 둘 클래스
     * 여기서는 ViewModel을 가진다
     */
    static class RepoViewHolder extends RecyclerView.ViewHolder {
        private final RepositoryItemViewModel viewModel;

        public RepoViewHolder(View itemView, RepositoryItemViewModel viewModel) {
            super(itemView);
            this.viewModel = viewModel;
        }

        public void loadItem(GitHubService.RepositoryItem item) {
            viewModel.loadItem(item);
        }
    }
}
