package com.growbiz.backend.Admin.helper;

import com.growbiz.backend.Categories.models.Category;
import com.growbiz.backend.Categories.models.SubCategory;
import com.growbiz.backend.RequestResponse.Category.CategoryResponse;
import com.growbiz.backend.User.models.User;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AdminControllerHelper {

    public ResponseEntity<CategoryResponse> createCategoryResponse(List<Category> categoriesList) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return ResponseEntity.ok(CategoryResponse.builder()
                .categories(categoriesList)
                .isSubCategory(false)
                .subject(user.getEmail())
                .role(user.getRole())
                .build());
    }

    public ResponseEntity<CategoryResponse> createSubCategoryResponse(List<SubCategory> subCategoriesList) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return ResponseEntity.ok(CategoryResponse.builder()
                .subCategories(subCategoriesList)
                .isSubCategory(true)
                .subject(user.getEmail())
                .role(user.getRole())
                .build());
    }

    public ResponseEntity<CategoryResponse> deleteCategoryResponse(Boolean isSubCategory, Boolean deleted) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return ResponseEntity.ok(CategoryResponse.builder()
                .isDeleted(deleted)
                .isSubCategory(isSubCategory)
                .subject(user.getEmail())
                .role(user.getRole())
                .build());
    }
}
