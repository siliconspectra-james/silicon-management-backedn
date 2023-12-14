package com.siliconspectra.management.Controller;

import com.siliconspectra.management.Entity.User;
import com.siliconspectra.management.Service.UserService;
import com.siliconspectra.management.exception.CustomException;
import com.siliconspectra.management.vo.Candidate;
import com.siliconspectra.management.vo.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UserController {

    @Autowired
    UserService userService;
    //Get single user data in Candidate form.
    @GetMapping("/candidate/{uid}")
    public Candidate getCandidateById(@PathVariable String uid) throws CustomException {
        return userService.getCandidateById(uid);
    }
    //Update user data in Candidate form
    @PutMapping("/candidate/{uid}")
    public ResponseEntity<Response> updateCandidate(@PathVariable String uid, @RequestBody Candidate c) throws CustomException{
        userService.updateCandidate(uid,c);
        return new ResponseEntity<>(new Response("User update success"), HttpStatus.OK);
    }
    //Update any user data with admin account.
    @PutMapping("/admin/{uid}")
    public ResponseEntity<Response> adminUpdateUser(@PathVariable String uid, @RequestBody User user) throws CustomException{
        userService.updateUser(uid,user);
        return new ResponseEntity<>(new Response("User update success"), HttpStatus.OK);
    }

    //Get single user with admin access.
    @GetMapping("/admin/{uid}")
    public User adminGetUserById(@PathVariable String uid) throws CustomException {
        return userService.getUserById(uid);
    }


    //Create new user data (Admin access)
    @PostMapping("/admin/{uid}")
    public ResponseEntity<Response> createUser(@PathVariable String uid, @RequestBody User user) throws CustomException {
        String message = userService.createUser(user);
        return new ResponseEntity<>(new Response(message), HttpStatus.OK);
    }
    //Get all user data in Candidate form.
    @GetMapping("/admin/all/candidates")
    public List<Candidate> getAllCandidates()throws CustomException{

        return userService.getAllCandidates();
    }

    //Get all user data in User form.
    @GetMapping("/admin/all/users")
    public List<User> getAllUsers()throws CustomException{

        return userService.getAllUsers();
    }
    //Delete user data in database.
    @DeleteMapping("/admin/delete/{uid}")
    public ResponseEntity<Response> deleteUser(@PathVariable String uid) throws  Exception{
        userService.deleteUserById(uid);
        return new ResponseEntity<>(new Response("delete user successful"), HttpStatus.OK);

    }



}



