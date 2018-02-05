package com.app.armetech.ajudae.user.domain;


public class Person {
    private long id;
    private String name;
    private User user = new User();
    private String birthDate;
    private String gender;
    private String cep;

    public void setCep(String cep){
        this.cep = cep;
    }

    public String getCep(){
        return cep;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getUserId() {
        return this.user.getId();
    }

    public void setUserId(long userId) {
        this.user.setId(userId);
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getGender() {
        return this.gender;
    }

    public void setGender(String gender){
        this.gender = gender;
    }


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
