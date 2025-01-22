package org.ryuu.learn.designpatterns.behavioral.chainofresponsibility;

public abstract class ConcreteHandler implements HandlerInterface {
    protected HandlerInterface nextHandler;

    @Override
    public void setNextHandler(HandlerInterface nextHandler) {
        this.nextHandler = nextHandler;
    }
}
