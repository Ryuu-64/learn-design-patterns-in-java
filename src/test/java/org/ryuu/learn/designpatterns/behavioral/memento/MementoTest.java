package org.ryuu.learn.designpatterns.behavioral.memento;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MementoTest {
    @Memento
    private static class DocumentMemento {
        private final String content;

        public DocumentMemento(String content) {
            this.content = content;
        }

        public String getContent() {
            return content;
        }
    }

    @Originator
    private static class DocumentOriginator {
        private String content = "";

        public String getContent() {
            return content;
        }

        public void writeContent(String content) {
            this.content += content;
        }

        public DocumentMemento createMemento() {
            return new DocumentMemento(content);
        }

        public void restoreFromMemento(DocumentMemento memento) {
            this.content = memento.getContent();
        }
    }

    @Caretaker
    private static class DocumentCaretaker {
        private final List<DocumentMemento> mementos = new ArrayList<>();

        public DocumentMemento get(int index) {
            return mementos.get(index);
        }

        public void add(DocumentMemento memento) {
            mementos.add(memento);
        }
    }

    @Test
    void mementoTest() {
        DocumentOriginator originator = new DocumentOriginator();
        DocumentCaretaker caretaker = new DocumentCaretaker();

        // write content
        originator.writeContent("Hello");
        caretaker.add(originator.createMemento());

        // write content
        originator.writeContent(" world.");
        caretaker.add(originator.createMemento());

        // Restore to previous state
        originator.restoreFromMemento(caretaker.get(1));
        assertEquals("Hello world.", originator.getContent());

        // Restore to previous state
        originator.restoreFromMemento(caretaker.get(0));
        assertEquals("Hello", originator.getContent());
    }
}
