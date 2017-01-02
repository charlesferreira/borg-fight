package br.pucpr.gema.core;

import br.pucpr.mage.Keyboard;
import br.pucpr.mage.Mouse;
import org.joml.Vector2f;

public class Input {
    private static Keyboard keyboard = Keyboard.getInstance();
    private static Mouse mouse = Mouse.getInstance();

    public static boolean getKey(int key) {
        return keyboard.isDown(key);
    }

    public static boolean getKeyDown(int key) {
        return keyboard.isPressed(key);
    }

    public static boolean getKeyUp(int key) {
        return keyboard.isReleased(key);
    }

    public static boolean getMouseButton(int button) {
        return mouse.isDown(button);
    }

    public static boolean getMouseButtonDown(int button) {
        return mouse.isPressed(button);
    }

    public static boolean getMouseButtonUp(int button) {
        return mouse.isReleased(button);
    }

    public static Vector2f mousePosition() {
        return mouse.getPosition();
    }
}
