package validator;

import model.Product;

public class QuantityValidator implements Validator<Product>{

    @Override
    public void validate(Product product) {
        if(product.getQuantity() < 0 || product.getQuantity() > 2147483647)
        {
            throw new IllegalArgumentException("The quantity limit is not respected!");
        }
    }
}
