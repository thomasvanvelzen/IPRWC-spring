package velzenvan.thomas.pro10.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;
import velzenvan.thomas.pro10.models.Role;
import velzenvan.thomas.pro10.repositories.AuthorityRepository;

import java.util.UUID;

import static org.springframework.http.HttpStatus.UNAUTHORIZED;

@Component
public class AuthUtil {

    @Autowired
    AuthorityRepository repository;

    @Autowired
    private TokenUtil tokenUtil;

    public void authorize(UUID userId, String roleToken, Role requiredRole) throws Exception {
        String tokenFromDatabase = repository.findById(userId).getToken();
        if (!tokenUtil.validateToken(roleToken, requiredRole, userId, tokenFromDatabase)) {
            throw new ResponseStatusException(UNAUTHORIZED, "Unauthorized");
        }
    }
}
