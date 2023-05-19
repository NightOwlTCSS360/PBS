package model.projectdata;


import model.User;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * This class represents the Project object.
 * @author Paul Schmidt
 */
public class Project implements Serializable {
    /**
     * The list of tasks belonging to this Project
     */
    private List<Task> myTasks;
    /**
     * The Project name
     */
    private String myProjectName;
    /**
     * The description of the Project
     */
    private String myDescription;
    /**
     * The User that this Project belongs to
     */
    private User myUser;
    /**
     * The path to the directory that contains this ProjectName.ser file
     */
    private transient Path myDirectoryPath;
    /**
     * The FilePath of this ProjectName.ser file
     */
    private transient Path myFilePath;
    /**
     * SerialVersionID
     */
    private static final long serialVersionUID = 5152023L;

    /**
     * Constructor to create a new Project object
     * @author Paul Schmidt
     * @param theUser the User that this Project belongs to
     * @param theName the Name of the Project
     * @throws IOException if the Directory for this Project can't be made
     */
    public Project(final User theUser, final String theName) throws IOException {
        myTasks = new ArrayList<>();
        myProjectName = theName;
        myDescription = "Default Description";
        myUser = theUser;
        myDirectoryPath = Paths.get("./ProjectManager/src/main/resources/appdata/" + myUser.getUserEmail() + "/" +
                myProjectName);
        myFilePath = Paths.get(myDirectoryPath.toString() + "/" + myProjectName + ".ser");
        if (Files.exists(myDirectoryPath)) {
            System.out.println(myDirectoryPath.toRealPath() + " exists (Project)");
        } else {
            System.out.println(myDirectoryPath.toString() +" doesn't exist. Creating Directory now...");
            Files.createDirectory(myDirectoryPath);
            Files.createDirectory(Paths.get(myDirectoryPath.toString() + "/User_Added_Files"));
            if (Files.exists(myDirectoryPath)) {
                System.out.println(myDirectoryPath.toRealPath() + " created!");
            } else {
                System.out.println("Error creating " + myDirectoryPath.toString());
            }
        }
    }

    /**
     * Constructor for a Project object used when Importing another Project.ser file into the User's list of Projects.
     * @author Paul Schmidt
     * @param theUser the User that this new Project belongs to
     * @param theProject the deserialized Project that we will copy fields from.
     * @throws IOException If something goes wrong when creating the Directory for the new Project or the Project.ser
     */
    public Project(final User theUser, final Project theProject) throws IOException {
        myProjectName = new String(theProject.myProjectName);
        myDescription = new String(theProject.myDescription);
        myUser = theUser;
        myTasks = theProject.myTasks;
        myDirectoryPath = Paths.get("./ProjectManager/src/main/resources/appdata/" + myUser.getUserEmail() + "/" +
                myProjectName);
        myFilePath = Paths.get(myDirectoryPath.toString() + "/" + myProjectName + ".ser");
        if (Files.exists(myDirectoryPath)) {
            System.out.println(myDirectoryPath.toRealPath() + " exists (Project)");
        } else {
            System.out.println(myDirectoryPath.toString() +" doesn't exist. Creating Directory now...");
            Files.createDirectory(myDirectoryPath);
            Files.createDirectory(Paths.get(myDirectoryPath.toString() + "/User_Added_Files"));
            if (Files.exists(myDirectoryPath)) {
                System.out.println(myDirectoryPath.toRealPath() + " created!");
            } else {
                System.out.println("Error creating " + myDirectoryPath.toString());
            }
        }
    }

    /**
     * Return the Path to the Directory containing this Project
     * @author Paul Schmidt
     * @return the Path of the directory containing this Project
     */
    public Path getDirectoryPath() {
        return myDirectoryPath;
    }

    /**
     * Return the Path to the .ser file of this Project
     * @author Paul Schmidt
     * @return the Path to the .ser file of this Project
     */
    public Path getMyFilePath() {
        return myFilePath;
    }

    /**
     * Return the name of this Project
     * @author Paul Schmidt
     * @return the name of this Project
     */
    public String getMyProjectName() {
        return myProjectName;
    }

    /**
     * Adds a task to this Project
     * @author Paul Schmidt
     * @param theTask the Task to add
     */
    public void addTask(final Task theTask) {
        this.myTasks.add(theTask);
    }

    /**
     * Static method to deserialize a Project.ser file into a Project obj
     * @author Paul Schmidt
     * @param filename the Name of the file to add
     * @return the deserialized Project obj
     * @throws Exception if there is an issue deserialized the given file into a Project object
     */
    public static Project deserialize(final String filename) throws Exception {
        Project p = null;
        try {
            // Reading the object from a file
            FileInputStream file = new FileInputStream
                    (filename);
            ObjectInputStream in = new ObjectInputStream
                    (file);

            // Method for deserialization of object
            p = (Project) in.readObject();

            in.close();
            file.close();
            System.out.println(p.myProjectName + " has been deserialized\n"
                    + "Data after Deserialization.");
            System.out.println(p);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            return p;
        }
    }

    /**
     * Serialize this Project object to a Project.ser file written to the given filepath
     * @author Paul Schmidt
     * @param filename the path of the file to write to
     */

    public void serialize(final String filename) {
        ObjectOutputStream oos = null;
        FileOutputStream fout = null;
        try{
            fout = new FileOutputStream(filename + "/src/main/resources/appdata/" + myUser.getUserEmail() + "/" +
                    myProjectName + "/" + myProjectName + ".ser", false);
            oos = new ObjectOutputStream(fout);
            oos.writeObject(this);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        finally {
            if(oos != null){
                try {oos.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Project Name: " + myProjectName);
        for (Task t : myTasks) {
            sb.append("\n    " + t.toString());
        }
        sb.append("\n");
        return sb.toString();
    }
}
