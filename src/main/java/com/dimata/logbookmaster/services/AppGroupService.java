package com.dimata.logbookmaster.services;

import com.dimata.logbookmaster.utilities.GenerateOID;
import com.dimata.logbookmaster.exception.NotFoundExceptionResource;
import com.dimata.logbookmaster.models.AppGroup;
import com.dimata.logbookmaster.repositories.AppGroupRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class AppGroupService {

    @Autowired
    private final AppGroupRepository appGroupRepository;

    AppGroupService(AppGroupRepository appGroupRepository) {
        this.appGroupRepository = appGroupRepository;
    }

    public Flux<AppGroup> getAll() {
        Sort sort = Sort.by(Sort.Direction.ASC, "group_name", "reg_date", "status", "description");
        return appGroupRepository.findAll(sort);
    }

    public Mono<AppGroup> getById(long id) {
        return appGroupRepository.findById(id).switchIfEmpty(Mono.defer(() -> Mono
                .error(new NotFoundExceptionResource(String.format("not found for the provided id %s", id)))));
    }

    public Mono<Long> create(AppGroup appGroup) {
        appGroup.setNewGroupId(GenerateOID.generateOID());
        return appGroupRepository.save(appGroup).flatMap(item -> Mono.just(item.getGroup_id()));
    }

    public Mono<AppGroup> update(AppGroup appGroup, long id) {
        return appGroupRepository.findById(id).flatMap(data -> {
            data.setGroup_name(appGroup.getGroup_name());
            return appGroupRepository.save(data);
        }).switchIfEmpty(Mono.defer(() -> Mono
                .error(new NotFoundExceptionResource(String.format("not found for the provided id %s", id)))));
    }

    public Mono<String> delete(long id) {
        return appGroupRepository.deleteById(id).map(aVoid -> "Terhapus!");
    }
}
