package velzenvan.thomas.pro10.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import velzenvan.thomas.pro10.entities.Authority;
import velzenvan.thomas.pro10.models.Role;
import velzenvan.thomas.pro10.repositories.AuthorityRepository;

import java.time.Duration;
import java.time.Instant;
import java.util.UUID;

@Component
public class TokenUtil {

    private static final Duration EXPIRES = Duration.ofDays(1);
    @Autowired
    EncryptionUtil encryption;

    @Autowired
    AuthorityRepository repository;

    public String createToken(Authority auth) throws Exception {
        Instant expiresAt = Instant.now().plus(EXPIRES);
        String token = encryption.encrypt(
                auth.getRole().toString() + ':' + auth.getId() + ':' + expiresAt.toEpochMilli());
        return token;
    }

    public boolean validateToken(String token, Role role, UUID userId, String tokenFromDatabase) throws Exception {
        if (token == null || token.isEmpty()) {
            setNewTokenForUser(userId);
            return false;
        }
        String decryptedToken = encryption.decrypt(token);
        String[] parts = decryptedToken.split(":");

        boolean tokenFromDatabaseValid = tokenFromDatabase.equals(token);
        boolean hasThreeParts = parts.length == 3;
        boolean isRoleValid = parts[0].equals(role.toString()) || parts[0].equals(Role.ADMIN.toString());
        boolean isUserIdValid = parts[1].equals(userId.toString());
        boolean isTokenValid = !isExpired(Long.parseLong(parts[2]));

        return isRoleValid && isUserIdValid && isTokenValid && hasThreeParts && tokenFromDatabaseValid;
    }

    private boolean isExpired(long expiresAtMillis) {
        Instant expiresAt = Instant.ofEpochMilli(expiresAtMillis);
        Instant now = Instant.now();
        return now.isAfter(expiresAt);
    }

    public void setNewTokenForUser(UUID userId) throws Exception {
        Authority authorityFromDatabase = repository.findById(userId);
        String newToken = createToken(authorityFromDatabase);
        authorityFromDatabase.setToken(newToken);
        repository.save(authorityFromDatabase);
    }
}
