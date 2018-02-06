package co.devhut.smartgroceries;

import android.annotation.SuppressLint;
import android.app.Application;

/**
 * Created by jrmromao on 27/10/2017.
 * user model class
 */

@SuppressLint("Registered")
public class UserModel extends Application {


    private String mUsername;
    private String mEmail;
    private int mUserID;
    private String dateJoined;



    public UserModel() {
    }

    public UserModel(int userID, String mUsername, String mEmail, String dateJoined) {
        this.mUserID = userID;
        this.mUsername = mUsername;
        this.mEmail = mEmail;
        this.dateJoined = dateJoined;
    }


    public UserModel(int mUserID, String mUsername, String mEmail) {
        this.mUsername = mUsername;
        this.mEmail = mEmail;
        this.mUserID = mUserID;
    }

    public String getUsername() {
        return mUsername;
    }

    public void setUsername(String username) {
        mUsername = username;
    }

    public String getEmail() {
        return mEmail;
    }

    public void setEmail(String email) {
        mEmail = email;
    }

    public int getUserID() {
        return mUserID;
    }

    public void setUserID(int userID) {
        mUserID = userID;
    }


    public String getDateJoined() {
        return dateJoined;
    }

    public void setDateJoined(String dateJoined) {
        this.dateJoined = dateJoined;
    }
}
