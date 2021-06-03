package validator;

import model.Product;

public class PriceValidator implements Validator<Product>{

    @Override
    public void validate(Product product) {
        if(product.getPrice() < 0 || product.getPrice() > 2147483647)
        {
            throw new IllegalArgumentException("The price limit is not respected!");
        }
    }
}
