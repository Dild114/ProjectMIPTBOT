package app.api.repository;

import app.api.controller.Sites;
import app.api.entity.Site;
import app.api.entity.SiteId;
import app.api.entity.UserId;
import app.api.repository.exception.dbNotFoundException;

import java.util.List;

public class dbSiteRepository implements SiteRepository {
  dbRepository db;
  public dbSiteRepository(dbRepository db) {
    this.db = db;
  }

  @Override
  public SiteId getSiteId(Site site) {
    return db.generateIdSite();
  }

  @Override
  public List<Site> findAllSite(UserId userId) {
    return db.findAllSite(userId);
  }

  @Override
  public void deleteSiteById(SiteId siteId, UserId userId) {
    try {
      db.deleteSiteById(siteId, userId);
    } catch (dbNotFoundException e) {
      throw new dbNotFoundException("Not found site", e);
    }
  }

  @Override
  public void add(SiteId siteId, UserId userId) {
    if (siteId.siteId() == 1) {
      Site site = new Site(new SiteId(1), Sites.SITE1, userId);
      db.addSite(site);
    } else if (siteId.siteId() == 2) {
      Site site = new Site(new SiteId(2), Sites.SITE2, userId);
      db.addSite(site);
    } else if (siteId.siteId() == 3) {
      Site site = new Site(new SiteId(3), Sites.SITE3, userId);
      db.addSite(site);
    } else if (siteId.siteId() == 4) {
      Site site = new Site(new SiteId(4), Sites.SITE4, userId);
      db.addSite(site);
    } else if (siteId.siteId() == 5) {
      Site site = new Site(new SiteId(5), Sites.SITE5, userId);
      db.addSite(site);
    }
  }

}
