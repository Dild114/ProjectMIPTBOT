package app.api.repository;

import app.api.entity.User;
import app.api.entity.UserId;
import app.api.service.exception.UserCreateException;
import app.api.service.exception.UserNotFoundException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRepositoryImpl implements UserRepository {
    private final Connection connection;

    public UserRepositoryImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public UserId generateId() {
        return null;
    }

    @Override
    public void createAccount(User user) {
        String sql = "INSERT INTO users (id, name, password) VALUES (?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, user.telegramId().id());
            statement.setString(2, user.userName());
            statement.setString(3, user.password());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new UserCreateException("Failed to create user", e);
        }
    }

    @Override
    public void deleteAccount(UserId userId) {
        String sql = "DELETE FROM users WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, userId.id());
            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                throw new UserNotFoundException("User with id " + userId + " not found");
            }
        } catch (SQLException e) {
            throw new UserNotFoundException("Failed to delete user", e);
        }
    }
}
