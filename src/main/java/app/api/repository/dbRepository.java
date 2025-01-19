package app.api.repository;

import app.api.entity.*;

import java.util.List;

public interface dbRepository {
  ArticleId generateIdArticle();
  List<Article> getArticles(UserId userId);
  CategoryId generateIdCategory();
  List<Category> findAllCategory(UserId userId);
  Category findCategoryById(CategoryId id);
  boolean addCategory(Category category);
  boolean deleteCategory(CategoryId id, UserId userId);

  SiteId generateIdSite();
  void deleteSiteById(SiteId id, UserId userId);
  void addSite(Site site);
  List<Site> findAllSite(UserId userId);
  Site findSiteById(SiteId id);

  UserId generateUserId();
  boolean createUser(User user);
  boolean deleteUser(UserId userId);




}
