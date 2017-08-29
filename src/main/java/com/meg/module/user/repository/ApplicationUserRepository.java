package com.meg.module.user.repository;

import com.meg.module.user.domain.ApplicationUser;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by meg on 8/27/17.
 */
public interface ApplicationUserRepository extends MongoRepository<ApplicationUser, String>{
}
