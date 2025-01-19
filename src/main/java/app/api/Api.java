package app.api;

import app.dto.User;
import app.repository.UserRepository;

public class Api {
    private final UserRepository userRepository;

    public Api(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void registerUser(User user) {
        userRepository.save(user);
    }
}
