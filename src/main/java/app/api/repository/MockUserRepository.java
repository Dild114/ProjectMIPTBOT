package app.api.repository;

import app.api.entity.User;
import app.api.entity.UserId;
import app.api.service.exception.UserCreateException;
import app.api.service.exception.UserNotFoundException;

import java.util.HashMap;
import java.util.Map;

public class MockUserRepository implements UserRepository {
    private final Map<UserId, User> userMap = new HashMap<>();
    private int idCounter = 1;

    @Override
    public UserId generateId() {
        return new UserId(idCounter++);
    }

    @Override
    public void createAccount(User user) {
        if (userMap.containsKey(user.telegramId())) {
            throw new UserCreateException("User with id " + user.telegramId() + " already exists");
        }
        userMap.put(user.telegramId(), user);
    }

    @Override
    public void deleteAccount(UserId userId) {
        if (!userMap.containsKey(userId)) {
            throw new UserNotFoundException("User with id " + userId + " not found");
        }
        userMap.remove(userId);
    }
}
