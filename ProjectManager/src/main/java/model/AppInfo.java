package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Stores the relevant information about the Application to be displayed in the AboutPanel
 * @author Jarvis Kampe
 */
public class AppInfo {
    /** The application version number */
    private static final String myVersionNum = "0.30";
    /** The list of Developers who worked on this Applciation */
    private final List<Developer> myDevs;



    public AppInfo() {
        myDevs  = new ArrayList<>();
        myDevs.add(new Developer("Jarvis Kampe", "https://github.com/JarvisK02"));
        myDevs.add(new Developer("Hieu Nguyen", "https://github.com/Hieu27nguyen"));
        myDevs.add(new Developer("David Piholyuk", "https://github.com/davidpiholyuk"));
        myDevs.add(new Developer("Derek Ruiz-Garcia", "https://github.com/DrRuiz"));
        myDevs.add(new Developer("Paul Schmidt", "https://github.com/PDSchmidt"));

    }

    /**
     * Returns the application version number.
     * @author Paul Schmidt
     * @return the version number.
     */
    public static String getVersion() {
        return myVersionNum;
    }

    /**
     * Returns the List of developers who worked on this application.
     * @author Paul schmidt
     * @return the list of Developers.
     */
    public List<Developer> getDevs() {
        return myDevs;
    }
}
