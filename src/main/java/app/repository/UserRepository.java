package app.repository;

import app.dto.User;

public interface UserRepository {
    void save(User user);
    User findByUsername(String username);
}
