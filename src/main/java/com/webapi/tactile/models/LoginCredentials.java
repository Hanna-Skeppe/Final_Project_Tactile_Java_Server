package com.webapi.tactile.models;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class LoginCredentials {

    // == fields ==
    @NotBlank(message = "Must enter an email") // Added Validation annotations
    @Email(message = "Must enter a valid email address")
    private String email;

    @NotBlank(message = "Must enter a password")
    @Size(max = 100,message = "Max length 100 Characters")
    private String password;
}
