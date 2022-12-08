package com.course.app;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Artists {
    private static Artists instance;
    private List<String> list = new ArrayList<>();
    private Artists() {
        list.add("Adele");
        list.add("Billie Eilish");
        list.add("Taylor Swift");
        list.add("Ed Sheeran");
    }

    public static Artists getInstance() {
        if (instance == null) {
            instance = new Artists();
        }
        return instance;
    }
    public void addArtist(String name) {
        list.add(name);
    }

    public void deleteArtist(String name){
        list.remove(name);
    }

    public List<String> getArtists() {
        return list;
    }

    public static class UserChoice {
      private Set<String> artists = new HashSet<>();
      private Set<String> genres = new HashSet<>();
      private String text;
      private String currentTime;
      private static final DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

      public UserChoice(String[] artistsArr, String[] genresArr, String text) {
        Collections.addAll(artists, artistsArr);
        Collections.addAll(genres, genresArr);
        this.text = text;
        this.currentTime = LocalDateTime.now().format(format);
      }

      public Set<String> getArtists() {
        return artists;
      }

      public Set<String> getGenres() {
        return genres;
      }

      public String getText() {
        return text;
      }

      public String getCurrentTime() {
        return currentTime;
      }
    }
}
