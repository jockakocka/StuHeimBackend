package com.example.stuheim.models.oauth2;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Blob;

@Entity
@Table(name="oauth_access_token")
@Getter
@Setter
public class OauthAccessToken {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name="authentication_id")
    String authenticationId;

    @Column(name="token_id")
    String tokenId;

    @Column(name="token")
    Blob token;

    @Column(name="user_name")
    String userName;

    @Column(name="client_id")
    String clientId;

    @Column(name="authentication")
    Blob authentication;

    @Column(name="refresh_token")
    String refreshToken;
}
