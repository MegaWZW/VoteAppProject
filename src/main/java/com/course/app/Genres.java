package com.course.app;

import java.util.ArrayList;
import java.util.List;

public class Genres {
    private static Genres instance;
    private List<String> list = new ArrayList<>();

    private Genres(){
        list.add("Pop");
        list.add("Hip hop");
        list.add("Rock");
        list.add("Rhythm and blues");
        list.add("Soul");
        list.add("Reggae");
        list.add("Country");
        list.add("Funk");
        list.add("Folk");
        list.add("Jazz");
    }

    public static Genres getInstance(){
        if (instance == null){
            instance = new Genres();
        }
        return instance;
    }
    public void addGenre(String name) {
        list.add(name);
    }

    public void deleteGenre(String name){
        list.remove(name);
    }

    public List<String> getGenres() {
        return list;
    }
}
