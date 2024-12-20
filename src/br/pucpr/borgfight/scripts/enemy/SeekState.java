package br.pucpr.borgfight.scripts.enemy;

import br.pucpr.gema.core.components.Transform;
import br.pucpr.gema.graphics.materials.DefaultMaterial;
import org.joml.Vector3f;

class SeekState extends EnemyBehaviour {
    private float seekToWanderDistance = 150;
    private float maxForce = 12f;
    private Vector3f distanceToPlayer = new Vector3f();
    private Vector3f desiredVelocity = new Vector3f();
    private Vector3f steering = new Vector3f();

    SeekState(EnemyMovement movement, Transform target) {
        super(movement, target);
    }

    @Override
    public void onStateEnter() {
//        ((DefaultMaterial)movement.gameObject.renderer.material).setColor(new Vector3f(1, 1, 0));
    }

    @Override
    public void update() {
        distanceToPlayer.set(player.getWorldPosition().sub(movement.transform.getWorldPosition()));
        if (distanceToPlayer.lengthSquared() < seekToWanderDistance * seekToWanderDistance) {
            movement.setState(movement.wanderState);
            movement.wanderState.update();
            return;
        }
        steer();
    }

    private void steer() {
        desiredVelocity.set(distanceToPlayer.normalize().mul(movement.maxSpeed));
        steering.set(desiredVelocity.sub(movement.rb.velocity));

        if (steering.lengthSquared() > maxForce * maxForce) {
            steering.normalize().mul(maxForce);
        }
        steering.div(movement.rb.getMass());
        movement.rb.addForce(steering);
    }
}
