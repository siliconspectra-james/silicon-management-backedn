package com.firebase.candidatemanagement.Controller;

import com.firebase.candidatemanagement.Entity.Role;
import com.firebase.candidatemanagement.Service.FirebaseService;
import com.google.firebase.auth.FirebaseAuthException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/admin")
public class UserController {
    @Autowired
    private FirebaseService firebaseService;


    @PostMapping("/user-claims/{uid}")
    public void setUserClaims(@PathVariable String uid, @RequestBody List<Role> requestedRoles) throws FirebaseAuthException {


        firebaseService.setUserClaims(uid, requestedRoles);

    }

    @GetMapping("/user")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String hello(Principal principal) {
        return principal.getName();
    }

    @GetMapping("/test")
    @Secured("ADMIN")
    public String test() {
        return "check";
    }
}



