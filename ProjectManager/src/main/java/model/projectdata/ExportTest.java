package model.projectdata;


import model.User;

import java.io.IOException;

public class ExportTest {
    public static void main(String[] args) throws IOException {
        for (int i = 1; i < 4; i++) {
            User testUser = new User("Schmidt", "Schmidt", "email@email.com", "pw");
            Project p = new Project(testUser, "TestProject" + i);
            Task t = new Task("TestTask" + i);
            Purchase pu = new Purchase("TestPurchase" + i, 1.00);
            t.addPurchase(pu);
            p.addTask(t);
            try {
                p.export();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
