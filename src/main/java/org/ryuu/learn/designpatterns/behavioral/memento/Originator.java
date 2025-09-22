package org.ryuu.learn.designpatterns.behavioral.memento;

public interface Originator<TContent, TMemento extends Memento<TContent>> {
    TContent getContent();

    void writeContent(TContent content);

    Memento<TContent> createMemento();

    void restoreFromMemento(TMemento memento);
}
