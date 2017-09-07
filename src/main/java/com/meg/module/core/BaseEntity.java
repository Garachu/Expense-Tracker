package com.meg.module.core;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.util.Date;

/**
 * Created by meg on 8/30/17.
 */

/*
  *Abstract class is a class that contain one or more abstract methods.
  * Abstract method is a method that is declared, but contains no implementation.
  * Abstract classes may not instantiated, and require subclasses to provide implementations for the abstract methods
 */
@NoArgsConstructor
@Entity
public abstract class BaseEntity {

    @Id
    // Objectify auto-generates Long IDs just like JDO / JPA
    private Long id;

    @NonNull
    protected Date createdDate;

    @NonNull
    private Date lastModifiedDate;



    public Long getId(){
        return this.id;
    }

    public Date getCreatedDate() {
        return new Date(createdDate.getTime());
    }

    public Date getLastModifiedDate() {
        return new Date(lastModifiedDate.getTime());
    }

    public void setCreatedDateNow(){
        this.createdDate = new Date();
    }

    public void updateLastModified(){
        this.lastModifiedDate = new Date();
    }
}
