package com.tads20262.catalago.service;

import com.tads20262.catalago.dto.CategoryDTO;
import com.tads20262.catalago.dto.ProductDTO;
import com.tads20262.catalago.entity.Category;
import com.tads20262.catalago.entity.Product;
import com.tads20262.catalago.repository.CategoryRepository;
import com.tads20262.catalago.repository.ProductRepository;
import com.tads20262.catalago.service.exceptions.DatabaseException;
import com.tads20262.catalago.service.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductService {
    @Autowired
    private ProductRepository repository;
    private CategoryRepository categoryRepository;

    @Transactional
    public Page<ProductDTO> findAllPaged(Pageable pageable){
        Page<Product> list = repository.findAll(pageable);
//        List<CategoryDTO> listDTO = new ArrayList<>();
//
//        for (Category cat: list){
//            listDTO.add(new CategoryDTO(cat)));
//        }

        //com expressão Lambda - (map reduce filter)
//       List<CategoryDTO> listDTO = list.stream().map(x -> new CategoryDTO(x)).collect(Collectors.toList());
        return list.map(ProductDTO::new);
    }

    @Transactional(readOnly = true)
    public ProductDTO findById (Long id) {
        Optional<Product> obj = repository.findById(id);
        Product entity = obj.orElseThrow(()-> new ResourceNotFoundException("Entity Not found"));

        return new ProductDTO (entity, entity.getCategories());


    }

    @Transactional
    public ProductDTO insert(ProductDTO dto) {
        Product entity = new Product();
        //entity.setName(dto.getName());
        copyDtoToEntity(dto,entity);
        entity = repository.save(entity);
        return new ProductDTO(entity);
    }

    @Transactional
    public ProductDTO update(Long id, ProductDTO dto) {
        try {
            Product entity = repository.getReferenceById(id);
            //entity.setName(dto.getName());
            copyDtoToEntity(dto,entity);
            entity = repository.save(entity);
            return new ProductDTO(entity);
        }
        catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Id not found " + id);
        }
    }

    public void delete(Long id)
    {
        try {
            Optional<Product> obj = repository.findById(id);

            obj.orElseThrow(()-> new ResourceNotFoundException("Id not found " + id));

            repository.deleteById(id);
        }
        catch(DataIntegrityViolationException e)
        {
            throw new DatabaseException("Integrity violation");
        }
    }

    private void copyDtoToEntity(ProductDTO dto, Product entity) {

        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());
        entity.setDate(dto.getDate());
        entity.setImgUrl(dto.getImgUrl());
        entity.setPrice(dto.getPrice());

        entity.getCategories().clear();
        for (CategoryDTO catDto : dto.getCategories()) {
            Category category = categoryRepository.getReferenceById(catDto.getId());
            entity.getCategories().add(category);
        }
    }

}
