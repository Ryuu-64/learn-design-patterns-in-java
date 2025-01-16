package org.ryuu.learn.designpatterns.behavioral.command;

import org.junit.jupiter.api.Test;

import java.util.Stack;

public class CommandTest {
    private static class PictureEditor implements CommandReceiver {
        private float rotation = 0;
        private float width = 0;
        private float height = 0;

        public void setRotationByDelta(float delta) {
            System.out.println("Rotating by " + delta);
            rotation += delta;
            System.out.println("Rotation is now " + rotation);
        }

        public void setSizeByDelta(float deltaWidth, float deltaHeight) {
            System.out.println("Changing size by (" + deltaWidth + ", " + deltaHeight + ").");
            width += deltaWidth;
            height += deltaHeight;
            System.out.println("Size is now (" + width + ", " + height + ").");
        }
    }

    private static class RotateCommand implements Command {
        private final PictureEditor receiver;
        private final float delta;

        public RotateCommand(PictureEditor receiver, float delta) {
            this.receiver = receiver;
            this.delta = delta;
        }

        @Override
        public void execute() {
            receiver.setRotationByDelta(delta);
        }

        @Override
        public void undo() {
            receiver.setRotationByDelta(-delta);
        }
    }

    private static class ResizeCommand implements Command {
        private final PictureEditor receiver;
        private final float deltaWidth;
        private final float deltaHeight;

        public ResizeCommand(PictureEditor receiver, float deltaWidth, float deltaHeight) {
            this.receiver = receiver;
            this.deltaWidth = deltaWidth;
            this.deltaHeight = deltaHeight;
        }

        @Override
        public void execute() {
            receiver.setSizeByDelta(deltaWidth, deltaHeight);
        }

        @Override
        public void undo() {
            receiver.setSizeByDelta(-deltaWidth, -deltaHeight);
        }
    }

    private static class Client implements CommandInvoker {
        private final Stack<Command> commandStack = new Stack<>();

        @Override
        public void invoke(Command command) {
            commandStack.push(command);
            System.out.println("Executing " + command.getClass().getSimpleName() + "...");
            command.execute();
        }

        public void redo() {
            if (commandStack.isEmpty()) {
                return;
            }

            Command command = commandStack.peek();
            commandStack.push(command);
            System.out.println("Redoing " + command.getClass().getSimpleName() + "...");
            command.execute();
        }

        public void undo() {
            if (commandStack.isEmpty()) {
                return;
            }

            Command command = commandStack.pop();
            System.out.println("Undoing " + command.getClass().getSimpleName() + "...");
            command.undo();
        }
    }

    @Test
    void test() {
        PictureEditor editor = new PictureEditor();

        RotateCommand rotateCommand = new RotateCommand(editor, 10);
        ResizeCommand resizeCommand = new ResizeCommand(editor, 1920, 1080);

        Client client = new Client();
        client.invoke(rotateCommand);
        client.invoke(resizeCommand);
        client.redo();
        client.undo();
        client.undo();
        client.undo();
        client.undo();
        client.undo();
    }
}
