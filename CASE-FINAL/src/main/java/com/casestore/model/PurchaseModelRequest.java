package com.casestore.model;

import com.casestore.model.dto.ProductItemDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PurchaseModelRequest implements Serializable {
    private Long id;
//    private String cusName;   //gui request toi Linh-fake thi mo dong nay, comment orderId
    private Long orderId;
    private List<ProductItemDto> productItemListDto;
}
