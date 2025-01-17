package org.ryuu.learn.designpatterns.structural.proxy;

import org.junit.jupiter.api.Test;

public class ProxyTest {
    private interface Image extends Subject {
        void display();
    }

    private static class ProxyImage implements Proxy, Image {
        private final String filename;
        private RealImage realImage;

        public ProxyImage(String filename) {
            this.filename = filename;
        }

        @Override
        public void display() {
            if (realImage == null) {
                realImage = new RealImage(filename);
            }

            realImage.display();
        }
    }

    private static class RealImage implements RealSubject {
        private final String filename;

        public RealImage(String filename) {
            this.filename = filename;
            loadImageFromDisk();
        }

        private void loadImageFromDisk() {
            System.out.println("Loading image: " + filename);
        }

        public void display() {
            System.out.println("Displaying image: " + filename);
        }
    }

    @Test
    void test() {
        ProxyImage image = new ProxyImage("example.jpg");
        image.display();
        image.display();
    }
}
