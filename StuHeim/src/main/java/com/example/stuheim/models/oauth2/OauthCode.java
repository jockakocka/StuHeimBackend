package com.example.stuheim.models.oauth2;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Blob;

@Entity
@Table(name="oauth_code")
@Getter
@Setter
public class OauthCode {

    @Id
    @GeneratedValue
    Long id;

    @Column(name="code")
    String code;

    @Column(name="authentication")
    Blob authentication;

}
