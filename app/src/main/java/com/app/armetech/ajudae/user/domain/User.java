package com.app.armetech.ajudae.user.domain;

import android.util.Log;

import com.app.armetech.ajudae.aulas.domain.Subject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class User {
    private String email;
    private String password;
    private String Token;
    private List<Subject> subjects;
    private long id;

    public User() { this.subjects = new ArrayList<>(); }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getToken() {
        return Token;
    }

    public void setToken(String token) {
        Token = token;
    }

    public List<Subject> getSubjects() {
        return subjects;
    }

    public String getSubjectsAsString() {
        String subjectsAsStrings = "";
        if(subjects.size() < 1)
            return "";
        for(Subject sub : subjects) {
            subjectsAsStrings += sub.getCourseClass() + ':' + sub.getSubjectName();
        }
        return "'" + subjectsAsStrings + "'";
    }

    public Subject getSubject(String subject) {
        for (Subject sub : subjects)
            if(sub.getSubjectName() == subject)
                return sub;
        return null;
    }

    public void setSubject(Subject subject) {
        subjects.add(subject);
    }

    public void setSubjects(List<Subject> subjects) {
        this.subjects = subjects;
    }

    public void setSubjects(String subjectsString) {
        if(subjectsString.length() > 1) {
            List<String> items = Arrays.asList(subjectsString.split("\\s*,\\s*"));
            for (String i : items) {
                String[] data = i.split(":");
                subjects.add(new Subject(data[0], data[1]));
            }
        }
    }
}
