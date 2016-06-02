package shashankm.gitlistofcommits;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by shashankm on 02/06/16.
 */
public class CommitsAdapter extends RecyclerView.Adapter<CommitsAdapter.CommitsViewHolder> {
    private List<Commit> listOfCommits;
    private Activity activity;

    public CommitsAdapter(List<Commit> listOfCommits, Activity activity) {
        this.listOfCommits = listOfCommits;
        this.activity = activity;
    }

    @Override
    public CommitsAdapter.CommitsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.commit_card, parent, false);

        return new CommitsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CommitsAdapter.CommitsViewHolder holder, int position) {
        Commit commit = listOfCommits.get(position);

        holder.authorName.setText(commit.getAuthorName());
        holder.commitMessage.setText(commit.getCommitMessage());

        Glide.with(activity).load(commit.getAuthorAvatar())
                .asBitmap().into(holder.authorAvatar);
    }

    @Override
    public int getItemCount() {
        return listOfCommits.size();
    }

    public class CommitsViewHolder extends RecyclerView.ViewHolder{
        protected TextView authorName, commitMessage;
        protected ImageView authorAvatar;

        public CommitsViewHolder(View itemView) {
            super(itemView);

            authorName = (TextView) itemView.findViewById(R.id.author_name);
            commitMessage = (TextView) itemView.findViewById(R.id.commit_message);
            authorAvatar = (ImageView) itemView.findViewById(R.id.author_pic);
        }
    }
}
