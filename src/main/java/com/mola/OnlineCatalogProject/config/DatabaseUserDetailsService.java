package com.mola.OnlineCatalogProject.config;

import com.mola.OnlineCatalogProject.model.PendingUser;
import com.mola.OnlineCatalogProject.model.User;
import com.mola.OnlineCatalogProject.repository.PendingUserRepository;
import com.mola.OnlineCatalogProject.repository.UserRepository;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;


import java.util.List;
import java.util.Optional;

@Component
@Getter
@Slf4j
public class DatabaseUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PendingUserRepository pendingUserRepository;

    @Autowired
    public DatabaseUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        log.info("Username " + userName);
        Optional<PendingUser> optional = pendingUserRepository.findByUsername(userName);

        if (optional.isPresent()) {
            log.info(optional.get().getActivationCode());
            throw new UsernameNotFoundException(userName);
        }

        User user = userRepository.findByEmail(userName)
                .orElseThrow(() -> new UsernameNotFoundException("Email " + userName + " not found"));
        return new CustomUserDetails(user);
    }

}
