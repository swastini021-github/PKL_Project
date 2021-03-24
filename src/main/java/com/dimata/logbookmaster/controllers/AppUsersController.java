package com.dimata.logbookmaster.controllers;

import com.dimata.logbookmaster.exception.NotFoundExceptionResource;
import com.dimata.logbookmaster.models.AppUsers;
import com.dimata.logbookmaster.models.IdentityResource;
import com.dimata.logbookmaster.services.AppUsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(value = "/api/v1/app-user")
public class AppUsersController {

    @Autowired
    private AppUsersService appUserService;

    @GetMapping
    @ResponseStatus(value = HttpStatus.OK)
    public Flux<AppUsers> getAll() {
        return appUserService.getAll();
    }

    @GetMapping("/{id}")
    public Mono<AppUsers> getById(@PathVariable(value = "id") Long id) throws NotFoundExceptionResource {
        return appUserService.getById(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public Mono<AppUsers> update(@PathVariable(value = "id") Long id, @RequestBody AppUsers appUser)
            throws NotFoundExceptionResource {
        return appUserService.update(appUser, id);
    }

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public Mono<ResponseEntity> add(@RequestBody AppUsers appUser) {
        Mono<Long> id = appUserService.create(appUser);
        return id.map(value -> ResponseEntity.status(HttpStatus.CREATED).body(new IdentityResource(value)))
                .cast(ResponseEntity.class);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public Mono<String> delete(@PathVariable(value = "id") Long id) throws NotFoundExceptionResource {
        return appUserService.delete(id);
    }

}
