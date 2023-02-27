package com.casestore.model.dto;

import com.casestore.model.dto.bo.response.OrderDetailDtoResponse;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
//bỏ qua các thuộc tính null cua Vòng lặp vô hạn trong JSON khi một đối tượng chứa 1 tham chiếu đệ quy
public class OrderDto implements Serializable {
    private CustomerDto customerDto;    //k duoc truyen entity lam property cua 1 data transform object(DTO)
    private Date orderDate;
    private List<OrderDetailDtoResponse> orderDetailDtos;
}