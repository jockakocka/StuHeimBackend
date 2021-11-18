package com.example.stuheim.models.oauth2;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="oauth_client_details")
@Getter
@Setter
public class OauthClientDetails {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name="client_id")
    String clientId;

    @Column(name="resource_ids")
    String resourceIds;

    @Column(name="client_secret")
    String clientSecret;

    @Column(name="scope")
    String scope;

    @Column(name="authorized_grant_types")
    String authorizedGrantTypes;

    @Column(name="web_server_redirect_uri")
    String webServerRedirectUri;

    @Column(name="authorities")
    String authorities;

    @Column(name="access_token_validity")
    Integer accessTokenValidity;

    @Column(name="refresh_token_validity")
    Integer refreshTokenValidity;

    @Lob
    @Column(name="additional_information")
    String additionalInformation;

    @Column(name="autoapprove")
    String autoApprove;

}
