package control;
import model.*;

public class PDC {
    private User currentUser;

    public PDC () {
        currentUser = null;
    }

    public void setCurrentUser( final User theUser) {
        currentUser = theUser;
    }
    public User getCurrentUser() {
        return currentUser;
    }
}
