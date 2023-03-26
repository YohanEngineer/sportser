package com.sportser.sportseremergencynotificationagent.repositories;

import com.sportser.sportseremergencynotificationagent.dto.EmergencyDto;
import com.sportser.sportseremergencynotificationagent.entities.UserEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends CrudRepository<UserEntity, Integer> {

    @Query(value = """
    SELECT new com.sportser.sportseremergencynotificationagent.dto.EmergencyDto(c.idUser.firstName, c.idUser.lastName, c.idCoach.email)
    FROM CoachingEntity c
    WHERE c.idUser.email = :userEmail
    """)
    EmergencyDto findUserAndCoach(@Param("userEmail") String userEmail);
}
