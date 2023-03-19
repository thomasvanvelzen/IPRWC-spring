package velzenvan.thomas.pro10.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import velzenvan.thomas.pro10.entities.Profile;
import velzenvan.thomas.pro10.models.Role;
import velzenvan.thomas.pro10.repositories.ProfileRepository;
import velzenvan.thomas.pro10.util.AuthUtil;

import java.util.UUID;

@Service
public class ProfileService implements IProfileService {
    @Autowired
    ProfileRepository repository;

    @Autowired
    private AuthUtil auth;

    @Override
    public void save(Profile profile, UUID userId, String roleToken) throws Exception {
        auth.authorize(userId, roleToken, Role.CUSTOMER);
        repository.save(profile);
    }

    @Override
    public Profile findById(UUID userId, String roleToken) throws Exception {
        auth.authorize(userId, roleToken, Role.CUSTOMER);
        return repository.findById(userId);
    }

}