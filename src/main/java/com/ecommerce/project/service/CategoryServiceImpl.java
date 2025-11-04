package com.ecommerce.project.service;

import com.ecommerce.project.model.Category;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;


@Service
public class CategoryServiceImpl implements CategoryService {

    private List<Category> categories = new ArrayList<>();

    //If you want to add a autoincrement id
    //private Long nextId=1;


    @Override
    public List<Category> getAllCategories() {
        return categories;
    }
 /*
//If you want to add a autoincrement id
    @Override
    public void createCategory(Category category) {
        category.setCategoryId(nextId++);
        categories.add(category);

    }
  */

    @Override
    public void createCategory(Category category) {
        categories.add(category);

    }

    /*

//This is the normal case of Delete Mapping
    @Override
    public String deleteCategory(Long categoryId) {
       Category category=categories.stream()
                .filter(c -> c.getCategoryId().equals(categoryId))
                .findFirst().get();
         categories.remove(category);
        return "Category with category Id:--> "+categoryId+" deleted successfully";
    }
    */

    // Let suppose category you want to delete is not present then we can also handle it through .orElse(null)----

    /*
    @Override
    public String deleteCategory(Long categoryId) {
        Category category=categories.stream()
                .filter(c -> c.getCategoryId().equals(categoryId))
                .findFirst().orElse(null);

        if(category==null){
            return "Category Not Found";
        }
        else {
            categories.remove(category);
            return "Category with category Id:--> " + categoryId + " deleted successfully";
        }
        }
*/
    // Let suppose category you do not want to use this if  else condition you can also through the exception here---



    @Override
    public String deleteCategory(Long categoryId) {
        Category category = categories.stream()
                .filter(c -> c.getCategoryId().equals(categoryId))
                .findFirst().orElseThrow(() ->new ResponseStatusException(HttpStatus.NOT_FOUND ,"Category not found"));
        categories.remove(category);
        return "Category with category Id:--> " + categoryId + " deleted successfully";

    }

    @Override
    public String updateCategory(Category category) {
        Category categoryUpdate = categories.stream()
                .filter(c -> c.getCategoryId().equals(category.getCategoryId()))
                .findFirst().orElseThrow(() ->new ResponseStatusException(HttpStatus.NOT_FOUND ,"Category which you want to update does not exist"));
categoryUpdate.setCategoryName(category.getCategoryName());
    return "Category with category Id:--> " + categoryUpdate.getCategoryId() + " updated successfully";
    }

}





