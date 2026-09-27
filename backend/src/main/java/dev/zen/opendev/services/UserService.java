package dev.zen.opendev.services;

import java.util.UUID;

import org.springframework.security.crypto.encrypt.TextEncryptor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dev.zen.opendev.models.User;
import dev.zen.opendev.repositories.UserRepository;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final TextEncryptor encryptor;

    public UserService(UserRepository userRepository, TextEncryptor encryptor) {
        this.userRepository = userRepository;
        this.encryptor = encryptor;
    }

    @Transactional(readOnly = true)
    public User fetchUserById(UUID id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User Not found"));
    }

    public String decryptAcessToken(User user) {
        return encryptor.decrypt(user.getAccessToken());
    }
}
