package app.api.entity;

import java.util.List;

public record User (
  // пока что telegramId и email не нужен и мы его не будет использовать
  UserId telegramId,
  String userName,
  String password,
  String email,
  List<Category> categories,
  List<Site> sites
  ) {}
