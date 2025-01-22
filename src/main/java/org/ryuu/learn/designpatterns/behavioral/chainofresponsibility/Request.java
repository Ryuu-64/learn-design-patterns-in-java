package org.ryuu.learn.designpatterns.behavioral.chainofresponsibility;

@FunctionalInterface
public interface Request {
    Object handle(HandlerInterface nextHandler);
}
