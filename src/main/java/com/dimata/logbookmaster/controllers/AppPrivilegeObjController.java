package com.dimata.logbookmaster.controllers;

import com.dimata.logbookmaster.exception.NotFoundExceptionResource;
import com.dimata.logbookmaster.models.AppPrivilegeObj;
import com.dimata.logbookmaster.models.IdentityResource;
import com.dimata.logbookmaster.services.AppPrivilegeObjService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(value = "/api/v1/app-privilege-obj")
public class AppPrivilegeObjController {

    @Autowired
    private AppPrivilegeObjService appPrivilegeObjService;

    @GetMapping
    @ResponseStatus(value = HttpStatus.OK)
    public Flux<AppPrivilegeObj> getAll() {
        return appPrivilegeObjService.getAll();
    }

    @GetMapping("/{id}")
    public Mono<AppPrivilegeObj> getById(@PathVariable(value = "id") Long id) throws NotFoundExceptionResource {
        return appPrivilegeObjService.getById(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public Mono<AppPrivilegeObj> update(@PathVariable(value = "id") Long id,
            @RequestBody AppPrivilegeObj appPrivilegeObj) throws NotFoundExceptionResource {
        return appPrivilegeObjService.update(appPrivilegeObj, id);
    }

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public Mono<ResponseEntity> add(@RequestBody AppPrivilegeObj appPrivilegeObj) {
        Mono<Long> id = appPrivilegeObjService.create(appPrivilegeObj);
        return id.map(value -> ResponseEntity.status(HttpStatus.CREATED).body(new IdentityResource(value)))
                .cast(ResponseEntity.class);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public Mono<String> delete(@PathVariable(value = "id") Long id) throws NotFoundExceptionResource {
        return appPrivilegeObjService.delete(id);
    }

}
