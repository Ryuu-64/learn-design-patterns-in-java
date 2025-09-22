package org.ryuu.learn.designpatterns.behavioral.mediator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 每个同事（{@link Colleague}）都知道他的中介者（{@link Mediator}）
 * 每个同事在需要与其他同事通信时，与他的中介者通信
 */
@Retention(RetentionPolicy.SOURCE)
@Target(ElementType.TYPE)
public @interface Colleague {
}
