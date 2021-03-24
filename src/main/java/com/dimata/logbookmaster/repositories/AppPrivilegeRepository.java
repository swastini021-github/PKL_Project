package com.dimata.logbookmaster.repositories;

import com.dimata.logbookmaster.models.AppPrivilege;
import org.springframework.data.repository.reactive.ReactiveSortingRepository;

public interface AppPrivilegeRepository extends ReactiveSortingRepository<AppPrivilege, Long> {
}