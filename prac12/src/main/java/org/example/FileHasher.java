package org.example;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

@Component
public class FileHasher {
    @Value("#{springApplicationArguments.nonOptionArgs[0]}")
    private String firstPath;
    @Value("#{springApplicationArguments.nonOptionArgs[1]}")
    private String secondPath;

    @PostConstruct
    private void onConstruct() {
        try {
            if (firstPath == null) {
                System.out.println("No args");
            } else if (secondPath == null) {
                Files.writeString(Path.of(firstPath), "null\n", StandardOpenOption.CREATE);
                System.out.println("Old file not specified. The new one contains 'null'");
            } else {
                String oldText = Files.readString(Path.of(firstPath));
                String newText = "null\n";
                if (!oldText.isEmpty()) {
                    String hashCode = String.valueOf(oldText.hashCode());
                    newText = hashCode + "\n";
                }
                Files.writeString(Path.of(secondPath), newText, StandardOpenOption.CREATE);
                System.out.println("The contents of the old file were hashed and placed into the new one");
            }
        } catch (NoSuchFileException e) {
            handleFileException(e);
        } catch (IOException e) {
            handleGeneralException(e);
        }
    }

    @PreDestroy
    private void preDestroy() {
        try {
            if (firstPath != null && secondPath != null) {
                Files.delete(Path.of(firstPath));
                System.out.println("Old file was deleted");
            }
        } catch (NoSuchFileException e) {
            handleFileException(e);
        } catch (IOException e) {
            handleGeneralException(e);
        }
    }

    private void handleFileException(NoSuchFileException e) {
        System.out.println("File not found: " + e.getFile());
        System.out.println("Current directory: " + System.getProperty("user.dir"));
    }

    private void handleGeneralException(Exception e) {
        System.out.println(e.toString());
    }
}
