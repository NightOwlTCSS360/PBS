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

    /** The file chooser that will be loaded to import or export files */
    private JFileChooser myFileChooser;

    /** A constructor for the PDC class. */
    public PDC () {
        currentUser = null;
        FlatLightLaf.setup();
        myFileChooser = new JFileChooser();
    }

    /**
     * Imports the settings located in a txt file using a File chooser. If there is already a settings file,
     * it will replace the old file with the new file that was exported. In this case the new file will contain
     * only the first 2 lines of the imported file.
     * @param theFrame the frame on top of which the file chooser will load.
     * @author Derek J. Ruiz Garcia
     */
    public void importSettings(JFrame theFrame) throws IOException {
        int result = myFileChooser.showDialog(theFrame, "Select settings file to import");

        if (result == JFileChooser.APPROVE_OPTION){
            String fileName = myFileChooser.getSelectedFile().getName();
            String fileType = fileName.substring(fileName.length() - 3, fileName.length());

            if (fileType.equals("txt")){
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
                File chosenFile = myFileChooser.getSelectedFile();
                System.out.println("File has been selected!: " + chosenFile.getName());
                Scanner sc = new Scanner(chosenFile);

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
            } else {
                JOptionPane.showMessageDialog(theFrame, "The selected file is not a txt file!",
                        "Error!", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    /**
     * Exports the settings using a File chooser to indicate the location to store the settings as a txt file.
     * Note: if a settings file already exist in the location selected it will overwrite it without asking.
     * @param theFrame the frame on top of which the file chooser will load.
     * @throws FileNotFoundException if there is not a Settings file inside the project.
     * @author Derek J. Ruiz Garcia
     */
    public void exportSettings(JFrame theFrame) throws FileNotFoundException {
        int result = myFileChooser.showDialog(theFrame, "Select location");

        if (result == JFileChooser.APPROVE_OPTION){
            final String settingsLocation = "ProjectManager\\src\\main\\resources\\Settings\\Settings.txt";
            File localSettingsFile = new File(settingsLocation);

            final String exportingLocation = myFileChooser.getSelectedFile().getPath();

            // This showed us that it will provide the right path!
//            String thePath = myFileChooser.getSelectedFile().getPath();
//            System.out.println("The path chosen: " + thePath);
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
    }

    public void setCurrentUser( final User theUser) {
        currentUser = theUser;
    }
    public User getCurrentUser() {
        return currentUser;
    }
}
