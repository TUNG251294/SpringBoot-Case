package com.casestore.service.impl;

import com.casestore.model.dto.ProductDto;
import com.casestore.model.entity.Product;
import com.casestore.repository.IProductRepository;
import com.casestore.service.IProductService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductService implements IProductService {
    @Autowired
    private IProductRepository productRepository;


    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Page<ProductDto> findAll(Pageable pageable) {
        Page<Product> entities = productRepository.findAll(pageable);
        List<ProductDto> dtos = new ArrayList<>(
                entities.getContent().stream()
                        .parallel()
                        .map(entity -> modelMapper.map(entity, ProductDto.class))
                        .collect(Collectors.toList()));
        return new PageImpl<>(dtos, pageable, entities.getTotalElements());
    }

    @Override
    public Optional<ProductDto> findById(Long id) {
        Product product = productRepository.findById(id).orElse(null);
        if(product != null) {
            return Optional.of(modelMapper.map(product, ProductDto.class));
        }
        return null;
    }


    @Override
    public ProductDto save(ProductDto productDto) {
        try {
            Product product = modelMapper.map(productDto, Product.class);
            product.setStatus(true);    //dto khong co truong status khong the lay duoc gia tri mac dinh set trong database
            productRepository.save(product);
        } catch (Exception ex) {
            System.out.println("Loi:" + ex.getCause());
            throw new RuntimeException("Error while saving Product", ex);
        }
        return productDto;
    }

    @Override
    public boolean delete(Long id) {
        if (id != null) {
            productRepository.deleteById(id);
            return true;
        }
        return false;
    }
}