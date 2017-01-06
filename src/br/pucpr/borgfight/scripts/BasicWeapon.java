package br.pucpr.borgfight.scripts;

import br.pucpr.gema.core.GameComponent;
import br.pucpr.gema.core.GameObject;
import br.pucpr.gema.core.Time;
import br.pucpr.gema.core.components.Transform;
import br.pucpr.gema.physics.RigidBody;

public class BasicWeapon extends GameComponent {
    private float cooldownTimer;
    private float cooldown = 0.2f;
    private GameObject bulletPrefab;
    private RigidBody rb;

    public void setBulletPrefab(GameObject bulletPrefab) {
        this.bulletPrefab = bulletPrefab;
    }

    private enum State {READY, FIRING, ON_COOLDOWN}

    private State state = State.READY;

    @Override
    public void start() {
        rb = getComponent(RigidBody.class);
    }

    @Override
    public void update() {
        switch (state) {
            case ON_COOLDOWN:
                cooldownTimer -= Time.deltaTime;
                if (cooldownTimer <= 0f)
                    state = State.READY;
                break;
            case FIRING:
                launchProjectile();
                cooldownTimer = cooldown;
                state = State.ON_COOLDOWN;
                break;
        }
    }

    public void fire() {
        if (state == State.READY) {
            state = State.FIRING;
        }
    }

    private void launchProjectile() {
        Transform bullet = GameObject.instantiate(bulletPrefab.getClass()).transform;
        bullet.setWorldPosition(transform.getWorldPosition());
        bullet.setWorldRotation(transform.getWorldRotation());
        bullet.getComponent(RigidBody.class).velocity.set(rb.velocity);
    }
}
