package com.firebase.candidatemanagement.Repository;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

import java.lang.reflect.ParameterizedType;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

@Slf4j
public abstract class FirestoreAbstractRepo<T> {

    private final CollectionReference collectionReference;
    private final String collectionName;
    private final Class<T> parameterizedType;


    protected FirestoreAbstractRepo(Firestore firestore, String collection) {
        this.collectionReference = firestore.collection(collection);
        this.collectionName = collection;
        this.parameterizedType = getParameterizedType();

    }
    @SuppressWarnings("unchecked")
    private Class<T> getParameterizedType(){
        ParameterizedType type = (ParameterizedType) this.getClass().getGenericSuperclass();

        return (Class<T>)type.getActualTypeArguments()[0];
    }

    public void  save(T model,String docId) throws ExecutionException, InterruptedException {
        ApiFuture<WriteResult> resultApiFuture = collectionReference.document(docId).set(model);






    }

    public void delete(T model,String docId){

        ApiFuture<WriteResult> resultApiFuture = collectionReference.document(docId).delete();

    }

    public List<T> retrieveAll() throws ExecutionException, InterruptedException {
        ApiFuture<QuerySnapshot> querySnapshotApiFuture = collectionReference.get();

        List<QueryDocumentSnapshot> queryDocumentSnapshots = querySnapshotApiFuture.get().getDocuments();

        return queryDocumentSnapshots.stream()
                .map(queryDocumentSnapshot -> queryDocumentSnapshot.toObject(parameterizedType))
                .collect(Collectors.toList());




    }


    public Optional<T> get(String documentId) throws ExecutionException, InterruptedException {
        DocumentReference documentReference = collectionReference.document(documentId);
        ApiFuture<DocumentSnapshot> documentSnapshotApiFuture = documentReference.get();


        DocumentSnapshot documentSnapshot = documentSnapshotApiFuture.get();

        if(documentSnapshot.exists()){
            return Optional.ofNullable(documentSnapshot.toObject(parameterizedType));
        }



        return Optional.empty();

    }

}
