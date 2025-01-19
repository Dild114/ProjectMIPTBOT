package app.repository;

import app.dto.User;
import java.util.HashMap;
import java.util.Map;

public class MockUserRepository implements UserRepository {
    private final Map<String, User> users = new HashMap<>();

    @Override
    public void save(User user) {
        users.put(user.getUsername(), user);
    }

    @Override
    public User findByUsername(String username) {
        return users.get(username);
    }
}
