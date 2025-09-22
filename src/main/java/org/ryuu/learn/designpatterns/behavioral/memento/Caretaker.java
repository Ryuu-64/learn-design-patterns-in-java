package org.ryuu.learn.designpatterns.behavioral.memento;

public interface Caretaker<TMemento extends Memento<?>> {
    TMemento getMemento(int index);

    void addMemento(TMemento memento);
}
