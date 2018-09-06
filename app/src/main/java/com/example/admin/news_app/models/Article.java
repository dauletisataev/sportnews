package com.example.admin.news_app.models;

import java.util.List;

public class Article {
    private String team1;
    private String team2;
    private String time;
    private String tournament;
    private String place;
    private String prediction;
    private List<Article> body;

    public Article(String team1, String team2, String time, String tournament, String place, List<Article> body) {
        this.team1 = team1;
        this.team2 = team2;
        this.time = time;
        this.tournament = tournament;
        this.place = place;
        this.body = body;
    }

    public String getTeam1() {
        return team1;
    }

    public void setTeam1(String team1) {
        this.team1 = team1;
    }

    public String getTeam2() {
        return team2;
    }

    public void setTeam2(String team2) {
        this.team2 = team2;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTournament() {
        return tournament;
    }

    public void setTournament(String tournament) {
        this.tournament = tournament;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public List<Article> getBody() {
        return body;
    }

    public void setBody(List<Article> body) {
        this.body = body;
    }
}
