package org.ryuu.learn.designpatterns.structural.facade;

import org.junit.jupiter.api.Test;

public class FacadeTest {
    private interface HouseSubsystem extends Subsystem {
        void work();
    }

    private static class Pipe implements HouseSubsystem {
        @Override
        public void work() {
            System.out.println("Water supply.");
        }
    }

    private static class Wire implements HouseSubsystem {
        @Override
        public void work() {
            System.out.println("Power supply.");
        }
    }

    private static class House implements Facade {
        private final HouseSubsystem pipe = new Pipe();
        private final HouseSubsystem wire = new Wire();

        public void work() {
            pipe.work();
            wire.work();
        }

        public void waterSupply() {
            pipe.work();
        }

        public void wireSupply() {
            wire.work();
        }
    }

    @Test
    void test() {
        House house = new House();

        house.work();

        house.waterSupply();
        house.wireSupply();
    }
}
