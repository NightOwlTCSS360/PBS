package control;
import model.*;
import model.projectdata.Project;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.rmi.NoSuchObjectException;
import java.util.Scanner;
import java.util.Set;

/**
 * This class, PDC (aka Persistent Data Controller), acts as a control layer entity that helps manage the interactions
 * between the GUI and the data modeled in the project.
 * @author Paul Schmidt
 */
public class PDC {
    /**
     * Field for storing a reference to the current User instance.
     */
    private User currentUser;
    private Project currentProject;

    public static String myDir;
    /**
     * Constructs a new instance of a PDC and sets the current User to null.
     * @author Paul Schmidt
     */
    public PDC () {
        currentUser = null;
        if (!System.getProperty("user.dir").contains("ProjectManager")) {
            myDir = "ProjectManager\\";
        } else {
            myDir = "";
        }
    }

    /**
     * Creates a settings file using the user information.
     * @uthor Derek J. Ruiz Garcia
     */
    public void createSettingsFile(){
        final String settingsLocation = myDir + "src\\main\\resources\\Settings\\Settings.txt";
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

        final String settingsLocation = myDir + "src\\main\\resources\\Settings\\Settings.txt";
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
        final String settingsLocation = myDir + "src\\main\\resources\\Settings\\Settings.txt";
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
     * This method is used for containing a reference to the User object after they've logged in. This reference is used
     * in order to access information about the User including name, email, the User's Projects, etc.
     * @author Paul Schmidt
     * @param theUser The reference to the User instance to be saved for use by the program.
     */
    public void setCurrentUser( final User theUser) {
        currentUser = theUser;
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
     * Set the current active Project obj via passing the Project's name as a parameter.
     * By setting the current project, the UI can populate Fields via other methods defined in the PDC class.
     * @author Paul Schmidt
     * @param theProjectName the name of the Project
     */
    public void setCurrentProject(final String theProjectName) {
        try {
            currentProject = currentUser.getProject(theProjectName);
        } catch (NoSuchObjectException e) {
            e.printStackTrace();
        }
    }

    /**
     * Exports a serialized Project obj file to the designated FilePath
     * @author Paul Schmidt
     * @param theProjectName The name of the Project to export
     * @param theFile The File to write to
     */
    public void exportProject(final String theProjectName, final File theFile)  {
        try {
            Files.copy(currentUser.getProject(theProjectName).getMyFilePath(),
                    theFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
            System.out.println(theProjectName + " exported to " + theFile.getPath());
        } catch (NoSuchObjectException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
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

    public User getCurrentUser() {
        return currentUser;
    }
}
