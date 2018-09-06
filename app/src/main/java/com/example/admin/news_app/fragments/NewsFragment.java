package com.example.admin.news_app.fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import com.example.admin.news_app.activities.ArticleActivity;
import com.example.admin.news_app.adapters.FeedAdapter;
import com.example.admin.news_app.models.Event;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class NewsFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener{


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "category";


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private String category;
    private static String EVENT_BASE_URL =  "http://mikonatoruri.win/list.php?category=";

    private RequestQueue requestQueue;

    RecyclerView rv;
    SwipeRefreshLayout swipeRefreshLayout;
    FeedAdapter adapter;
    public NewsFragment() {
        // Required empty public constructor
    }


    public static NewsFragment newInstance(String category) {
        NewsFragment fragment = new NewsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, category);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
        }
    }

    private void readBundle(Bundle bundle) {
        if (bundle != null) {
            category = bundle.getString(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        readBundle(getArguments());

        View v = inflater.inflate(R.layout.fragment_news, container, false);
        rv = (RecyclerView) v.findViewById(R.id.recycler_view);
        swipeRefreshLayout = (SwipeRefreshLayout) v.findViewById(R.id.refresh_layout);
        swipeRefreshLayout.setOnRefreshListener(this);
        swipeRefreshLayout.setRefreshing(true);
        getEvents(category);
        return v;
    }
    private  void OnRequestSuccess(JSONArray feed_array) throws Exception{
        hideRefeshing();
        List<Event> events = new ArrayList<>();
        for (int i = 0; i < feed_array.length(); i++) {
            JSONObject jsonobject = feed_array.getJSONObject(i);
            Event event = new Event(jsonobject.getString("title"), jsonobject.getString("coefficient"),jsonobject.getString("time"),jsonobject.getString("place"),jsonobject.getString("preview"), jsonobject.getString("article"));
            Log.d("mLog", "got new event: "+ event);
            events.add(event);
        }
        adapter = new FeedAdapter(events,new FeedAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(String article) {
                Intent intent = new Intent(getContext(), ArticleActivity.class);
                intent.putExtra("article", article);
                startActivity(intent);
            }
        });
        rv.setAdapter(adapter);
        rv.setLayoutManager(new LinearLayoutManager(getContext()));


    }

    public  List<Event> getEvents(String category ){
        String url = EVENT_BASE_URL+category;
        requestQueue = Volley.newRequestQueue(getContext());
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            OnRequestSuccess(response.getJSONArray("events"));

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
        requestQueue.add(jsonObjectRequest);

        return null;
    }


    public void hideRefeshing(){
        swipeRefreshLayout.setRefreshing(false);
    }
    public void showRefeshing(){
        swipeRefreshLayout.setRefreshing(true);
    }
    public void showToastError(String error){
        Toast.makeText(getContext(), error, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onRefresh() {
        getEvents(category);
    }
}
