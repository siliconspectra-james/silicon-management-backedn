package com.siliconspectra.management.Service;

import com.mongodb.client.result.UpdateResult;
import com.siliconspectra.management.Entity.User;
import com.siliconspectra.management.Repository.UserRepository;
import com.siliconspectra.management.exception.CustomException;
import com.siliconspectra.management.vo.Candidate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.*;
import org.springframework.stereotype.Service;
import java.lang.reflect.Field;

import java.util.List;

@Service
public class UserService {



    @Autowired
    UserRepository userRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    public Candidate getCandidateById(String uid) throws CustomException {
        User user = userRepository.findUserByUserId(uid);
        if (user != null) {
            return this.mapUserToCandidate(user);
        }else {
            throw new CustomException("user not found");
        }

    }

    public User getUserById(String uid) throws CustomException {
        User user = userRepository.findUserByUserId(uid);
        if (user != null) {
            return user;

        }else {
            throw new CustomException("user not found");
        }

    }

    public String createUser(User user) throws CustomException {
        try {
            User checkUser = userRepository.findUserByUserId(user.getUserId());
            if(checkUser==null){
                userRepository.save(user);
                return "create user success";
            }else{
                return "user exists already";
            }

        }catch (Exception e) {
            throw new CustomException("create user error");
        }
    }

    public void updateCandidate(String uid,Candidate candidate) throws CustomException{
        try{
            Query query = new Query(Criteria.where("userId").is(uid));
            Update update = new Update()
                    .set("userName", candidate.getCandidateName())
                    .set("userEmail", candidate.getCandidateEmail())
                    .set("userPhoneNumber", candidate.getCandidatePhoneNumber())
                    .set("userGender", candidate.getCandidateGender())
                    .set("userBirthday", candidate.getCandidateBirthday())
                    .set("userLinkedin", candidate.getCandidateLinkedin())
                    .set("userLocation", candidate.getCandidateLocation())
                    .set("userMarketingName", candidate.getCandidateMarketingName())
                    .set("userMarketingEmail", candidate.getCandidateMarketingEmail())
                    .set("userMarketingPhoneNumber", candidate.getCandidateMarketingPhoneNumber())
                    .set("userVisaType", candidate.getCandidateVisaType())
                    .set("userDegree", candidate.getDegree());

            UpdateResult updateResult = mongoTemplate.updateFirst(query, update, User.class);



        }catch (Exception e) {
            throw new CustomException("update user error");
        }

    }
    public void updateUser(String uid,User user) throws CustomException{
        try{
            Query query = new Query(Criteria.where("userId").is(uid));
            Update update = this.createUpdate(user);
            UpdateResult updateResult = mongoTemplate.updateFirst(query, update, User.class);

        }catch (Exception e) {
            throw new CustomException("update user error");
        }

    }

    public List<Candidate> getAllCandidates() throws CustomException{
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



    //test to get all user
    public List<User> getAllUsers() throws CustomException{
        List<User> userList = userRepository.findAll();
        if(!userList.isEmpty()) {
            return userList;
        }else{
            throw new CustomException("Unable to fetch all users");
        }

    }

    public void deleteUserById(String userId) throws CustomException{

        try{
            userRepository.deleteByUserId(userId);

        }catch (Exception e) {
            throw new CustomException("delete user failed");
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
//    private User mapCandidateToUser(Candidate candidate,User user){
//        user.setUserName(candidate.getCandidateName());
//        user.setUserEmail(candidate.getCandidateEmail());
//        user.setUserPhoneNumber(candidate.getCandidatePhoneNumber());
//        user.setUserGender(candidate.getCandidateGender());
//        user.setUserBirthday(candidate.getCandidateBirthday());
//        user.setUserLinkedin(candidate.getCandidateLinkedin());
//        user.setUserLocation(candidate.getCandidateLocation());
//        user.setUserMarketingName(candidate.getCandidateMarketingName());
//        user.setUserMarketingEmail(candidate.getCandidateMarketingEmail());
//        user.setUserMarketingPhoneNumber(candidate.getCandidateMarketingPhoneNumber());
//        user.setUserVisaType(candidate.getCandidateVisaType());
//        user.setUserDegree(candidate.getDegree());
//        return user;
//    }

    private Update createUpdate(User user) {
        Update update = new Update();
        Class<?> clazz = User.class;

        for (Field field : clazz.getDeclaredFields()) {
            field.setAccessible(true);

            // Exclude the "uid" field from being updated
            if (!field.getName().equals("userID")) {
                try {
                    Object value = field.get(user);
                    update.set(field.getName(), value);
                } catch (IllegalAccessException e) {
                    e.printStackTrace(); // Handle the exception appropriately
                }
            }
        }

        return update;
    }
}
