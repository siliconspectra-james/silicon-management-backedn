package com.firebase.candidatemanagement.Repository;

import com.google.cloud.firestore.Firestore;
import org.springframework.stereotype.Repository;

@Repository
public class FireStoreRepository extends FirestoreAbstractRepo{

    protected FireStoreRepository(Firestore firestore) {
        super(firestore, "User");
    }
}
