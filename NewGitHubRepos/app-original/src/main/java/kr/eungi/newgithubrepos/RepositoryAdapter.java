package kr.eungi.newgithubrepos;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RepositoryAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

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


    interface OnRepositoryItemClickListener {
        /**
         * 리포지토리의 아이템이 탭되면 호출된다
         */
        void onRepositoryItemClick(GitHubService.RepositoryItem item);
    }
}
