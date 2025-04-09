package org.ryuu.learn.designpatterns.behavioral.memento;

public interface Originator<C, M extends Memento<C>> {
    C getContent();

    void writeContent(C content);

    Memento<C> createMemento();

    void restoreFromMemento(M memento);
}
