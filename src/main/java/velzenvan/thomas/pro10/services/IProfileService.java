package velzenvan.thomas.pro10.services;

import velzenvan.thomas.pro10.entities.Profile;

import java.util.UUID;


public interface IProfileService {
    void save(Profile profile, UUID userId, String roleToken) throws Exception;
    Profile findById(UUID userId, String roleToken) throws Exception;
}