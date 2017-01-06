package br.pucpr.gema.physics;

import br.pucpr.gema.core.GameComponent;
import br.pucpr.gema.core.Time;
import br.pucpr.gema.core.components.Transform;
import org.joml.Vector3f;

public class RigidBody extends GameComponent {
    private float mass = 1f;
    private float drag = 0.001f;
    private float angularDrag = 0.001f;

    public Vector3f velocity;
    public Vector3f angularVelocity;

    private Transform transform;
    private Vector3f acceleration;
    private Vector3f angularAcceleration;

    private boolean outDated = false;

    @Override
    public void awake() {
        velocity = new Vector3f();
        angularVelocity = new Vector3f();
        acceleration = new Vector3f();
        angularAcceleration = new Vector3f();
    }

    @Override
    public void start() {
        transform = getComponent(Transform.class);
    }

    @Override
    public void fixedUpdate() {
        if (outDated) {
            outDated = false;
            velocity.add(new Vector3f(acceleration).mul(Time.deltaTime));
            acceleration.set(0);
            angularVelocity.add(new Vector3f(angularAcceleration).mul(Time.deltaTime));
            angularAcceleration.set(0);
        }

        // translação
        Vector3f translation = new Vector3f(velocity).mul(Time.fixedDeltaTime);
        transform.translate(translation);
        velocity.mul(1f - drag);

        // rotação
        Vector3f rotation = new Vector3f(angularVelocity).mul(Time.fixedDeltaTime);
        float radians = rotation.length();
        if (radians > 0) {
            Vector3f axis = rotation.normalize();
            transform.rotate(radians, axis);
        }
        angularVelocity.mul(1f - angularDrag);
    }

    @Override
    public void lateUpdate() {
        outDated = true;
    }

    public RigidBody addForce(Vector3f force) {
        return addForce(force, ForceMode.ACCELERATION);
    }

    public RigidBody addForce(Vector3f force, ForceMode mode) {
        switch (mode) {
            case FORCE:
                acceleration.add(new Vector3f(force).div(mass));
                break;
            case ACCELERATION:
                acceleration.add(force);
                break;
            case IMPULSE:
                velocity.add(new Vector3f(force).div(mass));
                break;
            case VELOCITY_CHANGE:
                velocity.add(force);
                break;
        }
        return this;
    }

    public RigidBody addTorque(Vector3f torque, ForceMode mode) {
        switch (mode) {
            case FORCE:
                angularAcceleration.add(new Vector3f(torque).div(mass));
                break;
            case ACCELERATION:
                angularAcceleration.add(torque);
                break;
            case IMPULSE:
                angularVelocity.add(new Vector3f(torque).div(mass));
                break;
            case VELOCITY_CHANGE:
                angularVelocity.add(torque);
                break;
        }
        return this;
    }

    public RigidBody setMass(float mass) {
        assert (mass > 0);
        this.mass = mass;
        return this;
    }

    public RigidBody setDrag(float drag) {
        assert (drag >= 0);
        this.drag = drag;
        return this;
    }

    public RigidBody setAngularDrag(float angularDrag) {
        assert (angularDrag >= 0);
        this.angularDrag = angularDrag;
        return this;
    }
}
