package com.mkaz.topgames.entity;

import com.mkaz.topgames.validation.EmailExistValidation;
import com.mkaz.topgames.validation.EmailValidation;
import com.mkaz.topgames.validation.UserNameExistValidation;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue
    private Long user_id;
    @NotNull
    @NotEmpty
    @Size(min = 2, max = 20)
    @Column(name = "first_name", nullable = false)
    private String firstName;
    @NotNull
    @NotEmpty
    @Size(min = 2, max = 20)
    @Column(name = "last_name", nullable = false)
    private String lastName;
    @NotNull
    @NotEmpty
    @Size(min = 3, max = 20)
    @UserNameExistValidation
    @Column(name = "user_name", nullable = false)
    private String userName;
    @NotNull
    @NotEmpty
    @EmailValidation
    @EmailExistValidation
    @Column(name = "email", nullable = false)
    private String email;
    @NotNull
    @NotEmpty
    @Size(min = 6, max = 100)
    @Column(name = "password", nullable = false)
    private String password;
    @Column(name = "enabled")
    private boolean isEnabled;
    @ManyToMany
    @JoinTable(name = "users_roles", joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    public User() {
    }

    public User(String firstName, String lastName, String userName, String email,
                String password, boolean isEnabled, Set<Role> roles) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.isEnabled = isEnabled;
        this.roles = roles;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isEnabled() {
        return isEnabled;
    }

    public void setEnabled(boolean enabled) {
        isEnabled = enabled;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "User{" +
                "user_id=" + user_id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", userName='" + userName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", isEnabled=" + isEnabled +
                '}';
    }
}
