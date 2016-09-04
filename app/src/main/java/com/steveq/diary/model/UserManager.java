package com.steveq.diary.model;

public class UserManager {

    public boolean contains(String input){
        for(User user : User.USERS){
            if(user.toString().equals(input)){
                return true;
            }
        }
        return false;
    }

    public boolean passwordMatches(String input, String password){
        for(User user : User.USERS){
            if(user.toString().equals(input)){
                if(user.getPassword().equals(password)){
                    return true;
                }
            }
        }
        return false;
    }
}
