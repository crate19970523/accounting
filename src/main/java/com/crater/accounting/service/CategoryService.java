package com.crater.accounting.service;

import com.crater.accounting.bean.service.categoryService.AddNewCategoryDto;
import com.crater.accounting.bean.service.categoryService.QueryCategoryDto;
import com.crater.accounting.bean.service.categoryService.QueryCategoryResultDto;
import com.crater.accounting.bean.service.categoryService.UpdateCategoryDto;

import java.util.List;

public interface CategoryService {
    void addCategory(AddNewCategoryDto addNewCategoryDto);

    void deleteCategory(int serialNo);

    void updateCategory(UpdateCategoryDto updateCategoryDto);

    QueryCategoryResultDto queryCategory(int serialNo);

    List<QueryCategoryResultDto> queryCategoriesIndex(QueryCategoryDto queryCategoryResultDto);
}
