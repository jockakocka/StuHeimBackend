package com.example.stuheim.models.oauth2;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Blob;

@Entity
@Table(name="oauth_refresh_token")
@Getter
@Setter
public class OauthRefreshToken {

    @Id
    @GeneratedValue
    Long id;

    @Column(name="token_id")
    String tokenId;

    @Column(name="token")
    Blob token;

    @Column(name="authentication")
    Blob authentication;
}
