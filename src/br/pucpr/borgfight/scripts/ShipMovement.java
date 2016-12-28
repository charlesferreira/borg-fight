package br.pucpr.borgfight.scripts;

import br.pucpr.gema.core.GameComponent;
import br.pucpr.gema.core.GameObject;
import br.pucpr.gema.core.Time;
import br.pucpr.gema.physics.ForceMode;
import br.pucpr.gema.physics.RigidBody;
import br.pucpr.mage.Keyboard;
import org.joml.Vector3f;
import org.lwjgl.glfw.GLFW;

public class ShipMovement extends GameComponent {
    private float turningSpeed = 0.5f;
    private float movementSpeed = 20f;
    private float turbo = 1f;

    RigidBody rb;

    public ShipMovement(GameObject gameObject) {
        super(gameObject);
    }

    @Override
    public void start() {
        rb = (RigidBody) getComponent(RigidBody.class);
    }

    public ShipMovement pitch(float pitch) {
        transform.rotate(turningSpeed * turbo * pitch * Time.deltaTime, new Vector3f(1, 0, 0));
        return this;
    }

    public ShipMovement yaw(float yaw) {
        transform.rotate(turningSpeed * turbo * yaw * Time.deltaTime, new Vector3f(0, 1, 0));
        return this;
    }

    public ShipMovement roll(float roll) {
        transform.rotate(turningSpeed * turbo * roll * Time.deltaTime, new Vector3f(0, 0, 1));
        return this;
    }

    public void thrust(float thrust) {
        Vector3f force = transform.forward().mul(thrust * movementSpeed * turbo);
        rb.addForce(force, ForceMode.VELOCITY_CHANGE);
    }

    @Override
    public void update() {
        turbo = Keyboard.getInstance().isDown(GLFW.GLFW_KEY_LEFT_SHIFT)
                ? 5f
                : 1f;
    }

    public void reset() {
        rb.velocity.set(0);
        transform.setPosition(new Vector3f(0, 0, 0));
        transform.setRotation(0, 0, 0);
    }
}
