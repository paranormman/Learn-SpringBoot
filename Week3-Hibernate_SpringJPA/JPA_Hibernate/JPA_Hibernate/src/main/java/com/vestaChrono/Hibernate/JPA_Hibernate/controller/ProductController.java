package com.vestaChrono.Hibernate.JPA_Hibernate.controller;

import com.vestaChrono.Hibernate.JPA_Hibernate.entities.ProductEntity;
import com.vestaChrono.Hibernate.JPA_Hibernate.repository.ProductRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.awt.print.Pageable;
import java.util.List;

@RestController
@RequestMapping(path = "/products")
public class ProductController {

    private final int Page_Size = 5;

    private final ProductRepository productRepository;

    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping
    public List<ProductEntity> getAllProducts(
            @RequestParam(defaultValue = "") String title,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "0") Integer pageNumber) {

            return productRepository.findByTitleContainingIgnoreCase(
                    title,
                    PageRequest.of(pageNumber, Page_Size, Sort.by(sortBy))
            );

//        return productRepository.findByTitleOrderByPrice("Mazza");

//        return productRepository.findByOrderByPrice();

//        return productRepository.findBy(Sort.by(Sort.Direction.DESC, sortBy, "price")); //by default sorting order is ascending

//        return productRepository.findBy(Sort.by(
//                Sort.Order.desc(sortBy),
//                Sort.Order.asc("title")
//        ));

        //Creating a page
//        Pageable pageable = PageRequest.of(
//                pageNumber,
//                Page_Size,
//                Sort.by(sortBy)
//        );
//
//        return productRepository.findAll(pageable); //use .getContent() to get only the content
    }
}
