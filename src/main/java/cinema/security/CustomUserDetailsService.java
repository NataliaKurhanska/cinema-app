package cinema.security;

import cinema.model.User;
import cinema.service.UserService;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private UserService userService;

    public CustomUserDetailsService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.findByEmail(username).orElseThrow(() ->
                new UsernameNotFoundException("User " + username + " not found"));
        UserBuilder builder =
                org.springframework.security.core.userdetails.User.withUsername(username);
        builder.password(user.getPassword());
        builder.authorities(user.getRoles().stream()
                .map(r -> r.getRoleName().name())
                .toArray(String[]::new));
        return builder.build();
    }
}
