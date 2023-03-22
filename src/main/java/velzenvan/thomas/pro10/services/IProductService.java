package velzenvan.thomas.pro10.services;

import velzenvan.thomas.pro10.entities.Product;

import java.util.UUID;


public interface IProductService {
    void save(Product product, String roleToken, UUID userId) throws Exception;
    Product findById(long id);

    Iterable<Product> findAll();
}