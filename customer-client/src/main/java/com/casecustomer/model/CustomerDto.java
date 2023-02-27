package com.casecustomer.model;

import lombok.*;

import java.io.Serializable;

@Data
public class CustomerDto implements Serializable {
    private Long id;
    private String name;
    private String address;
    private String phone;
}
