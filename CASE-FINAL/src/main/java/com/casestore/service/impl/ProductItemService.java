package com.casestore.service.impl;

import com.casestore.model.PurchaseModelRequest;
import com.casestore.model.entity.ProductItem;
import com.casestore.repository.IProductItemRepository;
import com.casestore.service.IProductItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;
@Service
public class ProductItemService implements IProductItemService {
    @Autowired
    private IProductItemRepository productItemRepository;
    @Override
    public Page<ProductItem> findAll(Pageable pageable) {
        return productItemRepository.findAll(pageable);
    }

    @Override
    public Optional<ProductItem> findById(Long id) {
        return productItemRepository.findById(id);
    }

    @Override
    public ProductItem save(ProductItem productItem) {
        return productItemRepository.save(productItem);
    }

    @Override
    public boolean delete(Long id) {
        if (id != null) {
            productItemRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
        public PurchaseModelRequest send(PurchaseModelRequest purchaseModelRequest) {
            RestTemplate restTemplate = new RestTemplate();
            String url = "http://192.168.4.99:8080/issue/order";

            // Thiết lập kiểu media type
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            // Gửi request dưới dạng JSON
            HttpEntity<PurchaseModelRequest> request = new HttpEntity<>(purchaseModelRequest, headers);
            ResponseEntity<PurchaseModelRequest> response = restTemplate.exchange(
                    url,
                    HttpMethod.POST,
                    request,
                    new ParameterizedTypeReference<PurchaseModelRequest>() {}
            );
            return response.getBody();
    }
}
