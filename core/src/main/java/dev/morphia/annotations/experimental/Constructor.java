package dev.morphia.annotations.experimental;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Marks an entity's constructor for use in creation rather than relying on a no arg constructor and field injection.
 *
 * @deprecated This annotation is not necessary and will be removed soon.
 */
@Inherited
@Target(ElementType.CONSTRUCTOR)
@Retention(RetentionPolicy.RUNTIME)
@Deprecated(forRemoval = true)
public @interface Constructor {
}
