package org.inspector4j.api;

import java.lang.annotation.*;

/***
 * Annotation which marks a {@link java.lang.reflect.Field} or {@link java.lang.reflect.Parameter} or {@link java.lang.reflect.Type}  not be  inspected
 */
@Inherited
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.PARAMETER,ElementType.FIELD,ElementType.TYPE})
public @interface Secret {

}
