package com.app.armetech.ajudae.user.domain;

public final class Session {
    private static User LOGGED_USER;
    private static Person LOGGED_PERSON;

    public static Person getLoggedPerson() {
        return LOGGED_PERSON;
    }

    public static void setLoggedPerson(Person personLogada) {
        LOGGED_PERSON = personLogada;
    }

    public static User getLoggedUser() {
        return LOGGED_USER;
    }

    public static void setLoggedUser(User loggedUser) {
        LOGGED_USER = loggedUser;
    }

    public static void finishSession(){
        setLoggedPerson(null);
        setLoggedUser(null);
    }

}
