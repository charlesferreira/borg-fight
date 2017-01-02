package br.pucpr.gema.core;

import br.pucpr.gema.core.components.Transform;

abstract public class GameComponent {
    protected Transform transform;
    protected GameObject gameObject;

    final void init(GameObject gameObject) {
        LifeCycleManager.getInstance().notifyCreation(this);
        this.gameObject = gameObject;
        transform = gameObject.transform;
    }

    // métodos de ciclo de vida

    public void awake() {
    }

    public void start() {
    }

    public void fixedUpdate() {
    }

    public void update() {
    }

    public void lateUpdate() {
    }

    public GameComponent getComponent(Class<? extends GameComponent> componentClass) {
        return gameObject.getComponent(componentClass);
    }
}
