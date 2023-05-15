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
    private transient Path myPath;

    public Project(final User theUser, final String theName) throws IOException {
        myTasks = new ArrayList<>();
        myProjectName = theName;
        myDescription = "Default Description";
        myUser = theUser;
        myPath = Paths.get("./ProjectManager/src/main/resources/appdata/" + myUser.getUserEmail() + "/" +
                myProjectName);
        if (Files.exists(myPath)) {
            System.out.println(myPath.toRealPath() + " exists (Project)");
        } else {
            System.out.println(myPath.toString() +" doesn't exist. Creating Directory now...");
            Files.createDirectory(myPath);
            if (Files.exists(myPath)) {
                System.out.println(myPath.toRealPath() + " created!");
            } else {
                System.out.println("Error creating " + myPath.toString());
            }
        }
    }

    public Path getPath() {
        return myPath;
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

    public void export () {
        ObjectOutputStream oos = null;
        FileOutputStream fout = null;
        try{
            fout = new FileOutputStream("ProjectManager/src/main/resources/appdata/" + myUser.getUserEmail() + "/" +
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
