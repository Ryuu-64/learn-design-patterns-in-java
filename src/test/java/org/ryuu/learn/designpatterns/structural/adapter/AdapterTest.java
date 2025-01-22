package org.ryuu.learn.designpatterns.structural.adapter;

import org.junit.jupiter.api.Test;

public class AdapterTest {
    private interface Printer extends TargetInterface {
        void print();
    }

    private static class LegacyPrinter implements Adaptee {
        void legacyPrint() {
            System.out.println("Legacy printing...");
        }
    }

    private static class PrinterAdapter implements Printer {
        private final LegacyPrinter legacyPrinter;

        public PrinterAdapter(LegacyPrinter legacyPrinter) {
            this.legacyPrinter = legacyPrinter;
        }

        public void print() {
            legacyPrinter.legacyPrint();
        }
    }

    @Test
    void test() {
        Printer printer = new PrinterAdapter(new LegacyPrinter());
        printer.print();
    }
}
