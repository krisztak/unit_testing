package com.example.junit.model;

import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

public class User {

    @Size(max = 100)
    @Email
    private String email;
    private String username;

    @Pattern(regexp = "^(?=\\s*\\S).*$", message = "Firstname must not be empty")
    private String firstName;
    @Pattern(regexp = "^(?=\\s*\\S).*$", message = "Lastname must not be empty")
    private String lastName;


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

}
