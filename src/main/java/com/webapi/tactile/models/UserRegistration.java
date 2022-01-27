package com.webapi.tactile.models;


import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class UserRegistration {

    // == fields ==
    @NotBlank(message = "Must enter an email") // Added Validation annotations
    @Email(message = "Must enter a valid email address")
    private String email;

    @NotBlank(message = "Must enter a password")
    @Size(max = 100,message = "Max length 100 Characters")
    private String password;

    @NotBlank(message = "Must enter a first name")
    @Size(max = 50,message = "Max length 50 Characters")
    private String firstName;

    @NotBlank(message = "Must enter a last name")
    @Size(max = 50,message = "Max length 50 Characters")
    private String lastName;

    @NotBlank(message = "Must enter a street address")
    @Size(max = 100,message = "Max length 100 Characters")
    private String streetName;

    @NotBlank(message = "Must enter a city")
    @Size(max = 100,message = "Max length 100 Characters")
    private String city;

    @NotBlank(message = "Must enter a zipcode")
    @Size(max = 16,message = "Max length 16 Characters")
    private String zipcode;

    @NotBlank(message = "Must enter a country")
    @Size(max = 100,message = "Max length 100 Characters")
    private String country;

    @Size(max = 50,message = "Max length 50 Characters")
    private String phoneNumber;

    private Boolean newsLetter;


}
