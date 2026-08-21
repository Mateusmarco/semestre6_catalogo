package com.tads20262.catalago.resource;


import com.tads20262.catalago.entity.Category;
import com.tads20262.catalago.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/categories")
public class CategoryResource {
    @Autowired
    private CategoryService service;


    @GetMapping
    public ResponseEntity<List<Category>> findAll(){
        List<Category> list = service.findAll();
//        list.add(new Category(1L, "Books"));
//        list.add(new Category(2L,"Eletronics"));
//        return ResponseEntity.ok().body(list);
        return ResponseEntity.ok(list);

    }
}
