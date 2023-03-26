package com.sportser.sportserheartratesensordataworker.dto;

public class UserDto {

    private Integer idUser;

    private String firstName;

    private String lastName;

    private String email;

    private String height;

    private String weight;

    private Boolean subscribing;

    private Integer age;

    public UserDto(Integer idUser, String firstName, String lastName, String email, String height, String weight, Boolean subscribing, Integer age) {
        this.idUser = idUser;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.height = height;
        this.weight = weight;
        this.subscribing = subscribing;
        this.age = age;
    }

    public void setSubscribing(Boolean subscribing) {
        this.subscribing = subscribing;
    }

    public UserDto() {
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public UserDto(String firstName, String lastName, String email, String height, String weight, Boolean subscribing) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.height = height;
        this.weight = weight;
        this.subscribing = subscribing;
    }


    public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public Boolean getSubscribing() {
        return subscribing;
    }

}
