package com.casecustomer.model;

import com.casecustomer.model.dto.OrderDetailDto;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

//@NoArgsConstructor
//@AllArgsConstructor
//@Getter
//@Setter
//public class Order {
//    private Long id;
//    private String customer_name;
//    private String customer_address;
//    private List<ProductDto> productDtos;
//    private Date order_date; //import java.sql.Date;
//}
@Data
public class Order implements Serializable {
//    @JsonProperty("id")
//    private Long id;
    private CustomerDto customerDto;
    private Date orderDate;
//    private Double totalAmount;
    private List<OrderDetailDto> orderDetailDtos;
}
