package model.projectdata;

import model.User;



public class DirectoryTesting {
    public static void main(String[] args) {
        try {
            User testUser = new User("Schmidt", "Paul", "email@email.com", "pw");
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            User testUser = new User("Last", "First", "user@email.com", "pw");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
