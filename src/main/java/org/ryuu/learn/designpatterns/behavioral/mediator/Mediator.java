package org.ryuu.learn.designpatterns.behavioral.mediator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 中介者（{@link Mediator}）定义一个接口用于和各同事（{@link Colleague}）通信
 */
@Retention(RetentionPolicy.SOURCE)
@Target(ElementType.TYPE)
public @interface Mediator {
}
