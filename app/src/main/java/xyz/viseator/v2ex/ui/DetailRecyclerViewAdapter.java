package xyz.viseator.v2ex.ui;

import android.content.Context;
import android.content.res.Resources;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import xyz.viseator.v2ex.R;
import xyz.viseator.v2ex.data.DetailContent;
import xyz.viseator.v2ex.http.GetAvatarTask;

/**
 * Created by viseator on 2016/11/19.
 */

public class DetailRecyclerViewAdapter extends
        RecyclerView.Adapter<DetailRecyclerViewAdapter.ViewHolder> {

    private Context context;
    private List<DetailContent> detailContents;


    public DetailRecyclerViewAdapter(Context context, List<DetailContent> detailContents) {
        this.context = context;
        this.detailContents = detailContents;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView avatarImageView;
        TextView usernameTextView;
        TextView timeTextView;
        TextView contentTextView;
        TextView numTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            avatarImageView = (ImageView) itemView.findViewById(R.id.detail_recyclerview_avatar);
            usernameTextView = (TextView) itemView.findViewById(R.id.detail_user);
            timeTextView = (TextView) itemView.findViewById(R.id.detail_time);
            contentTextView = (TextView) itemView.findViewById(R.id.detail_content);
            numTextView = (TextView) itemView.findViewById(R.id.detail_num);
        }
    }

    @Override
    public DetailRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.detail_recyclerview_content_layout, parent, false);
        return new DetailRecyclerViewAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if (position == 0) {
            holder.contentTextView.setText(detailContents.get(position).getContent());
        } else {
            holder.contentTextView.setText(detailContents.get(position).getContent());
            holder.contentTextView.setPadding(dpToPx(45), 0, 0, 0);
            new GetAvatarTask(holder.avatarImageView).execute(detailContents.get(position).getAvatarURL());
            holder.usernameTextView.setText(detailContents.get(position).getName());
            holder.timeTextView.setText(detailContents.get(position).getTime());
            holder.numTextView.setText(context.getString(R.string.di) + String.valueOf(position + 1) + context.getString(R.string.lou));
        }
    }

    @Override
    public int getItemCount() {

        return detailContents.size();
    }

    public static int dpToPx(int dp) {
        return (int) (dp * Resources.getSystem().getDisplayMetrics().density);
    }

    public List<DetailContent> getDetailContents() {
        return detailContents;
    }

    public void setDetailContents(List<DetailContent> detailContents) {
        this.detailContents = detailContents;
    }

}
