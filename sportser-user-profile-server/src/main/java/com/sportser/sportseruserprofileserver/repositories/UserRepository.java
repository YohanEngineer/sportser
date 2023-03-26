package com.sportser.sportseruserprofileserver.repositories;

import com.sportser.sportseruserprofileserver.entities.UserEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<UserEntity, Integer> {

    @Query("""
           Select u.subscribing from UserEntity u where u.email = ?1
            """)
    Boolean checkSubscribing(String email);

    UserEntity findByEmail(String email);
}
