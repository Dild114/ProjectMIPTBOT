package app.repository;

import app.dto.Category;

public interface CategoryRepository {
    void save(Category category);
    Category findById(int id);
    Category findByName(String name);
}
