package com.example.gestao_pedidos.core.constrain;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;

import static com.example.gestao_pedidos.utils.ConstraintValidatorErrorMessageUtils.*;
import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;
import static org.apache.commons.lang3.StringUtils.isBlank;
import static org.apache.commons.lang3.math.NumberUtils.isParsable;
import static org.springframework.util.NumberUtils.parseNumber;

/**
 * Defines the logic for validating constraint annotations of type {@link NumberConstraint}
 *
 */
@RequiredArgsConstructor
public class NumberConstraintValidator implements ConstraintValidator<NumberConstraint, String> {

    private final MessageSource messageSource;
    private NumberConstraint constraint;

    @Override
    public void initialize(NumberConstraint constraint) {
        this.constraint = constraint;
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        boolean result = true;
        final String message = isBlank(value) ? "message.error.number.notblank"
                : isParsableValue(value) ? null : "message.error.number.invalid";
        if (nonNull(message)) {
            setErrorMessage(context, messageSource, message, constraint.placeholder());
            result = (constraint.nullable() && isNull(value)) || false;
        } else if (isLessThan(parseNumber(value, constraint.targetClass()), constraint.min())) {
            setErrorMessage(context, messageSource, "message.page.min.inicial.error", constraint.placeholder());
            result = false;
        } else if (isGreaterThan(parseNumber(value, constraint.targetClass()), constraint.max())) {
            setErrorMessage(context, messageSource, "message.page.max.size.error", constraint.placeholder());
            result = false;
        }
        return result;
    }

    private boolean isParsableValue(String value) {
        try {
            return isParsable(value) && nonNull(parseNumber(value, constraint.targetClass()));
        } catch (Exception ex) {
            return false;
        }
    }

    private boolean isLessThan(Number value_1, Number value_2) {
        return value_1.longValue() < value_2.longValue();
    }

    private boolean isGreaterThan(Number value_1, Number value_2) {
        return value_1.longValue() > value_2.longValue();
    }
}

