package com.auction.api.configure;

import com.auction.api.util.PasswordEncoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.security.NoSuchAlgorithmException;

@Configuration
public class WebConfigure {

    @Bean
    public PasswordEncoder passwordEncoder() throws NoSuchAlgorithmException {
        return new PasswordEncoder("SHA-256");
    }
}
