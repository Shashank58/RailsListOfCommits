package shashankm.gitlistofcommits;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.List;

import shashankm.gitlistofcommits.NetworkHelper.ResultCallback;

public class ListOfCommitsActivity extends AppCompatActivity implements ResultCallback{
    private ProgressBar progressBar;
    private RecyclerView commitList;
    private ResultCallback resultCallback = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_of_commits);

        progressBar = (ProgressBar) findViewById(R.id.progress_bar);
        commitList = (RecyclerView) findViewById(R.id.list_of_commits);

        commitList.setLayoutManager(new LinearLayoutManager(this));
        commitList.setHasFixedSize(true);

        new NetworkHelper().getListOfCommits(resultCallback, this);
    }

    @Override
    public void onResultReceived(boolean isSuccessful, List<Commit> listOfCommits) {
        if (isSuccessful) {
            TextView commitSize = (TextView) findViewById(R.id.comment_size);
            commitSize.setText("Number of commits: " + String.valueOf(listOfCommits.size()));
            progressBar.setVisibility(View.GONE);
            CommitsAdapter commitsAdapter = new CommitsAdapter(listOfCommits, this);
            commitList.setAdapter(commitsAdapter);
        } else {
            progressBar.setVisibility(View.GONE);
            Button tryAgain = (Button) findViewById(R.id.try_again);
            tryAgain.setVisibility(View.VISIBLE);
            tryAgain.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    new NetworkHelper().getListOfCommits(resultCallback, ListOfCommitsActivity.this);
                }
            });
        }
    }
}
