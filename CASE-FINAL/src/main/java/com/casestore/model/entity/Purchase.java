package com.casestore.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "purchase")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
//khong the de Id tu tang ke ca o DB, khi gui di PurchaseModelRequest co id = orderId, lay Id do de tao doi tuong
//luu xuong DB, khi duoc nhan PurchaseModelResponse se lay orderId kem theo so sanh voi id o DB de cap nhat du lieu(status, totalPrice)
public class Purchase {
    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String status;
    @Column(name = "total_price")
    private Double totalPrice;
    @OneToMany(mappedBy = "purchase", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<ProductItem> productItemList = new ArrayList<>();

}
