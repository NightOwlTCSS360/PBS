package model;

import model.projectdata.Project;

import java.io.IOException;
import java.io.Serializable;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;

public class User implements Serializable {

    private String myUserLastName;

    private String myUserFirstName;

    private String myUserEmail;

    private String myPassword;

    private List<Project> myProjects;
    private transient Path myPath;
    public User(String theUserLastName, String theUserFirstName, String theUserEmail, String thePassword){

        myUserLastName = theUserLastName;
        myUserFirstName = theUserFirstName;
        myUserEmail = theUserEmail;
        myPassword = thePassword;
        myProjects = new ArrayList<>();
        try {
            myPath = Paths.get("./ProjectManager/src/main/resources/appdata/" + myUserEmail);
            if (Files.exists(myPath)) {
                System.out.println(myPath.toRealPath() + " exists (User Directory)");
            } else {
                System.out.println(myPath.toString() +" doesn't exist. Creating Directory now...");
                Files.createDirectory(myPath);
                if (Files.exists(myPath)) {
                    System.out.println(myPath.toRealPath() + " created!");
                } else {
                    System.out.println("Error creating " + myPath.toString());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        generateUserProjects();
    }

    public Path getUserPath() {
        return myPath;
    }
    private void generateUserProjects() {
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(myPath)) {
            System.out.println("Printing Directories in " + myUserFirstName + "'s directory:");
            for (Path projectFolder : stream) {
                System.out.println(projectFolder.getFileName());
                try {
                    addProject(Project.deserialize(projectFolder + "/" + projectFolder.getFileName() + ".ser"));
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        } catch (IOException | DirectoryIteratorException x) {
            // IOException can never be thrown by the iteration.
            // In this snippet, it can only be thrown by newDirectoryStream.
            System.err.println(x);
        }
    }
    private void addProject(final Project theProject) {
        myProjects.add(theProject);
    }
    public void setUserLastName(String theUserLastname){
        myUserLastName = theUserLastname;
    }

    public String getMyUserLastName(){
        return myUserLastName;
    }

    public void setUserFirstName(String theUserFirstname){
        myUserFirstName = theUserFirstname;
    }

    public String getMyUserFirstName(){
        return myUserFirstName;
    }

    public void setUserEmail(String theUserEmail){
        myUserEmail = theUserEmail;
    }

    public String getUserEmail(){
        return myUserEmail;
    }

    public void setPassword(String thePassword){
        myPassword = thePassword;
    }

    public String getMyPassword(){
        return myPassword;
    }
}

