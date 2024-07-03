package org.eu.luolei.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.groups.Default;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Category {
    @NotNull(groups = Update.class)
    private Integer id;

    @NotEmpty
    private String categoryName;

    @NotEmpty
    private String categoryAlias;

    private Integer createUser;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;

    // 1. 如果校验项没有指定分组，则默认属于Default
    // 2. 分组之间可以继承，A extends B，那么A中有B的所有校验项

    public interface Update extends Default {
    }

    public interface Add extends Default {
    }
}
