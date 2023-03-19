package velzenvan.thomas.pro10.entities;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
public class User {

    @Id
    private UUID id;
    private String email;
    private String password;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "authority_id")
    private Authority authority;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "profile_id")
    private Profile profile;

    protected User() {}

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }


    public UUID getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setAuthority(Authority authority) {
        this.authority = authority;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }



}