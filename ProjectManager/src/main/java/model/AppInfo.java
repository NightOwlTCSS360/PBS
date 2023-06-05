package model;

import java.util.ArrayList;
import java.util.List;

public class AppInfo {

    private static final String myVersionNum = "0.30";
    private final List<Developer> myDevs;



    public AppInfo() {
        myDevs  = new ArrayList<>();
        myDevs.add(new Developer("Jarvis Kampe", "https://github.com/JarvisK02"));
        myDevs.add(new Developer("Hieu Nguyen", "https://github.com/Hieu27nguyen"));
        myDevs.add(new Developer("David Piholyuk", "https://github.com/davidpiholyuk"));
        myDevs.add(new Developer("Derek Ruiz-Garcia", "https://github.com/DrRuiz"));
        myDevs.add(new Developer("Paul Schmidt", "https://github.com/PDSchmidt"));

    }
    public static String getVersion() {
        return myVersionNum;
    }

    public List<Developer> getDevs() {
        return myDevs;
    }
}
