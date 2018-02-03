package com.app.armetech.ajudae.classes.domain;

import java.util.List;

/**
 * Created by Emerson on 18/12/2017.
 */

public class Department{
    private String name;
    private int idDepartment;
    private List<Subject> Subjects;

    public List<Subject> getSubjects() {
        return Subjects;
    }

    public void setSubjects(List<Subject> subjects) {
        Subjects = subjects;
    }

    public int getIdDepartment() {
        return idDepartment;
    }

    public void setIdDepartment(int idDepartment) {
        this.idDepartment = idDepartment;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
