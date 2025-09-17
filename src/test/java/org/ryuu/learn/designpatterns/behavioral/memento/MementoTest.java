package org.ryuu.learn.designpatterns.behavioral.memento;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MementoTest {
    private static class DocumentMemento implements Memento<String> {
        private final String content;

        public DocumentMemento(String content) {
            this.content = content;
        }

        @Override
        public String getContent() {
            return content;
        }
    }

    private static class DocumentOriginator implements Originator<String, DocumentMemento> {
        private String content = "";

        @Override
        public String getContent() {
            return content;
        }

        @Override
        public void writeContent(String content) {
            this.content += content;
        }

        @Override
        public DocumentMemento createMemento() {
            return new DocumentMemento(content);
        }

        @Override
        public void restoreFromMemento(DocumentMemento memento) {
            this.content = memento.getContent();
        }
    }

    private static class DocumentCaretaker implements Caretaker<DocumentMemento> {
        private final List<DocumentMemento> mementos = new ArrayList<>();

        @Override
        public DocumentMemento getMemento(int index) {
            return mementos.get(index);
        }

        @Override
        public void addMemento(DocumentMemento memento) {
            mementos.add(memento);
        }
    }

    @Test
    void mementoTest() {
        DocumentOriginator originator = new DocumentOriginator();
        DocumentCaretaker caretaker = new DocumentCaretaker();

        // write content
        originator.writeContent("Hello");
        caretaker.addMemento(originator.createMemento());

        // write content
        originator.writeContent(" world.");
        caretaker.addMemento(originator.createMemento());

        // Restore to previous state
        originator.restoreFromMemento(caretaker.getMemento(1));
        assertEquals("Hello world.", originator.getContent());

        // Restore to previous state
        originator.restoreFromMemento(caretaker.getMemento(0));
        assertEquals("Hello", originator.getContent());
    }
}
