package br.pucpr.gema.util.scripts;

import br.pucpr.gema.core.GameComponent;
import br.pucpr.gema.core.Time;
import br.pucpr.mage.Keyboard;
import org.joml.Vector3f;
import org.lwjgl.glfw.GLFW;

public class DemoController extends GameComponent {
    static Keyboard key = Keyboard.getInstance();
    float xRotation = 0f;
    float yRotation = 0f;
    private float rotationSpeed = 3f;
    private float angularDrag = 0.05f;

    @Override
    public void update() {
        if (key.isDown(GLFW.GLFW_KEY_LEFT)) yRotation += rotationSpeed * Time.deltaTime;
        if (key.isDown(GLFW.GLFW_KEY_RIGHT)) yRotation -= rotationSpeed * Time.deltaTime;
        gameObject.transform.rotateLocal(yRotation * Time.deltaTime, new Vector3f(0, 1, 0));
        yRotation -= yRotation * angularDrag;

        if (key.isDown(GLFW.GLFW_KEY_UP)) xRotation += rotationSpeed * Time.deltaTime;
        if (key.isDown(GLFW.GLFW_KEY_DOWN)) xRotation -= rotationSpeed * Time.deltaTime;
        gameObject.transform.rotateLocal(xRotation * Time.deltaTime, new Vector3f(1, 0, 0));
        xRotation -= xRotation * angularDrag;
    }
}
