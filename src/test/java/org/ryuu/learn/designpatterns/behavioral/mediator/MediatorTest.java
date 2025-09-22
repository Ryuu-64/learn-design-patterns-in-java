package org.ryuu.learn.designpatterns.behavioral.mediator;

import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 对象只关注自身功能，交互逻辑集中在 Mediator
 * 增加新对象只需修改中介者，不改动现有对象
 * <p>
 * GUI 界面组件
 * 问题：多个控件之间互相影响（按钮、文本框、下拉列表等），如果直接互相调用，逻辑分散、耦合高。
 * 解决方案：引入 Dialog 或 Form 作为中介者，控件只把事件通知中介者，由中介者决定其他控件的状态。
 * 例子：
 * 注册表单：勾选“同意条款”才允许提交按钮可用。
 * 多选列表互斥逻辑（选择一个禁用另一个）。
 */
public class MediatorTest {
    @Mediator
    private interface AirTrafficControlTower {
        void requestTakeoff(Airplane airplane);

        void requestLanding(Airplane airplane);

        void notifyRunwayFree();
    }

    @Colleague
    private interface Airplane {
        void requestTakeoff();

        void requestLanding();

        void receiveMessage(String message);
    }

    @ConcreteMediator
    private static class AirportControlTower implements AirTrafficControlTower {
        private boolean isRunwayFree = true;
        private final Queue<String> queue = new LinkedList<>();

        @Override
        public void requestTakeoff(Airplane airplane) {
            if (isRunwayFree) {
                isRunwayFree = false;
                airplane.receiveMessage("Takeoff granted. Proceed to runway.");
            } else {
                queue.add("takeoff:" + airplane.hashCode());
                airplane.receiveMessage("Runway busy. Added to takeoff queue.");
            }
        }

        @Override
        public void requestLanding(Airplane airplane) {
            if (isRunwayFree) {
                isRunwayFree = false;
                airplane.receiveMessage("Landing granted. Proceed to runway.");
            } else {
                queue.add("landing:" + airplane.hashCode());
                airplane.receiveMessage("Runway busy. Added to landing queue.");
            }
        }

        @Override
        public void notifyRunwayFree() {
            isRunwayFree = true;
            if (!queue.isEmpty()) {
                String next = queue.poll();
                System.out.println("Next in queue: " + next);
            }
        }
    }

    @ConcreteColleague
    private static class CommercialAirplane implements Airplane {
        private final AirTrafficControlTower mediator;
        private final String name;

        public CommercialAirplane(String name, AirTrafficControlTower mediator) {
            this.name = name;
            this.mediator = mediator;
        }

        @Override
        public void requestTakeoff() {
            System.out.println(name + ": Requesting takeoff.");
            mediator.requestTakeoff(this);
        }

        @Override
        public void requestLanding() {
            System.out.println(name + ": Requesting landing.");
            mediator.requestLanding(this);
        }

        @Override
        public void receiveMessage(String message) {
            System.out.println(name + " received: " + message);
        }
    }

    @Test
    void test() {
        AirTrafficControlTower tower = new AirportControlTower();

        Airplane airplane1 = new CommercialAirplane("Airplane-1", tower);
        Airplane airplane2 = new CommercialAirplane("Airplane-2", tower);

        airplane1.requestTakeoff();
        airplane2.requestLanding();

        tower.notifyRunwayFree();
        tower.notifyRunwayFree();
    }
}
