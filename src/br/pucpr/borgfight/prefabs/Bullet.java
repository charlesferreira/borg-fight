package br.pucpr.borgfight.prefabs;

import br.pucpr.gema.core.Collider;
import br.pucpr.gema.core.GameComponent;
import br.pucpr.gema.physics.ForceMode;
import br.pucpr.gema.physics.RigidBody;

public class Bullet extends GameComponent {
    private float timeToLive = 5f;
    private float speed = 500f;
    private String target;

    @Override
    public void start() {
        destroy(gameObject, timeToLive);
        getComponent(RigidBody.class)
                .setDrag(0)
                .setMass(0.25f)
                .addForce(transform.forward().mul(speed), ForceMode.VELOCITY_CHANGE);
    }

    public void setTarget(String target) {
        this.target = target;
    }

    @Override
    public void onCollisionEnter(Collider other) {
        destroy(gameObject);
        if (other.gameObject.compareTag(target)) {
            destroy(other.gameObject);
        }
    }
}
