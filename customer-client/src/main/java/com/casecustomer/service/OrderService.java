package com.casecustomer.service;

import com.casecustomer.model.Order;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class OrderService {
//    public Order sendOrder(Order order) {
//        RestTemplate restTemplate = new RestTemplate();
//        String url = "http://localhost:8081/api/order/save";
//        //Su dung StringBuilder
//        ResponseEntity<Order> response = restTemplate.exchange(    //response o day da duoc chuyen tu JSON ma hoa khi gui tu con service server(User) sang object
//                url,
//                HttpMethod.POST,
//                null,   //header,body...o day
//                new ParameterizedTypeReference<Order>() {}
//        );
////        Order order = response.getBody();
////            List<User> users = response.getBody().getDatas();
//        return response.getBody();
//
//    }
public Order sendOrder(Order order) {
    RestTemplate restTemplate = new RestTemplate();
    String url = "http://localhost:8080/api/order";

    // Thiết lập kiểu media type
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);

    // Gửi request dưới dạng JSON
    HttpEntity<Order> request = new HttpEntity<>(order, headers);
    ResponseEntity<Order> response = restTemplate.exchange(
            url,
            HttpMethod.POST,
            request,
            new ParameterizedTypeReference<Order>() {}
    );
    return response.getBody();
}
}
