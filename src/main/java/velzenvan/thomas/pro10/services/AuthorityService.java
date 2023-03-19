package velzenvan.thomas.pro10.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import velzenvan.thomas.pro10.entities.Authority;
import velzenvan.thomas.pro10.models.Role;
import velzenvan.thomas.pro10.repositories.AuthorityRepository;
import velzenvan.thomas.pro10.util.AuthUtil;

import java.util.UUID;

@Service
public class AuthorityService implements IAuthorityService {
    @Autowired
    AuthorityRepository repository;

    @Autowired
    AuthUtil auth;

    @Override
    public void save(Authority role) {
        repository.save(role);
    }

    @Override
    public Authority findById(UUID id) {
        return repository.findById(id);
    }

    @Override
    public boolean isUserRole(UUID id, Role requiredRole) {
        return repository.findById(id).getRole().equals(requiredRole);
    }

    @Override
    public void validateToken(String token, Role role, UUID userId) throws Exception {
        auth.authorize(userId, token, role);
    }
}