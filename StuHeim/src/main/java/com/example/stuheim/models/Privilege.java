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
@Table(name = "APP_PRIVILEGES")
@Audited
public class Privilege extends AbstractEntity implements Serializable {

    @Column(name = "name")
    String name;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name="GROUPS_PRIVILEGES",
            joinColumns = @JoinColumn( name="privilege_id"),
            inverseJoinColumns = @JoinColumn( name="group_id")
    )
    List<Group> groups;
}