package com.dimata.logbookmaster.repositories;

import com.dimata.logbookmaster.models.AppUsers;
import org.springframework.data.repository.reactive.ReactiveSortingRepository;

public interface AppUsersRepository extends ReactiveSortingRepository<AppUsers, Long> {

}
