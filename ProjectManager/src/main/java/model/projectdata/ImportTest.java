package model.projectdata;


import model.User;

public class ImportTest {
    public static void main(String[] args) {
        User testUser = new User("Schmidt", "Schmidt", "email@email.com", "pw");
        for (int i = 1; i < 4; i++) {
            try {
                Project p = Project.deserialize("./ProjectManager/src/main/resources/appdata/" +
                        testUser.getUserEmail() + "/TestProject" + i + "/TestProject" + i + ".ser");
                System.out.println(p);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
