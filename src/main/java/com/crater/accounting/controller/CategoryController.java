package com.crater.accounting.controller;

import com.crater.accounting.bean.request.categoryController.AddNewCategoryRequest;
import com.crater.accounting.bean.request.categoryController.UpdateGetCategoryRequest;
import com.crater.accounting.bean.response.Status;
import com.crater.accounting.bean.response.categoryController.*;
import com.crater.accounting.bean.service.categoryService.AddNewCategoryDto;
import com.crater.accounting.bean.service.categoryService.QueryCategoryDto;
import com.crater.accounting.bean.service.categoryService.QueryCategoryResultDto;
import com.crater.accounting.bean.service.categoryService.UpdateCategoryDto;
import com.crater.accounting.exception.GenerateResponseException;
import com.crater.accounting.exception.RequestFormatException;
import com.crater.accounting.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotNull;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@RestController
@Tag(name = "category", description = "主要處理消費類別的 CRUD")
public class CategoryController {
    private final Logger log = LoggerFactory.getLogger(this.getClass());
    private CategoryService categoryService;

    @PostMapping("/categoryController/addNew")
    @Operation(summary = "add new category", description = "insert new category")
    public AddNewCategoryResponse addNewCategory(@RequestBody AddNewCategoryRequest addNewCategoryRequest,
                                                 @Schema(description = "authorization id, 將來會使用 Authorization 取代")
                                                 @NotNull @RequestHeader(name = "Authorization") String authorization) {
        try {
            validateRequest(addNewCategoryRequest);
            var addNewCategoryDto = generateAddNewCategoryDto(addNewCategoryRequest, authorization);
            categoryService.addCategory(addNewCategoryDto);
            return new AddNewCategoryResponse(Status.generateSuccessStatus());
        } catch (Exception e) {
            log.error(ExceptionUtils.getStackTrace(e));
            throw e;
        }
    }

    private void validateRequest(AddNewCategoryRequest addNewCategoryRequest) {
        var errorMessage = new ArrayList<>();
        if (addNewCategoryRequest == null) {
            throw new RequestFormatException("addNewCategoryRequest is cant be null");
        }
        if (StringUtils.isBlank(addNewCategoryRequest.name())) {
            errorMessage.add("name cant be blank!");
        }
        if (addNewCategoryRequest.isActive() == null) {
            errorMessage.add("active cant be null");
        }
        if (!errorMessage.isEmpty()) {
            throw new RequestFormatException(StringUtils.join(errorMessage, "\n"));
        }
    }

    private AddNewCategoryDto generateAddNewCategoryDto(AddNewCategoryRequest addNewCategoryRequest, String authorization) {
        return new AddNewCategoryDto(addNewCategoryRequest.name(), authorization, addNewCategoryRequest.isForSaving(),
                addNewCategoryRequest.isActive());
    }

    @Operation(summary = "update category", description = "update category data")
    @PutMapping("/categoryController/category")
    public UpdateCategoryResponse updateCategory(@RequestBody UpdateGetCategoryRequest updateGetCategoryRequest,
                                                 @Schema(description = "authorization id, 將來會使用 Authorization 取代")
                                                 @NotNull @RequestHeader("Authorization") String authorization) {
        try {
            validateRequest(updateGetCategoryRequest);
            var updateCatalogDto = generateUpdateCatalogDto(updateGetCategoryRequest, authorization);
            categoryService.updateCategory(updateCatalogDto);
            return new UpdateCategoryResponse(Status.generateSuccessStatus());
        } catch (Exception e) {
            log.error(ExceptionUtils.getStackTrace(e));
            throw e;
        }
    }

    private void validateRequest(UpdateGetCategoryRequest updateGetCategoryRequest) {
        if (updateGetCategoryRequest == null) {
            throw new RequestFormatException("updateGetCategoryRequest is cant be null");
        }
        var errorMessage = new ArrayList<>();
        if (updateGetCategoryRequest.serialNo() == null) {
            errorMessage.add("serialNo cant be null!");
        }
        if (!errorMessage.isEmpty()) {
            throw new RequestFormatException(StringUtils.join(errorMessage, "\n"));
        }
    }

