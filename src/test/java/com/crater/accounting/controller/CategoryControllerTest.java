package com.crater.accounting.controller;

import com.crater.accounting.bean.request.categoryController.AddNewCategoryRequest;
import com.crater.accounting.bean.request.categoryController.UpdateGetCategoryRequest;
import com.crater.accounting.bean.service.categoryService.AddNewCategoryDto;
import com.crater.accounting.bean.service.categoryService.QueryCategoryResultDto;
import com.crater.accounting.bean.service.categoryService.UpdateCategoryDto;
import com.crater.accounting.exception.RequestFormatException;
import com.crater.accounting.service.CategoryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CategoryControllerTest {

    private CategoryController testTarget;
    private CategoryService categoryService;

    @BeforeEach
    public void beforeEach() {
        categoryService = Mockito.mock(CategoryService.class);
        testTarget = new CategoryController();
        testTarget.setCategoryService(categoryService);
    }

    @Test
    public void addCategory_allInputNull_throwRequestFormatException() {
        var exception = assertThrows(RequestFormatException.class, () ->
                testTarget.addNewCategory(null, null));
        assertEquals("addNewCategoryRequest is cant be null", exception.getMessage());
    }

    @Test
    public void addCategory_requestAllNull_throwRequestFormatException() {
        var exception = assertThrows(RequestFormatException.class, () ->
                testTarget.addNewCategory(new AddNewCategoryRequest(null, null, null),
                        null));
        assertEquals("""
                name cant be blank!
                active cant be null""", exception.getMessage());
    }

    @Test
    public void addCategory_success() {
        var addNewCategoryRequest = new AddNewCategoryRequest("test", false, true);
        Mockito.doNothing().doAnswer(o -> {
            var dto = o.getArgument(0, AddNewCategoryDto.class);
            assertEquals("test", dto.name());
            assertFalse(dto.isForSaving());
            assertTrue(dto.isActive());
            assertEquals("test", dto.authorization());
            return null;
        }).when(categoryService).addCategory(Mockito.any());
        testTarget.addNewCategory(addNewCategoryRequest, "test");
    }

    @Test
    public void updateCategory_allInputNull_throwRequestFormatException() {
        var exception = assertThrows(RequestFormatException.class, () ->
                testTarget.updateCategory(null, null));
        assertEquals("updateGetCategoryRequest is cant be null", exception.getMessage());
    }

    @Test
    public void updateCategory_requestAllNull_throwRequestFormatException() {
        var exception = assertThrows(RequestFormatException.class, () ->
                testTarget.updateCategory(new UpdateGetCategoryRequest(null, null, null, null),
                        null));
        assertEquals("""
                serialNo cant be null!""", exception.getMessage());
    }

    @Test
    public void updateCategory_success() {
        var updateGetCategoryRequest = new UpdateGetCategoryRequest(1, "test", false, true);
        Mockito.doNothing().doAnswer(o -> {
            var dto = o.getArgument(0, UpdateCategoryDto.class);
            assertEquals(1, dto.serialNo());
            assertEquals("test", dto.name());
            assertFalse(dto.isForSaving());
            assertTrue(dto.isActive());
            assertEquals("test", dto.authorization());
            return null;
        }).when(categoryService).updateCategory(Mockito.any());
        testTarget.updateCategory(updateGetCategoryRequest, "test");
    }

    @Test
    public void getCategoryBySerialNo_allInputNull_throwRequestFormatException() {
        var exception = assertThrows(RequestFormatException.class, () ->
                testTarget.getCategoryBySerialNo(null, null));
        assertEquals("""
                serialNo cant be blank!""", exception.getMessage());
    }

    @Test
    public void getCategoryBySerialNo_success() {
        Mockito.when(categoryService.queryCategory(Mockito.anyInt()))
                .thenReturn(new QueryCategoryResultDto(1, "test", LocalDateTime.now(),
                        "user", LocalDateTime.now(), "user", false, true));
        var response = testTarget.getCategoryBySerialNo("1", "test");
        assertEquals(1, response.categoryData().serialNo());
        assertEquals("test", response.categoryData().name());
        assertFalse(response.categoryData().isForSaving());
        assertTrue(response.categoryData().isActive());
    }

    @Test
    public void getCategoryIndex_success() {
        Mockito.when(categoryService.queryCategoriesIndex(Mockito.any()))
                .thenReturn(List.of(new QueryCategoryResultDto(1, "test1", LocalDateTime.now(),
                                "user1", LocalDateTime.now(), "user1", false, true),
                        new QueryCategoryResultDto(2, "test2", LocalDateTime.now(),
                                "user2", LocalDateTime.now(), "user2", true, false)));
        var response = testTarget.getCategoryIndex("test");
        assertEquals(2, response.categoryIndexData().size());
        assertEquals(1, response.categoryIndexData().get(0).serialNo());
        assertEquals("test1", response.categoryIndexData().get(0).name());
        assertEquals(2, response.categoryIndexData().get(1).serialNo());
        assertEquals("test2", response.categoryIndexData().get(1).name());
    }

    @Test
    public void deleteCategory_serialNoNull_throwRequestFormatException() {
        var exception = assertThrows(RequestFormatException.class, () ->
                testTarget.deleteCategory(null));
        assertEquals("serialNo cant be null!", exception.getMessage());
    }

    @Test
    public void deleteCategory_success() {
        Mockito.doNothing().doAnswer(o -> {
            int serialNo = o.getArgument(0, int.class);
            assertEquals(1, serialNo);
            return null;
        }).when(categoryService).deleteCategory(Mockito.anyInt());
        testTarget.deleteCategory(1);
    }
}
