package velzenvan.thomas.pro10.services;

import velzenvan.thomas.pro10.entities.Authority;
import velzenvan.thomas.pro10.models.Role;

import java.util.UUID;


public interface IAuthorityService {
    void save(Authority role);

    Authority findById(UUID id);

    boolean isUserRole(UUID id, Role requiredRole);

    void validateToken(String token, Role role, UUID userId) throws Exception;
}