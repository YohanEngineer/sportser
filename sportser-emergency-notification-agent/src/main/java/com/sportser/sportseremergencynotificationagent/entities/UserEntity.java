package com.sportser.sportseremergencynotificationagent.entities;

import javax.persistence.*;

@Entity
@Table(name="users", schema="public")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_user", nullable = false)
    private Integer idUser;

    @Column(name = "firstname")
    private String firstName;

    @Column(name = "lastname")
    private String lastName;

    @Column(name = "email")
    private String email;

    @Column(name= "height")
    private Integer height;

    @Column(name= "weight")
    private Integer weight;

    @Column(name="subscribing")
    private Boolean subscribing;

    @Column(name="age")
    private Integer age;

    public UserEntity() {
    }

    public UserEntity(String firstName, String lastName, String email, Integer height, Integer weight, Boolean subscribing,Integer age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.height = height;
        this.weight = weight;
        this.subscribing = subscribing;
        this.age = age;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
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

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public Boolean getSubscribing() {
        return subscribing;
    }

    public void setSubscribing(Boolean subscribing) {
        this.subscribing = subscribing;
    }
}
