package control;
import com.formdev.flatlaf.FlatLightLaf;
import model.*;

import javax.swing.*;
import java.io.*;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class PDC {
    private User currentUser;

    /** A constructor for the PDC class. */
    public PDC () {
        currentUser = null;
    }

    /**
     * Createsa settings file using the user information.
     * @uthor Derek J. Ruiz Garcia
     */
    public void createSettingsFile(){
        final String settingsLocation = "ProjectManager\\src\\main\\resources\\Settings\\Settings.txt";
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

        final String settingsLocation = "ProjectManager\\src\\main\\resources\\Settings\\Settings.txt";
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
        final String settingsLocation = "ProjectManager\\src\\main\\resources\\Settings\\Settings.txt";
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

    public void setCurrentUser( final User theUser) {
        currentUser = theUser;
    }
    public User getCurrentUser() {
        return currentUser;
    }
}
