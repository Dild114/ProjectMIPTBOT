package app.api.repository;

import app.api.entity.Site;
import app.api.entity.SiteId;
import app.api.entity.UserId;

import java.util.List;

public interface SiteRepository {
  SiteId getSiteId(Site site);
  List<Site> findAllSite();
  void deleteSiteById(SiteId id, UserId userId);
  void add(SiteId siteId, UserId userId);
}
