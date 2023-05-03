package model;

public class User {

    private String myUserName;

    private String myUserEmail;

    private String myPassword;

    public User(String theUserName, String theUserEmail, String thePassword){

        myUserName = theUserName;
        myUserEmail = theUserEmail;
        myPassword = thePassword;
    }

    public void setUserName(String theUsername){
        myUserName = theUsername;
    }

    public String getMyUserName(){
        return myUserName;
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
