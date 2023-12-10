package com.firebase.candidatemanagement.Service;

import com.firebase.candidatemanagement.Entity.Role;
import com.firebase.candidatemanagement.Repository.FireStoreRepository;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class FirebaseService {

    @Autowired
    private  FirebaseAuth firebaseAuth;
    @Autowired
    private Firestore firestore;
    @Autowired
    private FireStoreRepository repository;



    public void setUserClaims(String uid, List<Role> requestedRoles) throws FirebaseAuthException {
        List<String> roles = requestedRoles
                .stream()
                .map(Enum::toString)
                .toList();

        Map<String, Object> claims = Map.of("roles", roles);

        firebaseAuth.setCustomUserClaims(uid, claims);
        System.out.println("sucess");
    }

    public void Save(){

    }
}