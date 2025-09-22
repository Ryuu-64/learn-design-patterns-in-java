package org.ryuu.learn.designpatterns.behavioral.mediator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Colleague
@Retention(RetentionPolicy.SOURCE)
@Target(ElementType.TYPE)
public @interface ConcreteColleague {
}
