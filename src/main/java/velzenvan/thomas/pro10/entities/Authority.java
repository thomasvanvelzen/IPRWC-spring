package velzenvan.thomas.pro10.entities;

import jakarta.persistence.*;
import velzenvan.thomas.pro10.models.Role;

import java.util.UUID;

@Entity
public class Authority {

    @Id
    private UUID id;
    private Role role;
    private String token;

    @OneToOne(mappedBy = "authority")
    private User user;

    protected Authority() {}

    public Authority(UUID id, Role role) {
        this.id = id;
        this.role = role;
    }



    public UUID getId() {
        return id;
    }

    public Role getRole() {
        return role;
    }

    public String getToken() {
        return token;
    }

    public User getUser() {
        return user;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setUser(User user) {
        this.user = user;
    }





}