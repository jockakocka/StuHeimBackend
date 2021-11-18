package com.example.stuheim.models;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "`GROUPS`")
@Audited
public class Group extends AbstractEntity implements Serializable {

    @Column(name = "`groups`")
    private String name;

    @Audited
    @ManyToMany(fetch = FetchType.LAZY, cascade = {})
    @JoinTable(
            name="USERS_GROUPS",
            joinColumns = @JoinColumn( name="group_id"),
            inverseJoinColumns = @JoinColumn( name="user_id")
    )
    List<User> users;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {})
    @JoinTable(
            name="GROUPS_PRIVILEGES",
            joinColumns = @JoinColumn( name="group_id"),
            inverseJoinColumns = @JoinColumn( name="privilege_id")
    )
    List<Privilege> privileges;
}
