package control;
import model.*;
import model.projectdata.Project;
import model.projectdata.Purchase;
import model.projectdata.Task;

import java.io.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.rmi.NoSuchObjectException;
import java.util.*;
import java.util.List;

/**
 * This class, PDC (aka Persistent Data Controller), acts as a control layer entity that helps manage the interactions
 * between the GUI and the data modeled in the project.
 * @author Paul Schmidt
 * @author Derek J. Ruiz Garcia
 */
public class PDC {

    /** Field for storing a reference to the current User instance. */
    private User currentUser;

    /** The project tha is currently selected. */
    private Project currentProject;

    /** The task that is currently selected */
    private Task currentTask;

    /** The directory being used for file search */
    public static String myDir;

    /**
     * Constructs a new instance of a PDC and sets the current User to null.
     * @author Paul Schmidt
     */
    public PDC () {
        currentUser = null;
        currentProject = null;
        currentTask = null;
        //This looks for a ProjectBudgetingSystem in the user's home directory to store information.
        //Creates one and adds a blank users.csv if they don't exist.
        try {
            Path appInfoDir = Paths.get(System.getProperty("user.home") + "\\ProjectBudgetingSystem\\");
            if(!Files.exists(appInfoDir)) {
                Files.createDirectory(appInfoDir);
                Files.createFile(new File(appInfoDir + "\\users.csv").toPath());
            }
            myDir = appInfoDir + "\\";
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Sets the currentUser to null.
     * @author Derek J. Ruiz Garcia
     */
    public void logoutUser(){
        currentUser = null;
        currentProject = null;
        currentTask = null;
    }

    /**
     * Creates a settings file using the user information.
     * @author Derek J. Ruiz Garcia
     */
    public void createSettingsFile(){
        final String settingsLocation = myDir + "Settings.txt";
        File settingsFile = new File(settingsLocation);

        try {
            boolean fileAlreadyExists = settingsFile.createNewFile();
        } catch (IOException e){
            e.printStackTrace();
        }

        //now we write to the file we just created
        try (BufferedWriter fileW = new BufferedWriter(new FileWriter(settingsLocation))) {
            fileW.write(currentUser.getMyUserFirstName());
            fileW.newLine();
            fileW.write(currentUser.getUserEmail());
            fileW.close();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Imports the settings located in a txt file using a File chooser. If there is already a settings file,
     * it will replace the old file with the new file that was exported. In this case the new file will contain
     * only the first 2 lines of the imported file.
     * @param theFileToImport that will be imported into the program.
     * @author Derek J. Ruiz Garcia
     */
    public void importSettings(File theFileToImport) throws IOException {
        String fileName = theFileToImport.getName();

        final String settingsLocation = myDir + "Settings.txt";
        File settingsFile = new File(settingsLocation);

        try {
            boolean fileAlreadyExists = settingsFile.createNewFile();
            if (fileAlreadyExists){
                System.out.println("File already exists!");
                settingsFile.delete();
                System.out.println("Has been deleted!");
                settingsFile.createNewFile();
                System.out.println("File has been re-created");
            } else {
                System.out.println("File was created!");
            }
        } catch (IOException e){
            e.printStackTrace();
        }
                //Because now we have a file we can write to a file.
        System.out.println("File has been selected!: " + theFileToImport.getName());
        Scanner sc = new Scanner(theFileToImport);

        int n = 0;                      // this is the number of lines a settings file should have (name, email)
        try (BufferedWriter fileW = new BufferedWriter(new FileWriter(settingsLocation))) {
            while(sc.hasNextLine() && n < 2) {
                fileW.write(sc.nextLine());
                fileW.newLine();
                n++;
            }
            fileW.close();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Exports the settings using a File chooser to indicate the location to store the settings as a txt file.
     * Note: if a settings file already exist in the location selected it will overwrite it without asking.
     * @param theFile that will be used to retrieve the location where the exported settings file will be stored.
     * @throws FileNotFoundException if there is not a Settings file inside the project.
     * @author Derek J. Ruiz Garcia
     */
    public void exportSettings(File theFile) throws FileNotFoundException {
        final String settingsLocation = myDir + "Settings.txt";
        File localSettingsFile = new File(settingsLocation);
        final String exportingLocation = theFile.getPath();

        File exportedSettingsFile = new File(exportingLocation);
        try {
            boolean fileAlreadyExists = exportedSettingsFile.createNewFile();
            if (fileAlreadyExists){
                System.out.println("File already exists!");
                exportedSettingsFile.delete();
                System.out.println("Has been deleted!");
                exportedSettingsFile.createNewFile();
                System.out.println("File has been re-created");
            } else {
                System.out.println("File was created!");
            }
        } catch (IOException e){
            e.printStackTrace();
        }
        // the local settings file
        Scanner sc = new Scanner(localSettingsFile);

        int n = 0;                      // this is the number of lines a settings file should have (name, email)
        try (BufferedWriter fileW = new BufferedWriter(new FileWriter(exportingLocation))) {
            while(sc.hasNextLine() && n < 2) {
                fileW.write(sc.nextLine());
                fileW.newLine();
                n++;
            }
            fileW.close();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Creates a project and checks if a project with the provided name already exists. If a project with the
     * provided name doesn't exist, then the project is added to the projects in the user's folder,
     * to the list of projects of the user, and the method returns true. If a project with the
     * provided name does exist, then the project is not added and the method returns false.
     * @param theProjectName the name of the project we want to add
     * @return a boolean value indicating whether the project was added to the users projects or not.
     * @throws IOException if the Directory for this Project can't be made
     * @author Derek J. Ruiz Garcia
     */
    public boolean addNewProject(String theProjectName) throws IOException {
        Map<String, Project> userProjects = currentUser.getProjects();
        boolean wasAdded = false;
        if (!userProjects.containsKey(theProjectName)){
            Project brandNewProject = new Project(currentUser, theProjectName);
            currentUser.addProject(brandNewProject);
            currentProject = brandNewProject;
            wasAdded = true;
        }
        return wasAdded;
    }

    /**
     * Exports a serialized Project obj file to the designated FilePath
     * @author Paul Schmidt
     * @param theProjectName The name of the Project to export
     * @param theFile The File to write to
     */
    public void exportProject(final String theProjectName, final File theFile) {
        try {
            // Get the project's serialized file path
            Path projectFilePath = currentUser.getProject(theProjectName).getMyFilePath();
            //System.out.println("TRYING EXPORT FROM: " + projectFilePath);
            //System.out.println("TRYING EXPORT TO: " + theFile.toPath());
            // Copy the project's serialized file to the specified destination
            Files.copy(projectFilePath, theFile.toPath(), StandardCopyOption.REPLACE_EXISTING);

            //System.out.println(theProjectName + " exported to " + theFile.getPath() + "\n");
        } catch (NoSuchObjectException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            //System.out.println("Error exporting: " + theProjectName);
            throw new RuntimeException(e);
        } catch (NullPointerException e) {
            System.out.println("Error exporting: " + theProjectName);
            e.printStackTrace();
        }
    }

    /**
     * Imports a Project to the Current User's List of Projects, then creates the appropriate file structure for the
     * Project and serializes it locally.
     * TODO Check for .ser validity
     * TODO Check to see if the imported Project has the name of an already existing Project
     * TODO Let the User decide if they want to overwrite the Previous Project if it exists OR change name of the imported Project
     * @author Paul Schmidt
     * @param theFile the .ser File to import
     */
    public void importProject(final File theFile) {
        try {
            Project p = new Project(currentUser, Project.deserialize(theFile.getPath()));
            p.serialize(this.myDir);
            currentUser.addProject(p);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Deletes the project from the project's folder of the current user, and removes it from the list of projects
     * of the user.
     * @author Derek J. Ruiz Garcia
     * @return a boolean value indicating whether the project was deleted or not.
     * @throws NullPointerException if the current user or the current project is null.
     */
    public boolean deleteCurrentProject() throws NullPointerException {
        boolean deleted = true;
        try {
            File projectDirectory = new File(currentProject.getDirectoryPath().toString());
            for(File nested : projectDirectory.listFiles()) {
                try {
                    java.nio.file.Files.delete(nested.toPath());
                } catch(IOException e) {
                    e.printStackTrace();
                    deleted = false;
                }
            }
            try {
                java.nio.file.Files.delete(projectDirectory.toPath());
            } catch(IOException e) {
                e.printStackTrace();
                deleted = false;
            }
            currentUser.deleteProject(currentProject.getMyProjectName());
            currentProject = null;
            currentTask = null;
        } catch (NullPointerException e) {
            throw e;
        }

        return deleted;
    }

    /**
     * Looks for the purchases in the current task and checks if there is a task with the provided name, if there isn't
     * then the purchase is added to the current task.
     * @param thePurchaseName the name of the purchase we want to add.
     * @param theCost the cost that the purchase is going to have.
     * @return a boolean value returning true if there is not a purchase with the same name, and if the cost is a non-negative numeric value.
     * @author Derek J. Ruiz Garcia
     */
    public boolean addNewPurchase(String thePurchaseName, String theCost){
        boolean purchaseWasAddedSuccessfully = false;
        if(!currentTask.getAllPurchaseNames().contains(thePurchaseName) && isNonNegativeDouble(theCost)){            // if the purchase doesn't exist
            Purchase brandNewPurchase = new Purchase(thePurchaseName, new BigDecimal(theCost));
            currentTask.addPurchase(brandNewPurchase);
            currentTask.recalculateCompleted();
            currentProject.recalculateTotalCost();
            currentProject.recalculateCompleted();
            currentProject.serialize(PDC.myDir);
            purchaseWasAddedSuccessfully = true;
        }
        return purchaseWasAddedSuccessfully;
    }

    /**
     * Searches for the purchase using the passed purchase name and sets its cost to the passed cost.
     * @param thePurchaseName the name of the purchase we want to edit.
     * @param theNewCost the new cost that is going to be given to the specified purchase.
     * @author Derek J. Ruiz Garcia
     */
    public void editPurchaseCost(String thePurchaseName, BigDecimal theNewCost){
        boolean purchaseExists = currentTask.getAllPurchaseNames().contains(thePurchaseName);
        if(purchaseExists){                                                     // if the purchase exists
            Purchase theChosenPurchase = currentTask.getPurchase(thePurchaseName);
            theChosenPurchase.editCost(theNewCost);
            currentProject.recalculateTotalCost();
            currentProject.serialize(PDC.myDir);
        }
    }

    /**
     * Deletes the purchase from the list of purchases of the task that is currently selected.
     * @param thePurchaseName the name of the purchase we want to delete.
     */
    public void deletePurchase(String thePurchaseName){
        Purchase purchaseToDelete = currentTask.getPurchase(thePurchaseName);
        if(purchaseToDelete != null){
            currentTask.deletePurchase(purchaseToDelete);
            currentTask.recalculateCompleted();
            currentProject.recalculateCompleted();
            currentProject.recalculateTotalCost();
            currentProject.serialize(PDC.myDir);
        }
    }

    /**
     * Creates a new task using the given name and if the tasks doesn't exist already in the project,
     * it will add it to the list of tasks, else it will return false and won't add it to the list.
     * @param theTaskName the name of the task we want to add to the project.
     * @return a boolean value indicating if a task with the provided name already exist in the current project.
     * @author Derek J. Ruiz Garcia
     */
    public boolean addNewTask(String theTaskName){
        boolean theTaskExists = currentProject.getAllTaskNames().contains(theTaskName);
        if(!theTaskExists){
            Task brandNewTask = new Task(theTaskName);
            currentProject.addTask(brandNewTask);
            currentProject.recalculateCompleted();
            currentProject.serialize(PDC.myDir);
            currentTask = brandNewTask;
        }
        return !theTaskExists;
    }

    /**
     * Deletes the current task from the project and sets the current task to null
     * @author Derek J. Ruiz Garcia
     */
    public void deleteCurrentTask(){
        currentProject.deleteTask(currentTask.getTaskName());
        currentProject.recalculateCompleted();
        currentProject.recalculateTotalCost();
        currentProject.serialize(PDC.myDir);
        currentTask = null;
    }

    /**
     * Returns the current User.
     * @author Paul Schmidt
     * @return the current User.
     */
    public User getCurrentUser() {
        return currentUser;
    }

    /**
     * Returns the name of the currently selected Project.
     * @author Paul Schmidt
     * @author Derek J. Ruiz Garcia
     * @return the name of the current Project, null if there is no current project selected
     */
    public String getCurrProjectName() {
        String result = null;
        if (currentProject != null) {
            result = currentProject.getMyProjectName();
        }
        return result;
    }

    /**
     * Returns the name of the currently selected Task.
     * @author Derek J. Ruiz Garcia
     * @author Paul Schmidt
     * @return the name of the current Task, null if there is no task selected.
     */
    public String getCurrTaskName() {
        String result = null;
        if (currentTask != null) {
            result = currentTask.getTaskName();
        }
        return result;
    }

    /**
     * This method returns a Set of Strings that represent the names of the Users Project. Use to populate information
     * to the UI.
     * @author Paul Schmidt
     * @return the Set of Project Names
     */
    public Set<String> getProjectNames() {
        return currentUser.getProjects().keySet();
    }

    /**
     * Returns a list of the tasks contained in the current project.
     * @return list of task objects located in the current project.
     * @author Derek J. Ruiz Garcia
     */
    public List<String> getTasks(){
        Set<String> names = currentProject.getAllTaskNames();
        List<String> result = new ArrayList<>();
        for (String taskName : names) {
            result.add(taskName);
        }
        return result;
    }

    /**
     * Returns the completed status of the task.
     * @param theTaskName the name of the task.
     * @return true if completed, false otherwise.
     */
    public boolean getTaskStatus(final String theTaskName) {
        return currentProject.getTask(theTaskName).getCompletedStatus();
    }

    /**
     * Returns the budget of the project.
     * @return the budget of the project as a BigDecimal.
     * @author Derek J. Ruiz Garcia
     */
    public BigDecimal getProjectBudget(){
        return currentProject.getProjectEstimate();
    }

    /**
     * Returns the cost of the project.
     * @return the total cost of the project as a BigDecimal.
     * @author Derek J. Ruiz Garcia
     */
    public BigDecimal getProjectCost(){
        return currentProject.getProjectCost();
    }

    /**
     * Returns the names of purchases of the current Task.
     * @author Derek J. Ruiz Garcia
     * @return the names of purchases of the current Task.
     */
    public List<String> getPurchases(){
        Set<String> names = currentTask.getAllPurchaseNames();
        List<String> result = new ArrayList<>();
        for (String purchaseName : names) {
            result.add(purchaseName);
        }
        return result;
    }

    /**
     * Returns the costs of a purchase associated with the current task, given the purchase name.
     * @author Paul Schmidt
     * @author Derek J. Ruiz Garcia
     * @param thePurchaseName the name of the Purchase.
     * @return the Cost as a BigDecimal, null if the Purchase doesn't exist.
     */
    public BigDecimal getPurchaseCost(final String thePurchaseName) {
        BigDecimal result = null;
        if(currentTask.getAllPurchaseNames().contains(thePurchaseName)) {
            result = currentTask.getPurchase(thePurchaseName).getCost();
        }
        return result;
    }
    
    /**
     * Returns the status of a purchase associated with the current task, given the purchase name.
     * @author Derek J. Ruiz Garcia
     * @param thePurchaseName the name of the purchase.
     * @return the status of the purchase as a boolean, will return false if the purchase is not
     * selected, and if it's not found.
     */
    public boolean getPurchaseStatus(final String thePurchaseName){
        boolean result = false;
        if(currentTask.getAllPurchaseNames().contains(thePurchaseName)) {
            result = currentTask.getPurchase(thePurchaseName).getCompletedStatus();
        }
        return result;
    }

    /**
     * Returns the completed status of the Project.
     * @return true if complete, false otherwise
     */
    public boolean getProjectStatus() {
        return currentProject.getCompletedStatus();
    }

    /**
     * Sets the budget of the project to the estimated budget passed as a parameter.
     * @param theEstimatedBudget the budget that the project is going to have.
     * @author Derek J. Ruiz Garcia
     */
    public void setProjectBudget(BigDecimal theEstimatedBudget){
        currentProject.setProjectEstimate(theEstimatedBudget);
    }

    /**
     * Looks for the passed purchase name in the list of purchases of the current task, and
     * if a purchase with the passed name is found, its completed status is set to the passed boolean value.
     * @param thePurchaseName the name of the purchase as a String to which the status is going to be set to.
     * @param theStatus a boolean value that will be given to the specified purchase (true means completed,
     *                  false means incomplete).
     * @author Derek J. Ruiz Garcia
     */
    public void setPurchaseStatus(String thePurchaseName, boolean theStatus){
        if (currentTask.getAllPurchaseNames().contains(thePurchaseName)) {
            currentTask.getPurchase(thePurchaseName).setCompletedStatus(theStatus);
            currentTask.recalculateCompleted();
            currentProject.recalculateCompleted();
            currentProject.serialize(PDC.myDir);
        }
    }
    
    /**
     * Sets the passed cost to the purchase with the passed name, rounding to two decimal places. 
     * @param thePurchaseName the name of the purchase as a string.
     * @param theNewPurchaseCost the new cost as a string.
     * @return a boolean value returning true if the cost was set correctly, false otherwise.
     */
    public boolean setPurchaseCost(String thePurchaseName, String theNewPurchaseCost){
        boolean addedSuccessfully = false;
        if (currentTask.getAllPurchaseNames().contains(thePurchaseName) && isNonNegativeDouble(theNewPurchaseCost)) {
            BigDecimal cost = new BigDecimal(theNewPurchaseCost);
            cost.setScale(2, RoundingMode.HALF_EVEN);
            currentTask.getPurchase(thePurchaseName).editCost(cost);
            currentProject.recalculateTotalCost();
            currentProject.serialize(PDC.myDir);
            addedSuccessfully = true;
        }
        return addedSuccessfully;
    }

    /**
     * Set the current active Project obj via passing the Project's name as a parameter.
     * By setting the current project, the UI can populate Fields via other methods defined in the PDC class.
     * @author Paul Schmidt
     * @param theProjectName the name of the Project
     */
    public void setCurrentProject(final String theProjectName) {
        try {
            currentProject = currentUser.getProject(theProjectName);
            currentTask = null;
        } catch (NoSuchObjectException e) {
            e.printStackTrace();
        }
    }

    /**
     * Sets the current task to the task wih the name passed as a string. If there is not a task with the
     * given name, the current task won't change.
     * @param theTaskName the name of the task we want to set as current task.
     * @author Derek J. Ruiz Garcia
     */
    public void setCurrentTask(String theTaskName){
        boolean theTaskExists = currentProject.getAllTaskNames().contains(theTaskName);
        if (theTaskExists) {
            currentTask = currentProject.getTask(theTaskName);
        }
    }

    /**
     * This method is used for containing a reference to the User object after they've logged in. This reference is used
     * in order to access information about the User including name, email, the User's Projects, etc.
     * @author Paul Schmidt
     * @param theUser The reference to the User instance to be saved for use by the program.
     */
    public void setCurrentUser( final User theUser) {
        currentUser = theUser;
    }

    /**
     * This method allows for the User's first name and Email address to be updated after the creation of the account.
     * TODO Update the User's CSV line and the Users data folder name with the new email
     * @author Paul Schmidt
     * @param theFirstName The new first name of the User
     * @param theEmail The new last name of the User
     */
    public void setUserInfo(final String theFirstName, final String theEmail) {
        currentUser.setUserFirstName(theFirstName);
        currentUser.setUserEmail(theEmail);
        createSettingsFile();
    }

    /**
     * Checks if the input passed as a parameter is a double or a negative value.
     * @param input the input as a string to be checked
     * @return a boolean value returning false if the input is a double or a negative value, true otherwise.
     * @author Derek J. Ruiz Garcia
     */
    private boolean isNonNegativeDouble(String input){
        boolean response = true;
        try{
            double doubleInput = Double.parseDouble(input);
            if (doubleInput < 0){
                response = false;
            }
        } catch (NumberFormatException e){
            response = false;
        }
        return response;
    }
}
