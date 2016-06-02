package shashankm.gitlistofcommits;

import android.app.Activity;
import android.util.Log;

import com.android.volley.Request.Method;
import com.android.volley.RequestQueue;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shashankm on 02/06/16.
 */
public class NetworkHelper {

    private static final String TAG = "Network Helper";

    public interface ResultCallback {
        void onResultReceived(boolean isSuccessful, List<Commit> listOfCommits);
    }

    public void getListOfCommits(final ResultCallback resultCallback, Activity activity) {
        String url = "https://api.github.com/repos/rails/rails/commits";
        JsonArrayRequest stringRequest = new JsonArrayRequest(Method.GET, url,
                new Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        parseResult(response, resultCallback);
                    }
                }, new ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        RequestQueue queue = Volley.newRequestQueue(activity);
        queue.add(stringRequest);
    }

    private void parseResult(JSONArray response, ResultCallback resultCallback) {
        Log.d(TAG, "parseResult: Response is -" + response.toString());
        Log.d(TAG, "parseResult: Size - " + response.length());
        List<Commit> listOfCommits = new ArrayList<>();
        for (int i = 0; i < response.length(); i++) {
            try {
                JSONObject individualCommit = response.getJSONObject(i);
                Commit commit = new Commit();
                JSONObject commitDetails = individualCommit.getJSONObject("commit");
                JSONObject authorDetails = commitDetails.getJSONObject("author");
                commit.setAuthorName(authorDetails.getString("name"));
                commit.setCommitMessage("Commit message: " + commitDetails.getString("message"));
                JSONObject author = individualCommit.getJSONObject("author");
                commit.setAuthorAvatar(author.getString("avatar_url"));
                listOfCommits.add(commit);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        resultCallback.onResultReceived(true, listOfCommits);
    }
}
