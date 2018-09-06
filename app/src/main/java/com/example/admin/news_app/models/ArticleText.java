package com.example.admin.news_app.models;

public class ArticleText {
    private String header;
    private String text;

    public ArticleText(String header, String text) {
        this.header = header;
        this.text = text;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString(){
        return header+ ":   "+text;
    }
}
