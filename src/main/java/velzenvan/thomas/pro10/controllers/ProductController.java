package velzenvan.thomas.pro10.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import velzenvan.thomas.pro10.entities.Product;
import velzenvan.thomas.pro10.services.IProductService;

import java.util.UUID;
@CrossOrigin()
@RestController
public class ProductController {

    private static final String ROLE_TOKEN_HEADER = "role_token";
    private static final String USER_ID_HEADER = "user_id";

    @Autowired
    private IProductService service;

    @GetMapping("/product/id/{id}")
    @ResponseBody
    public Product getProductById(@PathVariable("id") long id) {
        return service.findById(id);
    }

    @GetMapping("/product/all")
    @ResponseBody
    public Iterable<Product> getAllProducts() {
        return service.findAll();
    }

    @PostMapping("/product")
    @ResponseBody
    public Iterable<Product> createProduct(
            @RequestBody Product product,
            @RequestHeader(ROLE_TOKEN_HEADER) String roleToken,
            @RequestHeader(USER_ID_HEADER) String stringUserId) throws Exception {
        UUID userId = UUID.fromString(stringUserId);
        service.save(product, roleToken, userId);
        return service.findAll();
    }
}