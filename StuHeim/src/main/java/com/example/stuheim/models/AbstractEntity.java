package com.example.stuheim.models;

import com.sun.source.doctree.SerialDataTree;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import org.hibernate.envers.Audited;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

@Getter
@Setter
@MappedSuperclass
@Audited
public class AbstractEntity implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "modified_by")
    private String modifiedBy;

    @Column(name = "date_created")
    private Date dateCreated;

    @Column(name = "date_modified")
    private Date dateModified;

    @PrePersist
    private void persistProps(){
        this.setDateCreated(Calendar.getInstance().getTime());
    }

    @PreUpdate
    private void updateProps(){
        this.setDateModified(Calendar.getInstance().getTime());
    }
}
