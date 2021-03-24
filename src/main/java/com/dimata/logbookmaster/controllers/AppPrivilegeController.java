package com.dimata.logbookmaster.controllers;

import com.dimata.logbookmaster.exception.NotFoundExceptionResource;
import com.dimata.logbookmaster.models.AppPrivilege;
import com.dimata.logbookmaster.models.IdentityResource;
import com.dimata.logbookmaster.services.AppPrivilegeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(value = "/api/logbook/appprivilege")
public class AppPrivilegeController {

    @Autowired
    private AppPrivilegeService appPrivilegeService;

    @GetMapping
    @ResponseStatus(value = HttpStatus.OK)
    public Flux<AppPrivilege> getAll() {
        return appPrivilegeService.getAll();
    }

    @GetMapping("/{id}")
    public Mono<AppPrivilege> getById(@PathVariable(value = "id") Long id) throws NotFoundExceptionResource {
        return appPrivilegeService.getById(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public Mono<AppPrivilege> update(@PathVariable(value = "id") Long id, @RequestBody AppPrivilege appPrivilege)
            throws NotFoundExceptionResource {
        return appPrivilegeService.update(appPrivilege, id);
    }

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public Mono<ResponseEntity> add(@RequestBody AppPrivilege appPrivilege) {
        Mono<Long> id = appPrivilegeService.create(appPrivilege);
        return id.map(value -> ResponseEntity.status(HttpStatus.CREATED).body(new IdentityResource(value)))
                .cast(ResponseEntity.class);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public Mono<String> delete(@PathVariable(value = "id") Long id) throws NotFoundExceptionResource {
        return appPrivilegeService.delete(id);
    }

}