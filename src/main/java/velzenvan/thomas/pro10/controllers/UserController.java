package velzenvan.thomas.pro10.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import velzenvan.thomas.pro10.entities.User;
import velzenvan.thomas.pro10.services.IUserService;
@CrossOrigin()
@RestController
public class UserController {

    @Autowired
    private IUserService service;

    @GetMapping("/user/{id}")
    @ResponseBody
    public User getUserById(@PathVariable("id") long id) {
        return service.findById(id);
    }

    @PostMapping("/user/register")
    @ResponseBody
    public User registerUser(@RequestBody User user) throws Exception {
        return service.register(user);
    }

    @PostMapping("/user/login")
    @ResponseBody
    public User loginUser(@RequestBody User user) throws Exception {
        return service.login(user);
    }
}