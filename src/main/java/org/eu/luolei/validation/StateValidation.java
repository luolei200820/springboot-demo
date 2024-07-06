package org.eu.luolei.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.eu.luolei.anno.State;

public class StateValidation implements ConstraintValidator<State, String> {
    /**
     * @param s                          将来要校验的数据
     * @param constraintValidatorContext
     * @return 返回false不通过
     */
    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        // 提供校验规则
        if (s == null) return false;
        return s.equals("草稿") || s.equals("已发布");
    }
}