    private UpdateCategoryDto generateUpdateCatalogDto(UpdateGetCategoryRequest updateGetCategoryRequest, String authorization) {
        return new UpdateCategoryDto(updateGetCategoryRequest.serialNo(), updateGetCategoryRequest.name(),
                updateGetCategoryRequest.isForSaving(), authorization, updateGetCategoryRequest.isActive());
    }

    @Operation(summary = "get category 目錄", description = "取得消費類型目錄")
    @GetMapping("/categoryController/categoryIndex")
    public GetCategoryIndexResponse getCategoryIndex(@RequestHeader("Authorization") String authorization) {
        try {
            var queryCategoryDto = new QueryCategoryDto(null, null, authorization);
            var queryCategoryResultDto = categoryService.queryCategoriesIndex(queryCategoryDto);
            return generateGetCategoryIndexResponse(queryCategoryResultDto);
        } catch (Exception e) {
            log.error(ExceptionUtils.getStackTrace(e));
            throw e;
        }
    }

    private GetCategoryIndexResponse generateGetCategoryIndexResponse(List<QueryCategoryResultDto> queryCategoryResultDto) {
        try {
            var categoryIndexData = queryCategoryResultDto.stream()
                    .map(resultDto -> {
                        var updateTime = (resultDto.updateTime() == null ? resultDto.createTime() :
                                resultDto.updateTime()).format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
                        return new CategoryIndexData(resultDto.serialNo(), resultDto.name(), updateTime);
                    })
                    .toList();
            return new GetCategoryIndexResponse(Status.generateSuccessStatus(), categoryIndexData);
        } catch (Exception e) {
            throw new GenerateResponseException("generate get category index response error", e);
        }
    }

    @Operation(summary = "取得消費類別細節", description = "取得消費類別細節")
    @GetMapping("/categoryController/category")
    public GetCategoryResponse getCategoryBySerialNo(@RequestParam("serialNo") String serialNo,
                                                     @RequestHeader("Authorization") String authorization) {
        try {
            validateGetCategoryBySerialNo(serialNo);
            var queryCategoryResultDto = categoryService.queryCategory(Integer.parseInt(serialNo));
            return generateGetCategoryResponse(queryCategoryResultDto);
        } catch (Exception e) {
            log.error(ExceptionUtils.getStackTrace(e));
            throw e;
        }
    }

    private void validateGetCategoryBySerialNo(String serialNo) {
        var errorMessage = new ArrayList<>();
        if (StringUtils.isBlank(serialNo)) {
            errorMessage.add("serialNo cant be blank!");
        }
        if (!errorMessage.isEmpty()) {
            throw new RequestFormatException(StringUtils.join(errorMessage, "\n"));
        }
    }

    private GetCategoryResponse generateGetCategoryResponse(QueryCategoryResultDto queryCategoryResultDto) {
        try {
            var formatedUpdateTime = queryCategoryResultDto.updateTime() == null ? null :
                    queryCategoryResultDto.updateTime().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
            return new GetCategoryResponse(Status.generateSuccessStatus(),
                    new CategoryData(queryCategoryResultDto.serialNo(), queryCategoryResultDto.name(),
                            queryCategoryResultDto.createTime().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME),
                            queryCategoryResultDto.createUser(), formatedUpdateTime, queryCategoryResultDto.updateUser(),
                            queryCategoryResultDto.isForSaving(), queryCategoryResultDto.isActive()));
        } catch (Exception e) {
            throw new GenerateResponseException("generate get category response error", e);
        }
    }

    @Operation(summary = "delete category", description = "delete category")
    @DeleteMapping("/categoryController/category")
    public DeleteCategoryResponse deleteCategory(@RequestParam("serialNo") Integer serialNo) {
        try {
            if (serialNo == null) {
                throw new RequestFormatException("serialNo cant be null!");
            }
            categoryService.deleteCategory(serialNo);
            return new DeleteCategoryResponse(Status.generateSuccessStatus());
        } catch (Exception e) {
            log.error(ExceptionUtils.getStackTrace(e));
            throw e;
        }
    }

    @Autowired
    public void setCategoryService(CategoryService categoryService) {
        this.categoryService = categoryService;
    }
}
