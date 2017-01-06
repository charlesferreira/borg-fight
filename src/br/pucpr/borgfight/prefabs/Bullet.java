package br.pucpr.borgfight.prefabs;

import br.pucpr.gema.core.GameComponent;
import br.pucpr.gema.physics.ForceMode;
import br.pucpr.gema.physics.RigidBody;

public class Bullet extends GameComponent {
    private float speed = 500f;

    @Override
    public void start() {
        getComponent(RigidBody.class)
                .setDrag(0)
                .setMass(0.25f)
                .addForce(transform.forward().mul(speed), ForceMode.VELOCITY_CHANGE);
    }
}
