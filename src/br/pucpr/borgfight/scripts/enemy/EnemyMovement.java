package br.pucpr.borgfight.scripts.enemy;

import br.pucpr.borgfight.scripts.Tags;
import br.pucpr.gema.core.GameComponent;
import br.pucpr.gema.core.GameObject;
import br.pucpr.gema.core.components.Transform;
import br.pucpr.gema.physics.ForceMode;
import br.pucpr.gema.physics.RigidBody;
import org.joml.Quaternionf;
import org.joml.Vector3f;

public class EnemyMovement extends GameComponent {
    float maxSpeed = 10f;

    private Transform target;
    private EnemyBehaviour currentState;
    EnemyBehaviour wanderState;
    EnemyBehaviour seekState;
    EnemyBehaviour fleeState;
    RigidBody rb;

    @Override
    public void start() {
        rb = getComponent(RigidBody.class);
        rb.addForce(transform.forward().mul(maxSpeed), ForceMode.VELOCITY_CHANGE);
        target = findObjectWithTag(Tags.Player.toString()).transform;
        wanderState = new WanderState(this, target);
        seekState = new SeekState(this, target);
        fleeState = new FleeState(this, target);
        setState(wanderState);
    }

    @Override
    public void update() {
        currentState.update();
    }

    @Override
    public void lateUpdate() {
        if (rb.velocity.lengthSquared() > maxSpeed * maxSpeed) {
            rb.velocity.normalize().mul(maxSpeed);
        }
        transform.setLocalRotation(new Quaternionf().lookAlong(rb.velocity, transform.up()));
    }

    void setState(EnemyBehaviour state) {
        state.onStateEnter();
        currentState = state;
    }
}
