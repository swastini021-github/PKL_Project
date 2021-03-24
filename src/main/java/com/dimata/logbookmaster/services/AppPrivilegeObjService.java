package com.dimata.logbookmaster.services;

import com.dimata.logbookmaster.exception.NotFoundExceptionResource;
import com.dimata.logbookmaster.models.AppPrivilegeObj;
import com.dimata.logbookmaster.repositories.AppPrivilegeObjRepository;
import com.dimata.logbookmaster.utilities.GenerateOID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class AppPrivilegeObjService {

    @Autowired
    private final AppPrivilegeObjRepository appPrivilegeObjRepo;

    AppPrivilegeObjService(AppPrivilegeObjRepository appPrivilegeObjRepo) {
        this.appPrivilegeObjRepo = appPrivilegeObjRepo;
    }

    public Flux<AppPrivilegeObj> getAll() {
        Sort sort = Sort.by(Sort.Direction.ASC, "privId", "Code");
        return appPrivilegeObjRepo.findAll(sort);
    }

    public Mono<AppPrivilegeObj> getById(long id) {
        return appPrivilegeObjRepo.findById(id).switchIfEmpty(Mono.defer(() -> Mono
                .error(new NotFoundExceptionResource(String.format("not found for the provided id %s", id)))));
    }

    public Mono<Long> create(AppPrivilegeObj appPrivilegeObj) {
        appPrivilegeObj.setNewId(GenerateOID.generateOID());
        return appPrivilegeObjRepo.save(appPrivilegeObj).flatMap(item -> Mono.just(item.getPrivObjId()));
    }

    public Mono<AppPrivilegeObj> update(AppPrivilegeObj appPrivilegeObj, long id) {
        return appPrivilegeObjRepo.findById(id).flatMap(data -> {
            data.setPrivId(appPrivilegeObj.getPrivId());
            return appPrivilegeObjRepo.save(data);
        }).switchIfEmpty(Mono.defer(() -> Mono
                .error(new NotFoundExceptionResource(String.format("not found for the provided id %s", id)))));
    }

    public Mono<String> delete(long id) {
        return appPrivilegeObjRepo.deleteById(id).map(aVoid -> "Terhapus!");
    }
}
