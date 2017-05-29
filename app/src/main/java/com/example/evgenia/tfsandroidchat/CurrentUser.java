package com.example.evgenia.tfsandroidchat;

/**
 * Created by User on 29.05.2017.
 */

public class CurrentUser {
    private static String login;
    private static Long id;

    public CurrentUser() {
    }

    public static String getLogin() {
        return login;
    }

    public static void setLogin(String login) {
        CurrentUser.login = login;
    }

    public static Long getId() {
        return id;
    }

    public static void setId(Long id) {
        CurrentUser.id = id;
    }
}
