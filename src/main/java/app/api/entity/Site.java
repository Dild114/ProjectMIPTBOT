package app.api.entity;

import app.api.controller.Sites;

public record Site(SiteId id, Sites url, UserId userId) {}
