package br.com.Movieflix.mapper;

import br.com.Movieflix.controller.request.CategoryRequest;
import br.com.Movieflix.controller.response.CategoryResponse;
import br.com.Movieflix.entity.Category;
import lombok.experimental.UtilityClass;

@UtilityClass
public class CategoryMapper {
     public static  Category toCategory (CategoryRequest categoryRequest) {
         return Category.builder()
                 .name(categoryRequest.name())
                 .build();
     }

     public  static CategoryResponse toCategoryResponse(Category category) {
           return CategoryResponse.builder()
                   .name(category.getName())
                   .id(category.getId())
                   .build();
     }
}
