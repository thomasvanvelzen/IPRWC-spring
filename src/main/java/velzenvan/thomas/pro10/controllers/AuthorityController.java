package velzenvan.thomas.pro10.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import velzenvan.thomas.pro10.entities.Authority;
import velzenvan.thomas.pro10.models.Role;
import velzenvan.thomas.pro10.models.TokenPayload;
import velzenvan.thomas.pro10.services.IAuthorityService;

import java.util.UUID;

@CrossOrigin()
@RestController
public class AuthorityController {

    @Autowired
    private IAuthorityService service;

    @GetMapping("/auth/{id}")
    @ResponseBody
    public Authority getAuthorityByUserId(@PathVariable("id") String id) {
        UUID uuid = UUID.fromString(id);
        return service.findById(uuid);
    }

    @PostMapping("/auth/validate")
    @ResponseBody
    public Authority validateToken(
            @RequestBody TokenPayload payload) throws Exception {
        Role role = Role.valueOf(payload.getRole());
        UUID userId = UUID.fromString(payload.getUserId());
        service.validateToken(payload.getToken(), role, userId);
        return service.findById(userId);
    }
}