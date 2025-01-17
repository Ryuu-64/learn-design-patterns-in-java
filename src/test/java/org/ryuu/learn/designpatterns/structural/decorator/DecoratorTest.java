package org.ryuu.learn.designpatterns.structural.decorator;

import org.junit.jupiter.api.Test;

public class DecoratorTest {
    private interface Coffee extends ComponentInterface {
        String getDescription();
    }

    private static class PlainCoffee implements Coffee, ConcreteComponent {
        @Override
        public String getDescription() {
            return "plain coffee";
        }
    }

    private abstract static class CoffeeDecorator implements Coffee, Decorator {
        protected Coffee decoratedCoffee;

        public CoffeeDecorator(Coffee decoratedCoffee) {
            this.decoratedCoffee = decoratedCoffee;
        }
    }

    private static class MilkCoffee extends CoffeeDecorator implements ConcreteDecorator {
        public MilkCoffee(Coffee decoratedCoffee) {
            super(decoratedCoffee);
        }

        @Override
        public String getDescription() {
            return decoratedCoffee.getDescription() + " with milk";
        }
    }

    private static class SugarCoffee extends CoffeeDecorator implements ConcreteDecorator {
        public SugarCoffee(Coffee decoratedCoffee) {
            super(decoratedCoffee);
        }

        @Override
        public String getDescription() {
            return decoratedCoffee.getDescription() + " with sugar";
        }
    }

    @Test
    void test() {
        Coffee coffee = new PlainCoffee();

        String description = new MilkCoffee(coffee).getDescription();
        System.out.println(description);

        description = new SugarCoffee(new MilkCoffee(new PlainCoffee())).getDescription();
        System.out.println(description);
    }
}
