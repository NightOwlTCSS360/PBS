package model;

import model.projectdata.Project;

import java.io.IOException;
import java.io.Serializable;
import java.nio.file.*;
import java.rmi.NoSuchObjectException;
import java.util.HashMap;
import java.util.Map;

public class User implements Serializable {

    private String myUserLastName;

    private String myUserFirstName;

    private String myUserEmail;

    private String myPassword;

    private Map<String, Project> myProjects;
    private transient Path myPath;

    private static final long serialVersionUID = 15152023L;

    public User(String theUserFirstName, String theUserLastName, String theUserEmail, String thePassword) {

        myUserFirstName = theUserFirstName;
        myUserLastName = theUserLastName;
        myUserEmail = theUserEmail;
        myPassword = thePassword;
        myProjects = new HashMap<>();
        try {
            myPath = Paths.get("./ProjectManager/src/main/resources/appdata/" + myUserEmail);
            if (Files.exists(myPath)) {
                System.out.println(myPath.toRealPath() + " exists (User Directory)");
            } else {
                System.out.println(myPath.toString() + " doesn't exist. Creating Directory now...");
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
    }

    public Path getUserPath() {
        return myPath;
    }
    public void loadInUserProjects() {
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(myPath)) {
            System.out.println("Printing Directories in " + myUserFirstName + "'s directory:");
            for (Path projectFolder : stream) {
                System.out.println(projectFolder.getFileName());
                try {
                    Project p = Project.deserialize(projectFolder + "/" + projectFolder.getFileName() + ".ser");
                    addProject(p);
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
    public void addProject(final Project theProject) {
        String pName = theProject.getMyProjectName();
        if (myProjects.containsKey(pName)) {
            System.out.println("Project " + pName + " already exists in " + myUserFirstName + "'s Projects, overwriting old project. . .");
        } else {
            System.out.println("Project " + pName + " doesn't exist in "+ myUserFirstName + "'s Projects, adding now. . .");
        }
        myProjects.put(theProject.getMyProjectName(), theProject);
    }
    public Map<String, Project> getProjects() {
        return myProjects;
    }
    public Project getProject(final String theProjectName) throws NoSuchObjectException {
        Project result = null;

        result =  myProjects.get(theProjectName);
        if (result == null) {
            throw new NoSuchObjectException(theProjectName + " doesn't exist! How did you do that???");
        }
        return result;
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

