//package com.casestore.formatter;
//
//import com.casestore.model.entity.Customer;
//import com.casestore.service.ICustomerService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.format.Formatter;
//
//import java.text.ParseException;
//import java.util.Locale;
//import java.util.Optional;
//
//public class CustomerFormatter implements Formatter<Customer> {
//
//    private ICustomerService customerService;
//
//    @Autowired
//    public CustomerFormatter(ICustomerService customerService) {
//        this.customerService = customerService;
//    }
//
//    @Override
//    public Customer parse(String text, Locale locale) throws ParseException {
//        Optional<Customer> customerOtional = customerService.findById(Long.parseLong(text));
//        return customerOtional.orElse(null);
//    }
//
//    @Override
//    public String print(Customer object, Locale locale) {
//        return "[" + object.getId() + ", " +object.getName() + ", " +object.getAddress() + ", " +object.getPhone() + ", " +object.isActive()+ "]";
//    }
//}
