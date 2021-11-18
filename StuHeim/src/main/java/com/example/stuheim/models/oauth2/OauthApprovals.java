package com.example.stuheim.models.oauth2;

import lombok.Generated;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name="oauth_approvals")
@Getter
@Setter
public class OauthApprovals {

    @Id
    @GeneratedValue
    Long id;

    @Column(name="userId")
    String userId;

    @Column(name="clientId")
    String clientId;

    @Column(name="scope")
    String scope;

    @Column(name="status")
    String status;

    @Column(name="expiresAt")
    Timestamp expiresAt;

    @Column(name="lastModifiedAt")
    Timestamp lastModifiedAt;

}
