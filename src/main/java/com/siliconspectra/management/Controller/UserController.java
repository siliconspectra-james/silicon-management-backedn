package com.siliconspectra.management.Controller;

import com.siliconspectra.management.Entity.User;
import com.siliconspectra.management.Service.UserService;
import com.siliconspectra.management.exception.CustomException;
import com.siliconspectra.management.vo.Candidate;
import com.siliconspectra.management.vo.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


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
    public Candidate updateCandidate(@PathVariable String uid, @RequestBody Candidate c) {
        return null;
    }

    @PostMapping("/admin/{uid}")
    public ResponseEntity<Response> createUser(@PathVariable String uid, @RequestBody User user) throws CustomException {
        userService.createUser(user);
        return new ResponseEntity<>(new Response("create user successful"), HttpStatus.OK);
    }



}



