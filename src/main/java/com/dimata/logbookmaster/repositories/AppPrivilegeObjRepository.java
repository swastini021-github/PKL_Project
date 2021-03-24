package com.dimata.logbookmaster.repositories;

import com.dimata.logbookmaster.models.AppPrivilegeObj;
import org.springframework.data.repository.reactive.ReactiveSortingRepository;

public interface AppPrivilegeObjRepository extends ReactiveSortingRepository<AppPrivilegeObj, Long> {
}
