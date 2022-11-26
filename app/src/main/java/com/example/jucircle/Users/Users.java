package com.example.jucircle.Users;

import com.google.firebase.firestore.auth.User;

public class Users {
    String userName , profilePic , userId , DateOfBirth , Gender , userBio  ;
     public Users(){}

    public Users(String userName, String profilePic, String userId, String dateOfBirth, String gender, String userBio) {
        this.userName = userName;
        this.profilePic = profilePic;
        this.userId = userId;
        DateOfBirth = dateOfBirth;
        Gender = gender;
        this.userBio = userBio;
    }

    public Users(String profilePic) {
        this.profilePic = profilePic;
    }


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(String profilePic) {
        this.profilePic = profilePic;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getDateOfBirth() {
        return DateOfBirth;
    }

    public void setDateOfBirth(String age) {
        this.DateOfBirth = DateOfBirth;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String gender) {
        Gender = gender;
    }

    public String getUserBio() {
        return userBio;
    }

    public void setUserBio(String userBio) {
        this.userBio = userBio;
    }
}
