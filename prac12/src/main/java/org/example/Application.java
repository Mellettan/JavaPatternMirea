package org.example;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.swing.*;

@SpringBootApplication
public class Application implements CommandLineRunner {
    private static FileHasher fileHasher;

    public static void main(String[] args) throws InterruptedException {
        SpringApplication.run(Application.class, args);
        System.out.println("Application start");
        Thread.sleep(5000);
        System.out.println("Application finish");
    }

    @Override
    public void run(String... args) throws Exception { }
}
