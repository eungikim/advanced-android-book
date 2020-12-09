package kr.eungi.newgithubrepos;

import android.content.Context;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RepositoryAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final OnRepositoryItemClickListener onRepositoryItemClickListener;
    private final Context context;
    private List<GitHubService.RepositoryItem> items;

    public RepositoryAdapter(Context context, OnRepositoryItemClickListener onRepositoryItemClickListener) {
        this.context = context;
        this.onRepositoryItemClickListener = onRepositoryItemClickListener;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public void setItemsAndRefresh(List<GitHubService.RepositoryItem> items) {
    }


    interface OnRepositoryItemClickListener {
        /**
         * 리포지토리의 아이템이 탭되면 호출된다
         */
        void onRepositoryItemClick(GitHubService.RepositoryItem item);
    }
}
