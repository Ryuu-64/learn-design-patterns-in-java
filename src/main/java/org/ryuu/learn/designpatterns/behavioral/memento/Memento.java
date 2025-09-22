package org.ryuu.learn.designpatterns.behavioral.memento;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 备忘录（{@link Memento}）存储发起者（{@link Originator}）的内部状态。发起者决定备忘录存储发起者的哪些内部状态。
 * 备忘录防止发起者以外的其他对象访问备忘录。
 * 备忘录维护两个接口：
 * 窄接口：管理者（{@link Caretaker}）只能访问窄接口，只能将备忘录传递给其他对象。
 * 宽接口：发起者可以访问宽接口，允许访问供他回到原先状态的所有数据。理想情况下只允许生成备忘录的那个发起者访问备忘录的内部状态。
 */
@Retention(RetentionPolicy.SOURCE)
@Target(ElementType.TYPE)
public @interface Memento {
}
