package model.projectdata;

import model.User;

import java.io.IOException;
import java.nio.file.*;

public class DirectoryTesting {
    public static void main(String[] args) {
        User testUser = new User("Schmidt", "Schmidt", "email@email.com", "pw");
        try {
            Path dir = Paths.get("./ProjectManager/src/main/resources/appdata/" + testUser.getUserEmail()); //get a path to the current directory
            if (Files.exists(dir)) {
                System.out.println(dir.toRealPath().toString() + " exists");
            } else {
                System.out.println(dir.toString() +" doesn't exist. Creating Directory now...");
                Files.createDirectory(dir);
                if (Files.exists(dir)) {
                    System.out.println(dir.toRealPath().toString() + " created!");
                } else {
                    System.out.println("Error creating " + dir.toString());
                }
            }
            try (DirectoryStream<Path> stream = Files.newDirectoryStream(dir)) {
                for (Path file: stream) {
                    System.out.println(file.getFileName());
                }
            } catch (IOException | DirectoryIteratorException x) {
                // IOException can never be thrown by the iteration.
                // In this snippet, it can only be thrown by newDirectoryStream.
                System.err.println(x);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
