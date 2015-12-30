package com.hungnv132.core.support.validator;

import static java.lang.annotation.ElementType.*;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Target(value= {TYPE, FIELD, ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy ={CheckDateValidator.class})
@Documented
public @interface CheckDate {
	String message() default "{date không đúng}";
	
	Class<?>[] groups() default {};
	
    Class<? extends Payload>[] payload() default { };
    
    String pattern();

}
