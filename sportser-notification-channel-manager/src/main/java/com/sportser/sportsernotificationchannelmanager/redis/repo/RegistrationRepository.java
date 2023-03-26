package com.sportser.sportsernotificationchannelmanager.redis.repo;

import com.sportser.sportsernotificationchannelmanager.redis.model.Registration;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface RegistrationRepository  extends CrudRepository<Registration, String> {}