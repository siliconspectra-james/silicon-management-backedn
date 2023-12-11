package com.siliconspectra.management.Service;

import com.siliconspectra.management.Entity.User;
import com.siliconspectra.management.Repository.UserRepository;
import com.siliconspectra.management.exception.CustomException;
import com.siliconspectra.management.vo.Candidate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {



    @Autowired
    UserRepository userRepository;

    public Candidate getCandidateById(String uid) throws CustomException {
        User user = userRepository.findUserByUserId(uid);
        if (user != null) {
            Candidate candidate = new Candidate();
            candidate.setCandidateName(user.getUserName());
            candidate.setCandidateEmail(user.getUserEmail());
            candidate.setCandidatePhoneNumber(user.getUserPhoneNumber());
            candidate.setCandidateGender(user.getUserGender());
            candidate.setCandidateBirthday(user.getUserBirthday());
            candidate.setCandidateLinkedin(user.getUserLinkedin());
            candidate.setCandidateLocation(user.getUserLocation());
            candidate.setCandidateMarketingName(user.getUserMarketingName());
            candidate.setCandidateMarketingEmail(user.getUserMarketingEmail());
            candidate.setCandidateMarketingPhoneNumber(user.getUserMarketingPhoneNumber());
            candidate.setCandidateVisaType(user.getUserVisaType());
            candidate.setDegree(user.getUserDegree());
            return candidate;
        }else {
            throw new CustomException("user not found");
        }

    }

    public void createUser(User user) throws CustomException {
        try {
            userRepository.save(user);
        }catch (Exception e) {
            throw new CustomException("create user error");
        }
    }

    public void updateUser(User user) {
        User u = userRepository.findUserByUserId(user.getUserId());
    }
}
