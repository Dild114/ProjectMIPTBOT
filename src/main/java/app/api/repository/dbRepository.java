package app.api.repository;


import ai.onnxruntime.OrtException;
import app.api.entity.Article;
import app.api.entity.ArticleId;
import app.api.entity.Category;
import app.api.entity.CategoryId;
import app.api.entity.Site;
import app.api.entity.SiteId;
import app.api.entity.User;
import app.api.entity.UserId;

import java.io.IOException;
import java.util.Map;
import java.util.List;

public interface dbRepository {
  ArticleId generateIdArticle();
  Map<Article, Category> getArticles(UserId userId) throws IOException, OrtException;
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
