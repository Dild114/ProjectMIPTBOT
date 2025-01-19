package app.api.repository;

import app.api.entity.User;
import app.api.entity.UserId;
import app.api.service.exception.UserCreateException;

public class dbUserRepository implements UserRepository {
  dbRepository db;
  public dbUserRepository(dbRepository db) {
    this.db = db;
  }


  @Override
  public UserId generateId() {
    return db.generateUserId();
  }

  @Override
  public void createAccount(User user) {
    boolean flag = db.createUser(user);
    if (!flag) {
      throw new UserCreateException("User already exist");
    }
  }


  @Override
  public void deleteAccount(UserId userId) {
    boolean flag = db.deleteUser(userId);
    if (!flag) {
      throw new UserCreateException("User does not exist");
    }
  }
}
