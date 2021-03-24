package com.dimata.logbookmaster.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Table;

import java.sql.Date;

@Data
@NoArgsConstructor
@Table("app_group")
public class AppGroup implements Persistable<Long> {
    @Id
    private Long group_id;
    private String group_name;
    private Date reg_Date;
    private String status;
    private String description;

    @Transient
    private long newGroupId;

    @Override
    public Long getId() {
        return group_id;
    }

    @Override
    @Transient
    public boolean isNew() {
        if (newGroupId != 0) {
            this.group_id = newGroupId;
            return true;
        }
        return false;
    }

}