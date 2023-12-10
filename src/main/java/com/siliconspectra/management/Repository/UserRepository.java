package com.siliconspectra.management.Repository;

import com.siliconspectra.management.Entity.User;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository {

    public User getUserById(String uid) {
        return new User();
    }
}
