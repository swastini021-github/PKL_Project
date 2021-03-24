package com.dimata.logbookmaster.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Table;


@Data
@NoArgsConstructor
@Table("app_privilege_obj")
public class AppPrivilegeObj implements Persistable<Long> {
    @Id
    private Long privObjId;
    private Long privId;
    private Integer Code;

    @Transient
    private long newId;

    @Override
    public Long getId() {
        return privObjId;
    }

    @Override
    @Transient
    public boolean isNew() {
        if (newId != 0) {
            this.privObjId= newId;
            return true;
        }
        return false;
    }
}

