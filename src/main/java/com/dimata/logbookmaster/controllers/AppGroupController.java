package com.dimata.logbookmaster.controllers;

import com.dimata.logbookmaster.exception.NotFoundExceptionResource;
import com.dimata.logbookmaster.models.AppGroup;
import com.dimata.logbookmaster.services.AppGroupService;
import com.dimata.logbookmaster.models.IdentityResource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(value = "/api/logbook/appgroup")
public class AppGroupController {

    @Autowired
    private AppGroupService appGroupService;

    @GetMapping
    @ResponseStatus(value = HttpStatus.OK)
    public Flux<AppGroup> getAll() {
        return appGroupService.getAll();
    }

    @GetMapping("/{id}")
    public Mono<AppGroup> getById(@PathVariable(value = "id") Long id) throws NotFoundExceptionResource {
        return appGroupService.getById(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public Mono<AppGroup> update(@PathVariable(value = "id") Long id, @RequestBody AppGroup appGroup)
            throws NotFoundExceptionResource {
        return appGroupService.update(appGroup, id);
    }

    @PostMapping("/add")
    @ResponseStatus(value = HttpStatus.CREATED)
    public Mono<ResponseEntity> add(@RequestBody AppGroup appGroup) {
        Mono<Long> id = appGroupService.create(appGroup);
        return id.map(value -> ResponseEntity.status(HttpStatus.CREATED).body(new IdentityResource(value)))
                .cast(ResponseEntity.class);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public Mono<String> delete(@PathVariable(value = "id") Long id) throws NotFoundExceptionResource {
        return appGroupService.delete(id);
    }

}
