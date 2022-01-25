package com.webapi.tactile.models;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class LoginCredentials {
    // == fields ==
    private String email;
    private String password;
}
