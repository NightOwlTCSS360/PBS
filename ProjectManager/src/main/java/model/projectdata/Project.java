package model.projectdata;


import control.PDC;
import model.User;

import java.io.*;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * This class represents the Project object.
 * @author Paul Schmidt
 */
public class Project implements Serializable {

    /** The list of tasks belonging to this Project */
    private final Map<String, Task> myTasks;

    /** The Project name */
    private final String myProjectName;

    /** The status of whether this Project is completed or not. */
    private boolean completedStatus;

    /** The description of the Project */
    private BigDecimal myEstimate;

    /** The total Current Expenses of the Project */
    private BigDecimal currentExpenses;

    /** The User that this Project belongs to */
    private final User myUser;

    /** The path to the directory that contains this ProjectName.ser file */
    private transient Path myDirectoryPath;

    /** The FilePath of this ProjectName.ser file */
    private transient Path myFilePath;

    /** SerialVersionID */
    private static final long serialVersionUID = 5152023L;

    // CONSTRUCTORS
    /**
     * Constructor to create a new Project object
     * @author Paul Schmidt
     * @param theUser the User that this Project belongs to
     * @param theName the Name of the Project
     * @throws IOException if the Directory for this Project can't be made
     */
    public Project(final User theUser, final String theName) throws IOException {
        myTasks = new HashMap<>();
        myProjectName = theName;
        myUser = theUser;
        currentExpenses = new BigDecimal("0.0");
        myEstimate = new BigDecimal("0.0");
        completedStatus = false;
        updatePaths();

        //TODO PRINT LINE STATEMENTS FOR TESTING. REMOVE BEFORE OFFICIAL RELEASE
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
        myProjectName = theProject.myProjectName;
        myUser = theUser;
        myTasks = theProject.myTasks;
        currentExpenses = theProject.getProjectCost();
        myEstimate = theProject.getProjectEstimate();
        completedStatus = theProject.getCompletedStatus();
        updatePaths();
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

    //PUBLIC METHODS

    /**
     * Adds a task to this Project
     * @author Paul Schmidt
     * @param theTask the Task to add
     */
    public void addTask(final Task theTask) {
        this.myTasks.put(theTask.getMyTaskName(), theTask);
    }

    /**
     * Deletes the Task from this Projects Map of Tasks.
     * TODO TEST TO MAKE SURE THIS ACTUALLY REMOVED THE TASK
     * @author Derek J. Ruiz Garcia
     * @param myTaskName the name of the Task to remove.
     */
    public void deleteTask(final String myTaskName) {
        myTasks.remove(myTaskName);
    }

    /**
     * Static method to deserialize a Project.ser file into a Project obj
     * @author Paul Schmidt
     * @param filename the Name of the file to add
     * @return the deserialized Project obj
     * @throws IOException if there is an issue deserialized the given file into a Project object
     */
    public static Project deserialize(final String filename) throws IOException {
        Project theProject = null;
        try {
            // Reading the object from a file
            FileInputStream file = new FileInputStream(filename);
            ObjectInputStream in = new ObjectInputStream(file);
            // Method for deserialization of object
            theProject = (Project) in.readObject();
            theProject.updatePaths();
            in.close();
            file.close();
            System.out.println(theProject.myProjectName + " has been deserialized\nData after Deserialization:");
            System.out.println(theProject);
        } catch (IOException ex) {
            throw new IOException(ex);
        } finally {
            return theProject;
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
            fout = new FileOutputStream(filename + "src/main/resources/appdata/" + myUser.getUserEmail() + "/" +
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

    /**
     * Return the estimated budget of this Project as a BigDecimal.
     * @author Paul Schmidt
     * @return the Project Estimate.
     */
    public BigDecimal getProjectEstimate() {
        return myEstimate;
    }

    /**
     * Updates the current Project's estimated budget to the new value.
     * @author Paul Schmidt
     * @param theEstimate the new estimated budget.
     */
    public void setProjectEstimate(final BigDecimal theEstimate) {
        myEstimate = theEstimate;
    }

    /**
     * Recalculate the total cost of this Project.
     * @author Paul Schmidt
     */
    public void recalculateTotalCost() {
        BigDecimal total = new BigDecimal("0.0");
        for (Task t : myTasks.values()) {
            t.recalculateCost();
            total = total.add(t.getTotalCost());
        }
        currentExpenses = total;
    }

    /**
     * Recalculates whether this Project is complete based on the completions of this Project's Tasks.
     * @author Paul Schmidt
     */
    public void recalculateCompleted() {
        boolean result = true;
        for (Task theTask : myTasks.values()) {
            if(!theTask.getCompletedStatus()) {
                result = false;
                break;
            }
        }
        completedStatus = result;
    }

    /**
     * Returns a Set of Task Names located in this Project.
     * @return a Set of Task names as Strings.
     * @author Derek J. Ruiz Garcia
     */
    public Set<String> getAllTaskNames(){
        return myTasks.keySet();
    }

    /**
     * Returns a task if it exists given the name of the Task.
     * @author Derek J. Ruiz Garcia
     * @param theTask the name of the desired Task.
     * @return the Task if it exits, null otherwise.
     */
    public Task getTask(final String theTask) {
        return myTasks.get(theTask);
    }
    /**
     * Returns the cumulative cost associated with this Project as a BigDecimal.
     * @author Paul Schmidt
     * @return the cumulative cost.
     */
    public BigDecimal getProjectCost() {
        return currentExpenses;
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
     * Returns whether this Project is complete or not.
     * @author Paul Schmidt
     * @return Completed: true | Incomplete: false.
     */
    public boolean getCompletedStatus() {
        return completedStatus;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Project Name: ");
        sb.append(myProjectName);
        for (Task t : myTasks.values()) {
            sb.append("\n    ");
            sb.append(t.toString());
        }
        sb.append("\n");
        return sb.toString();
    }

    //PRIVATE METHODS
    /**
     * Initializes the file path to this project. Used in Project creation and in deserialization.
     * @author Paul Schmidt
     */
    private void updatePaths() {
        myDirectoryPath = Paths.get(PDC.myDir + "src/main/resources/appdata/" + myUser.getUserEmail() + "/" +
                myProjectName);
        myFilePath = Paths.get(myDirectoryPath + "/" + myProjectName + ".ser");
    }
}
