package com.testconnectserviceproject.service;


import com.testconnectserviceproject.model.entity.Test;
import com.testconnectserviceproject.repository.ITestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Service
public class TestService implements ITestService {
    @Autowired
    private ITestRepository testRepository;

    //Khi gui 1 object Test qua POSTMAN, POST http://localhost:8080/api/senttest thi object do duoc chuyen toi
    //controller duoc anh xa tu duong dan api/order tren may có ipconfig 192.168.4.99 và server.port=8080
    @Override
    public Test sendTest(Test test) {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://192.168.4.99:8080/api/order";

        // Thiết lập kiểu media type
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        // Gửi request dưới dạng JSON
        HttpEntity<Test> request = new HttpEntity<>(test, headers);
        ResponseEntity<Test> response = restTemplate.exchange(
                url,
                HttpMethod.POST,
                request,
                new ParameterizedTypeReference<Test>() {
                }
        );
        return response.getBody();
    }

    @Override
    public Page<Test> findAll(Pageable pageable) {
        return testRepository.findAll(pageable);
    }

    @Override
    public Optional<Test> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public Test save(Test test) {
        return testRepository.save(test);
    }

    @Override
    public boolean delete(Long id) {
        return false;
    }
}
