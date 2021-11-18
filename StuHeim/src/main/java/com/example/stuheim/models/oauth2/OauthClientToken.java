package com.example.stuheim.models.oauth2;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Blob;

@Entity
@Table(name="oauth_client_token")
@Getter
@Setter
public class OauthClientToken {

    @Id
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
}
