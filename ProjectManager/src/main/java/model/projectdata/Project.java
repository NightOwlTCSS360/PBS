package model.projectdata;


import model.User;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Project implements Serializable {
    private List<Task> myTasks;
    private String myProjectName;
    private String myDescription;
    private User myUser;
    private transient Path myDirectoryPath;
    private transient Path myFilePath;
    private static final long serialVersionUID = 5152023L;

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

    public Path getDirectoryPath() {
        return myDirectoryPath;
    }
    public Path getMyFilePath() {
        return myFilePath;
    }
    public String getMyProjectName() {
        return myProjectName;
    }
    public void addTask(final Task theTask) {
        this.myTasks.add(theTask);
    }

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
