package com.example.evgenia.tfsandroidchat.presentation.login.user;

/**
 * Created by User on 18.05.2017.
 */

public class User {
    private static String name = " name";

    public User(String name) {
        this.name = name;
    }

    public static String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
