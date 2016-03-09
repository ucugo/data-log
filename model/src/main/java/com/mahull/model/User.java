package com.mahull.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

/**
 * Created by Ugo on 05/03/2016.
 */
@Entity
public class User extends ModelObject {

    @NotNull
    @Column(nullable = false, name = "firstname")
    private String firstName;
    @NotNull
    @Column(nullable = false, name = "lastname")
    private String lastName;
    @NotNull
    @Column(nullable = false, name = "username")
    private String userName;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
