package org.ryuu.learn.designpatterns.behavioral.chainofresponsibility;

public interface HandlerInterface {
    void handle(Object request);

    void setNextHandler(HandlerInterface nextHandler);
}
