package com.casestore.service.impl;

import com.casestore.mapper.OrderDetailMapper;
import com.casestore.mapper.OrderMapper;
import com.casestore.model.dto.CustomerDto;
import com.casestore.model.dto.bo.response.OrderDetailDtoResponse;
import com.casestore.model.dto.bo.response.OrderDtoResponse;
import com.casestore.model.dto.sf.request.SFOrderDetailtRequestDto;
import com.casestore.model.dto.sf.request.SFOrderDtoRequest;
import com.casestore.model.dto.sf.response.SFOrderDtoResponse;
import com.casestore.model.entity.Customer;
import com.casestore.model.entity.Order;
import com.casestore.model.entity.OrderDetail;
import com.casestore.repository.ICustomerRepository;
import com.casestore.repository.IOrderDetailRepository;
import com.casestore.repository.IOrderRepository;
import com.casestore.service.SFOrderService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class SFOrderServiceImpl implements SFOrderService {

    @Autowired
    private ICustomerRepository customerRepository;

    @Autowired
    private IOrderRepository orderRepository;

    @Autowired
    private IOrderDetailRepository orderDetailRepository;

    @Autowired
    private OrderDetailMapper orderDetailMapper;

    @Autowired
    private OrderMapper orderMapper;

    @Override
    @Transactional
    public SFOrderDtoResponse save(SFOrderDtoRequest requestDto) {
        Order order = new Order();
        Long customerId = requestDto.getCustomerId();
        Customer customer = customerRepository.findById(customerId).orElse(null);
        order.setCustomer(customer);
        order.setOrderDate(new Date());
        orderRepository.save(order);

        List<SFOrderDetailtRequestDto> orderDetailtRequestDtoList = requestDto.getOrderDetailtRequestDtoList();

        List<OrderDetail> orderDetails = orderDetailMapper.toEntities(orderDetailtRequestDtoList, order);

        orderDetailRepository.saveAll(orderDetails);

        Double totalPrice = calcTotalPrice(orderDetails);
        order.setTotalAmount(totalPrice);
        orderRepository.save(order);

        SFOrderDtoResponse reponse = new SFOrderDtoResponse();
        reponse = orderMapper.toResponseDto(order);
        return reponse;
    }

    //KET QUA TRA VE DA LOC CAC GIA TRI NULL DO VONG LAP VO HAN TRONG JSON KHI OBJECT CHUA
    //THAM CHIEU DE QUY = @JsonInclude(JsonInclude.Include.NON_NULL) tai class OrderDetailDtoResponse
    @Override
    @Transactional
    public Page<OrderDtoResponse> findAll(Pageable pageable) {
        Page<Order> orders = orderRepository.findAll(pageable);
        List<OrderDtoResponse> orderDtoResponseList = new ArrayList<>();
        for(Order ele: orders){
            OrderDtoResponse orderDtoResponse = new OrderDtoResponse();
            BeanUtils.copyProperties(ele,orderDtoResponse);

            Customer customer = ele.getCustomer();
            CustomerDto customerDto = new CustomerDto();
            BeanUtils.copyProperties(customer,customerDto);
            orderDtoResponse.setCustomerDto(customerDto);

            List<OrderDetail> orderDetails = ele.getOrderDetails();
            List<OrderDetailDtoResponse> orderDetailDtoResponseList = new ArrayList<>();
            for(OrderDetail od: orderDetails){
                OrderDetailDtoResponse orderDetailDtoResponse = new OrderDetailDtoResponse();
                BeanUtils.copyProperties(od,orderDetailDtoResponse);

                orderDetailDtoResponseList.add(orderDetailDtoResponse);
            }
            orderDtoResponse.setOrderDetailDtos(orderDetailDtoResponseList);
            orderDtoResponse.setOrderDetailDtos(orderDetailDtoResponseList);

            orderDtoResponseList.add(orderDtoResponse);
        }
        return new PageImpl<>(orderDtoResponseList);
    }

    @Override
    @Transactional
    public Optional<OrderDtoResponse> findById(Long id) {
        //.orElse(null) tranh ngoai le NullPointerException
        Order order = orderRepository.findById(id).orElse(null);

        if(order != null) {
            OrderDtoResponse orderDtoResponse = new OrderDtoResponse();

            //map cac property giong nhau tu object sang dto
            BeanUtils.copyProperties(order, orderDtoResponse);

            //map cac property cua Customer trong Order sang cac property cua CustomerDto trong OrderDtoResponse
            //do ban than truong Customer la 1 object
            Customer customer = order.getCustomer();
            CustomerDto customerDto = new CustomerDto();
            BeanUtils.copyProperties(customer, customerDto);
            orderDtoResponse.setCustomerDto(customerDto);

            List<OrderDetail> orderDetails = order.getOrderDetails();
            List<OrderDetailDtoResponse> orderDetailDtoResponseList = new ArrayList<>();
            for (OrderDetail ele : orderDetails) {
                OrderDetailDtoResponse orderDetailDtoResponse = new OrderDetailDtoResponse();
                BeanUtils.copyProperties(ele, orderDetailDtoResponse);

                orderDetailDtoResponseList.add(orderDetailDtoResponse);
            }
            orderDtoResponse.setOrderDetailDtos(orderDetailDtoResponseList);

            return Optional.of(orderDtoResponse);
        }
        return null;
    }

    private Double calcTotalPrice(List<OrderDetail> orderDetails) {
        Double result= 0.0;
        for(OrderDetail ele : orderDetails) {
            result = result + ele.getProduct().getPrice() * ele.getQuantity();
        }
        return result;
    }
}
