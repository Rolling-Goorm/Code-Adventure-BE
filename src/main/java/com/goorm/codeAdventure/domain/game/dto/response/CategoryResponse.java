package com.goorm.codeAdventure.domain.game.dto.response;

import com.goorm.codeAdventure.domain.game.entity.Category;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryResponse {
    private Long id;

    private String name;

//    private Double progress;

    public static CategoryResponse of(Category category) {
        CategoryResponse categoryResponse = new CategoryResponse();

        categoryResponse.id = category.getId();
        categoryResponse.name = category.getName();

        return categoryResponse;
    }
}
