package com.ecommerce.project.controller;

import com.ecommerce.project.model.Category;
import com.ecommerce.project.service.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;

@RestController
@RequestMapping("api/")
public class CategoryController {

    private CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    /*
    @GetMapping("api/public/categories")
public List<Category> getAllCategories() {
    return  categoryService.getAllCategories();
}

*/
    @GetMapping("public/categories")
    //@RequestMapping(value ="public/categories" , method = RequestMethod.GET )
    public ResponseEntity<List<Category>> getAllCategories() {
        return  new ResponseEntity<>(categoryService.getAllCategories() ,HttpStatus.OK);
    }


/*
@PostMapping("api/public/categories")
public String createCategory(@RequestBody Category category){
categoryService.createCategory(category);
    return "categories added successfully";
}
*/
@PostMapping("public/categories")
//@RequestMapping(value ="public/categories" , method = RequestMethod.POST )
public ResponseEntity<String> createCategory(@RequestBody Category category){
    categoryService.createCategory(category);
    return new ResponseEntity<>("categories added successfully" ,HttpStatus.CREATED);
}



/*
@DeleteMapping("api/admin/categories/{categoryId}")
public String deleteCategory(@PathVariable  Long categoryId){
String status=categoryService.deleteCategory(categoryId);
return status;
}
*/
@DeleteMapping("admin/categories/{categoryId}")
//@RequestMapping(value ="admin/categories/{categoryId}" , method = RequestMethod.DELETE )

public ResponseEntity<String> deleteCategory(@PathVariable  Long categoryId) {
    try {
        String status = categoryService.deleteCategory(categoryId);
        return new ResponseEntity<>(status, HttpStatus.OK);
    }catch (ResponseStatusException e){
        return new ResponseEntity<>(e.getReason(),e.getStatusCode());
    }
}

@PutMapping("public/categories")
//@RequestMapping(value ="public/categories" , method = RequestMethod.PUT )
public ResponseEntity<String> updateCategory(@RequestBody Category category) {
    try {
        String status = categoryService.updateCategory(category);
        return new ResponseEntity<>(status, HttpStatus.OK);
    } catch (ResponseStatusException e) {
        return new ResponseEntity<>(e.getReason(), e.getStatusCode());
    }
}






}
