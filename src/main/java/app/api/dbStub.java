package app.api;

import app.api.entity.*;
import app.api.repository.dbRepository;

import java.util.ArrayList;
import java.util.List;

public class dbStub implements dbRepository {
  private List<Article> articles = new ArrayList<>();
  private List<Category> categories = new ArrayList<>();
  private final List<Site> sites = new ArrayList<>();
  private final List<User> users = new ArrayList<>();
  private int articleIdCounter = 1;
  private int categoryIdCounter = 1;
  private int siteIdCounter = 1;
  private int userIdCounter = 1;

  @Override
  public ArticleId generateIdArticle() {
    return new ArticleId(articleIdCounter++);
  }

  @Override
  public List<Article> getArticles() {
    // наверное тут буду делать запрос на парсер и мл а потом мы добавляем артиклы
    return new ArrayList<>(articles);
  }

  @Override
  public CategoryId generateIdCategory() {
    return new CategoryId(categoryIdCounter++);
  }

  @Override
  public List<Category> findAllCategory() {
    return new ArrayList<>(categories);
  }

  @Override
  public Category findCategoryById(CategoryId id) {
    return categories.stream()
        .filter(category -> category.id().equals(id))
        .findFirst()
        .orElse(null);
  }

  @Override
  public boolean addCategory(Category category) {
    return categories.add(category);
  }

  @Override
  public boolean deleteCategory(CategoryId id) {
    return categories.removeIf(category -> category.id().equals(id));
  }

  @Override
  public SiteId generateIdSite() {
    return new SiteId(siteIdCounter++);
  }

  @Override
  public void deleteSiteById(SiteId id, UserId userId) {
    for (Site site : sites) {
      if (site.userId().equals(userId)) {
        sites.remove(site);
      }
    }
  }

  @Override
  public void addSite(Site site) {
    sites.add(site);
  }

  @Override
  public List<Site> findAllSite() {
    return new ArrayList<>(sites);
  }

  @Override
  public Site findSiteById(SiteId id) {
    return sites.stream()
        .filter(site -> site.id().equals(id))
        .findFirst()
        .orElse(null);
  }

  @Override
  public UserId generateUserId() {
    return new UserId(userIdCounter++);
  }

  @Override
  public boolean createUser (User user) {
    return users.add(user);
  }

  @Override
  public boolean deleteUser (UserId userId) {
    for (Category category : categories) {
      if (category.userId().equals(userId)) {
        categories.remove(category);
      }
    }
    for (Site site : sites) {
      if (site.userId().equals(userId)) {
        sites.remove(site);
      }
    }
    return users.removeIf(user -> user.telegramId().equals(userId));
  }
}