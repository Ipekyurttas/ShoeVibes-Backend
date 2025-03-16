package com.ShoeVibes.service;

import com.ShoeVibes.dto.CategoryDto;
import com.ShoeVibes.repository.CategoryRepository;
import com.ShoeVibes.entity.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> listCategory(){
        return categoryRepository.findAll();
    }

    public Category addCategory(CategoryDto categoryDto) {
        Category category= new Category();
        category.setName(categoryDto.getName());
        category.setDescription(categoryDto.getDescription());
        return categoryRepository.save(category);
    }

    public Category updateCategory(Long id,CategoryDto categoryDto) {
        Category category = categoryRepository.findById(id).orElseThrow(() -> new RuntimeException("Category not found"));
        category.setName(categoryDto.getName());
        category.setDescription(categoryDto.getDescription());
        return categoryRepository.save(category);
    }

    public void deleteCategory(Long id){
        Optional<Category> category = categoryRepository.findById(id);
        if(category.isPresent())
            categoryRepository.deleteById(id);
        else
            throw new RuntimeException("Category not found");
    }
}
