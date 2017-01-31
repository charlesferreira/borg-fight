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
    //    private float maxSpeed = 12.5f;
    float maxSpeed = 8f;

    RigidBody rb;
    Transform target;
    EnemyBehaviour wanderState;
    EnemyBehaviour seekState;
    private EnemyBehaviour currentState;

    @Override
    public void start() {
        rb = getComponent(RigidBody.class);
        rb.addForce(transform.forward(), ForceMode.VELOCITY_CHANGE);
        target = findObjectWithTag(Tags.Player.toString()).transform;
        wanderState = new WanderState(this, target);
        seekState = new SeekState(this, target);
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

    public void setState(EnemyBehaviour state) {
        state.onStateEnter();
        currentState = state;
    }
}
