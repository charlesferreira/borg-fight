package br.pucpr.gema.core;

import br.pucpr.gema.physics.CollisionsManager;

abstract public class Collider extends GameComponent {

    @Override
    public final void awake() {
        gameObject.collider = this;
    }

    @Override
    public final void start() {
        CollisionsManager.getInstance().add(this);
    }

    @Override
    public final void onDestroy() {
        CollisionsManager.getInstance().remove(this);
    }

    public abstract boolean isCollidingWith(Collider other);
}
