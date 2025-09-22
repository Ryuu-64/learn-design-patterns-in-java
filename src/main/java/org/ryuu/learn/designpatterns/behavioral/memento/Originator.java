package org.ryuu.learn.designpatterns.behavioral.memento;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 发起者（{@link Originator}）负责创建备忘录（{@link Memento}），用于记录当前时刻他的内部状态。
 * 发起者可以使用备忘录（{@link Memento}）回复内部的状态。
 */
@Retention(RetentionPolicy.SOURCE)
@Target(ElementType.TYPE)
public @interface Originator {
}
