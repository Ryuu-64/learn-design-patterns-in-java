package org.ryuu.learn.designpatterns.behavioral.memento;

public interface Caretaker<M extends Memento<?>> {
    M getMemento(int index);

    void addMemento(M memento);
}
