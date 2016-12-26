package br.pucpr.gema.util.scripts;

import br.pucpr.gema.core.GameComponent;
import br.pucpr.gema.core.GameObject;
import br.pucpr.gema.core.Time;
import br.pucpr.mage.Keyboard;
import org.joml.Vector3f;
import org.lwjgl.glfw.GLFW;

public class DemoController extends GameComponent {
    static Keyboard key = Keyboard.getInstance();

    public DemoController(GameObject gameObject) {
        super(gameObject);
    }

    @Override
    public void update() {
        float angle = 0f;
        if (key.isDown(GLFW.GLFW_KEY_LEFT)) angle++;
        if (key.isDown(GLFW.GLFW_KEY_RIGHT)) angle--;
        gameObject.transform.rotate(angle * Time.deltaTime, new Vector3f(0, 1, 0));

        angle = 0f;
        if (key.isDown(GLFW.GLFW_KEY_UP)) angle++;
        if (key.isDown(GLFW.GLFW_KEY_DOWN)) angle--;
        gameObject.transform.rotate(angle * Time.deltaTime, new Vector3f(1, 0, 0));
    }
}
