package com.mahull.model.model.profile;

import com.mahull.model.model.ModelObject;
import com.mahull.model.model.constraints.NonBlank;
import com.mahull.model.model.inventory.Item;
import com.mahull.model.security.Role;
import org.hibernate.validator.constraints.Email;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

/**
 * Created by Ugo on 05/03/2016.
 */
@Entity
public class CraftUser extends ModelObject {

    public static final String REQUEST_SCOPED_ATTRIBUTE_NAME = "craftUser";

    @NonBlank
    @Column(nullable = false)
    private String firstName;

    @NonBlank
    @Column(nullable = false)
    private String lastName;

    @NonBlank
    @Email
    @Column(nullable = false)
    private String userName;

    @NonBlank
    @Column(nullable = false)
    private String password;

    @NotNull
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToMany(mappedBy = "craftUser", targetEntity = Item.class)
    private List<Item> items;

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }
}
