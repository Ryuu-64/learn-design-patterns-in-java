package org.ryuu.learn.designpatterns.behavioral.chainofresponsibility;

import org.junit.jupiter.api.Test;

class ChainOfResponsibilityTest {
    private static abstract class Handler implements HandlerInterface {
        protected HandlerInterface nextHandler;

        @Override
        public void setNextHandler(HandlerInterface nextHandler) {
            this.nextHandler = nextHandler;
        }
    }

    private static class Level1Handler extends Handler {
        @Override
        public void handle(Object request) {
            if (request == null) {
                System.out.println("Level 1 handler. Request is null.");
                return;
            }

            nextHandler.handle(request);
        }
    }

    private static class Level2Handler extends Handler {
        @Override
        public void handle(Object request) {
            System.out.println("Level 2 handler. Request is " + request + ".");
            nextHandler.handle(request);
        }
    }

    private static class Level3Handler extends Handler {
        @Override
        public void handle(Object request) {
            System.out.println("Level 3 handler. Request is " + request + ".");
        }
    }

    @Test
    void test() {
        HandlerInterface level1Handler = new Level1Handler();
        HandlerInterface level2Handler = new Level2Handler();
        HandlerInterface level3Handler = new Level3Handler();

        level1Handler.setNextHandler(level2Handler);
        level2Handler.setNextHandler(level3Handler);

        level1Handler.handle(null);
        level1Handler.handle("Hello World!");
    }
}
