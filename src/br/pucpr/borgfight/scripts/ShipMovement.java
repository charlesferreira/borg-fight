package br.pucpr.borgfight.scripts;

import br.pucpr.gema.core.GameComponent;
import br.pucpr.gema.physics.ForceMode;
import br.pucpr.gema.physics.RigidBody;
import org.joml.Vector3f;

public class ShipMovement extends GameComponent {
    private float turningSpeed = 10f;
    private float movementSpeed = 20f;

    RigidBody rb;

    @Override
    public void start() {
        rb = (RigidBody) getComponent(RigidBody.class);
        rb.setMass(1f)
                .setDrag(0.001f)
                .setAngularDrag(0.005f);
    }

    public ShipMovement pitch(float pitch) {
        Vector3f torque = new Vector3f(-1, 0, 0).mul(turningSpeed * pitch);
        rb.addTorque(torque, ForceMode.FORCE);
        return this;
    }

    public ShipMovement yaw(float yaw) {
        Vector3f torque = new Vector3f(0, -1, 0).mul(turningSpeed * yaw);
        rb.addTorque(torque, ForceMode.FORCE);
        return this;
    }

    public ShipMovement roll(float roll) {
        Vector3f torque = new Vector3f(0, 0, -1).mul(turningSpeed * roll);
        rb.addTorque(torque, ForceMode.FORCE);
        return this;
    }

    public ShipMovement thrust(float thrust) {
        Vector3f force = transform.forward().mul(thrust * movementSpeed);
        rb.addForce(force);
        return this;
    }

    public ShipMovement strafe(float strafe) {
        Vector3f force = transform.right().mul(strafe * movementSpeed);
        rb.addForce(force);
        return this;
    }

    public void reset() {
        rb.velocity.set(0);
        rb.angularVelocity.set(0);
        transform.setPosition(new Vector3f(0, 0, 0));
        transform.setRotation(0, 0, 0);
    }
}
