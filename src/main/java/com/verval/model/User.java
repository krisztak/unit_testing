package com.verval.model;

import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class User {

    @Size(max = 100)
    @Email
    private String email;
    private String username;
    @Pattern(regexp = "^(?=\\s*\\S).*$", message = "Firstname must not be empty")
    private String firstName;
    @Pattern(regexp = "^(?=\\s*\\S).*$", message = "Lastname must not be empty")
    private String lastName;
}
