package velzenvan.thomas.pro10.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import velzenvan.thomas.pro10.services.IUserService;

@Configuration
public class SpringConfig {

    @Bean
    public IUserService userService() {
        return new com.poc.service.UserService();
    }

}