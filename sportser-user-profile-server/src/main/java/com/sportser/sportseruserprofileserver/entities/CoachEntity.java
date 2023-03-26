package com.sportser.sportseruserprofileserver.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="coachs")
public class CoachEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_coach", nullable = false)
    private Integer idCoach;

    @Column(name = "firstname")
    private String firstName;

    @Column(name = "lastname")
    private String lastName;

    @Column(name = "email")
    private String email;

    public CoachEntity() {
    }

    public CoachEntity(Integer idCoach, String firstName, String lastName, String email) {
        this.idCoach = idCoach;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public Integer getIdCoach() {
        return idCoach;
    }

    public void setIdCoach(Integer idCoach) {
        this.idCoach = idCoach;
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
}
