package com.example.brunoorocha.petshop;

public class Foods {

    private String[] likes, dislikes;

    public Foods(String[] likes, String[] dislikes) {
        this.likes = likes;
        this.dislikes = dislikes;
    }

    public String[] getLikes() {
        return likes;
    }

    public void setLikes(String[] likes) {
        this.likes = likes;
    }

    public String[] getDislikes() {
        return dislikes;
    }

    public void setDislikes(String[] dislikes) {
        this.dislikes = dislikes;
    }



}