package com.dimata.logbookmaster.services;

import com.dimata.logbookmaster.exception.NotFoundExceptionResource;
import com.dimata.logbookmaster.models.AppUsers;
import com.dimata.logbookmaster.repositories.AppUsersRepository;
import com.dimata.logbookmaster.utilities.GenerateOID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class AppUsersService {

    @Autowired
    private final AppUsersRepository appUserRepository;

    AppUsersService(AppUsersRepository appUserRepository) {
        this.appUserRepository = appUserRepository;
    }

    public Flux<AppUsers> getAll() {
        Sort sort = Sort.by(Sort.Direction.ASC, "userId", "loginId", "Password", "fullName", "Email", "Description",
                "regDate", "updateDate", "userStatus", "lastLoginDate", "lastLoginIp", "userType", "employeeId");
        return appUserRepository.findAll(sort);
    }

    public Mono<AppUsers> getById(long id) {
        return appUserRepository.findById(id).switchIfEmpty(Mono.defer(() -> Mono
                .error(new NotFoundExceptionResource(String.format("not found for the provided id %s", id)))));
    }

    public Mono<Long> create(AppUsers appUser) {
        appUser.setNewId(GenerateOID.generateOID());
        return appUserRepository.save(appUser).flatMap(item -> Mono.just(item.getUserId()));
    }

    public Mono<AppUsers> update(AppUsers appUser, long id) {
        return appUserRepository.findById(id).flatMap(data -> {
            data.setUserId(appUser.getUserId());
            return appUserRepository.save(data);
        }).switchIfEmpty(Mono.defer(() -> Mono
                .error(new NotFoundExceptionResource(String.format("not found for the provided id %s", id)))));
    }

    public Mono<String> delete(long id) {
        return appUserRepository.deleteById(id).map(aVoid -> "Terhapus!");
    }
}
