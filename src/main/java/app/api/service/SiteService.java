package app.api.service;

import app.api.entity.Site;
import app.api.entity.SiteId;
import app.api.entity.UserId;
import app.api.repository.SiteRepository;

import java.util.List;

public class SiteService {
  private SiteRepository siteRepository;
  public SiteService(SiteRepository siteRepository) {
    this.siteRepository = siteRepository;
  }

  public List<Site> getSites() {
    return siteRepository.findAllSite();
  }
  public void deleteSite(SiteId siteId, UserId userId) {
    siteRepository.deleteSiteById(siteId, userId);
  }
  public void addSite(SiteId siteId, UserId userId) {
    siteRepository.add(siteId, userId);
  }


}
