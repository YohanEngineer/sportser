package com.sportser.sportseremergencynotificationagent.entities;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@AllArgsConstructor
@Getter
@Setter
@Table(name="coaching", schema="public")
public class CoachingEntity implements Serializable {

    @Id
    @ManyToOne
    @JoinColumn(name="id_user")
    private UserEntity idUser;

    @Id
    @ManyToOne
    @JoinColumn(name="id_coach")
    private CoachEntity idCoach;

    public CoachingEntity() {
    }

}
