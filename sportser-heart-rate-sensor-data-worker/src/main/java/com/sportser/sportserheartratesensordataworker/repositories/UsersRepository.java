package com.sportser.sportserheartratesensordataworker.repositories;

import com.sportser.sportserheartratesensordataworker.model.Users;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UsersRepository extends MongoRepository<Users, Integer> {
}
