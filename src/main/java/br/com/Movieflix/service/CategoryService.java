package br.com.Movieflix.service;

import br.com.Movieflix.entity.Category;
import br.com.Movieflix.mapper.CategoryMapper;
import br.com.Movieflix.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    public Category saveCategory(Category category) {
        return categoryRepository.save(category);
    }

    public Optional<Category> findByID (Long id) {
        return categoryRepository.findById(id);
    }

    public Optional<Category> update(Long id, Category updatedCategory) {
        Optional<Category> newCategory = categoryRepository.findById(id);
        if (newCategory.isPresent()){
            Category category = newCategory.get();
            category.setName(updatedCategory.getName());

            categoryRepository.save(category);
            return Optional.of(category);
        }
        return Optional.empty();
    }

    public void deletarCategory(Long id) {
         categoryRepository.deleteById(id);
    }
}
