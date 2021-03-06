package de.codecentric.psd.worblehat.web.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.apache.commons.lang.StringUtils;

public class NumericConstraintValidator implements ConstraintValidator<Numeric, String> {

    @Override
    public void initialize(Numeric constraintAnnotation) {
        // Nothing to do here
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        // Don't validate null, empty and blank strings, since these are validated by @NotNull, @NotEmpty and @NotBlank
        if (StringUtils.isNotBlank(value)) {
            return StringUtils.isNumeric(value);
        }
        return true;
    }

}
