package com.sofkau.categoryNotesReactive.mapper;

import com.sofkau.categoryNotesReactive.collection.Category;
import com.sofkau.categoryNotesReactive.model.CategoryDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class CategoryMapper {

    @Autowired
    private ModelMapper modelMapper;

    public Function<Category, CategoryDto> convertEntityToDto(){
        return category -> modelMapper.map(category, CategoryDto.class);
    }

    public Function<CategoryDto, Category> convertDtoToEntity(){
        return categoryDto -> modelMapper.map(categoryDto, Category.class);
    }
}
