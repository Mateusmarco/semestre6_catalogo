package com.tads20262.catalago.service;

import com.tads20262.catalago.dto.ProductDTO;
import com.tads20262.catalago.entity.Product;
import com.tads20262.catalago.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductService {
    @Autowired
    private ProductRepository repository;

    @Transactional
    public List<ProductDTO> findAll(){

        List<Product> list = repository.findAll();


        return list
                .stream()
                .map(ProductDTO::new)
                .collect(Collectors.toList());

    }
    @Transactional(readOnly = true)
    public ProductDTO findById(Long id){
        Optional<Product> obj = repository.findById(id);

        Product entity = obj.get();

        return new ProductDTO(entity);
    }
}
