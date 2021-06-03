package bll;

import model.Product;
import validator.PriceValidator;
import validator.QuantityValidator;
import validator.Validator;

import java.util.ArrayList;
import java.util.List;

public class ProductBLL {
    private List<Validator<Product>> validators;
    public ProductBLL(){
        validators = new ArrayList<Validator<Product>>();
        validators.add(new PriceValidator());
        validators.add(new QuantityValidator());
    }
}
