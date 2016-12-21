package br.pucpr.borgfight.scripts;

import br.pucpr.gema.core.GameComponent;
import br.pucpr.gema.core.GameObject;
import br.pucpr.gema.core.Time;
import br.pucpr.mage.Keyboard;
import org.joml.Vector3f;
import org.lwjgl.glfw.GLFW;

public class ShipMovement extends GameComponent {
    private float turningSpeed = 0.5f;
    private float movementSpeed = 2f;
    private float turbo = 1f;

    public ShipMovement(GameObject gameObject) {
        super(gameObject);
    }

    public ShipMovement pitch(float pitch) {
        gameObject.transform.rotate(turningSpeed * turbo * pitch * Time.deltaTime, new Vector3f(1, 0, 0));
        return this;
    }

    public ShipMovement yaw(float yaw) {
        gameObject.transform.rotate(turningSpeed * turbo * yaw * Time.deltaTime, new Vector3f(0, 1, 0));
        return this;
    }

    public ShipMovement roll(float roll) {
        gameObject.transform.rotate(turningSpeed * turbo * roll * Time.deltaTime, new Vector3f(0, 0, 1));
        return this;
    }

    public void thrust(Vector3f thrust) {
        gameObject.transform.translate(thrust.mul(movementSpeed * turbo * Time.deltaTime));
    }

    @Override
    public void update() {
        turbo = Keyboard.getInstance().isDown(GLFW.GLFW_KEY_LEFT_SHIFT)
                ? 5f
                : 1f;
    }
}
