package com.example.users;

import android.graphics.Bitmap;

/**
 * Created by Merlyne on 3/9/2017.
 */

public class GitUser {
    private String username, git_url;
    private Bitmap profile_image;

    public GitUser(){
    }
    public GitUser(String username, String git_url, Bitmap profile_image){
        this.username = username;
        this.git_url = git_url;
        this.profile_image = profile_image;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String  userName) {
        this.username = userName;
    }

    public String getGitURL() {
        return git_url;
    }

    public void setGitURL(String gitURL) {
        this.git_url = gitURL;
    }
   public Bitmap getProfileImage() {
        return profile_image;
    }

    public void setProfileImage(Bitmap profileImage) {
        this.profile_image = profileImage;
    }
}
