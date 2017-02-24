package br.pucpr.borgfight.scripts.enemy;

import br.pucpr.gema.core.components.Transform;
import br.pucpr.gema.graphics.materials.DefaultMaterial;
import org.joml.Vector3f;

class FleeState extends EnemyBehaviour {
    private float fleeToWanderDistance = 120;
    private float maxForce = 50f;
    private Vector3f distanceFromPlayer = new Vector3f();
    private Vector3f desiredVelocity = new Vector3f();
    private Vector3f steering = new Vector3f();

    FleeState(EnemyMovement movement, Transform target) {
        super(movement, target);
    }

    @Override
    public void onStateEnter() {
//        ((DefaultMaterial)movement.gameObject.renderer.material).setColor(new Vector3f(1, 0, 0));
    }

    @Override
    public void update() {
        distanceFromPlayer.set(movement.transform.getWorldPosition().sub(player.getWorldPosition()));
        if (distanceFromPlayer.lengthSquared() < fleeToWanderDistance * fleeToWanderDistance) {
            movement.setState(movement.wanderState);
            movement.wanderState.update();
            return;
        }
        steer();
    }

    private void steer() {
        desiredVelocity.set(distanceFromPlayer.normalize().mul(movement.maxSpeed));
        steering.set(desiredVelocity.sub(movement.rb.velocity));

        if (steering.lengthSquared() > maxForce * maxForce) {
            steering.normalize().mul(maxForce);
        }
        steering.div(movement.rb.getMass());
        movement.rb.addForce(steering);
    }
}
