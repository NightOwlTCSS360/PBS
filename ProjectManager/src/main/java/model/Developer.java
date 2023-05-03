package model;

import java.util.AbstractList;

public class Developer {

    private final String myName;

    private final String myGit;

    public Developer(final String theName, final String theGit) {
        myName = theName;
        myGit = theGit;
    }

    public String getName() {
        return myName;
    }
    public String getGit() {
        return myGit;
    }
}
