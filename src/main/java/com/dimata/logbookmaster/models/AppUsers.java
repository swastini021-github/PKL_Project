package com.dimata.logbookmaster.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Table;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Table("app_user")
public class AppUsers implements Persistable<Long> {
    @Id
    private Long userId;
    private String loginId;
    private String Password;
    private String fullName;
    private String Email;
    private String Description;
    private Timestamp regDate;
    private LocalDateTime updateDate;
    private Integer userStatus;
    private LocalDateTime lastLoginDate;
    private String lastLoginIp;
    private Integer userType;
    private Long employeeId;

    @Transient
    private long newId;

    @Override
    public Long getId() {
        return userId;
    }

    @Override
    @Transient
    public boolean isNew() {
        if (newId != 0) {
            this.userId = newId;
            return true;
        }
        return false;
    }
}
