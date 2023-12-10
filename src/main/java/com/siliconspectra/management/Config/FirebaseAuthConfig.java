package com.siliconspectra.management.Config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import java.io.FileInputStream;
import java.io.IOException;

@Configuration
public class FirebaseAuthConfig {

    @Autowired
    private ResourceLoader resourceLoader;


    @PostConstruct
    public void init() throws IOException {
        Resource config = resourceLoader.getResource("classpath:silicon-spectra-management-firebase-adminsdk-mxlyv-230feab587.json");
        FirebaseOptions options = FirebaseOptions.builder()
                .setCredentials(GoogleCredentials.fromStream(config.getInputStream()))
                .build();

        FirebaseApp.initializeApp(options);

    }

    public static void main(String[] args) throws IOException {
        new FirebaseAuthConfig().init();
    }
}
