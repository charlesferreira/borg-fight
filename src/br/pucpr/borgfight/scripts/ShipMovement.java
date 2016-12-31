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
    private float turningSpeed = 1f;
    private float movementSpeed = 20f;
    private float turbo = 1f;

    RigidBody rb;

    public ShipMovement(GameObject gameObject) {
        super(gameObject);
    }

    @Override
    public void start() {
        rb = (RigidBody) getComponent(RigidBody.class);
        rb.setMass(1f)
                .setDrag(0.005f)
                .setAngularDrag(0.005f);
    }

    public ShipMovement pitch(float pitch) {
        Vector3f torque = new Vector3f(1, 0, 0).mul(turningSpeed * turbo * pitch);
        rb.addTorque(torque, ForceMode.FORCE);
        return this;
    }

    public ShipMovement yaw(float yaw) {
        Vector3f torque = new Vector3f(0, 1, 0).mul(turningSpeed * turbo * yaw);
        rb.addTorque(torque, ForceMode.FORCE);
        return this;
    }

    public ShipMovement roll(float roll) {
        Vector3f torque = new Vector3f(0, 0, 1).mul(turningSpeed * turbo * roll);
        rb.addTorque(torque, ForceMode.FORCE);
        return this;
    }

    public ShipMovement thrust(float thrust) {
        Vector3f force = transform.forward().mul(thrust * movementSpeed * turbo);
        rb.addForce(force);
        return this;
    }

    public ShipMovement strafe(float strafe) {
        Vector3f force = transform.right().mul(strafe * movementSpeed * turbo);
        rb.addForce(force);
        return this;
    }

    @Override
    public void update() {
        turbo = Keyboard.getInstance().isDown(GLFW.GLFW_KEY_LEFT_SHIFT)
                ? 5f
                : 1f;
    }

    public void reset() {
        rb.velocity.set(0);
        rb.angularVelocity.set(0);
        transform.setPosition(new Vector3f(0, 0, 0));
        transform.setRotation(0, 0, 0);
    }
}
