package org.eu.luolei.anno;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import org.eu.luolei.validation.StateValidation;

import java.lang.annotation.*;

@Documented
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(
        validatedBy = {StateValidation.class}
)
public @interface State {
    // 提供校验失败的信息
    String message() default "state参数的值只能是已发布或者草稿";

    // 指定分组
    Class<?>[] groups() default {};

    // 负载，获取添加到state注解的附加信息
    Class<? extends Payload>[] payload() default {};
}
