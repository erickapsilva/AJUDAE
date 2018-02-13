package com.app.armetech.ajudae.user.domain;

import android.util.Log;

import com.app.armetech.ajudae.classes.domain.Subject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class User {
    private String email;
    private String password;
    private String Token;
    private String course;
    private List<Subject> subjectsHelped;
    private List<Subject> subjectsHelper;
    private long id;
    private int stage;

    public User() {
        this.subjectsHelped = new ArrayList<>();
        this.subjectsHelper = new ArrayList<>();
    }

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

    public List<Subject> getSubjectsHelped() {
        return subjectsHelped;
    }

    public List<Subject> getSubjectsHelper() {
        return subjectsHelper;
    }

    public Subject getSubjectHelped(String subject) {
        for (Subject sub : subjectsHelped)
            if(sub.getSubjectName() == subject)
                return sub;
        return null;
    }

    public Subject getSubjectHelper(String subject) {
        for (Subject sub : subjectsHelper)
            if(sub.getSubjectName() == subject)
                return sub;
        return null;
    }

    public void addSubjectHelper(Subject subject) { subjectsHelper.add(subject); }

    public void addSubjectHelped(Subject subject) { subjectsHelped.add(subject); }

    public void delSubjectHelper(Subject subject) {
        subjectsHelper.remove(subject);
    }

    public void setSubjectsHelped(List<Subject> subjects) {
        this.subjectsHelped = subjects;
    }

    public void setSubjectsHelper(List<Subject> subjects) {
        this.subjectsHelper = subjects;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public int getStage() {
        return stage;
    }

    public void setStage(int stage) {
        this.stage = stage;
    }
}
