package view;

import java.util.ArrayList;

public class FrameStack {
    private static FrameStack instance;
    private ArrayList<UniversalFrame> stack;

    private FrameStack() {
        stack = new ArrayList<UniversalFrame>();
    }

    public static FrameStack getInstance() {
        if (instance == null) {
            instance = new FrameStack();
        }
        return instance;
    }

    public UniversalFrame peek() {
        return stack.get(0);
    }

    public void push(UniversalFrame universalFrame) {
        if (!stack.isEmpty()) {
            peek().setVisible(false);
        }
        universalFrame.setVisible(true);
        stack.add(0, universalFrame);
    }

    public void pop() {
        if (!stack.isEmpty()) {
            peek().dispose();
            stack.remove(0);
            if (!stack.isEmpty()) {
                peek().setVisible(true);
            }
        }
    }
}
