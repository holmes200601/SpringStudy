package restaurant.dozer.custom.mapper;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Defines non-standard mappings on property or field levels.
 * If property level is considered this annotation should be put on valid bean property getter method.
 * 
 * @author dmitry.buzdin
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.METHOD})
public @interface CustomMapping {

  String value() default "";
  String relationshipType() default "non-cumulative"; // cumulative/non-cumulative
  boolean removeOrphans() default true;

}
