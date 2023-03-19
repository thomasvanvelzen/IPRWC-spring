package velzenvan.thomas.pro10.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import velzenvan.thomas.pro10.entities.Product;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {

}