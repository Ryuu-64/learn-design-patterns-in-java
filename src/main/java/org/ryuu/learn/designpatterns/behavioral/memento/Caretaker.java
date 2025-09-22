package org.ryuu.learn.designpatterns.behavioral.memento;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 管理者（{@link Caretaker}）负责保存备忘录（{@link Memento}）。
 * 管理者本身不对备忘录的内容进行任何操作。
 */
@Retention(RetentionPolicy.SOURCE)
@Target(ElementType.TYPE)
public @interface Caretaker {
}
