package model;

import java.util.ArrayList;
import java.util.List;

public class AppInfo {

    private static final String myVersionNum = "0.01";
    private final List<Developer> myDevs;



    public AppInfo() {
        myDevs  = new ArrayList<>();
        myDevs.add(new Developer("Jarvis Kampe", "Git"));
        myDevs.add(new Developer("Hieu Nguyen", "Git"));
        myDevs.add(new Developer("David Piholyuk", "Git"));
        myDevs.add(new Developer("Derek Ruiz-Garcia", "Git"));
        myDevs.add(new Developer("Paul Schmidt", "https://github.com/PDSchmidt"));

    }
    public static String getVersion() {
        return myVersionNum;
    }

    public List<Developer> getDevs() {
        return myDevs;
    }
}
