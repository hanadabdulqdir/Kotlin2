package com.example.hanad.cucknorisapi.model;

/**
 * Created by hanad on 22/02/2018.
 */

public class Joke {

    private int id;
    private String joke;
    private String[] categories;

    public int getId() {
        return id;
    }

    public String getJokes() {
        return joke;
    }

    public String[] getCategories() {
        return categories;
    }

    @Override
    public String toString() {
        return "Jokes{" +
                "id=" + id +
                ", jokes='" + joke + '\'' +
                ", categories='" + categories + '\'' +
                '}';
    }
}
