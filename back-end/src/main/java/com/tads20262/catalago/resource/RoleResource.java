package com.tads20262.catalago.resource;

import com.tads20262.catalago.entity.Role;
import com.tads20262.catalago.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/roles")
public class RoleResource {
    @Autowired
    private RoleService service;


    @GetMapping
    public ResponseEntity<List<Role>> findAll(){
        List<Role> list = service.findAll();

        return ResponseEntity.ok(list);

    }
}
