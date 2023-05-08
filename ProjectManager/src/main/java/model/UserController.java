package model;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * author Hieu Nguyen
 */
public class UserController {

    private List<User> users;
    private CSVHandler csvHandler;

    public UserController(CSVHandler csvHandler) throws IOException {
        this.csvHandler = csvHandler;

        List<String[]> userData = csvHandler.readAll();
        this.users = new ArrayList<>();

        for (String[] data : userData) {


            User user = new User(data[0], data[1], data[2], data[3]);
            this.users.add(user);
        }
    }

    public static boolean addUser(String UserFirstName, String UserLastName, String password, String email) throws IOException {
        UserController userController = new UserController(new CSVHandler("users.csv"));
        List<User> users = userController.getUsers();

        // Check if username already exists
        for (User user : users) {
            if (user.getUserEmail().equals(email)) {
                return false;
            }
        }

        User newUser = new User(UserFirstName, UserLastName, password, email);
        users.add(newUser);

        List<String[]> data = new ArrayList<>();
        for (User user : users) {
            String[] userData = {user.getMyUserFirstName(), user.getMyUserLastName(), user.getMyPassword(), user.getUserEmail()};
            data.add(userData);
        }

        userController.csvHandler.writeAll(data);

        return true;
    }


    public boolean login(String email, String password) {

        for (User user : users) {
            if (user.getUserEmail().equals(email) && user.getMyPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }

    public List<User> getUsers() {
        return users;
    }
}