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
    @GetMapping("/candidate/{uid}")
    public Candidate getCandidateById(@PathVariable String uid) throws CustomException {
        return userService.getCandidateById(uid);
    }

    @PutMapping("/candidate/{uid}")
    public ResponseEntity<Response> updateCandidate(@PathVariable String uid, @RequestBody Candidate c) throws CustomException{
        userService.updateUser(uid,c);
        return new ResponseEntity<>(new Response("User update success"), HttpStatus.OK);
    }

    @PostMapping("/admin/{uid}")
    public ResponseEntity<Response> createUser(@PathVariable String uid, @RequestBody User user) throws CustomException {
        String message = userService.createUser(user);
        return new ResponseEntity<>(new Response(message), HttpStatus.OK);
    }

    @GetMapping("/admin/all")
    public List<Candidate> getAllCandidates()throws CustomException{

        return userService.getAllCandidates();
    }

    //This is for test
//    @GetMapping("/admin/alluser")
//    public List<User> getAllUsers()throws CustomException{
//
//        return userService.getAllUsers();
//    }
    @DeleteMapping("/admin/delete/{uid}")
    public ResponseEntity<Response> deleteUser(@PathVariable String uid) throws  Exception{
        userService.deleteUserById(uid);
        return new ResponseEntity<>(new Response("delete user successful"), HttpStatus.OK);

    }



}



