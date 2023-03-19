package com.poc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import velzenvan.thomas.pro10.entities.Authority;
import velzenvan.thomas.pro10.entities.Profile;
import velzenvan.thomas.pro10.entities.User;
import velzenvan.thomas.pro10.models.Role;
import velzenvan.thomas.pro10.repositories.UserRepository;
import velzenvan.thomas.pro10.services.IUserService;
import velzenvan.thomas.pro10.util.TokenUtil;
import velzenvan.thomas.pro10.util.EncryptionUtil;

import java.util.UUID;

@Service
public class UserService implements IUserService {
    @Autowired
    UserRepository repository;

    @Autowired
    private EncryptionUtil encryption;

    @Autowired
    private TokenUtil tokenUtil;

    @Override
    public void save(User user) {
        repository.save(user);
    }

    @Override
    public User register(User user) throws Exception {
        UUID uuid = UUID.randomUUID();
        user.setId(uuid);
        String encryptedPassword = encryption.encrypt(user.getPassword());
        user.setPassword(encryptedPassword);

        Authority authority = new Authority(user.getId(), Role.CUSTOMER);
        String token = tokenUtil.createToken(authority);
        authority.setToken(token);
        user.setAuthority(authority);

        Profile profile = new Profile(user.getId());
        user.setProfile(profile);
        return repository.save(user);
    }

    @Override
    public User login(User user) throws Exception {
        User userFound;
        try{
            userFound = repository.findByEmail(user.getEmail()).get(0);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        }

        String decryptedPassword = encryption.decrypt(userFound.getPassword());
        if (!user.getPassword().equals(decryptedPassword)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Wrong password");
        }

        tokenUtil.setNewTokenForUser(userFound.getId());
        return userFound;
    }

    @Override
    public User findByEmail(String email) {
        return repository.findByEmail(email).get(0);
    }

    @Override
    public User findById(long id) {
        return repository.findById(id).get();
    }
}