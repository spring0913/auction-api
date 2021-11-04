package com.auction.api.service.user;

import com.auction.api.model.user.Email;
import com.auction.api.model.user.User;
import com.auction.api.repository.user.UserRepository;
import com.auction.api.security.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static com.google.common.base.Preconditions.checkArgument;
import static org.apache.commons.lang3.StringUtils.isNotEmpty;

@Service
public class UserService {

    private final PasswordEncoder passwordEncoder;

    private final UserRepository userRepository;

    public UserService(PasswordEncoder passwordEncoder, UserRepository userRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    @Transactional
    public User join(String name, Email email, String password){
        checkArgument(isNotEmpty(password), "password must be provided.");
        checkArgument(
            password.length() >= 4 && password.length() <= 15,
                "password length must be between 4 and 15 characters."
        );

        User user = new User(name, email, passwordEncoder.encode(password));
        return userRepository.insert(user);
    }

    @Transactional(readOnly = true)
    public Optional<User> findByEmail(Email email){
        checkArgument(email != null, "email must be provided.");

        return userRepository.findByEmail(email);
    }
}
