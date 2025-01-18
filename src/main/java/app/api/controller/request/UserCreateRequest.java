package app.api.controller.request;

import app.api.entity.UserId;

public record UserCreateRequest(String name, String password) {}
