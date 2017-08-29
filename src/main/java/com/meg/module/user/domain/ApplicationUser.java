package com.meg.module.user.domain;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

/**
 * Created by meg on 8/27/17.
 */


@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "application_user")
public class ApplicationUser {

    @Id
    public String id;

    public String label;

    public String fullname;

    public LocalDate creationDate;

    public boolean recordState;

    public ApplicationUser(String label, String fullname, LocalDate creationDate) {
        this.label = label;
        this.fullname = fullname;
        this.creationDate = creationDate;
    }

    @Override
    public String toString() {
        return String.format(
                "Application_User[id=%s, label='%s', fullname='%s']",
                id, label, fullname);
    }
}
