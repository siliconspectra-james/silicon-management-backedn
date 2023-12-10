package com.siliconspectra.management.Service;

import com.siliconspectra.management.Entity.User;
import com.siliconspectra.management.Repository.UserRepository;
import com.siliconspectra.management.vo.Candidate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public Candidate getCandidateById(String uid) {
        User user = userRepository.getUserById(uid);
        Candidate candidate = new Candidate();

        return candidate;
    }
}
