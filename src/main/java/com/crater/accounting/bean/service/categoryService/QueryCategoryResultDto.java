package com.crater.accounting.bean.service.categoryService;

import java.time.LocalDateTime;

public record QueryCategoryResultDto(Integer serialNo, String name, LocalDateTime createTime,
                                     String createUser, LocalDateTime updateTime, String updateUser,
                                     Boolean isForSaving, Boolean isActive) {
}
