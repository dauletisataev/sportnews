package com.example.admin.news_app.adapters;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.admin.news_app.R;
import com.example.admin.news_app.models.Article;
import com.example.admin.news_app.models.ArticleText;
import com.example.admin.news_app.models.Event;

import java.util.List;

public class ArticleAdapter extends RecyclerView.Adapter<ArticleAdapter.ArticleViewHolder> {
    List<ArticleText> texts;


    public static class ArticleViewHolder extends RecyclerView.ViewHolder {
        TextView header;
        TextView text;

        ArticleViewHolder(View itemView) {
            super(itemView);
            header = (TextView) itemView.findViewById(R.id.header);
            text = (TextView) itemView.findViewById(R.id.text);
        }

    }


    public ArticleAdapter(List<ArticleText> texts) {
        this.texts = texts;
    }

    @Override
    public ArticleViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_article_view, viewGroup, false);
        ArticleViewHolder feedView = new ArticleViewHolder(v);
        return feedView;
    }
    @Override
    public void onBindViewHolder(ArticleViewHolder personViewHolder, int i) {
        personViewHolder.header.setText(texts.get(i).getHeader());
        personViewHolder.text.setText(texts.get(i).getText());
    }
    @Override
    public int getItemCount() {
        return texts.size();
    }

    public interface OnItemClickListener {
        void onItemClick(ArticleViewHolder item);
    }
}








