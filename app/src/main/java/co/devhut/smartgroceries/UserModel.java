package co.devhut.smartgroceries;

import android.app.Application;

/**
 * Created by jrmromao on 27/10/2017.
 */

public class UserModel extends Application {


    private String mUsername;
    private String mEmail;
    private int mUserID;

    public UserModel(int userID, String mUsername, String mEmail) {
        this.mUserID = userID;
        this.mUsername = mUsername;
        this.mEmail = mEmail;
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
}
