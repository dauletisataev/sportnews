package com.example.admin.news_app.adapters;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.admin.news_app.R;
import com.example.admin.news_app.models.Event;

import java.util.List;

public class FeedAdapter extends RecyclerView.Adapter<FeedAdapter.FeedViewHolder> {
    List<Event> events;
    private final OnItemClickListener listener;



    public FeedAdapter(List<Event> events, OnItemClickListener listener) {
        this.events = events;
        this.listener = listener;
    }

    @Override
    public FeedViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_feed_view, viewGroup, false);
        FeedViewHolder feedView = new FeedViewHolder(v);
        return feedView;
    }
    @Override
    public void onBindViewHolder(FeedViewHolder viewHolder, int i) {
        viewHolder.title.setText(events.get(i).getTitle());
        viewHolder.time.setText(events.get(i).getTime());
        viewHolder.place.setText(events.get(i).getPlace());
        viewHolder.coefficient.setText(events.get(i).getCoefficient());
        viewHolder.previewText.setText(events.get(i).getPreview());
        viewHolder.bind(events.get(i), listener);
    }
    @Override
    public int getItemCount() {
        return events.size();
    }

    public static class FeedViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        TextView place;
        TextView time;
        TextView coefficient;
        TextView previewText;

        FeedViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.title);
            place = (TextView) itemView.findViewById(R.id.place);
            time = (TextView) itemView.findViewById(R.id.time);
            coefficient = (TextView) itemView.findViewById(R.id.coefficient);
            previewText = (TextView) itemView.findViewById(R.id.preview);
        }
        public void bind(final Event item, final OnItemClickListener listener) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    listener.onItemClick(item.getArticle());
                }
            });
        }

    }

    public interface OnItemClickListener {
        void onItemClick(String article);
    }
}








