package org.ryuu.learn.designpatterns.structural.proxy;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * 控制访问 / 权限管理（Protection Proxy）
 * 代理在访问真实对象前做权限检查。
 * <p>
 * 延迟加载 / 性能优化（Virtual Proxy）
 * 代理延迟创建真实对象，直到真正需要。
 * <p>
 * 远程访问（Remote Proxy）
 * 代理在本地表现得像真实对象，实际操作转发给远程对象。
 * <p>
 * 智能代理 / 增强功能（Smart Proxy）
 * 代理在访问前后添加功能，比如统计、缓存、日志。
 */
public class ProxyTest {
    private interface Image extends Subject {
        void display();
    }

    private record RealImage(String filename) implements RealSubject {
        private RealImage(String filename) {
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

    private static class ProxyImage implements Proxy, Image {
        private final String filename;
        private RealImage realImage;
        private final String userRole; // 权限控制
        private static final Map<String, Integer> accessLog = new HashMap<>(); // 访问日志

        public ProxyImage(String filename, String userRole) {
            this.filename = filename;
            this.userRole = userRole;
        }

        @Override
        public void display() {
            // 权限检查
            if (!"admin".equals(userRole) && !"user".equals(userRole)) {
                System.out.println("Access denied for role: " + userRole);
                return;
            }

            // 延迟加载真实对象
            if (realImage == null) {
                realImage = new RealImage(filename);
            }

            // 调用真实对象的方法
            realImage.display();

            // 记录访问日志
            accessLog.put(filename, accessLog.getOrDefault(filename, 0) + 1);
            System.out.println("Access count for " + filename + ": " + accessLog.get(filename));
        }
    }

    @Test
    void test() {
        Image image1 = new ProxyImage("example1.jpg", "user");
        Image image2 = new ProxyImage("example2.jpg", "guest"); // 没权限
        Image image3 = new ProxyImage("example1.jpg", "admin");

        image1.display(); // 第一次加载并显示
        image1.display(); // 第二次直接显示，不再加载

        image2.display(); // 拒绝访问

        image3.display(); // admin 可以访问
    }
}
