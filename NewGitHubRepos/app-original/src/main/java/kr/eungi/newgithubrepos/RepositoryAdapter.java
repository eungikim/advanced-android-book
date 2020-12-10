package kr.eungi.newgithubrepos;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.graphics.drawable.RoundedBitmapDrawable;
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;

import java.util.List;

public class RepositoryAdapter extends RecyclerView.Adapter<RepositoryAdapter.RepoViewHolder> {
    private final OnRepositoryItemClickListener onRepositoryItemClickListener;
    private final Context context;
    private List<GitHubService.RepositoryItem> items;

    public RepositoryAdapter(Context context, OnRepositoryItemClickListener onRepositoryItemClickListener) {
        this.context = context;
        this.onRepositoryItemClickListener = onRepositoryItemClickListener;
    }

    /**
     * 리포지토리의 데이터를 설정해서 갱신한다
     *
     * @param items
     */
    public void setItemsAndRefresh(List<GitHubService.RepositoryItem> items) {
        this.items = items;
        notifyDataSetChanged();
    }

    public GitHubService.RepositoryItem getItemAt(int position) {
        return items.get(position);
    }

    /**
     * RecyclerView의 아이템 뷰 생성과 뷰를 유지할 ViewHolder를 생성
     */
    @Override
    public RepoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(context).inflate(R.layout.repo_item, parent, false);
        return new RepoViewHolder(view);
    }

    /**
     * onCreateViewHolder로 만든 ViewHolder의 뷰에
     * setItemsAndRefresh(items)으로 설정된 데이터를 넣는다
     */
    @Override
    public void onBindViewHolder(final RepoViewHolder holder, final int position) {
        final GitHubService.RepositoryItem item = getItemAt(position);

        // 뷰가 클릭되면 클릭된 아이템을 Listener에게 알린다
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onRepositoryItemClickListener.onRepositoryItemClick(item);
            }
        });

        holder.repoName.setText(item.name);
        holder.repoDetail.setText(item.description);
        holder.starCount.setText(item.stargazers_count);
        // 이미지는 Glide라는 라이브러리로 데이터를 설정한다
        Glide.with(context)
                .asBitmap()
                .load(item.owner.avatar_url)
                .centerCrop().into(new BitmapImageViewTarget(holder.repoImage) {
            @Override
            protected void setResource(Bitmap resource) {
                // 이미지를 동그랗게 만든다
                RoundedBitmapDrawable circularBitmapDrawable =
                        RoundedBitmapDrawableFactory.create(context.getResources(), resource);
                circularBitmapDrawable.setCircular(true);
                holder.repoImage.setImageDrawable(circularBitmapDrawable);
            }
        });

    }

    @Override
    public int getItemCount() {
        if (items == null) {
            return 0;
        }
        return items.size();
    }

    interface OnRepositoryItemClickListener {
        /**
         * 리포지토리의 아이템이 탭되면 호출된다
         */
        void onRepositoryItemClick(GitHubService.RepositoryItem item);
    }

    /**
     * 뷰를 저장해 둘 클래스
     */
    static class RepoViewHolder extends RecyclerView.ViewHolder {
        private final TextView repoName;
        private final TextView repoDetail;
        private final ImageView repoImage;
        private final TextView starCount;

        public RepoViewHolder(View itemView) {
            super(itemView);
            repoName = itemView.findViewById(R.id.repo_name);
            repoDetail = itemView.findViewById(R.id.repo_detail);
            repoImage = itemView.findViewById(R.id.repo_image);
            starCount = itemView.findViewById(R.id.repo_star);
        }
    }
}
