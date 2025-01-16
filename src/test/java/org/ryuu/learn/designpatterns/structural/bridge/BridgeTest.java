package org.ryuu.learn.designpatterns.structural.bridge;

import org.junit.jupiter.api.Test;

public class BridgeTest {
    private static abstract class Plant implements Abstraction {
        public abstract void manufacture();
    }

    private static class CarPlant extends Plant implements RefinedAbstraction {
        private final Process process1;

        public CarPlant(Process process1) {
            this.process1 = process1;
        }

        @Override
        public void manufacture() {
            process1.work();
            System.out.println("Manufacturing car.");
        }
    }

    private static class TankPlant extends Plant implements RefinedAbstraction {
        private final Process process1;
        private final Process process2;

        public TankPlant(Process process1, Process process2) {
            this.process1 = process1;
            this.process2 = process2;
        }

        @Override
        public void manufacture() {
            process1.work();
            process2.work();
            System.out.println("Manufacturing tank.");
        }
    }

    private interface Process extends Implementer {
        void work();
    }

    private static class HullAssemblyProcess implements Process, ConcreteImplementation {
        @Override
        public void work() {
            System.out.println("Hull assemble.");
        }
    }

    private static class TurretAssemblyProcess implements Process, ConcreteImplementation {
        @Override
        public void work() {
            System.out.println("Turret assemble.");
        }
    }

    @Test
    void test() {
        Plant carManufacturer = new CarPlant(
                new HullAssemblyProcess()
        );
        carManufacturer.manufacture();

        Plant tankManufacturer = new TankPlant(
                new HullAssemblyProcess(),
                new TurretAssemblyProcess()
        );
        tankManufacturer.manufacture();
    }
}
