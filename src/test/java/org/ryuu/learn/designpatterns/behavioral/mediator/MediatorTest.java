package org.ryuu.learn.designpatterns.behavioral.mediator;

import org.junit.jupiter.api.Test;
import java.util.LinkedList;
import java.util.Queue;

public class MediatorTest {
    // Mediator
    private interface AirTrafficControlTower {
        void requestTakeoff(Airplane airplane);
        void requestLanding(Airplane airplane);
        void notifyRunwayFree();
    }

    // Colleague
    private interface Airplane {
        void requestTakeoff();
        void requestLanding();
        void receiveMessage(String message);
    }

    // Concrete Mediator
    private static class AirportControlTower implements AirTrafficControlTower {
        private boolean runwayFree = true;
        private final Queue<String> queue = new LinkedList<>();

        @Override
        public void requestTakeoff(Airplane airplane) {
            if (runwayFree) {
                runwayFree = false;
                airplane.receiveMessage("Takeoff granted. Proceed to runway.");
            } else {
                queue.add("takeoff:" + airplane.hashCode());
                airplane.receiveMessage("Runway busy. Added to takeoff queue.");
            }
        }

        @Override
        public void requestLanding(Airplane airplane) {
            if (runwayFree) {
                runwayFree = false;
                airplane.receiveMessage("Landing granted. Proceed to runway.");
            } else {
                queue.add("landing:" + airplane.hashCode());
                airplane.receiveMessage("Runway busy. Added to landing queue.");
            }
        }

        @Override
        public void notifyRunwayFree() {
            runwayFree = true;
            if (!queue.isEmpty()) {
                String next = queue.poll();
                System.out.println("Next in queue: " + next);
            }
        }
    }

    // Concrete Colleague
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
