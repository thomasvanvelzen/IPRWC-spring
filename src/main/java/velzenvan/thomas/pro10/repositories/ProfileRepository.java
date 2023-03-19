package velzenvan.thomas.pro10.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import velzenvan.thomas.pro10.entities.Profile;

import java.util.UUID;

@Repository
public interface ProfileRepository extends CrudRepository<Profile, Long> {
    Profile findById(UUID id);
}