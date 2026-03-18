package practica8.pkg2;

import java.lang.annotation.*;
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@interface InfoClase {
    String autor();
    String version() default "1.0";
}