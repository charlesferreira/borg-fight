package br.pucpr.mage;

import org.joml.Vector2f;

import java.util.HashSet;
import java.util.Set;

import static org.lwjgl.glfw.GLFW.GLFW_PRESS;
import static org.lwjgl.glfw.GLFW.GLFW_RELEASE;

public class Mouse {
    private static Mouse INSTANCE = new Mouse();

    private Set<Integer> pressedButtons = new HashSet<>();
    private Set<Integer> downButtons = new HashSet<>();
    private Set<Integer> releasedButtons = new HashSet<>();

    private Vector2f position = new Vector2f();
    private boolean moved = false;

    private Mouse() {
    }

    public static Mouse getInstance() {
        return INSTANCE;
    }

    public boolean isPressed(int key) {
        return pressedButtons.contains(key);
    }

    public boolean isDown(int key) {
        return downButtons.contains(key);
    }

    public boolean isReleased(int key) {
        return releasedButtons.contains(key);
    }

    void setPosition(double x, double y) {
        position.set((float)x, (float)y);
        moved = true;
    }

    public Vector2f getPosition() {
        return new Vector2f(position);
    }

    void setButton(int button, int action) {
        if (action == GLFW_PRESS) {
            downButtons.add(button);
            pressedButtons.add(button);
        }
        else if (action == GLFW_RELEASE) {
            downButtons.remove(button);
            releasedButtons.add(button);
        }
    }

    void update() {
        pressedButtons.clear();
        releasedButtons.clear();
        moved = false;
    }

    public boolean moved() {
        return moved;
    }
}
