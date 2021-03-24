package com.dimata.logbookmaster.models;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Table;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Column;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Table("app_privilege")
public class AppPrivilege implements Persistable<Long> {
    @Id
    private Long priv_id;
    private String priv_name;

    // @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    // @Column(name = "REG_DATE")
    private Date reg_Date;
    private String description;

    @Transient
    private long newPrivId;

    @Override
    public Long getId() {
        return priv_id;
    }

    @Override
    @Transient
    public boolean isNew() {
        if (newPrivId != 0) {
            this.priv_id = newPrivId;
            return true;
        }
        return false;
    }
}
