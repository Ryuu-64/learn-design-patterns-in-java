package org.ryuu.learn.designpatterns.behavioral.interpreter;

import org.junit.jupiter.api.Test;

public class InterpreterTest {
    private static class ConcreteContext implements Context {
    }

    private static class NumberExpression implements TerminalExpression {
        private final int number;

        public NumberExpression(int number) {
            this.number = number;
        }

        public int interpret(Context context) {
            return number;
        }
    }

    private static class AdditionExpression implements NonTerminalExpression {
        private final AbstractExpression left;
        private final AbstractExpression right;

        public AdditionExpression(AbstractExpression left, AbstractExpression right) {
            this.left = left;
            this.right = right;
        }

        public int interpret(Context context) {
            return left.interpret(context) + right.interpret(context);
        }
    }

    private static class MultiplicationExpression implements NonTerminalExpression {
        private final AbstractExpression left;
        private final AbstractExpression right;

        public MultiplicationExpression(AbstractExpression left, AbstractExpression right) {
            this.left = left;
            this.right = right;
        }

        @Override
        public int interpret(Context context) {
            return left.interpret(context) * right.interpret(context);
        }
    }

    private static class ConcreteInterpreter implements Interpreter {
        private final Context context;

        public ConcreteInterpreter(Context context) {
            this.context = context;
        }

        public int interpret(String expression) {
            AbstractExpression expressionTree = buildExpressionTree(expression);
            return expressionTree.interpret(context);
        }

        private AbstractExpression buildExpressionTree(String expression) {
            // Logic to parse expression and create expression tree
            // For simplicity, assume the expression is already parsed
            // and represented as an expression tree
            return new AdditionExpression(
                    new NumberExpression(2),
                    new MultiplicationExpression(
                            new NumberExpression(3),
                            new NumberExpression(4)
                    )
            );
        }
    }

    @Test
    void test() {
        String expression = "2 + 3 * 4";

        Context context = new ConcreteContext();
        Interpreter interpreter = new ConcreteInterpreter(context);

        int result = interpreter.interpret(expression);
        System.out.println("Result: " + result);
    }
}
