package com.casestore.service.impl;

import com.casestore.model.dto.CustomerDto;
import com.casestore.model.entity.Customer;
import com.casestore.repository.ICustomerRepository;
import com.casestore.service.ICustomerService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CustomerService implements ICustomerService {
    @Autowired
    private ICustomerRepository customerRepository;
    //    @Autowired
//    private Mapper mapper;
    @Autowired
    private ModelMapper modelMapper;

    //    @Override
//    public List<Customer> findAll() {
//        return (List<Customer>) customerRepository.findAll();
//    }
    @Override
    public Page<CustomerDto> findAll(Pageable pageable) {
        Page<Customer> entities = customerRepository.findAll(pageable);
        List<CustomerDto> dtos = new ArrayList<>(
                entities.getContent().stream()
                        .parallel()
                        .map(entity -> modelMapper.map(entity, CustomerDto.class))
                        .collect(Collectors.toList()));
        return new PageImpl<>(dtos, pageable, entities.getTotalElements());
    }

    @Override
    public Optional<CustomerDto> findById(Long id) {
        Customer customer = customerRepository.findById(id).orElse(null);
        if (customer != null) {
            return Optional.of(modelMapper.map(customer, CustomerDto.class));
        }
        return null;
    }

    //    @Override
//    public Customer save(Customer customer) {
//        return customerRepository.save(customer);
//    }
    @Override
    public CustomerDto save(CustomerDto customerDto) {
        try {
            Customer customer = modelMapper.map(customerDto, Customer.class);
            customer.setIsActive(true);   //dto khong co truong status khong the lay duoc gia tri mac dinh set trong database
            customerRepository.save(customer);
        } catch (Exception ex) {
            System.out.println("Loi:" + ex.getCause());
            throw new RuntimeException("Error while saving Customer", ex);
        }
        return customerDto;
    }

    @Override
    public boolean delete(Long id) {
        if (id != null) {
            customerRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
