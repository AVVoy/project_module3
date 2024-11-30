package service;

import dao.ProductDAO;
import entity.Product;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ProductService {
    private final ProductDAO productDao;

    public void createProduct(Product product) {
        productDao.save(product);
    }
}
