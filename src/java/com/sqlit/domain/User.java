/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.sqlit.domain;

/**
 *
 * @author Aravind Sarma Yeluripati
 */
public class User {
    private String userId;
    private String password;
    private String firstName;
    private String lastName;
    private String email;

    public User() {
        super();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
