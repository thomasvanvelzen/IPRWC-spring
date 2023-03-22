package velzenvan.thomas.pro10.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import velzenvan.thomas.pro10.entities.Product;
import velzenvan.thomas.pro10.models.Role;
import velzenvan.thomas.pro10.repositories.ProductRepository;
import velzenvan.thomas.pro10.util.AuthUtil;

import java.util.UUID;

@Service
public class ProductService implements IProductService {
    @Autowired
    ProductRepository repository;

    @Autowired
    private AuthUtil auth;

    @Override
    public Iterable<Product> save(Product product, String roleToken, UUID userId) throws Exception {
        auth.authorize(userId, roleToken, Role.CUSTOMER);
        repository.save(product);
        return repository.findAll();
    }

    @Override
    public Product findById(long id) {
        return repository.findById(id).get();
    }

    @Override
    public Iterable<Product> findAll() {
        return repository.findAll();
    }
}