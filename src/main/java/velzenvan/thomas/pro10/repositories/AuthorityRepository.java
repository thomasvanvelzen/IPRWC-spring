package velzenvan.thomas.pro10.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import velzenvan.thomas.pro10.entities.Authority;
import velzenvan.thomas.pro10.models.Role;

import java.util.UUID;

@Repository
public interface AuthorityRepository extends CrudRepository<Authority, Long> {
    Authority findById(UUID id);

    Authority findByRoleAndUserId(Role role, long id);

}