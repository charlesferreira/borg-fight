package br.pucpr.borgfight.scripts;

import br.pucpr.gema.core.GameComponent;
import br.pucpr.gema.physics.ForceMode;
import br.pucpr.gema.physics.RigidBody;
import org.joml.Quaternionf;
import org.joml.Vector3f;

public class ShipMovement extends GameComponent {
    private float maxSpeed = 15f;
    private float mainThrusterPower = 15f;
    private float sideThrusterPower = 5f;
    private float pitchStrength = 2.5f;
    private float yawStrength = 2.5f;
    private float rollStrength = 1.5f;

    RigidBody rb;

    @Override
    public void start() {
        rb = getComponent(RigidBody.class)
                .setMass(1f)
                .setDrag(0.0005f)
                .setAngularDrag(0.01f);
    }

    @Override
    public void lateUpdate() {
        if (rb.velocity.lengthSquared() > maxSpeed * maxSpeed) {
            rb.velocity.normalize().mul(maxSpeed);
        }
    }

    public ShipMovement pitch(float pitch) {
        Vector3f torque = new Vector3f(-1, 0, 0).mul(pitch * pitchStrength);
        rb.addTorque(torque, ForceMode.FORCE);
        return this;
    }

    public ShipMovement yaw(float yaw) {
        Vector3f torque = new Vector3f(0, -1, 0).mul(yaw * yawStrength);
        rb.addTorque(torque, ForceMode.FORCE);
        return this;
    }

    public ShipMovement roll(float roll) {
        Vector3f torque = new Vector3f(0, 0, -1).mul(roll * rollStrength);
        rb.addTorque(torque, ForceMode.FORCE);
        return this;
    }

    public ShipMovement thrust(float thrust) {
        Vector3f force = transform.forward().mul(thrust * mainThrusterPower);
        rb.addForce(force);
        return this;
    }

    public ShipMovement strafe(float strafe) {
        Vector3f force = transform.right().mul(strafe * sideThrusterPower);
        rb.addForce(force);
        return this;
    }

    public void reset() {
        rb.velocity.set(0);
        rb.angularVelocity.set(0);
        transform.setLocalPosition(new Vector3f(0, 0, 0));
        transform.setLocalRotation(new Quaternionf());
    }
}
