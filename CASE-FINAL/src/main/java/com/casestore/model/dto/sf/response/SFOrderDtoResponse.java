package com.casestore.model.dto.sf.response;

import com.casestore.model.entity.Customer;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.Date;

@Data
public class SFOrderDtoResponse {

    private Long id;

    private Long customerId;

    private Date orderDate;

    private Double totalAmount;

}
