package org.ryuu.learn.designpatterns.behavioral.mediator;

import org.junit.jupiter.api.Test;

public class MediatorTest {
    private interface Airplane extends Colleague {
        void requestTakeoff();

        void requestLanding();

        void notifyAirTrafficControl(String message);
    }

    private static class CommercialAirplane implements Airplane, ConcreteColleague {
        private final AirTrafficControlTower mediator;

        public CommercialAirplane(AirTrafficControlTower mediator) {
            this.mediator = mediator;
        }

        @Override
        public void requestTakeoff() {
            mediator.requestTakeoff(this);
        }

        @Override
        public void requestLanding() {
            mediator.requestLanding(this);
        }

        @Override
        public void notifyAirTrafficControl(String message) {
            System.out.println("Commercial Airplane: " + message);
        }
    }

    private interface AirTrafficControlTower extends Mediator {
        void requestTakeoff(Airplane airplane);

        void requestLanding(Airplane airplane);
    }

    private static class AirportControlTower implements AirTrafficControlTower, ConcreteMediator {
        @Override
        public void requestTakeoff(Airplane airplane) {
            // Logic for coordinating takeoff
            airplane.notifyAirTrafficControl("Requesting takeoff clearance.");
        }

        @Override
        public void requestLanding(Airplane airplane) {
            // Logic for coordinating landing
            airplane.notifyAirTrafficControl("Requesting landing clearance.");
        }
    }

    @Test
    void test() {
        // Instantiate the Mediator (Airport Control Tower)
        AirTrafficControlTower controlTower = new AirportControlTower();

        // Instantiate Concrete Colleagues (Commercial Airplanes)
        Airplane airplane1 = new CommercialAirplane(controlTower);
        Airplane airplane2 = new CommercialAirplane(controlTower);

        // Set up the association between Concrete Colleagues and the Mediator
        airplane1.requestTakeoff();
        airplane2.requestLanding();
    }
}
