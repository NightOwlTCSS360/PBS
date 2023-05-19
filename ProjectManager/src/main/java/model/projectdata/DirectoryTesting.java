package model.projectdata;

import model.User;

import java.io.IOException;


public class DirectoryTesting {
    public static void main(String[] args) {
        try {
            User testUser1 = new User("Schmidt", "Paul", "email@email.com", "pw");
            DirectoryTesting.addTestProjects(testUser1, 1, 4); //Uncomment to generate some project files, comment out if these projects already generated
            System.out.println("Printing Projects in " + testUser1.getMyUserFirstName() + "'s Project array");
            for (Project p : testUser1.getProjects().values()) {
                System.out.println(p);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            User testUser2 = new User("Last", "First", "user@email.com", "pw");
            DirectoryTesting.addTestProjects(testUser2, 4, 7); //Uncomment to generate some project files, comment out if these projects already generated
            System.out.println("Printing Projects in " + testUser2.getMyUserFirstName() + "'s Project array");
            for (Project p : testUser2.getProjects().values()) {
                System.out.println(p);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private static void addTestProjects(final User theUser, final int start, final int end) throws IOException {
        for (int i = start; i < end; i++) {
            Project p = new Project(theUser, "TestProject" + i);
            Task t = new Task("TestTask" + i);
            Purchase pu = new Purchase("TestPurchase" + i, 1.00);
            t.addPurchase(pu);
            p.addTask(t);
            theUser.addProject(p);
            try {
                p.export();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
