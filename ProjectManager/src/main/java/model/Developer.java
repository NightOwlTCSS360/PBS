package model;

/**
 * Class that represents a Developer that worked on this application.
 * @author Paul Schmidt
 */
public class Developer {
    /** The name of the Developer */
    private final String myName;
    /** The Developer's GITHUB account */
    private final String myGit;

    /**
     * Constructs a developer with the given name and link to their GitHub.
     * @author Paul Schmidt
     * @param theName name of the Developer
     * @param theGit GitHub profile link as a String
     */
    public Developer(final String theName, final String theGit) {
        myName = theName;
        myGit = theGit;
    }

    /**
     * Returns the name of the Developer.
     * @author Paul Schmidt
     * @return the name of the Developer.
     */
    public String getName() {
        return myName;
    }

    /**
     * Returns the Developer's GitHub profile link as a String.
     * @author Paul Schmidt
     * @return the Developer's GitHub profile link.
     */
    public String getGit() {
        return myGit;
    }
}
