package com.webapi.tactile.models;


import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class UserVerification {

    // == fields ==
    private String email;

    // == public methods
    public UserVerification(String email) {
        this.email = email;
    }
}
