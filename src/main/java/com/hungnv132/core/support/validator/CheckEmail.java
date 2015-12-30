package com.hungnv132.core.support.validator;

import static java.lang.annotation.ElementType.*;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;


@Target(value= {TYPE, FIELD, ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy ={CheckEmailValidator.class})
@Documented
public @interface CheckEmail {
	enum Action{CREATE, LOGIN, NONE};
	String message() default "";
	
	Class<?>[] groups() default {};
	
    Class<? extends Payload>[] payload() default { };
 
    String pattern() default "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    
    Action actionFor() default Action.NONE;
    
}
