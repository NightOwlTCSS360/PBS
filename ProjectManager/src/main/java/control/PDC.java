package control;
import model.*;

/**
 * This class, PDC (aka Persistent Data Controller), acts as a control layer entity that helps manage the interactions
 * between the GUI and the data modeled in the project.
 * @author Paul Schmidt
 */
public class PDC {
    /**
     * Field for storing a reference to the current User instance.
     */
    private User currentUser;

    /**
     * Constructs a new instance of a PDC and sets the current User to null.
     * @author Paul Schmidt
     */
    public PDC () {
        currentUser = null;
    }

    /**
     * This method allows for the User's first name and Email address to be updated after the creation of the account.
     * @author Paul Schmidt
     * @param theFirstName The new first name of the User
     * @param theEmail The new last name of the User
     */
    public void setUserInfo(final String theFirstName, final String theEmail) {
        currentUser.setUserFirstName(theFirstName);
        currentUser.setUserEmail(theEmail);
    }

    /**
     * This method is used for containing a reference to the User object after they've logged in. This reference is used
     * in order to access information about the User including name, email, the User's Projects, etc.
     * @author Paul Schmidt
     * @param theUser The reference to the User instance to be saved for use by the program.
     */
    public void setCurrentUser( final User theUser) {
        currentUser = theUser;
    }
    public User getCurrentUser() {
        return currentUser;
    }
}
