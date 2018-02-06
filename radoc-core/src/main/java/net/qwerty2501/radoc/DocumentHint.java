package net.qwerty2501.radoc;

public @interface DocumentHint {
    String description() default "";
    String name() default "";
    boolean optional() default false;
    boolean xmlAttribute() default false;
}

