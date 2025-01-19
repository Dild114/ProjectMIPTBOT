package app.repository;

import app.dto.Site;

public interface SiteRepository {
    void save(Site site);
    Site findByUrl(String url);
}
