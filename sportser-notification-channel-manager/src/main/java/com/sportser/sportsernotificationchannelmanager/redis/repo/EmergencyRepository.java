package com.sportser.sportsernotificationchannelmanager.redis.repo;

import com.sportser.sportsernotificationchannelmanager.redis.model.Emergency;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface EmergencyRepository extends CrudRepository<Emergency, String> {}