package br.pucpr.gema.core;

import br.pucpr.gema.core.components.Transform;

abstract public class GameComponent {
    public Transform transform;
    public GameObject gameObject;

    final void init(GameObject gameObject) {
        LifeCycleManager.getInstance().notifyCreation(this);
        this.gameObject = gameObject;
        transform = gameObject.transform;
    }

    protected final void destroy(GameObject gameObject) {
        gameObject.selfDestruct(0);
    }

    protected final void destroy(GameObject gameObject, float delay) {
        gameObject.selfDestruct(delay);
    }

    public <T extends GameComponent> T getComponent(Class<T> componentClass) {
        return gameObject.getComponent(componentClass);
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

    public void onDestroy() {
    }

    public void onCollisionEnter(Collider other) {
    }

    public void onCollisionStay(Collider other) {
    }

    public void onCollisionExit(Collider other) {
    }
}
