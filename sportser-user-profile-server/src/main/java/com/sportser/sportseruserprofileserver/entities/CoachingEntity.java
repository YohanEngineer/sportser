package com.sportser.sportseruserprofileserver.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name="coaching")
public class CoachingEntity implements Serializable {

    @Id
    @Column(name="id_user")
    private Integer idUser;

    @Id
    @Column(name="id_coach")
    private Integer idCoach;

    public CoachingEntity() {
    }

    public CoachingEntity(Integer idUser, Integer idCoach) {
        this.idUser = idUser;
        this.idCoach = idCoach;
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
