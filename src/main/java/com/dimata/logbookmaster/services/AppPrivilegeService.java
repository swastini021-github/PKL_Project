package com.dimata.logbookmaster.services;

import com.dimata.logbookmaster.repositories.AppPrivilegeRepository;
import com.dimata.logbookmaster.exception.NotFoundExceptionResource;
import com.dimata.logbookmaster.models.AppPrivilege;
import com.dimata.logbookmaster.utilities.GenerateOID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class AppPrivilegeService {

    @Autowired
    private final AppPrivilegeRepository appPrivilegeRepository;

    AppPrivilegeService(AppPrivilegeRepository appPrivilegeRepository) {
        this.appPrivilegeRepository = appPrivilegeRepository;
    }

    public Flux<AppPrivilege> getAll() {
        Sort sort = Sort.by(Sort.Direction.ASC, "priv_name", "reg_date", "description");
        return appPrivilegeRepository.findAll(sort);
    }

    public Mono<AppPrivilege> getById(long priv_id) {
        return appPrivilegeRepository.findById(priv_id).switchIfEmpty(Mono.defer(() -> Mono
                .error(new NotFoundExceptionResource(String.format("not found for the provided id %s", priv_id)))));
    }

    public Mono<Long> create(AppPrivilege appPrivilege) {
        appPrivilege.setNewPrivId(GenerateOID.generateOID());
        return appPrivilegeRepository.save(appPrivilege).flatMap(item -> Mono.just(item.getPriv_id()));
    }

    public Mono<AppPrivilege> update(AppPrivilege appPrivilege, long id) {
        return appPrivilegeRepository.findById(id).flatMap(data -> {
            data.setPriv_name(appPrivilege.getPriv_name());
            return appPrivilegeRepository.save(data);
        }).switchIfEmpty(Mono.defer(() -> Mono
                .error(new NotFoundExceptionResource(String.format("not found for the provided id %s", id)))));
    }

    public Mono<String> delete(long id) {
        return appPrivilegeRepository.deleteById(id).map(aVoid -> "Terhapus!");
    }
}
