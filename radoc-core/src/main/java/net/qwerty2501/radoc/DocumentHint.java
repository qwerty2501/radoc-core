package net.qwerty2501.radoc;

public @interface DocumentHint {

    String description();
    String name() default "";
    boolean omitEmpty() default false;
}

