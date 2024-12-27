package pl.javastart.foundones.domain.api;

import pl.javastart.foundones.domain.category.Category;
import pl.javastart.foundones.domain.category.CategoryDao;

import java.util.List;
import java.util.Optional;

public class CategoryService {
    private CategoryDao categoryDao = new CategoryDao();

    public List<CategoryName> findAllCategoryName() {
        return categoryDao
                .findAll().stream()
                .map(CategoryNameMapper::map)
                .toList();
    }

    public Optional<CategoryFullInfo> findById(int categoryId) {
        return categoryDao.findById(categoryId)
                .map(CategoryFullInfoMapper::map);
    }

    private static class CategoryFullInfoMapper{
        static CategoryFullInfo map(Category category){
            return new CategoryFullInfo(category.getId(),category.getName(),category.getDescription());
        }
    }

    private static class CategoryNameMapper{
        static CategoryName map(Category category){
            return new CategoryName(category.getId(),category.getName());
        }
    }
}
