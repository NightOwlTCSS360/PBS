package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class User implements Serializable {

    private String myUserLastName;

    private String myUserFirstName;

    private String myUserEmail;

    private String myPassword;



    public User(String theUserLastName, String theUserFirstName, String theUserEmail, String thePassword){

        myUserLastName = theUserLastName;
        myUserFirstName = theUserFirstName;
        myUserEmail = theUserEmail;
        myPassword = thePassword;
    }

    public void setUserLastName(String theUserLastname){
        myUserLastName = theUserLastname;
    }

    public String getMyUserLastName(){
        return myUserLastName;
    }

    public void setUserFirstName(String theUserFirstname){
        myUserFirstName = theUserFirstname;
    }

    public String getMyUserFirstName(){
        return myUserFirstName;
    }

    public void setUserEmail(String theUserEmail){
        myUserEmail = theUserEmail;
    }

    public String getUserEmail(){
        return myUserEmail;
    }

    public void setPassword(String thePassword){
        myPassword = thePassword;
    }

    public String getMyPassword(){
        return myPassword;
    }
}

