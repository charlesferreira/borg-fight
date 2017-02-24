package br.pucpr.borgfight.scripts.enemy;

import br.pucpr.gema.core.components.Transform;
import br.pucpr.gema.graphics.materials.DefaultMaterial;
import br.pucpr.gema.physics.Steering;
import org.joml.Vector3f;

class WanderState extends EnemyBehaviour {
    private float wanderToSeekDistance = 180;
    private float wanderToFleeDistance = 100;
    private float sphereDistance = 2;
    private float sphereRadius = 2;
    private float angleChange = 0.2f;
    private float maxForce = 25f;
    private float phi;
    private float theta;

    private Vector3f distanceToPlayer = new Vector3f();
    private Vector3f steering = new Vector3f();

    WanderState(EnemyMovement movement, Transform player) {
        super(movement, player);
    }

    @Override
    public void onStateEnter() {
//        ((DefaultMaterial)movement.gameObject.renderer.material).setColor(new Vector3f(0, 1, 0));
        phi = (float) (Math.random() * Math.PI);
        theta = (float) (Math.random() * Math.PI);
    }

    @Override
    public void update() {
        steer();
        distanceToPlayer.set(player.getWorldPosition().sub(movement.transform.getWorldPosition()));
        float sqrDistToPlayer = distanceToPlayer.lengthSquared();
        if (sqrDistToPlayer >= wanderToSeekDistance * wanderToSeekDistance) {
            movement.setState(movement.seekState);
        } else if (sqrDistToPlayer <= wanderToFleeDistance * wanderToFleeDistance) {
            movement.setState(movement.fleeState);
        }
    }

    private void steer() {
        steering.set(Steering.wander(movement.rb.velocity, sphereDistance, sphereRadius, phi, theta));
        phi += Math.random() * angleChange - angleChange * .5f;
        theta += Math.random() * angleChange - angleChange * .5f;

        if (steering.lengthSquared() > maxForce * maxForce) {
            steering.normalize().mul(maxForce);
        }
        steering.div(movement.rb.getMass());
        movement.rb.addForce(steering);
    }
}
