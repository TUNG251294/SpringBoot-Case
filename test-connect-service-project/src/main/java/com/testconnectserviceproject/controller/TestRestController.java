package com.testconnectserviceproject.controller;

import com.testconnectserviceproject.model.entity.Test;
import com.testconnectserviceproject.service.ITestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController("/api")
public class TestRestController {
    @Autowired
    private ITestService testService;

    @PostMapping("/sendtest")
    public ResponseEntity<ResponseStatus> send(@RequestBody Test test) {
        testService.sendTest(test);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/gettest")
    public ResponseEntity<Test> save(@RequestBody Test test) {
        return new ResponseEntity<>(testService.save(test), HttpStatus.CREATED);
    }

    @GetMapping("/test/list")
    public ResponseEntity<Page<Test>> list(@PageableDefault(value = 5) Pageable pageable) {
        Page<Test> tests = testService.findAll(pageable);
        return new ResponseEntity<>(tests, HttpStatus.OK);
    }
}
