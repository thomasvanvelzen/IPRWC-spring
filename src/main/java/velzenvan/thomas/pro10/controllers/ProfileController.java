package velzenvan.thomas.pro10.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import velzenvan.thomas.pro10.entities.Profile;
import velzenvan.thomas.pro10.services.IProfileService;

import java.util.UUID;
@CrossOrigin(maxAge = 3600)
@RestController
public class ProfileController {

    private static final String ROLE_TOKEN_HEADER = "role_token";
    private static final String USER_ID_HEADER = "user_id";

    @Autowired
    private IProfileService service;

    @GetMapping("/profile")
    @ResponseBody
    public Profile getProfileById(
            @RequestHeader(ROLE_TOKEN_HEADER) String roleToken,
            @RequestHeader(USER_ID_HEADER) String stringUserId) throws Exception {
        System.out.println("roleToken = " + roleToken);
        System.out.println("stringUserId = " + stringUserId);
        UUID userId = UUID.fromString(stringUserId);
        return service.findById(userId, roleToken);
    }

    @PostMapping("/profile")
    @ResponseBody
    public Profile saveProfile(
            @RequestBody Profile profile,
            @RequestHeader(ROLE_TOKEN_HEADER) String roleToken,
            @RequestHeader(USER_ID_HEADER) String stringUserId) throws Exception {
        UUID userId = UUID.fromString(stringUserId);
        service.save(profile, userId, roleToken);
        return profile;
    }
}