package com.tads20262.catalago.service;

import com.tads20262.catalago.dto.CategoryDTO;
import com.tads20262.catalago.entity.Category;
import com.tads20262.catalago.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository repository;

    @Transactional
    public List<CategoryDTO> findAll(){

        List<Category> list = repository.findAll();


       return list
               .stream()
               .map(CategoryDTO::new)
               .collect(Collectors.toList());

    }
    @Transactional(readOnly = true)
    public CategoryDTO findById(Long id){
        Optional<Category> obj = repository.findById(id);

        Category entity = obj.get();

        return new CategoryDTO(entity);
    }
}
