package org.ryuu.learn.designpatterns.structural.flyweight;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

public class FlyweightTest {
    private interface IconDrawer extends FlyweightInterface {
        void draw(int x, int y);
    }

    private record FileIconDrawer(String type) implements IconDrawer, ConcreteFlyweight {
        @Override
        public void draw(int x, int y) {
            System.out.println("Drawing " + type + " icon at (" + x + ", " + y + ")");
        }
    }

    private record FolderIconDrawer(String color) implements IconDrawer, ConcreteFlyweight {
        @Override
        public void draw(int x, int y) {
            System.out.println("Drawing folder icon with color " + color + " at (" + x + ", " + y + ")");
        }
    }

    private static class IconFactory implements FlyweightFactory {
        private final Map<String, IconDrawer> iconCache = new HashMap<>();

        public IconDrawer getIcon(String key) {
            if (iconCache.containsKey(key)) {
                return iconCache.get(key);
            }

            IconDrawer iconDrawer = null;
            if (key.equals("file")) {
                iconDrawer = new FileIconDrawer("document");
            } else if (key.equals("folder")) {
                iconDrawer = new FolderIconDrawer("blue");
            }
            System.out.println("Create drawer.");

            iconCache.put(key, iconDrawer);
            return iconDrawer;
        }
    }

    @Test
    void test() {
        IconFactory factory = new IconFactory();
        IconDrawer fileIconDrawer1 = factory.getIcon("file");
        fileIconDrawer1.draw(10, 20);

        IconDrawer fileIconDrawer2 = factory.getIcon("file");
        fileIconDrawer2.draw(10, 20);

        IconDrawer factoryIconDrawer1 = factory.getIcon("folder");
        factoryIconDrawer1.draw(30, 40);

        IconDrawer factoryIconDrawer2 = factory.getIcon("folder");
        factoryIconDrawer2.draw(30, 40);
    }
}
