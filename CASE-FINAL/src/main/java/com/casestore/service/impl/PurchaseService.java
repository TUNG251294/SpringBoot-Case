package com.casestore.service.impl;

import com.casestore.mapper.ProductItemMapper;
import com.casestore.mapper.PurchaseModelMapper;
import com.casestore.model.ConfirmModel;
import com.casestore.model.PurchaseModelRequest;

import com.casestore.model.dto.ProductItemDto;
import com.casestore.model.dto.PurchaseDto;
import com.casestore.model.entity.ProductItem;
import com.casestore.model.entity.Purchase;
import com.casestore.repository.IProductItemRepository;
import com.casestore.repository.IPurchaseRepository;
import com.casestore.service.IPurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;
@Service
public class PurchaseService implements IPurchaseService {
    @Autowired
    PurchaseModelMapper purchaseModelMapper;
    @Autowired
    private ProductItemMapper productItemMapper;
    @Autowired
    private IProductItemRepository productItemRepository;
    @Autowired
    private IPurchaseRepository purchaseRepository;
    @Override
    public Page<Purchase> findAll(Pageable pageable) {
        return purchaseRepository.findAll(pageable);
    }

    @Override
    public Optional<Purchase> findById(Long id) {
        Optional<Purchase> purchaseOptional = purchaseRepository.findById(id);
        return purchaseOptional;
    }

    @Override
    public Purchase save(Purchase purchase) {
        return purchaseRepository.save(purchase);
    }

    @Override
    public boolean delete(Long id) {
        if (id != null) {
            purchaseRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Transactional
    public ResponseEntity<?> restTemplateInventory(PurchaseModelRequest purchaseModelRequest) {
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<?> response = restTemplate.exchange(
                "http://192.168.4.151:8080/export/received",
                HttpMethod.POST,
                new HttpEntity<PurchaseModelRequest>(purchaseModelRequest),
                String.class    //kieu du lieu tra ve tu phia Linh la 1 chuoi thong bao da nhan don dat hang
        );

        //purchaseModelRequest là dto cua Purchase, sau khi gui qua Linh sẽ lưu xuong database cua bang Purchase
        Purchase purchase = new Purchase();
        purchase.setId(purchaseModelRequest.getId());
        purchaseRepository.save(purchase);
        //save List<ProductItemDto> vao bang ProductItem
        List<ProductItemDto> productItemDtoList = purchaseModelRequest.getProductItemListDto();
        List<ProductItem> productItemList = productItemMapper.toEntities(productItemDtoList, purchase);
        productItemRepository.saveAll(productItemList);

        return response;
    }

    //sau khi nhan hoa don tu phia Linh, tra ve xac nhan da nhan hang
    public ResponseEntity<?> restTemplateConfirm(ConfirmModel confirmModel) {
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<?> response = restTemplate.exchange(
                "http://192.168.4.151:8080/export/complete",
                HttpMethod.POST,
                new HttpEntity<ConfirmModel>(confirmModel),
                String.class    //kieu du lieu tra ve tu phia Linh
        );
        return response;
    }

//    public PurchaseDto saveSendModel(PurchaseModelRequest purchaseModelRequest) {
//
//        Purchase purchase = new Purchase();
//        purchase.setId(purchaseModelRequest.getId());
//
//        purchaseRepository.save(purchase);
//
//        List<ProductItemDto> productItemDtoList = purchaseModelRequest.getProductItemListDto();
//        List<ProductItem> productItemList = productItemMapper.toEntities(productItemDtoList, purchase);
//        productItemRepository.saveAll(productItemList);
//
//        purchase.setStatus();
//        purchase.setTotalPrice();
//        purchaseRepository.save(purchase);
//
//        PurchaseDto reponse = new PurchaseDto();
//        reponse = purchaseModelMapper.toResponseDto(purchase);
//        return reponse;
//    }
//

//    @Override
//    public PurchaseModelRequest send(PurchaseModelRequest purchaseModel) {
//            RestTemplate restTemplate = new RestTemplate();
//            String url = "http://192.168.4.99:8080/issue/order";
//
//            // Thiết lập kiểu media type
//            HttpHeaders headers = new HttpHeaders();
//            headers.setContentType(MediaType.APPLICATION_JSON);
//
//            // Gửi request dưới dạng JSON
//            HttpEntity<PurchaseModelRequest> request = new HttpEntity<>(purchaseModel, headers);
//            ResponseEntity<PurchaseModelRequest> response = restTemplate.exchange(
//                    url,
//                    HttpMethod.POST,
//                    request,
//                    new ParameterizedTypeReference<PurchaseModelRequest>() {}
//            );
//            return response.getBody();
//    }

}
