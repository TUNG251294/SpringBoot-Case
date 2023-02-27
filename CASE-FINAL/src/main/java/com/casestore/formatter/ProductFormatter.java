//package com.casestore.formatter;
//
//import com.casestore.model.entity.Product;
//import com.casestore.service.IProductService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.format.Formatter;
//
//import java.text.ParseException;
//import java.util.Locale;
//import java.util.Optional;
//
//public class ProductFormatter implements Formatter<Product> {
//
//    private IProductService productService;
//
//    @Autowired
//    public ProductFormatter(IProductService productService) {
//        this.productService = productService;
//    }
//
//    @Override
//    public Product parse(String text, Locale locale) throws ParseException {
//        Optional<Product> productOtional = productService.findById(Long.parseLong(text));
//        return productOtional.orElse(null);
//    }
//
//    @Override
//    public String print(Product object, Locale locale) {
//        return "[" + object.getId() + ", " +object.getName() + ", " +object.getPrice() + ", " +object.getQuantity() + "]";
//    }
//}
