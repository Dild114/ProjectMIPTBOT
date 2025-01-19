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
        String sql = "SELECT nextval('user_id_seq')";
        try (PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            if (resultSet.next()) {
                return new UserId(resultSet.getInt(1));
            } else {
                throw new UserCreateException("Failed to generate user ID");
            }
        } catch (SQLException e) {
            throw new UserCreateException("Failed to generate user ID", e);
        }
    }

    @Override
    public void createAccount(User user) {
        String sql = "INSERT INTO users (id, username, password, email) VALUES (?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, user.telegramId().id());
            statement.setString(2, user.userName());
            statement.setString(3, user.password());
            statement.setString(4, user.email() != null ? user.email() : ""); 
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new UserCreateException("Failed to create user", e);
        }
    }

    @Override
    public void deleteAccount(UserId userId) {
        String checkSql = "SELECT 1 FROM users WHERE id = ?";
        String deleteSql = "DELETE FROM users WHERE id = ?";
        try (PreparedStatement checkStatement = connection.prepareStatement(checkSql);
             PreparedStatement deleteStatement = connection.prepareStatement(deleteSql)) {
            checkStatement.setInt(1, userId.id());
            try (ResultSet resultSet = checkStatement.executeQuery()) {
                if (!resultSet.next()) {
                    throw new UserNotFoundException("User with id " + userId + " not found");
                }
            }
            deleteStatement.setInt(1, userId.id());
            int affectedRows = deleteStatement.executeUpdate();
            if (affectedRows == 0) {
                throw new UserNotFoundException("User with id " + userId + " not found");
            }
        } catch (SQLException e) {
            throw new UserNotFoundException("Failed to delete user", e);
        }
    }
}
