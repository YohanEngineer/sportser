package com.sportser.sportseremergencynotificationagent.entities.composite;

import java.io.Serializable;

public class Coaching implements Serializable {

    private Integer idUser;
    private Integer idCoach;

    public Coaching(Integer idUser, Integer idCoach) {
        this.idUser = idUser;
        this.idCoach = idCoach;
    }

    public Coaching() {
    }

    public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }

    public Integer getIdCoach() {
        return idCoach;
    }

    public void setIdCoach(Integer idCoach) {
        this.idCoach = idCoach;
    }
}
