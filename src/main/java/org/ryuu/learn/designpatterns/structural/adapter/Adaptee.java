package org.ryuu.learn.designpatterns.structural.adapter;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.SOURCE)
@java.lang.annotation.Target(ElementType.TYPE)
public @interface Adaptee {
}
