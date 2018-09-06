package com.example.admin.news_app.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.admin.news_app.R;
import com.example.admin.news_app.adapters.ArticleAdapter;
import com.example.admin.news_app.adapters.FeedAdapter;
import com.example.admin.news_app.models.ArticleText;
import com.example.admin.news_app.models.Event;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ArticleActivity extends AppCompatActivity {
    TextView team1, team2, time, place, tournament, prediction;
    RecyclerView rv;
    LinearLayout articleLayout;
    ProgressBar progressBar;
    private RequestQueue requestQueue;
    private static String ARTICLE_BASE_URL =  "http://mikonatoruri.win/post.php?article=";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        team1 = (TextView) findViewById(R.id.text_team1);
        team2 = (TextView) findViewById(R.id.text_team2);
        time = (TextView) findViewById(R.id.time);
        place = (TextView) findViewById(R.id.place);
        tournament = (TextView) findViewById(R.id.tournament);
        prediction = (TextView) findViewById(R.id.prediction);
        rv= (RecyclerView) findViewById(R.id.text_recycler_view);
        progressBar = (ProgressBar) findViewById(R.id.progress_bar);
        articleLayout = (LinearLayout) findViewById(R.id.article_layout);
        getArticle(getIntent().getStringExtra("article"));
    }
    private  void OnRequestSuccess(JSONObject article) throws Exception{
        List<ArticleText> texts = new ArrayList<>();
        JSONArray textArticles = article.getJSONArray("article");
        for (int i = 0; i < textArticles.length(); i++) {
            JSONObject jsonobject = textArticles.getJSONObject(i);
            ArticleText text = new ArticleText(jsonobject.getString("header"), jsonobject.getString("text"));
            Log.d("mLog", "got new event: "+jsonobject.getString("header")+"  "+ text);
            texts.add(text);
        }

        ArticleAdapter adapter = new ArticleAdapter(texts);
        rv.setAdapter(adapter);
        rv.setLayoutManager(new LinearLayoutManager(this));
        setValues(article);



    }
    public void setValues(JSONObject article) throws Exception{
        articleLayout.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.GONE);
        Log.d("mLog", "setValues: " +article.getString("team1")+"  "+ article.getString("tournament"));
        team1.setText(article.getString("team1"));
        team2.setText(article.getString("team2"));
        time.setText(article.getString("time"));
        tournament.setText(article.getString("tournament"));
        prediction.setText(article.getString("prediction"));
        place.setText(article.getString("place"));
    }

    public List<Event> getArticle(String article ){
        String url = ARTICLE_BASE_URL+article;
        requestQueue = Volley.newRequestQueue(this);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            Log.d("mLog", "onResponse: json:"+response.toString());
                            OnRequestSuccess(response);

                        }catch (Exception e){
                            showToastError(e.getMessage());
                        }

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        showToastError(error.getMessage());

                    }
                });
        // Adds the JSON object request "obreq" to the request queue
        requestQueue.add(jsonObjectRequest);

        return null;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
    public void showToastError(String error){
        Toast.makeText(this, error, Toast.LENGTH_LONG).show();
    }
}
