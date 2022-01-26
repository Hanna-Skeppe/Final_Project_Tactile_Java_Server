package com.webapi.tactile.models;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserVerification {


    private String email;

    public UserVerification(String email) {
        this.email = email;
    }
}
