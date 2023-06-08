package model;

import control.PDC;
import model.projectdata.Project;

import java.io.IOException;
import java.io.Serializable;
import java.nio.file.*;
import java.rmi.NoSuchObjectException;
import java.util.HashMap;
import java.util.Map;

/**
 * Class that represents a User
 * @author Hieu Nguyen
 * @author Derek J. Ruiz Garcia
 * @author Paul Schmidt
 */
public class User implements Serializable {

    /** The Users Last Name */
    private String myUserLastName;

    /** The User's First Name */
    private String myUserFirstName;

    /** The User's email */
    private String myUserEmail;

    /** The User's password */
    private String myPassword;

    /** Map of the User's Projects using the Projects' names as keys (String) */
    private Map<String, Project> myProjects;

    /** Path to the User's folder */
    private transient Path myPath;

    /** Serial ID */
    private static final long serialVersionUID = 15152023L;

    /**
     * Creates a User object with the given first, last, email, and password.
     * Uses the User's email to generate the User's appdata Folder that will contain the Projects etc. if it
     * doesn't already exist.
     * @author Hieu Nguyen
     * @author Derek J. Ruiz Garcia
     * @author Paul Schmidt
     * @param theUserFirstName the first name of the User
     * @param theUserLastName the last name of the User
     * @param theUserEmail the email of the User
     * @param thePassword the password of the User
     */
    public User(String theUserFirstName, String theUserLastName, String theUserEmail, String thePassword) {

        myUserFirstName = theUserFirstName;
        myUserLastName = theUserLastName;
        myUserEmail = theUserEmail;
        myPassword = thePassword;
        myProjects = new HashMap<>();
        try {
            myPath = Paths.get(PDC.myDir + "\\" + myUserEmail);
            if (!Files.exists(myPath)) {
                Files.createDirectory(myPath);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns the path to this User's appdata
     * @author Paul Schmidt
     * @return the Path of this User's appdata folder
     */
    public Path getUserPath() {
        return myPath;
    }

    /**
     * Populates the Map of User Projects by looking in the User's appdata Folder and scanning for serialized projects,
     * deserializing them, and adding them to the Map.
     * @author Paul Schmidt
     */
    public void loadInUserProjects() {
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(myPath)) {
            for (Path projectFolder : stream) {
                try {
                    Project p = Project.deserialize(projectFolder + "\\" + projectFolder.getFileName() + ".ser");
                    addProject(p);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        } catch (IOException | DirectoryIteratorException x) {
            System.err.println(x);
        }
    }

    /**
     * Add the given Project object to this User's Map of Projects using the Project's Name as the key.
     * @author Paul Schmidt
     * @param theProject to add
     */
    public void addProject(final Project theProject) {
        theProject.serialize(PDC.myDir);
        myProjects.put(theProject.getMyProjectName(), theProject);
    }

    /**
     * Deletes the project from the list of projects from the current user.
     * @author Derek J. Ruiz Garcia
     * @param theProjectName the name of the project we want to remove.
     */
    public void deleteProject(String theProjectName){
        myProjects.remove(theProjectName);
    }

    /**
     * Return a reference to the Map of User's Projects
     * @author Paul Schmidt
     * @return the map of Projects
     */
    public Map<String, Project> getProjects() {
        return Map.copyOf(myProjects);
    }

    /**
     * Returns a reference to the desired Project given the Project's name as a key.
     * @author Paul Schmidt
     * @param theProjectName the name of the Project desired
     * @return the reference to the Project
     * @throws NoSuchObjectException if the Project doesn't exist
     */
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

