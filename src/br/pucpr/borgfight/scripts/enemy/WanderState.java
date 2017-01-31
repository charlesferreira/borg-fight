package br.pucpr.borgfight.scripts.enemy;

import br.pucpr.gema.core.components.Transform;
import br.pucpr.gema.graphics.materials.DefaultMaterial;
import br.pucpr.gema.physics.Steering;
import org.joml.Vector3f;

public class WanderState extends EnemyBehaviour {
    private float maxWanderDistance = 200;
    private float sphereDistance = 2;
    private float sphereRadius = 2;
    private float angleChange = 0.175f;
    private float maxForce = 15f;
    private float phi;
    private float theta;

    private Vector3f distanceToPlayer = new Vector3f();
    private Vector3f steering = new Vector3f();

    public WanderState(EnemyMovement movement, Transform player) {
        super(movement, player);
        phi = (float) (Math.random() * Math.PI);
        theta = (float) (Math.random() * Math.PI);
    }

    @Override
    public void onStateEnter() {
        ((DefaultMaterial)movement.gameObject.renderer.material).setColor(new Vector3f(1, 0, 0));
    }

    @Override
    public void update() {
        steer();
        distanceToPlayer.set(player.getWorldPosition().sub(movement.transform.getWorldPosition()));
        if (distanceToPlayer.lengthSquared() > maxWanderDistance * maxWanderDistance) {
            movement.setState(movement.seekState);
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
