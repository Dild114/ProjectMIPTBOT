package app.api;

import app.api.entity.Article;
import app.api.entity.ArticleId;
import app.api.entity.Category;
import app.api.entity.CategoryId;
import app.api.entity.Site;
import app.api.entity.SiteId;
import app.api.entity.User;
import app.api.entity.UserId;
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
  public List<Article> getArticles(UserId userId) {

    // наверное тут буду делать запрос на парсер и мл а потом мы добавляем артиклы
    return new ArrayList<>(articles);
  }

  @Override
  public CategoryId generateIdCategory() {
    return new CategoryId(categoryIdCounter++);
  }

  @Override
  public List<Category> findAllCategory(UserId userId) {
    List<Category> answerCategory = new ArrayList<>();
    for (Category category : categories) {
      if (category.userId().equals(userId)) {
        answerCategory.add(category);
      }
    }
    return answerCategory;
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
  public boolean deleteCategory(CategoryId id, UserId userId) {
    for (Category category : categories) {
      if (category.userId().equals(userId)) {
        categories.remove(category);
        return true;
      }
    }
    return false;
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
  public List<Site> findAllSite(UserId userId) {
    List<Site> answerSites = new ArrayList<>();
    for (Site site : sites) {
      if (site.userId().equals(userId)) {
        answerSites.add(site);
      }
    }
    return answerSites;
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