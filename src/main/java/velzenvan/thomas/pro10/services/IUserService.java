package velzenvan.thomas.pro10.services;

import velzenvan.thomas.pro10.entities.User;


public interface IUserService {
    void save(User user);

    User register(User user) throws Exception;

    User login(User user) throws Exception;
    User findByEmail(String email);
    User findById(long id);
}