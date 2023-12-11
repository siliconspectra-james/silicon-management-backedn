package com.siliconspectra.management.Repository;


import com.siliconspectra.management.Entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<User, String> {

    @Query("{userId:'?0'}")
    User findUserByUserId (String userId);


}
