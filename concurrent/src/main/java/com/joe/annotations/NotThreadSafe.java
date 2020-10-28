package com.joe.annotations;

import java.lang.annotation.*;

/**
 * The class to which this annotation is applied is not thread-safe.
 * This annotation primarily exists for clarifying the non-thread-safety of a class
 * that might otherwise be assumed to be thread-safe, despite the fact that it is a bad
 * idea to assume a class is thread-safe without good reason.
 *
 * @author Brian Goetz and Tim Peierls
 * @see ThreadSafe
 */
@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface NotThreadSafe {
}
