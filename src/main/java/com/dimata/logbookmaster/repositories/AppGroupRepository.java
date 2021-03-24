package com.dimata.logbookmaster.repositories;

import com.dimata.logbookmaster.models.AppGroup;
import org.springframework.data.repository.reactive.ReactiveSortingRepository;

public interface AppGroupRepository extends ReactiveSortingRepository<AppGroup, Long> {
}
