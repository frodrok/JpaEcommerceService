package se.fredrikandthenurses.validation;

import se.fredrikandthenurses.exception.RepositoryException;
import se.fredrikandthenurses.model.Product;

/**
 * Created by TheYellowBelliedMarmot on 2016-01-13.
 */
public class ProductValidator implements Validator<Product> {

    @Override
    public boolean isValid(Product entity) {
        if (productNameIsEmpty(entity.getProductName()) || !priceIsOk(entity.getProductPrice()) || productNumberIsEmpty(entity.getProductNumber()))
        {
            throw new RepositoryException("Not valid Product");
        }
        return true;
    }

    private boolean productNameIsEmpty(String productName){
        return productName.trim().length() < 1;
    }

    private boolean priceIsOk(Double price){
        return price > 0;
    }

    private boolean productNumberIsEmpty(String productNumber){
        return productNumber.trim().length() < 1;
    }
}
