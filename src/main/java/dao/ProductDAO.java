package dao;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import entity.Product;
import lombok.RequiredArgsConstructor;

import java.io.File;
import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
public class ProductDAO {

    private final ObjectMapper objectMapper;
    private final File file;

    public void save(Product product) {
        try {
            List<Product> products = objectMapper.readValue(file, new TypeReference<List<Product>>() {
            });
            products.add(product);

            objectMapper.writeValue(file, products);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
