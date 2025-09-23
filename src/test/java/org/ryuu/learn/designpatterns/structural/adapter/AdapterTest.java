package org.ryuu.learn.designpatterns.structural.adapter;

import org.junit.jupiter.api.Test;

public class AdapterTest {
    @Target
    private interface Printer {
        void print();
    }

    @Adaptee
    private static class LegacyPrinter {
        public void legacyPrint() {
            System.out.println("Legacy printing...");
        }
    }

    @Adapter
    private record PrinterAdapter(LegacyPrinter legacyPrinter) implements Printer {
        public void print() {
            legacyPrinter.legacyPrint();
        }
    }

    @Client
    private record PrinterClient(Printer printer) {
        public void print() {
            printer.print();
        }
    }

    @Test
    void test() {
        PrinterClient client = new PrinterClient(new PrinterAdapter(new LegacyPrinter()));
        client.print();
    }
}
