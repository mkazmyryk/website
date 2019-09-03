package com.mkaz.topgames.entity;

import com.mkaz.topgames.validation.EmailExistValidation;
import com.mkaz.topgames.validation.EmailValidation;
import com.mkaz.topgames.validation.UserNameExistValidation;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Data
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
}
