package com.firebase.candidatemanagement.Config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseAuth;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.FileInputStream;
import java.io.IOException;

@Configuration
public class FirebaseAuthConfig {
    @Bean
    public void firebaseInitialize() throws IOException {
        FileInputStream serviceAccount =
                new FileInputStream("E:\\candidate-management\\src\\main\\resources\\candidate-management-a62c5-firebase-adminsdk-sbwgl-6b04519ab5.json");

        FirebaseOptions options = FirebaseOptions.builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .build();

        FirebaseApp.initializeApp(options);

    }
}
