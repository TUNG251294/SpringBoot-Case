package com.casestore.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Data
@JsonIgnoreProperties(ignoreUnknown = true) //tu choi nhung truong du thua duoc gui den
public class PurchaseModelResponse implements Serializable {
    private Long orderId;
    private String status;
    private Double totalPrice;
}
