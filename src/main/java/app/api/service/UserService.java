package app.api.service;

import app.api.entity.User;
import app.api.entity.UserId;
import app.api.repository.UserRepository;
import app.api.service.exception.UserCreateException;
import app.api.service.exception.UserDeleteByIdException;
import app.api.service.exception.UserDuplicateException;
import app.api.service.exception.UserNotFoundException;

public class UserService {
  private UserRepository userRepository;

  public UserService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public UserId createUser(String userName, String password) {
    UserId id = userRepository.generateId();
    User user = new User(id, userName, password, null, null, null);
    try {
      userRepository.createAccount(user);
      return id;
    } catch (UserDuplicateException e) {
      throw new UserCreateException("User with name " + user.userName() + " already exists", e);
    }
  }

  public void deleteUser(UserId userId) {
    try {
      userRepository.deleteAccount(userId);
    } catch (UserNotFoundException e) {
      throw new UserDeleteByIdException("User with id " + userId + " not found", e);
    }
  }
}