package com.flashapps.humorfinder.Models;

import java.util.List;

/**
 * Created by dietervaesen on 3/11/15.
 */
public class Joke {
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Value getValue() {
        return value;
    }

    public void setValue(Value value) {
        this.value = value;
    }

    private String type;
    private Value value;


    public class Value {
        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getJoke() {
            return joke;
        }

        public void setJoke(String joke) {
            this.joke = joke;
        }

        public List<Integer> getCategories() {
            return categories;
        }

        public void setCategories(List<Integer> categories) {
            this.categories = categories;
        }

        private int id;
        private String joke;
        private List<Integer> categories;
    }
}
