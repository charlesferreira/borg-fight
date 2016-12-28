package br.pucpr.gema.physics;

import br.pucpr.gema.core.GameComponent;
import br.pucpr.gema.core.GameObject;
import br.pucpr.gema.core.Space;
import br.pucpr.gema.core.Time;
import br.pucpr.gema.core.components.Transform;
import org.joml.Vector3f;

public class RigidBody extends GameComponent {
    public Vector3f velocity;
    public float mass = 1f;
    public float maxSpeed = 1f;

    Transform transform;

    public RigidBody(GameObject gameObject) {
        super(gameObject);
    }

    @Override
    public void awake() {
        velocity = new Vector3f();
    }

    @Override
    public void start() {
        transform = (Transform) getComponent(Transform.class);
    }

    @Override
    public void fixedUpdate() {
        Vector3f translation = new Vector3f(velocity).mul(Time.fixedDeltaTime);
        transform.translate(translation, Space.WORLD);
    }

    public RigidBody addForce(Vector3f force) {
        return addForce(force, ForceMode.FORCE);
    }

    public RigidBody addForce(Vector3f force, ForceMode mode) {
        Vector3f timedForce = new Vector3f(force).mul(Time.fixedDeltaTime);
        switch (mode) {
            case IMPULSE: applyImpulse(timedForce); break;
            case VELOCITY_CHANGE: applyVelocityChange(timedForce); break;
        }
        return this;
    }

    private void applyImpulse(Vector3f impulse) {
        velocity.add(impulse.div(mass));
    }

    private void applyVelocityChange(Vector3f change) {
        velocity.add(change);
    }
}
