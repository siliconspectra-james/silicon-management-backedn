package com.siliconspectra.management.Service;

import com.siliconspectra.management.Entity.User;
import com.siliconspectra.management.Repository.UserRepository;
import com.siliconspectra.management.exception.CustomException;
import com.siliconspectra.management.vo.Candidate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {



    @Autowired
    UserRepository userRepository;

    public Candidate getCandidateById(String uid) throws CustomException {
        User user = userRepository.findUserByUserId(uid);
        if (user != null) {
            return this.mapUserToCandidate(user);
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

    public void updateUser(String uid,Candidate candidate) throws CustomException{
        try{
            User user = userRepository.findUserByUserId(uid);
            System.out.println(this.mapCandidateToUser(candidate,user));

        }catch (Exception e) {
            throw new CustomException("update user error");
        }




    }

    public List<Candidate> getAllUsers() throws CustomException{
        List<User> userList = userRepository.findAll();
        if(!userList.isEmpty()) {
            return userList
                    .stream()
                    .map(this::mapUserToCandidate)
                    .toList();

        }else{
            throw new CustomException("Unable to fetch all users");
        }

    }

    public void deleteUserById(String userId) throws CustomException{

        try{
            userRepository.deleteById(userId);

        }catch (Exception e) {
            throw new CustomException("delete user success");
        }
    }
    //Convert user to candidate (helper)
    private Candidate mapUserToCandidate(User user){
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
    }
    private User mapCandidateToUser(Candidate candidate,User user){
        user.setUserName(candidate.getCandidateName());
        user.setUserEmail(candidate.getCandidateEmail());
        user.setUserPhoneNumber(candidate.getCandidatePhoneNumber());
        user.setUserGender(candidate.getCandidateGender());
        user.setUserBirthday(candidate.getCandidateBirthday());
        user.setUserLinkedin(candidate.getCandidateLinkedin());
        user.setUserLocation(candidate.getCandidateLocation());
        user.setUserMarketingName(candidate.getCandidateMarketingName());
        user.setUserMarketingEmail(candidate.getCandidateMarketingEmail());
        user.setUserMarketingPhoneNumber(candidate.getCandidateMarketingPhoneNumber());
        user.setUserVisaType(candidate.getCandidateVisaType());
        user.setUserDegree(candidate.getDegree());
        return user;
    }
}
